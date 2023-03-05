package noleggioAuto.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoleggioTest {

	@Test
	public void testCreaNoleggio() {
		Noleggio n = new Noleggio();
		n.idNoleggio = 1;
		assertEquals(n.getIdNoleggio(), n.idNoleggio);
	}
	
	@Test
	public void test2 () {
		Noleggio n1 = new Noleggio();
		assertNull(n1.getIdNoleggio());
	}
	
	@Test
	public void TestTipoNoleggio1() {
		assertEquals(100, TipologiaNoleggio.BrevePeriodo);
	}
	
	public void TestTipoNoleggio2() {
		assertEquals(50, TipologiaNoleggio.BrevePeriodo);
	}
	
	public void TestTipoNoleggio3() {
		assertEquals(100, TipologiaNoleggio.LungoPerido);
	}
	
	public void TestTipoNoleggio4 ( ) {
		assertEquals(50, TipologiaNoleggio.CarSharing);
	}
	
	public void TestTipoNoleggio5 ( ) {
		assertEquals(200, TipologiaNoleggio.LungoPerido);
	}

}
