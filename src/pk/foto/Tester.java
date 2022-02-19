package pk.foto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.UUID;

public class Tester {
	public static void main(String[] args) {
		FotoMetadaten daten = new FotoMetadaten(1028, 2064, "Canon", "2066A");
		Foto f1 = new Foto("Baum", "Wald", daten);
		Foto f2 = new Foto("Fluss", "Wasser", daten);
		Foto f3 = new Foto("Baum", "Wald", daten);
		
		Album a = new Album("Natur", "Hasan");
		Album a1 = new Album("Aatur", "Hassan");
		Album a66 = null;
		
		FotoVerwaltung fv = new FotoVerwaltung();

		//a.addFoto(f1);
		//a.addFoto(f2);
		
		try {

            OutputStream output = new FileOutputStream("file.txt");
            a.drucke(output);
            
            InputStream input = new FileInputStream("file.txt");
            int i = 0;
            while ((i = input.read()) != -1) {
            	System.out.print(Character.toString(i));
            }
            
			
		} catch (Exception e) {
			
		}
		
		
		
		//fv.addAlbum(a);
		//fv.addAlbum(a1);
		
		
		System.out.println(".....................................");
		
		fv.druckeAlleAlben();
		
		System.out.println(".....................................");
		
		f1.setName("See");
		//fv.findeAlbumMitName("Natur").drucke();
		
		System.out.println(".....................................");
		//f1.drucke();

		
	}
}

