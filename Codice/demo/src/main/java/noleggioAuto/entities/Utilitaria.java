package noleggioAuto.entities;


public class Utilitaria extends Auto {

	// CAMPI

	private static final Integer puntiBonus = 50;
	private String prezzo;

	// COSTRUTTORI
	

	public Utilitaria(String targa, String modello) {
		super(targa, modello);
	}
	
	public Utilitaria(String targa,String modello,String prezzo) {
		this(targa, modello);
		this.setPrezzo(prezzo);
	}

	
	public static Integer getPuntibonus() {
		return puntiBonus;
	}


	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}
	

}