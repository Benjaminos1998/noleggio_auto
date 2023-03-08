package noleggioAuto.entities;

public class Utilitaria extends Auto {

	// CAMPI

	private static final Integer puntiBonus = 50;
	private double prezzo;

	// COSTRUTTORI

	public Utilitaria() {
	}

	public Utilitaria(String targa, String modello) {
		super(targa, modello);
	}

	public Utilitaria(String targa, String modello, double prezzo) {
		this(targa, modello);
		this.setPrezzo(prezzo);
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