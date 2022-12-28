package noleggioAuto.entities;

public class Luxury extends Auto {

	private double prezzo;

	public Luxury(String modello, String targa) {
		super(modello, targa);

	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}