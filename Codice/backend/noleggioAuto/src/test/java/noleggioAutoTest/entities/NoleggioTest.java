package noleggioAutoTest.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import noleggioAuto.entities.Noleggio;
import noleggioAuto.entities.TipologiaNoleggio;

public class NoleggioTest {

	@Test
	public void test2() {
		Noleggio n1 = new Noleggio(null, null, 10, null, null, null);
		assertNull(n1.getId());
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
