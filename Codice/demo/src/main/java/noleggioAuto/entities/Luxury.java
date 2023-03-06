package noleggioAuto.entities;


public class Luxury extends Auto {

	private double prezzo;
	private static final Integer puntiBonus = 150;

	public Luxury(String targa,String modello) {
	super(targa, modello);
	}

	public Luxury(String targa, String modello, double prezzo) {
		this(targa, modello);
		this.prezzo = prezzo;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}