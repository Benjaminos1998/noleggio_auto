package noleggioAuto.entities;

import javax.persistence.*;

@Entity
public class UtenteVip extends Utente {

	
	private Integer idCarta;
	private Integer numeroPunti;

	public UtenteVip() {
		super();
	}
	

	public UtenteVip(Integer idCarta) {
		super();
		this.idCarta = idCarta;
	}

	public UtenteVip(Integer idCarta, Integer numeroPunti) {
		this.idCarta = idCarta;
		this.numeroPunti = numeroPunti;
	}

	public Integer getIdCarta() {
		return idCarta;
	}

	public Integer getNumeroPunti() {
		return numeroPunti;
	}

	public void setIdCarta(Integer idCarta) {
		this.idCarta = idCarta;
	}

	public void setNumeroPunti(Integer numeroPunti) {
		this.numeroPunti = numeroPunti;
	}
}