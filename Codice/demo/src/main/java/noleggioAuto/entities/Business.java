package noleggioAuto.entities;

public class Business extends Auto {

	public Business(String modello, String targa) {
		super(modello, targa);
		// TODO Auto-generated constructor stub
	}

	private double prezzo;

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}