package noleggioAuto.entities;

import java.util.*;

public abstract class Utente {

	private String nome;
	private String cognome;
	private Integer id;
	private Integer numeroPatente;
	private Integer eta;
	private Set<Auto> auto = new HashSet<>();
	private Set<Noleggio> noleggi = new HashSet<>();

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

	public Set<Auto> getAuto() {
		return auto;
	}

	public Set<Noleggio> getNoleggi() {
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

	public void linkAuto(Auto _auto) {
		if (_auto != null) {
			getAuto().add(_auto);
		}
	}

	public void linkNoleggi(Noleggio _noleggi) {
		if (_noleggi != null) {
			getNoleggi().add(_noleggi);
		}
	}

	public void unlinkAuto(Auto _auto) {
		if (_auto != null) {
			getAuto().remove(_auto);
		}
	}

	public void unlinkAuto(Iterator<Auto> it) {
		it.remove();
	}

	public void unlinkNoleggi(Noleggio _noleggi) {
		if (_noleggi != null) {
			getNoleggi().remove(_noleggi);
		}
	}

	public void unlinkNoleggi(Iterator<Noleggio> it) {
		it.remove();
	}

}