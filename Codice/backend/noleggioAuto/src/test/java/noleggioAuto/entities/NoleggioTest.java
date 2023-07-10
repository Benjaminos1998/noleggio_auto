package noleggioAuto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class NoleggioTest {
	@Test
	public void test() {
		Noleggio noleggio = new Noleggio(LocalDate.of(1990, 12, 1), LocalDate.of(1990, 12, 10), 10);

		assertEquals(LocalDate.of(1990, 12, 1), noleggio.getDataInizio());
		assertEquals(LocalDate.of(1990, 12, 10), noleggio.getDataFine());
		assertEquals(10, noleggio.getPrezzo());

		Noleggio.durataNoleggio(noleggio);
		Noleggio.controlloPrezzoNoleggio(noleggio.getPrezzo());

	}

	@Test
	public void test2() {
		Auto auto = new Auto();
		Utente utente = new Utente();
		Noleggio noleggio = new Noleggio(LocalDate.of(1990, 12, 1), LocalDate.of(1990, 12, 10), 0,
				TipologiaNoleggio.CarSharing, auto, utente);

		assertEquals(LocalDate.of(1990, 12, 1), noleggio.getDataInizio());
		assertEquals(LocalDate.of(1990, 12, 10), noleggio.getDataFine());
		assertEquals(0, noleggio.getPrezzo());
		assertEquals(utente, noleggio.getUtente());
		assertEquals(auto, noleggio.getAuto());
		assertEquals(TipologiaNoleggio.CarSharing, noleggio.getTipologiaNoleggio());
		assertEquals(10, noleggio.getTipologiaNoleggio().getValore());
		assertEquals("10", noleggio.getTipologiaNoleggio().toString());
		


		Noleggio.controlloPrezzoNoleggio(noleggio.getPrezzo());
	}

	@Test
	public void test3() {
		Auto auto = new Auto();
		Utente utente = new Utente();

		Noleggio noleggio = Noleggio.builder().idNoleggio((long) 1).dataInizio(LocalDate.of(1990, 12, 1))
				.dataFine(LocalDate.of(1990, 12, 10)).prezzo(10).tipologiaNoleggio(TipologiaNoleggio.BrevePeriodo)
				.auto(auto).utente(utente).build();

		assertEquals(LocalDate.of(1990, 12, 1), noleggio.getDataInizio());
		assertEquals(LocalDate.of(1990, 12, 10), noleggio.getDataFine());
		assertEquals(10, noleggio.getPrezzo());
		assertEquals(utente, noleggio.getUtente());
		assertEquals(auto, noleggio.getAuto());
		assertEquals(TipologiaNoleggio.BrevePeriodo, noleggio.getTipoNoleggio());
		assertEquals((long) 1, noleggio.getId());
		assertEquals("25", noleggio.getTipologiaNoleggio().toString());


	}

	@Test
	public void test4() {
		Auto auto = new Auto();
		Utente utente = new Utente();

		Noleggio noleggio = new Noleggio();

		noleggio.setAuto(auto);
		noleggio.setUtente(utente);
		noleggio.setDataFine(LocalDate.of(1991, 1, 10));
		noleggio.setDataInizio(LocalDate.of(1990, 12, 1));
		noleggio.setIdNoleggio((long) 1);
		noleggio.setTipologiaNoleggio(TipologiaNoleggio.LungoPerido);
		noleggio.setPrezzo(10);

		assertEquals(LocalDate.of(1990, 12, 1), noleggio.getDataInizio());
		assertEquals(LocalDate.of(1991, 1, 10), noleggio.getDataFine());
		assertEquals(10, noleggio.getPrezzo());
		assertEquals(utente, noleggio.getUtente());
		assertEquals(auto, noleggio.getAuto());
		assertEquals(TipologiaNoleggio.LungoPerido, noleggio.getTipoNoleggio());
		assertEquals((long) 1, noleggio.getIdNoleggio());

		assertEquals(50, noleggio.getTipologiaNoleggio().getValore());
		assertEquals("50", noleggio.getTipologiaNoleggio().toString());

		
		
	}

	@Test
	public void test5() {

		Auto auto = new Auto();
		Utente utente = new Utente();

		Noleggio noleggio = new Noleggio((long) 1, LocalDate.of(1990, 12, 1), LocalDate.of(1990, 12, 10), 10,
				TipologiaNoleggio.BrevePeriodo, auto, utente);

		assertEquals(25, noleggio.getTipologiaNoleggio().getValore());

		assertEquals(LocalDate.of(1990, 12, 1), noleggio.getDataInizio());
		assertEquals(LocalDate.of(1990, 12, 10), noleggio.getDataFine());
		assertEquals(10, noleggio.getPrezzo());
		assertEquals(utente, noleggio.getUtente());
		assertEquals(auto, noleggio.getAuto());
		assertEquals(TipologiaNoleggio.BrevePeriodo, noleggio.getTipoNoleggio());
		assertEquals((long) 1, noleggio.getIdNoleggio());

		Noleggio noleggio2 = new Noleggio((long) 1, LocalDate.of(1990, 12, 1), LocalDate.of(1990, 12, 10), 10,
				TipologiaNoleggio.BrevePeriodo, auto, utente);

		Noleggio noleggio3 = new Noleggio((long) 2, LocalDate.of(1990, 12, 1), LocalDate.of(1990, 12, 10), 10,
				TipologiaNoleggio.BrevePeriodo, auto, utente);

		assertEquals(true, noleggio.equals(noleggio2));
		assertEquals(false, noleggio.equals(noleggio3));
		
		Object o = new Object();
		Object o1 = new Noleggio((long) 2, LocalDate.of(1990, 12, 1), LocalDate.of(1990, 12, 10), 10,
				TipologiaNoleggio.BrevePeriodo, auto, utente);

		assertEquals(false, noleggio3.equals(o) );
		assertEquals(true, noleggio3.equals(o1) );
		
		
		
	}

}
