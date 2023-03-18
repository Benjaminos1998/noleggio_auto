package noleggioAuto.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Utente {

	@Id
	@SequenceGenerator(name = "utente_sequence", sequenceName = "utente_sequence", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_sequence")
	private Integer id;
	@Column(nullable = false)
	public String nome;
	@Column(nullable = false)
	public String cognome;
	@Column(nullable = false)
	public Integer numeroPatente;
	@Column(nullable = true)
	public LocalDate dob;

	@Transient
	private Integer eta;
	@OneToMany
	private List<Auto> auto = new ArrayList<>();
	@OneToMany
	private List<Noleggio> noleggi = new ArrayList<>();

	public Utente(String nome, String cognome, Integer numeroPatente, LocalDate dob) {
		this.nome = nome;
		this.cognome = cognome;
		this.numeroPatente = numeroPatente;
		this.dob = dob;
	}

	public Utente(Integer id, String nome, String cognome, Integer numeroPatente, LocalDate dob) {
		this(nome, cognome, numeroPatente, dob);
		this.id = id;
	}

	public Integer getEta() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	@Override
	public String toString() {
		return "Utente{" + "id=" + id + ", nome='" + nome + '\'' + ", cognome='" + cognome + '\''
				+ ", data di nascita= " + dob + ", età=" + eta + ", numero patente=" + numeroPatente + '}';
	}

}