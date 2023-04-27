package noleggioAuto.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "noleggi")
public class Noleggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNoleggio;
	@Column(name = "data_inizio")
	private LocalDate dataInizio;
	@Column(name = "data_fine")
	private LocalDate dataFine;
	@Column(name = "prezzo")
	private double prezzo;
	@Column(name = "tipologia_noleggio")
	private TipologiaNoleggio tipologiaNoleggio;
	@ManyToOne
	private Auto auto;
	@ManyToOne
	private UtenteRegistrato utenteRegistrato;

	public Noleggio(LocalDate dataInizio, LocalDate dataFine, double prezzo, TipologiaNoleggio tipologiaNoleggio,
			Auto auto, UtenteRegistrato utenteRegistrato) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.prezzo = prezzo;
		this.tipologiaNoleggio = tipologiaNoleggio;
		this.auto = auto;
		this.utenteRegistrato = utenteRegistrato;
	}

	public static int durataNoleggio(Noleggio noleggio) {
		return (int) ChronoUnit.DAYS.between(noleggio.dataInizio, noleggio.dataFine);
	}

	@Override
	public String toString() {
		return "Noleggio{" + "idNoleggio=" + this.idNoleggio + ", dataInizio=" + this.dataInizio + ", dataFine="
				+ this.dataFine + ", prezzo=" + this.prezzo + ", tipologiaNoleggio=" + this.tipologiaNoleggio
				+ ", auto=" + this.auto.getTarga() + ", utenteRegistrato=" + this.utenteRegistrato.getUsername() + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Noleggio))
			return false;
		Noleggio noleggio = (Noleggio) o;
		return this.idNoleggio == noleggio.idNoleggio;
	}

	public static void controlloPrezzoNoleggio(double prezzoNoleggio) {
		if (prezzoNoleggio <= 0)
			throw new IllegalArgumentException("Hai inserito un prezzo non valido");
	}
}
