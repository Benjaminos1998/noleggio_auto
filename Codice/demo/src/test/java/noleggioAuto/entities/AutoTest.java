package noleggioAuto.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class AutoTest {

	@Test
	public void testAutoUtilitaria() {
		Auto a = new Auto();
		a.setModello("Utilitaria");
		assertEquals("Utilitaria",a.getModello());
	}
	
	public void testAutoUtilitaria2() {
		Auto a = new Auto();
		a.setModello("Business");
		assertEquals("Utilitaria",a.getModello());
	}
	
	public void testAutoBusiness() {
		Auto a = new Auto();
		a.setModello("Business");
		assertEquals("Business",a.getModello());
	}
	
	public void testAutoBusiness2() {
		Auto a = new Auto();
		a.setModello("Luxury");
		assertEquals("Business",a.getModello());
	}
	
	public void testAutoLuxury() {
		Auto a = new Auto();
		a.setModello("Luxury");
		assertEquals("Luxury",a.getModello());
	}
	
	public void testAutoLuxury2() {
		Auto a = new Auto();
		a.setModello("Business");
		assertEquals("Luxury",a.getModello());
	}

}
