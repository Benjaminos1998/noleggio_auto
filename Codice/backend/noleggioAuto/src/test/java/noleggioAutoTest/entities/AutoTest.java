package noleggioAutoTest.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import noleggioAuto.entities.Auto;
import noleggioAuto.entities.TipologiaAuto;

public class AutoTest {

@Test
	public void testAuto() {
		Auto auto = new Auto();
		auto.setTarga("CA123DA");
		auto.setModello("Panda");
		assertEquals("CA123DA", auto.getTarga());
		assertEquals("Panda", auto.getModello());
		assertEquals(1, auto.getId(), 0);

	}


	@Test
    public void testAuto2() {
		Auto auto = new Auto((long)1, "DA123DE", "Bmw", TipologiaAuto.Business);
		assertEquals(1, auto.getId(), 0);
 	    assertEquals("DA123DE", auto.getTarga());
		assertEquals("Bmw", auto.getModello());
	}
}