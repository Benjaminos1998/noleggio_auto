package noleggioAuto.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import noleggioAuto.gestione.TipologiaNoleggio;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "noleggi")
public class Noleggio {

	public double prezzo;
	@Id
	@SequenceGenerator(sequenceName = "noleggio_sequence", allocationSize = 1, name = "noleggio_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noleggio_sequence")
	public Integer idNoleggio;
	@Transient
	public TipologiaNoleggio noleggio;
	@Transient
	private String sceltaNoleggio;

	public Noleggio(Integer idNoleggio, double prezzo, String sceltaNoleggio) {
		this.prezzo = prezzo;
		this.idNoleggio = idNoleggio;
		this.sceltaNoleggio = sceltaNoleggio;
	}

	public double calcolaPrezzo() {
		return 0;
	}

}