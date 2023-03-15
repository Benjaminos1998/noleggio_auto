package noleggioAutoTest.entities;

import static org.junit.Assert.*;

import org.junit.Test;
import noleggioAuto.entities.Business;

public class BusinessTest {

	/**
	 * Test per il costruttore di Business senza argomenti 
	 */
	@Test
	public void testAutoBusiness() {
		Business autoBusiness = new Business();
		autoBusiness.setTarga("CA123DA");
		autoBusiness.setModello("Panda");
		autoBusiness.setPrezzo(100);
		assertEquals("CA123DA", autoBusiness.getTarga());
		assertEquals("Panda", autoBusiness.getModello());
		assertEquals(100, autoBusiness.getPrezzo(), 0);
		assertEquals(100, Business.getPuntibonus(), 0);
	}
	
	/**
	 * Test per il costruttore di Business con argomenti 
	 */
	@Test
	public void testAutoBusiness2() {
		Business autoBusiness = new Business("CA234DA", "Panda",100);
		assertEquals("CA234DA", autoBusiness.getTarga());
		assertEquals("Panda", autoBusiness.getModello());
		assertEquals(100, autoBusiness.getPrezzo(), 0);
	}
	

}
