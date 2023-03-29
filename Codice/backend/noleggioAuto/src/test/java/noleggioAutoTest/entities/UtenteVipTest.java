package testnoleggioauto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class UtenteVipTest {

	@Test
	void test() {
		Integer id = 54;
		Integer numeroPatente = 88;
		String n= new String("Pino");
		String c= new String("Lanfra");
		LocalDate date = null;
		Integer idCarta=0001;
		Integer numeroPunti=15;;
		
		//UtenteVip(num,String nome , String cognome, Integer numeroPatente, LocalDate dob,Integer idCarta,Integer numeroPunti)
		UtenteVip p = new UtenteVip(id, n, c,numeroPatente, date,idCarta,numeroPunti);
		assertEquals(88,p.numeroPatente);
		assertEquals("Pino",p.nome);
		assertEquals("Lanfra",p.cognome);
		assertEquals(null,p.dob);
		assertEquals(0001,p.idCarta);
		assertEquals(15,p.numeroPunti);
		//fail("Not yet implemented");
	}

}
