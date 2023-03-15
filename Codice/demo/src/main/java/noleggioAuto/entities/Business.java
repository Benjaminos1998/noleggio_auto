package noleggioAuto.entities;

public class Business extends Auto {

	private double prezzo;
	private static final Integer puntiBonus = 100;

	// COSTRUTTORI

	public Business() {
	}

	public Business(String targa, String modello) {
		super(targa, modello);
	}

	public Business(String targa, String modello, double prezzo) {
		this(targa, modello);
		this.prezzo=prezzo;
	}

	public static Integer getPuntibonus() {
		return puntiBonus;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}