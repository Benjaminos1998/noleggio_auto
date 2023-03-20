package noleggioAutoTest.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import noleggioAuto.entities.Noleggio;
import noleggioAuto.gestione.TipologiaNoleggio;

public class NoleggioTest {

	@Test
	public void testCreaNoleggio() {
		Noleggio n = new Noleggio();
		n.idNoleggio = 1;
		assertEquals(n.getIdNoleggio(), n.idNoleggio);
	}

	@Test
	public void test2() {
		Noleggio n1 = new Noleggio();
		assertNull(n1.getIdNoleggio());
	}

	@Test
	public void TestTipoNoleggio1() {
		assertEquals(100, TipologiaNoleggio.BrevePeriodo);
	}

	@Test
	public void TestTipoNoleggio2() {
		assertEquals(50, TipologiaNoleggio.BrevePeriodo);
	}

	@Test
	public void TestTipoNoleggio3() {
		assertEquals(100, TipologiaNoleggio.LungoPerido);
	}

	@Test
	public void TestTipoNoleggio4() {
		assertEquals(50, TipologiaNoleggio.CarSharing);
	}

	@Test
	public void TestTipoNoleggio5() {
		assertEquals(200, TipologiaNoleggio.LungoPerido);
	}
}
