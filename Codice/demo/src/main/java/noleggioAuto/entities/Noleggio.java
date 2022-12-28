package noleggioAuto.entities;

public class Noleggio {

	private double prezzo;
	private Integer idNoleggio=1;
	
	public Noleggio(double prezzo) {
		this.prezzo=prezzo;
		this.idNoleggio=+1;
		
	}

	public double getPrezzo() {
		return prezzo;
	}

	public Integer getIdNoleggio() {
		return idNoleggio;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public void setIdNoleggio(Integer idNoleggio) {
		this.idNoleggio = idNoleggio;
	}

	public double calcolaPrezzo() {
		return 0;
	}

}