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
public class Luxury extends Auto {

	@Column(insertable = false)
	private static final Integer puntiBonus = 150;

	public static Integer getPuntibonus() {
		return puntiBonus;
	}

}