package pk.foto;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Fachobjekt implements CsvExportable, Serializable {
	private String name;
	private final UUID uuid;

	public Fachobjekt() {
		uuid = UUID.randomUUID();
	}

	public Fachobjekt(String name) {
		this();
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void drucke(OutputStream stream);

	
	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.uuid);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fachobjekt other = (Fachobjekt) obj;
		return Objects.equals(name, other.name) && Objects.equals(uuid, other.uuid);
	}
	
	@Override
	public String exportiereAlsCsv() {
		return this.name;
	}

}
