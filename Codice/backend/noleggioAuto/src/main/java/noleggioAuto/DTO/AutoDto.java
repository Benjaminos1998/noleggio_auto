package noleggioAuto.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import noleggioAuto.entities.TipologiaAuto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutoDto {

	public String targa;
	public String modello;
	public TipologiaAuto tipoAuto;
}
