package noleggioAuto.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
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
	@Enumerated(EnumType.STRING)
	private TipologiaNoleggio tipologiaNoleggio;
	@ManyToOne
	private Auto auto;
	@ManyToOne
	private Utente utente;

	public Noleggio(LocalDate dataInizio, LocalDate dataFine, double prezzo) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.prezzo = prezzo;
	}

	public Noleggio(LocalDate dataInizio, LocalDate dataFine, double prezzo, TipologiaNoleggio tipologiaNoleggio,
			Auto auto, Utente utenteRegistrato) {
		this(dataInizio, dataFine, prezzo);
		this.tipologiaNoleggio = tipologiaNoleggio;
		this.auto = auto;
		this.utente = utenteRegistrato;
	}

	public static int durataNoleggio(Noleggio noleggio) {
		return (int) ChronoUnit.DAYS.between(noleggio.dataInizio, noleggio.dataFine);
	}

	public static void controlloPrezzoNoleggio(double prezzoNoleggio) {
		if (prezzoNoleggio <= 0)
			throw new IllegalArgumentException("Hai inserito un prezzo non valido");
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

}
