package noleggioAuto.entities;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	public Long idAuto;
	@Column(nullable = false)
	public String targa;
	@Column(nullable = false)
	public String modello;
	@Column(name = "Tipo")
	public TipologiaAuto tipoAuto;
	@Transient
	public boolean inUso = false;

	public Auto(Long idAuto, String targa, String modello, TipologiaAuto tipoAuto) {
		this.idAuto = idAuto;
		this.targa = targa;
		this.modello = modello;
		this.tipoAuto = tipoAuto;
	}

	public static void controlloTarga(String targa) throws TargaAutoNonValidaException {
		String formato = "^[A-Z]{2}[0-9]{3}[A-Z]{2}$";
		if (!(targa.matches(formato))) {
			throw new TargaAutoNonValidaException("La targa inserita non è valida!");
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
		return "Auto [idAuto=" + idAuto + ", targa=" + targa + ", modello=" + modello + ", tipoAuto=" + tipoAuto
				+ ", inUso=" + inUso + "]";
	}

}