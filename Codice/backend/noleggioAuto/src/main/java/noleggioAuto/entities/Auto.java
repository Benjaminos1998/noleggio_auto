package noleggioAuto.entities;

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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	public TipologiaAuto tipoAuto;
	@Transient
	public boolean inUso;

	public Auto(Long idAuto, String targa, String modello, TipologiaAuto tipoAuto) {
		this.idAuto = idAuto;
		this.targa = targa;
		this.modello = modello;
		this.tipoAuto = tipoAuto;
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
        return "Auto [idAuto=" + idAuto + ", targa=" + targa + ", modello=" + modello + ", tipoAuto=" + tipoAuto + ", inUso=" + inUso + "]";
    }

	
}