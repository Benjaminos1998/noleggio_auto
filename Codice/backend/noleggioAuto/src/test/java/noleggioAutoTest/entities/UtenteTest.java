//package noleggioAutoTest.entities;
//
//import static org.junit.Assert.*;
//
//import java.time.LocalDate;
//
//import org.junit.Test;
//
//import noleggioAuto.entities.Utente;
//
//public class UtenteTest {
//
//	/**
//	 * Test per il costruttore con argomenti nome, cognome, numeroPatente e dob dell'utente.
//	 */
//	@Test
//	public void testCreaUtente() {
//		Utente utente = new Utente("Mario","Rossi",1,LocalDate.of(2000, 12, 12));
//		assertEquals("Mario", utente.getNome());
//		assertEquals("Rossi", utente.getCognome());
//		assertTrue(1==utente.getNumeroPatente());
//		assertEquals(LocalDate.of(2000, 12, 12) , utente.getDob());
//	}
//
//	/**
//	 * Test per il costruttore con argomenti id,nome, cognome, numeroPatente e dob dell'utente
//	 */
//	@Test
//	public void testCreaUtente2() {
//		Utente utente = new Utente(1,"Mario","Rossi",1,LocalDate.of(2000, 12, 12));
//		assertTrue(utente.getId()==1);
//		assertEquals("Mario", utente.getNome());
//		assertEquals("Rossi", utente.getCognome());
//		assertTrue(1==utente.getNumeroPatente());
//		assertEquals(LocalDate.of(2000, 12, 12) , utente.getDob());
//	}
//}
