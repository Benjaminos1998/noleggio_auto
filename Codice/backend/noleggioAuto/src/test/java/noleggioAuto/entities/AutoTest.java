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
		
		assertEquals((long) 1, auto.getId());
		
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
	
	@Test
	public void TestEquals() {
		Auto auto = new Auto((long) 1, "CA123AS", "Ferrari", TipologiaAuto.Utilitaria, false);
		Auto auto2 = new Auto((long) 1, "CA123AS", "Ferrari", TipologiaAuto.Utilitaria, false);
		
		assertEquals(true, auto.getTarga().equals(auto2.getTarga()));
		assertEquals(true, auto.getIdAuto().equals(auto2.getIdAuto()));
		
		Object o = new Auto((long) 1, "CA123AS", "Ferrari", TipologiaAuto.Utilitaria, false);
		
		
		assertEquals(true, auto.equals(o));
		assertEquals(true, auto.equals(auto2));
		
		
		
	}
	
	@Test
	public void TestEquals2() {
		Auto auto = new Auto((long) 1, "CA123AS", "Ferrari", TipologiaAuto.Utilitaria, false);
		Auto auto2 = new Auto((long) 2, "CA123AF", "Ferrari", TipologiaAuto.Utilitaria, false);
		
		assertEquals(false, auto.getTarga().equals(auto2.getTarga()));
		assertEquals(false, auto.getIdAuto().equals(auto2.getIdAuto()));
		
		assertEquals(false, auto.equals(auto2));
		
		Object o = new Object();
		assertEquals(false, auto2.equals(o));
		
	}

	
	@Test
	public void TestEquals3() {
		Auto auto = new Auto((long) 1, "CA123AS", "Ferrari", TipologiaAuto.Utilitaria, false);
		Auto auto2 = new Auto((long) 1, "CA123AF", "Ferrari", TipologiaAuto.Utilitaria, false);
		
		assertEquals(false, auto.getTarga().equals(auto2.getTarga()));
		assertEquals(true, auto.getIdAuto().equals(auto2.getIdAuto()));
		
		assertEquals(false, auto.equals(auto2));
		
		Object o = new Object();
		assertEquals(false, auto2.equals(o));
		
	}
}
