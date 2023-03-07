package noleggioAuto.entities;

import javax.persistence.*;

@Entity
@Table(name = "auto")
public class Auto {

	// CAMPI

	@Id
	@SequenceGenerator(name = "auto_sequence", sequenceName = "auto_sequence", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_sequence")
	private Integer id;
	@Column(nullable = false)
	public String targa;
	@Column(nullable = false)
	public String modello;

	// COSTRUTTORI

	public Auto() {
	}

	public Auto(String targa, String modello) {
		this.targa = targa;
		this.modello = modello;
	}

	public Auto(Integer id, String targa, String modello) {
		this(targa, modello);
		this.id = id;
	}

	// METODI

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModello() {
		return modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String toString() {
		return this.modello + "Targa: " + this.targa;
	}
}