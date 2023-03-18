package noleggioAuto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UtenteVip extends Utente {
	

	@Column(name = "numerocarta")
	public Integer idCarta;
//	public Integer numeroPunti;


}