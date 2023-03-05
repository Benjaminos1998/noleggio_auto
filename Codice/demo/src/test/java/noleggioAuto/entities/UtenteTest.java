package noleggioAuto.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UtenteTest {

	@Test
	public void testCreaUtente() {
		Utente u1 = new Utente();
		u1.nome = "Paolo";
		u1.cognome = "Rossi";
		u1.numeroPatente = 123456;
		assertEquals("Paolo", u1.nome);
		assertEquals("Rossi", u1.cognome);
		assertEquals("123456", u1.numeroPatente);
	}
	
	@Test
	public void testUtenteVIP() {
		UtenteVip u1 = new UtenteVip();
		assertNull(u1.getIdCarta());
	}

}
