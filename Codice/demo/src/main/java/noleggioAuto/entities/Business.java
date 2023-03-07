package noleggioAuto.entities;


public class Business extends Auto {

	private static final Integer puntiBonus=100;

	// COSTRUTTORI
	
	
	public Business(String targa, String modello) {
		super(targa, modello);
	}

	public static Integer getPuntibonus() {
		return puntiBonus;
	}
	
}