package noleggioAutoTest.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import noleggioAuto.entities.Luxury;

public class LuxuryTest {

	/**
	 * Test per il costruttore di Luxury senza argomenti 
	 */
	@Test
	public void testLuxury() {
		Luxury autoLuxury = new Luxury();
		autoLuxury.setTarga("CA123DA");
		autoLuxury.setModello("Panda");
		autoLuxury.setPrezzo(100);
		assertEquals("CA123DA", autoLuxury.getTarga());
		assertEquals("Panda", autoLuxury.getModello());
		assertEquals(100, autoLuxury.getPrezzo(), 0);
		assertEquals(150, Luxury.getPuntibonus(), 0);
	} 
	
	/**
	 * Test per il costruttore di Luxury con argomenti la targa, il modello e il prezzo dell'auto
	 */
	@Test
	public void testLuxury2() {
		Luxury autoLuxury = new Luxury("CA123DA", "Panda",100);
		assertEquals("CA123DA", autoLuxury.getTarga());
		assertEquals("Panda", autoLuxury.getModello());
		assertEquals(100, autoLuxury.getPrezzo(), 0);
	} 
	
	
	

}
