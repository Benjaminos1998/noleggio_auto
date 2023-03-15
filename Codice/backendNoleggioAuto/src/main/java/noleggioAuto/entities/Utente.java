package noleggioAuto.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "utente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utente {

	// CAMPI 
	
	@Column(nullable = false)
	public String nome;
	@Column(nullable = false)
	public String cognome;
	
	@Id
	@SequenceGenerator(name = "utente_sequence",sequenceName = "utente_sequence",allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "utente_sequence")
	private Integer id;
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
	
	
	// COSTRUTTORI 
	
	public Utente() {
		super();
	}
	
	public Utente(String nome, String cognome,Integer numeroPatente,LocalDate dob) {
		this.nome=nome;
		this.cognome=cognome;
		this.numeroPatente=numeroPatente;
		this.dob=dob;
	}
	
	public Utente(Integer id,String nome, String cognome,Integer numeroPatente,LocalDate dob) {
		this(nome,cognome,numeroPatente,dob);
		this.id=id;
	}

	
	//METODI
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNumeroPatente() {
		return numeroPatente;
	}

	public Integer getEta() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}
	
	public LocalDate getDob() {
		return dob;
	}

	public List<Auto> getAuto() {
		return auto;
	}

	public List<Noleggio> getNoleggi() {
		return noleggi;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumeroPatente(Integer numeroPatente) {
		this.numeroPatente = numeroPatente;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data di nascita= " + dob +
                ", età=" + eta +
                ", numero patente=" + numeroPatente+
                '}';
	}

}