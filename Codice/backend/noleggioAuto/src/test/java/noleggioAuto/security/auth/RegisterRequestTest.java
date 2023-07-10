package noleggioAuto.security.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class RegisterRequestTest {

	
	@Test
	public void test() {
		RegisterRequest registerRequest = new RegisterRequest();
		
		
		registerRequest = RegisterRequest.builder()
			.nome("Mario")
			.cognome("Rossi")
			.email("rossi@gmaill.com")
			.password("password")
			.dataDiNascita(LocalDate.of(1990, 12, 1))
			.numeroPatente("numeroPatente")
			.build();
		
		assertEquals("Mario", registerRequest.getNome());
		assertEquals("Rossi", registerRequest.getCognome());
		assertEquals("rossi@gmaill.com", registerRequest.getEmail());
		assertEquals("password", registerRequest.getPassword());
		assertEquals(LocalDate.of(1990, 12, 1), registerRequest.getDataDiNascita());
		assertEquals("numeroPatente", registerRequest.getNumeroPatente());
		
	}
	@Test
	public void test2() {
		RegisterRequest registerRequest = new RegisterRequest("Mario", "Rossi","rossi@gmaill.com", "password", LocalDate.of(1990, 12, 1), "numeroPatente");
		
		
		assertEquals("Mario", registerRequest.getNome());
		assertEquals("Rossi", registerRequest.getCognome());
		assertEquals("rossi@gmaill.com", registerRequest.getEmail());
		assertEquals("password", registerRequest.getPassword());
		assertEquals(LocalDate.of(1990, 12, 1), registerRequest.getDataDiNascita());
		assertEquals("numeroPatente", registerRequest.getNumeroPatente());
		
	}
	@Test
	public void test3() {
		RegisterRequest registerRequest = new RegisterRequest();
		
		registerRequest.setNome("Mario");
		registerRequest.setCognome("Rossi");
		registerRequest.setDataDiNascita(LocalDate.of(1990, 12, 1));
		registerRequest.setEmail("rossi@gmaill.com");
		registerRequest.setPassword("password");
		registerRequest.setNumeroPatente("numeroPatente");
		
		
		
		assertEquals("Mario", registerRequest.getNome());
		assertEquals("Rossi", registerRequest.getCognome());
		assertEquals("rossi@gmaill.com", registerRequest.getEmail());
		assertEquals("password", registerRequest.getPassword());
		assertEquals(LocalDate.of(1990, 12, 1), registerRequest.getDataDiNascita());
		assertEquals("numeroPatente", registerRequest.getNumeroPatente());
		
	}
}
