package noleggioAuto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Utilitaria extends Auto {

	@Column(insertable = false)
	private static final Integer puntiBonus = 50;

	public static Integer getPuntibonus() {
		return puntiBonus;
	}

}