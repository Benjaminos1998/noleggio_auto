package noleggioAutoTest.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import noleggioAuto.entities.Auto;

public class AutoTest {

	/**
	 * Test sul costruttore con argomenti targa e modello dell'auto
	 */
	@Test
	public void testAuto() {
		Auto auto = new Auto("CS123SA", "Panda");
		assertEquals("CS123SA", auto.getTarga());
		assertEquals("Panda", auto.getModello());
	}

	/**
	 * Test per il costruttore che ha come argomenti id, targa e modello dell'auto
	 */
	@Test
	public void testAuto2() {
		Auto auto = new Auto(1, "DA123DE", "Bmw");
		assertTrue(auto.getId() == 1);
		assertEquals("DA123DE", auto.getTarga());
		assertEquals("Bmw", auto.getModello());
	}

	@Test
	public void testAutoUtilitaria() {
		Auto a = new Auto();
		a.setModello("Utilitaria");
		assertEquals("Utilitaria", a.getModello());
	}

	@Test
	public void testAutoUtilitaria2() {
		Auto a = new Auto();
		a.setModello("Business");
		assertEquals("Utilitaria", a.getModello());
	}

	@Test
	public void testAutoBusiness() {
		Auto a = new Auto();
		a.setModello("Business");
		assertEquals("Business", a.getModello());
	}

	@Test
	public void testAutoBusiness2() {
		Auto a = new Auto();
		a.setModello("Luxury");
		assertEquals("Business", a.getModello());
	}

	@Test
	public void testAutoLuxury() {
		Auto a = new Auto();
		a.setModello("Luxury");
		assertEquals("Luxury", a.getModello());
	}

	@Test
	public void testAutoLuxury2() {
		Auto a = new Auto();
		a.setModello("Business");
		assertEquals("Luxury", a.getModello());
	}
}
