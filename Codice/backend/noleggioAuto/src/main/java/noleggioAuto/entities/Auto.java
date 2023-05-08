package noleggioAuto.entities;

import java.util.Arrays;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import noleggioAuto.exception.TargaAutoNonValidaException;
import noleggioAuto.exception.TipologiaAutoNonValidaException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "automobili")
public class Auto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long idAuto;
	@Column(nullable = false)
	private String targa;
	@Column(nullable = false)
	private String modello;
	@Column(name = "Tipo")
	@Enumerated(EnumType.STRING)
	private TipologiaAuto tipoAuto;
	@Column(name = "InUso")
	private boolean inUso = false;

	public Auto(Long idAuto, String targa, String modello, TipologiaAuto tipoAuto) {
		this.idAuto = idAuto;
		this.targa = targa;
		this.modello = modello;
		this.tipoAuto = tipoAuto;
	}

	public static void controlloTarga(String targa) throws TargaAutoNonValidaException {
		String formato = "^[A-Z]{2}[0-9]{3}[A-Z]{2}$";
		if (!(targa.matches(formato))) {
			throw new TargaAutoNonValidaException();
		}
	}

	public static void controlloTipologiaAuto(String tipologiaAuto) throws TipologiaAutoNonValidaException {
		String tipologiaFormatted = tipologiaAuto.toLowerCase();
		if (!Arrays.stream(TipologiaAuto.values()).map(Enum::toString).map(String::toLowerCase)
				.collect(Collectors.toList()).contains(tipologiaFormatted)) {
			throw new TipologiaAutoNonValidaException("La tipologia " + tipologiaAuto + " non è valida");
		}
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Auto)) {
			return false;
		}
		Auto auto = (Auto) o;
		return this.idAuto == auto.idAuto && this.targa.equals(auto.targa);
	}

	public String toString() {
		return "Auto [idAuto=" + this.idAuto + ", targa=" + this.targa + ", modello=" + this.modello + ", tipoAuto=" + this.tipoAuto
				+ ", inUso=" + this.inUso + "]";
	}

}