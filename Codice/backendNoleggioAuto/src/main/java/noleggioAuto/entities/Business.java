package noleggioAuto.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Business extends Auto {

	@Column(insertable = false)
	private static final Integer puntiBonus = 100;

	public static Integer getPuntibonus() {
		return puntiBonus;
	}

}