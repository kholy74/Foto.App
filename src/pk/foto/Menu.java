package pk.foto;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	
	public static void main(String[] args) {

		FotoVerwaltung fv = new FotoVerwaltung();
		Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.println("Foto-App");
			System.out.println("\t1. Album hinzufgen");
			System.out.println("\t2. Drucke alle Alben");
			System.out.println("\t3. Drucke Album mit Name");
			System.out.println("\t4. CSV-Export");
			System.out.println("\t5. Lade aus Datei");
			System.out.println("\t6. Speichere in Datei");
			System.out.println("\t7. Beenden");
			System.out.print("Bitte Aktion w�hlen: ");

			int choice = 0;

			try {
				choice = input.nextInt();
			} catch (Exception e) {
				System.out.println("Falsche Nummer eingegeben.\n");
				String buffer = input.nextLine();
			}

			if (choice == 1) {
				String name = "", besitzer = "";
				while (true) {
					name = JOptionPane.showInputDialog(null, "Bitte geben Sie den Namen ein: ");
					if (name.equals("")) {
						JOptionPane.showMessageDialog(null, "Bitte g�ltigen Namen eingeben!");
					} else
						break;
				}

				while (true) {
					besitzer = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Besitzer ein: ");
					if (besitzer.equals("")) {
						JOptionPane.showMessageDialog(null, "Bitte g�ltigen Namen eingeben!");
					} else
						break;
				}

				
				Album album = new Album(name, besitzer);
				int addMoreFotos = 0;
				boolean failer = false;
				while (addMoreFotos == 0) {
					String fotoname = JOptionPane.showInputDialog(null, "Bitte geben Sie den FotoNamen ein: ");
					String dateiname = JOptionPane.showInputDialog(null, "Bitte geben Sie den Dateinamen ein: ");
					Foto foto = new Foto(fotoname, dateiname);
					try {	
						album.addFoto(foto);
					} catch (Exception e) {
						failer = true;
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					addMoreFotos = JOptionPane.showConfirmDialog(null, "Moechten Sie einen neuen Bild hinzufgen?");
				}

				if (!failer) {
					try {
						fv.addAlbum(album);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}

			} else if (choice == 2) {
				fv.druckeAlleAlben();
			} else if (choice == 3) {
				String albumName = JOptionPane.showInputDialog(null, "Bitte geben Sie den Albumname ein: ");
				Album album = fv.findeAlbumMitName(albumName);
				
				if (null != album) {
					InputStream inputStream = null;
					try {
						OutputStream stream = new FileOutputStream("file.txt");
						album.drucke(stream);
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
						} catch (Exception e) {
							System.out.print(e.getMessage());
						}
					}
				}

			} else if (choice == 4) {
				
				String dateiName = "";
				
				while (true) {
					dateiName = JOptionPane.showInputDialog(null, "Bitte geben Sie den Dateiname ein: ");
					if (dateiName.equals("")) {
						JOptionPane.showMessageDialog(null, "Bitte g�ltigen Namen eingeben!");
					} else
						break;
				}

				String savedFolder = Paths.get("").toAbsolutePath().toString() + "\\";
				String fileName = savedFolder + dateiName + ".csv";
				File file = new File(fileName);

				if (file.exists() && file.isFile()) {
					String message = "Die Datei schon existiert\nM�chten Sie die alte Datei �berschreiben?";
					int confirm = JOptionPane.showConfirmDialog(null, message);
					if (confirm == 0) {
						try {
							fv.exportiereEintraegeAlsCsv(file);
							//fv.exportiereEintraegeAlsCsvNio(file);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				} else {
					try {
						fv.exportiereEintraegeAlsCsv(file);
						//fv.exportiereEintraegeAlsCsvNio(file);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}

			} else if (choice == 5) {
				fv.laden();
			} else if (choice == 6) {
				fv.speichern();
			} else if (choice == 7) {
				System.out.println("Programm ist beendet");
				break;
			} else {
				System.out.println("Falsche Nummer eingegeben.\n");
			}

		}

	}

}
