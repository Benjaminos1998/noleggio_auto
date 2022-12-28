package noleggioAuto.entities;

public class UtenteVip extends Utente {

	private Integer idCarta;
	private Integer numeroPunti;

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