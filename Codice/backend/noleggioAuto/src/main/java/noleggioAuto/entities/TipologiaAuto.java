package noleggioAuto.entities;

public enum TipologiaAuto {
	Utilitaria("Utilitaria"), Business("Business"), Luxury("Luxury");

	String tipologia;

	private TipologiaAuto(String tipologia) {
		this.tipologia=tipologia;
	}

	@Override
	public String toString() {
		return this.tipologia;
	}
}
