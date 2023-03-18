package noleggioAuto.gestione;

public enum TipologiaNoleggio {
	CarSharing(50), BrevePeriodo(100), LungoPerido(200);

	int valore;

	TipologiaNoleggio(int i) {
		this.valore = i;
	}

	@Override
	public String toString() {
		return "";
	}
}