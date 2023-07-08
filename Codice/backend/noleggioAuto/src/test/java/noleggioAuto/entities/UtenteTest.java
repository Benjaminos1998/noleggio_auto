package noleggioAuto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import noleggioAuto.entities.Utente;

public class UtenteTest {
	/**
//	 * Test per il costruttore con argomenti id,nome, cognome, numeroPatente e dob dell'utente
//	 */
	@Test
	public void testCreaUtente2() {
		Utente utente = new Utente();
		utente.nome ="Mario";
		utente.cognome ="Rossi";
		utente.idUtente = (long)2L;
		assertTrue(utente.getId()==1L);
		assertEquals("Mario", utente.getNome());
		assertEquals(25 , utente.getEta(LocalDate.of(1998, 06, 21)));
	}
}
