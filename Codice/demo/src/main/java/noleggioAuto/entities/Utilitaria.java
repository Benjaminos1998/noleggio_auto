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
		this.prezzo=prezzo;
	}
	

}