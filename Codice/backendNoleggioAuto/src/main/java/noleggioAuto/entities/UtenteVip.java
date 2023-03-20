package noleggioAuto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utenti_Vip")
public class UtenteVip extends Utente {

	@Column(name = "numerocarta")
	public Integer idCarta;
	@Transient
	public Integer numeroPunti;

}