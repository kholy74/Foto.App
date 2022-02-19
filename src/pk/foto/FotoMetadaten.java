package pk.foto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class FotoMetadaten implements CsvExportable, Serializable {
	
	//private static final long serialVersionUID = 42L;
	private int breite;
	private int hoehe;
	private String kameraMarke;
	private String kameraModell;
	private LocalDateTime erstellungszeitpunkt;
	
	public FotoMetadaten() {
		this.erstellungszeitpunkt = LocalDateTime.now();
	}

	public FotoMetadaten(int breite, int hoehe, String kameraMarke, String kameraModell) {
		this();
		this.breite = breite;
		this.hoehe = hoehe;
		this.kameraMarke = kameraMarke;
		this.kameraModell = kameraModell;	
	}
	
	public FotoMetadaten(int breite, int hoehe, String kameraMarke, String kameraModell, LocalDateTime erstellungszeitpunkt) {
		this();
		this.breite = breite;
		this.hoehe = hoehe;
		this.kameraMarke = kameraMarke;
		this.kameraModell = kameraModell;	
		this.erstellungszeitpunkt = erstellungszeitpunkt;
	}
	
	public int getBreite() {
		return breite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public String getKameraMarke() {
		return kameraMarke;
	}

	public String getKameraModell() {
		return kameraModell;
	}
	
	public LocalDateTime getErstellungszeitpunkt() {
		return this.erstellungszeitpunkt;
	}

	public String getDtf() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		return erstellungszeitpunkt.format(dtf);
	}
	
	public void setBreite(int breite) {
		this.breite = breite;
	}
	
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}
	
	public void setKameraMarke(String kameraMarke) {
		this.kameraMarke = kameraMarke;
	}
	
	public void setKameraModell(String kameraModell) {
		this.kameraModell = kameraModell;
	}
	
	public void setErstellungszeitpunkt(LocalDateTime erstellungszeitpunkt) {
		this.erstellungszeitpunkt = erstellungszeitpunkt;
	}

	@Override
	public String toString() {
		return this.breite + " " + this.hoehe + " " + this.kameraMarke +
				" " + this.kameraModell + " " + this.getDtf();
	}

	@Override
	public int hashCode() {
		return Objects.hash(breite, hoehe, kameraMarke, kameraModell, erstellungszeitpunkt);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FotoMetadaten other = (FotoMetadaten) obj;
		return breite == other.breite && Objects.equals(erstellungszeitpunkt, other.erstellungszeitpunkt)
				&& hoehe == other.hoehe && Objects.equals(kameraMarke, other.kameraMarke)
				&& Objects.equals(kameraModell, other.kameraModell);
	}

	@Override
	public String exportiereAlsCsv() {
		return this.breite + "," + this.hoehe + "," 
				+ this.kameraMarke + "," + this.kameraModell + "," + this.getDtf();
	}
}
