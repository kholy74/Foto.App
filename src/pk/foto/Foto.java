package pk.foto;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;

public class Foto extends Fachobjekt implements CsvExportable, Serializable {

	//private static final long serialVersionUID = 42L;
	private String dateiName;
	private FotoMetadaten metadaten;
	
	public Foto() {
		super();
	}
	
	public Foto(String name, String dateiName) {
		super(name);
		this.dateiName = dateiName;
		this.metadaten = null;
	}

	public Foto(String name, String dateiName, FotoMetadaten metadaten) {
		this(name, dateiName);
		this.metadaten = metadaten;
	}
	
	public String getName() {
		return super.getName();
	}
	
	public String getDateiName() {
		return dateiName;
	}

	public FotoMetadaten getDaten() {
		return metadaten;
	}
	
	public void setName(String name) {
		super.setName(name);
	}
	
	public void setDateiName(String dateiName) {
		this.dateiName = dateiName;
	}
	
	public void setMetadaten(FotoMetadaten metadaten) {
		this.metadaten = metadaten;
	}

	@Override
	public void drucke(OutputStream stream) {

		try {
			stream.write(("Fotoname: " + super.getName() + "\n").getBytes());
			stream.write(("Dateiname: " + this.dateiName + "\n").getBytes());
			stream.write(("Groesse: " + metadaten.getBreite() + " x " + metadaten.getHoehe() + "\n").getBytes());
			stream.write(("Kamera: " + metadaten.getKameraMarke() + " " + metadaten.getKameraModell() + "\n").getBytes());
			stream.write(("Erstellungsdatum: " + metadaten.getDtf() + "\n").getBytes());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public String toString() {
		return super.getName() + " " + this.dateiName + " ";
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), dateiName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		return Objects.equals(dateiName, other.dateiName);
	}

	@Override
	public String exportiereAlsCsv() {
		return super.exportiereAlsCsv() + "," + this.dateiName + "," + this.metadaten.exportiereAlsCsv();
	}
}