package noleggioAuto.entities;

import lombok.Getter;

@Getter
public enum TipologiaNoleggio {
	CarSharing(10), BrevePeriodo(25), LungoPerido(50);

	int valore;

	TipologiaNoleggio(int i) {
		this.valore = i;
	}

	@Override
	public String toString() {
		return String.valueOf(valore);
	}
}