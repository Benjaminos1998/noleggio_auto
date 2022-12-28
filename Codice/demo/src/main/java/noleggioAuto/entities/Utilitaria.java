package noleggioAuto.entities;

public class Utilitaria extends Auto {

	public Utilitaria(String modello, String targa) {
		super(modello, targa);
	}

	private double prezzo;

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}