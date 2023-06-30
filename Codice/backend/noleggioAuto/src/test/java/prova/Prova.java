package prova;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import noleggioAuto.entities.Auto;
import noleggioAuto.entities.TipologiaAuto;

public class Prova {

	@Test
	public void testAuto() {
		Auto auto = new Auto( (long) 1, "CA234AS", "Ferrari", TipologiaAuto.Luxury);
		assertEquals("CA234AS", auto.getTarga());
		assertEquals("Ferrari", auto.getModello());
		assertEquals(TipologiaAuto.Luxury, auto.getTipoAuto());
	}
	
	
	
	
	
}
