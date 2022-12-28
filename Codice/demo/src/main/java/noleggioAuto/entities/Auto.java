package noleggioAuto.entities;



public abstract class Auto {

	private String modello;
	private String targa;
	
	public Auto(String modello,String targa) {
		this.modello=modello;
		this.targa=targa;
	}

	public String getModello() {
		return modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

}