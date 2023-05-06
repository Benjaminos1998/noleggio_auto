//package noleggioAutoTest.entities;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import noleggioAuto.entities.Utilitaria;
//
//public class UtilitariaTest {
//
//	/**
//	 * Test per il costruttore di Utilitaria senza argomenti
//	 */
//	@Test
//	public void testUtilitaria() {
//		Utilitaria autoUtilitaria = new Utilitaria();
//		autoUtilitaria.setTarga("CA123DA");
//		autoUtilitaria.setModello("Panda");
//		autoUtilitaria.setPrezzo(100);
//		assertEquals("CA123DA", autoUtilitaria.getTarga());
//		assertEquals("Panda", autoUtilitaria.getModello());
//		assertEquals(100, autoUtilitaria.getPrezzo(), 0);
//		assertEquals(50, Utilitaria.getPuntibonus(), 0);
//	}
//
//	/**
//	 * Test per il costruttore di Utilitaria con argomenti
//	 */
//	@Test
//	public void testUtilitaria2() {
//		Utilitaria autoUtilitaria = new Utilitaria("CA123DA", "Panda", 100);
//		assertEquals("CA123DA", autoUtilitaria.getTarga());
//		assertEquals("Panda", autoUtilitaria.getModello());
//		assertEquals(100, autoUtilitaria.getPrezzo(), 0);
//	}
//
//}
