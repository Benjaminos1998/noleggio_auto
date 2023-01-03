package noleggioAuto.entities;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "utente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Utente {

	private String nome;
	private String cognome;
	
	@Id
	@SequenceGenerator(name = "utente_sequence",sequenceName = "utente_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "utente_sequence")
	private Integer id;
	
	@Column(unique = true)
	private Integer numeroPatente;
	private Integer eta;
	
	@OneToMany
	private List<Auto> auto = new ArrayList<>();
	@OneToMany
	private List<Noleggio> noleggi = new ArrayList<>();
	
	public Utente() {
		
	}
	
	public Utente(String nome, String cognome) {
		this.nome=nome;
		this.cognome=cognome;
	}

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
		return eta;
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

}