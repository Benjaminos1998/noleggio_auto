package noleggioAuto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import noleggioAuto.entities.Noleggio;
import noleggioAuto.entities.TipologiaNoleggio;

public class NoleggioTest {
    private Noleggio n1;
    private Noleggio n2;
    private Noleggio n3;
    private TipologiaNoleggio t;
    
	@Test
	public void setup() {
		 n1 = new Noleggio(null, null, 10, null, null, null);
		n1.idNoleggio =(long)1L;
		n1.tipologiaNoleggio = TipologiaNoleggio.BrevePeriodo;
		
		n2 = new Noleggio(null, null, 10, null, null, null);
		n2.idNoleggio =(long)2L;
		n2.tipologiaNoleggio = TipologiaNoleggio.CarSharing;
		
		n2 = new Noleggio(null, null, 10, null, null, null);
		n2.idNoleggio =(long)3L;
		n2.tipologiaNoleggio = TipologiaNoleggio.LungoPerido;
		
		assertEquals(1L, n1.getId());
		assertEquals(2L, n2.getId());
		assertEquals(3L, n3.getId());
		
		assertSame(t.BrevePeriodo, n1.getTipoNoleggio());
		
		assertSame(t.CarSharing, n2.getTipoNoleggio());
		
		assertSame(t.LungoPerido, n3.getTipoNoleggio());

	}
}
