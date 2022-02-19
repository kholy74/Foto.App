package pk.foto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class FotoVerwaltung implements Serializable {
	
	//private static final long serialVersionUID = 42L;
	private Set<Album> alben;
	
	public FotoVerwaltung() {
		alben = new TreeSet<Album>();
	}
	
	public FotoVerwaltung(Album album) {
		this();
		this.alben.add(album);
	}

	public void druckeAlleAlben() {
		int counter = 1;
		Iterator<Album> iterator = alben.iterator();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			outputStream = new FileOutputStream("file.txt");
			while (iterator.hasNext()) {
				outputStream.write(("=== Album " + counter++ + " ===\n").getBytes());
				Album next = iterator.next();
				next.drucke(outputStream);
			}
			
			inputStream = new FileInputStream("file.txt");
            int char_ = 0;
            while ((char_ = inputStream.read()) != -1) {
            	System.out.print(Character.toString(char_));
            }
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
	}
	
	public int gibAnzahlAlben() {
		return this.alben.size();
	}
	
	public Set<Album> gibAlleAlben() {
		return this.alben;
	}
	
	public void addAlbum(Album album) throws HandledException {
		if (null != this.findeAlbumMitName(album.getName()))
			throw new HandledException("AlbumExixtiet","Albumname schon existiert. Es wurde nicht eingeg�ht");
		else
			this.alben.add(album);
	}
	
	public Album findeAlbumMitName(String name) {
		for (Album album : this.alben) {
			if (album.getName().equals(name))
				return album;
		}
		return null;
	}
	
	public void exportiereEintraegeAlsCsv(File datei) throws IOException {
		
		List<String> data = new ArrayList<>();
		List<String> menu = new ArrayList<>(Arrays.asList("Album-ID,Name","Name","Besitzer",
				"Foto-ID","Name","Dateiname","Breite","H�he","Kameramarke", "Kameramodell", "Erstellungsdatum"));
		
	    try (PrintWriter writer = new PrintWriter(datei)) {
	    	String collect = menu.stream().collect(Collectors.joining(","));
	    	data.clear();
	    	writer.write(collect + "\n");
	    	
	    	for (Album album : this.alben) {
				
				for (Foto foto : album.getFotos()) {
					data.add(album.exportiereAlsCsv());
					data.add(foto.exportiereAlsCsv());
					collect = data.stream().collect(Collectors.joining(","));
					data.clear();
					
					writer.write(collect + "\n");
				}
			}
	    	
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }

	}
	
	public void exportiereEintraegeAlsCsvNio(File datei) throws IOException {

		String data = "";
		for (Album album : this.alben) {
			data += album.exportiereAlsCsv() + ",";
			for (Foto foto : album.getFotos()) {
				data += foto.exportiereAlsCsv();
			}
		}

		try (PrintWriter writer = new PrintWriter(datei)) {
			StringBuilder sb = new StringBuilder();
			sb.append(data);
			sb.append("\n");

			writer.write(sb.toString());
			writer.close();

		} catch (IOException e) {
			throw new IOException("Fehler beim Schreiben\n");
		}

	}
	
	public void speichern() {
		try
        {   
            FileOutputStream file = new FileOutputStream("fileSpeichern_.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            
            Album album = new Album("Hello", "World");
            FotoVerwaltung obj = new FotoVerwaltung();
            out.writeObject(this);
              
            out.close();
            file.close();
              
            System.out.println("Object has been serialized");
  
        }
          
        catch(IOException ex)
        {
            System.out.println("IOException is caught" + ex.getMessage());
        }
		
		catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
	}
	
	public void laden() {
		try
        {   
            FileInputStream file = new FileInputStream("fileSpeichern_.ser");
            ObjectInputStream in = new ObjectInputStream(file);
             
            FotoVerwaltung temp = new FotoVerwaltung();
            temp = (FotoVerwaltung)in.readObject();
              
            in.close();
            file.close();
              
            System.out.println("Object has been deserialized ");
            temp.druckeAlleAlben();
        }
          
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
          
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
	}
	
}
