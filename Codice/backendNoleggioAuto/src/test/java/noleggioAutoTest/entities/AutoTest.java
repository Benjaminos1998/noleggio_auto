package noleggioAutoTest.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import noleggioAuto.entities.Auto;

public class AutoTest {

	/**
	 * Test per il costruttore di Auto senza argomenti
	 */
	@Test
	public void testAuto() {
		Auto auto = new Auto();
		auto.setTarga("CA123DA");
		auto.setModello("Panda");
		auto.setId(1);
		assertEquals("CA123DA", auto.getTarga());
		assertEquals("Panda", auto.getModello());
		assertEquals(1, auto.getId(), 0);

	}

	/**
	 * Test per il costruttore di Auto con argomenti
	 */
	@Test
	public void testAuto2() {
		Auto auto = new Auto(1, "DA123DE", "Bmw");
		assertEquals(1, auto.getId(), 0);
		assertEquals("DA123DE", auto.getTarga());
		assertEquals("Bmw", auto.getModello());
	}

	/**
	 * Test per il metodo toString di Auto
	 */
	@Test
	public void testAuto3() {
		Auto auto = new Auto("CA123DA", "Panda");
		assertEquals("Panda Targa: CA123DA", auto.toString());
	}
}
