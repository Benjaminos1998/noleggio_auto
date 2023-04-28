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
public class UtenteRegistratoDto {

	private String nome; 
	private String cognome; 
	private String username;
	private String password;
	private LocalDate dataDiNascita;
	private String numeroPatente;
	
}
