package noleggioAuto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class AutoTest {

	@Test
	public void testCostruttore() {
		Auto auto = new Auto((long) 1, "CA234AS", "Ferrari", TipologiaAuto.Luxury);
		assertEquals((long) 1, auto.getIdAuto());
		assertEquals("CA234AS", auto.getTarga());
		assertEquals("Ferrari", auto.getModello());
		assertEquals(TipologiaAuto.Luxury, auto.getTipoAuto());
		Auto.controlloTipologiaAuto("Luxury");

	}

	@Test
	public void testCostruttore2() {
		Auto auto = new Auto();
		auto.setIdAuto((long) 1);
		auto.setTarga("CA123AS");
		auto.setModello("Ferrari");
		auto.setTipoAuto(TipologiaAuto.Business);
		auto.setInUso(true);

		assertEquals(1, auto.getIdAuto());
		assertEquals("CA123AS", auto.getTarga());
		assertEquals("Ferrari", auto.getModello());
		assertEquals(TipologiaAuto.Business, auto.getTipoAuto());
		
		assertEquals(true, auto.isInUso());

		Auto.controlloTarga(auto.getTarga());
		Auto.controlloTipologiaAuto("Business");
		
	}

	@Test
	public void testCostruttore3() {
		Auto auto = new Auto((long) 1, "CA123AS", "Ferrari", TipologiaAuto.Utilitaria, false);

		assertEquals(1, auto.getIdAuto());
		assertEquals("CA123AS", auto.getTarga());
		assertEquals("Ferrari", auto.getModello());
		assertEquals(TipologiaAuto.Utilitaria, auto.getTipoAuto());
		
		assertEquals(false, auto.isInUso());


		Auto.controlloTarga(auto.getTarga());
		Auto.controlloTipologiaAuto("Utilitaria");
		

	}

}
