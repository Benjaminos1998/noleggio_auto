package noleggioAuto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Auto {

	@Id
	@SequenceGenerator(name = "auto_sequence", sequenceName = "auto_sequence", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_sequence")
	private Integer id;
	@Column(nullable = false)
	public String targa;
	@Column(nullable = false)
	public String modello;
	@Column(nullable = false)
	public double prezzo;
	


	public String toString() {
		return this.modello + " Targa: " + this.targa;
	}
}