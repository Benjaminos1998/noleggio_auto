package noleggioAuto.entities;

import javax.persistence.*;

@Entity
@Table(name = "noleggi")
public class Noleggio {

	// CAMPI
	
	public double prezzo;
	@Id
	@SequenceGenerator(sequenceName = "noleggio_sequence", allocationSize = 1, name = "noleggio_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noleggio_sequence")
	public Integer idNoleggio;

	// COSTRUTTORI
	
	public Noleggio() {
	}

	public Noleggio(double prezzo, Integer idNoleggio) {
		this.prezzo = prezzo;
		this.idNoleggio = idNoleggio;
	}

	// METODI 
	public double getPrezzo() {
		return prezzo;
	}

	public Integer getIdNoleggio() {
		return idNoleggio;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public void setIdNoleggio(Integer idNoleggio) {
		this.idNoleggio = idNoleggio;
	}

	public double calcolaPrezzo() {
		return 0;
	}

}