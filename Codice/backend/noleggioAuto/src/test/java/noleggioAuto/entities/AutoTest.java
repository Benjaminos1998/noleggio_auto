package noleggioAuto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import noleggioAuto.entities.Auto;
import noleggioAuto.entities.TipologiaAuto;

public class AutoTest {

@Test
	public void testAuto() {
		Auto auto = new Auto((long)1L, "ABCDE1234", "Ferrari", TipologiaAuto.Luxury);
		assertEquals("ABCDE1234", auto.getTarga());
		assertEquals("Ferrari", auto.getModello());
		assertEquals(1, auto.getId(), 0);

	}


	@Test
    public void testAuto2() {
		Auto auto = new Auto((long)1, "DA123DE", "Bmw", TipologiaAuto.Business);
		auto.controlloTarga(auto.getTarga());
		assertEquals(1, auto.getId(), 0);
 	    assertEquals("DA123DE", auto.getTarga());
		assertEquals("Bmw", auto.getModello());
	}
}