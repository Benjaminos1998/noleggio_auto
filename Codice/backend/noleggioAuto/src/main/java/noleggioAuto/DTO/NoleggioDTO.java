package noleggioAuto.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoleggioDTO {

	public LocalDate dataInizio;
	public LocalDate dataFine;
	public double prezzo;
	public Long idAuto;
	public Long idUtenteRegistrato;
}
