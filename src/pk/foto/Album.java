package pk.foto;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;
import pk.foto.util.FotoUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album extends Fachobjekt implements Comparable<Album>, CsvExportable, Serializable {

	//private static final long serialVersionUID = 42L;
	private String besitzer;
	private List<Foto> fotos;

	public Album() {
		super();
		fotos = new ArrayList<>();
	}
	
	public Album(String name, String besitzer) {
		super(name);
		this.besitzer = besitzer;
		fotos = new ArrayList<>();
	}
	
	public Album(String name, String besitzer, List fotos) {
		this(name, besitzer);
		this.fotos = fotos;
	}

	public String getName() {
		return super.getName();
	}
	
	public String getBesitzer() {
		return besitzer;
	}
	
	public List<Foto> getFotos() {
		return this.fotos;
	}

	public void setName(String name) {
		super.setName(name);
	}
	
	public void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}

	public void addFoto(Foto foto) throws HandledException {
		try {
			String savedFolder = Paths.get("").toAbsolutePath().toString() + "\\" + "images\\";
			File file = new File(savedFolder + "7861351302_74a45956dd_o.jpg");
			FotoMetadaten metadaten = FotoUtil.readMetadata(file);
			foto.setMetadaten(metadaten);
			this.fotos.add(foto);
		} catch (ImageProcessingException e) {
			throw new HandledException("ImageProcessingException","ImageProcessingException", e);
		} catch (IOException e) {
			throw new HandledException("IOException","IOException", e);
		} catch (MetadataException e) {
			throw new HandledException("MetadataException","MetadataException", e);
		} catch (NullPointerException e) {
			throw new HandledException("NullPointerException","NullPointerException", e);
		}
	}
	
	@Override
	public void drucke(OutputStream stream) {
		
		try {
			
			stream.write(("Name: " + super.getName() + "\n").getBytes());
			stream.write(("Besitzer: " + this.besitzer + "\n").getBytes());
            
			for (int i = 0; i < this.fotos.size(); i++) {
				stream.write(("=== Foto " + (i+1) + " ===\n").getBytes());
				this.fotos.get(i).drucke(stream);
			}
		
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	@Override
	public String toString() {
		return super.getName() + " " + this.besitzer;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Album other = (Album) obj;
		return Objects.equals(besitzer, other.besitzer) && Objects.equals(fotos, other.fotos)
				&& fotos.size() == other.fotos.size();
	}
	
	@Override
	public int compareTo(Album album) {	
		try {
			if (this == album)
				return 0;
			return this.getName().compareTo(album.getName());
		} catch (Exception e) {
			System.out.println("null kann nicht gegeben werden");
			return -1;
		}
	}

	@Override
	public String exportiereAlsCsv() {
		return super.exportiereAlsCsv() + "," + this.besitzer;
	}

}
