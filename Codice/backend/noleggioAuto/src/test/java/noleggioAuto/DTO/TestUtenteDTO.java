package noleggioAuto.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TestUtenteDTO {

	@Test
	public void test() {
		UtenteDTO utenteDTO = new UtenteDTO();

		utenteDTO.setNome("Mario");
		utenteDTO.setCognome("Rossi");
		utenteDTO.setDataDiNascita(LocalDate.of(1990, 12, 1));
		utenteDTO.setEmail("rossi@gmaill.com");
		utenteDTO.setPassword("password");
		utenteDTO.setNumeroPatente("numeroPatente");

		assertEquals("Mario", utenteDTO.getNome());
		assertEquals("Rossi", utenteDTO.getCognome());
		assertEquals(LocalDate.of(1990, 12, 1), utenteDTO.getDataDiNascita());
		assertEquals("rossi@gmaill.com", utenteDTO.getEmail());
		assertEquals("password", utenteDTO.getPassword());
		assertEquals("numeroPatente", utenteDTO.getNumeroPatente());
	}

	@Test
	public void test2() {
		UtenteDTO utenteDTO = new UtenteDTO("Mario", "Rossi", "rossi@gmaill.com", "password", LocalDate.of(1990, 12, 1),
				"numeroPatente");
		assertEquals("Mario", utenteDTO.getNome());
		assertEquals("Rossi", utenteDTO.getCognome());
		assertEquals(LocalDate.of(1990, 12, 1), utenteDTO.getDataDiNascita());
		assertEquals("rossi@gmaill.com", utenteDTO.getEmail());
		assertEquals("password", utenteDTO.getPassword());
		assertEquals("numeroPatente", utenteDTO.getNumeroPatente());

	}
}
