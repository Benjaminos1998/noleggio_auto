package noleggioAuto.entities;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utenti_registrati")
public class UtenteRegistrato extends Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idUtente;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "numero_punti")
	private Integer numeroPunti;
	@Column(name = "id_carta")
	private Integer idCarta;
	@Column(name = "data_di_nascita")
	@Temporal(TemporalType.DATE)
	private LocalDate dataDiNascita;
	@Column(name = "numero_patente")
	private String numeroPatente;
	@Transient
	private int eta;

	public Integer getEta() {
		return Period.between(this.dataDiNascita, LocalDate.now()).getYears();
	}

	@Override
	public String toString() {
		return "UtenteRegistrato{" + "id=" + this.idUtente + ", nome='" + this.nome + '\'' + ", cognome='"
				+ this.cognome + '\'' + ", username='" + this.username + '\'' + ", password='" + this.password + '\''
				+ ", numeroPunti=" + this.numeroPunti + ", idCarta=" + this.idCarta + ", dataDiNascita="
				+ this.dataDiNascita + ", numeroPatente='" + this.numeroPatente + '\'' + ", eta=" + getEta() + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UtenteRegistrato))
			return false;
		UtenteRegistrato utenteRegistrato = (UtenteRegistrato) o;
		return this.numeroPatente.equals(utenteRegistrato.numeroPatente);
	}

}