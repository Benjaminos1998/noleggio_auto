package noleggioAuto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



public class UtenteTest {
	
	@Test
	public void testCreaUtente() {		
		Utente utente = new Utente((long) 1, "Mario", "Rossi", "rossi@gmaill.com", "password", 0, LocalDate.of(1990, 12, 1), "numeroPatente",Utente.getEta(LocalDate.of(1990, 12, 1)), Ruolo.UTENTE, false);
		assertEquals((long)1, utente.idUtente);
		assertEquals("Mario", utente.getNome());
		assertEquals("Rossi", utente.getCognome());
		assertEquals("rossi@gmaill.com", utente.getEmail());
		assertEquals("password", utente.getPassword());
		assertEquals(0, utente.getNumeroPunti());
		assertEquals(LocalDate.of(1990, 12, 1), utente.getDataDiNascita());
		assertEquals("numeroPatente", utente.getNumeroPatente());
		assertEquals(Utente.getEta(LocalDate.of(1990, 12, 1)), utente.getEta());
		assertEquals(Ruolo.UTENTE, utente.getRuolo());
		assertEquals(false, utente.isNoleggioInCorso());
		
		assertEquals("rossi@gmaill.com", utente.getUsername());
		
		assertEquals(true, utente.isAccountNonExpired());
		assertEquals(true, utente.isAccountNonLocked());
		assertEquals(true, utente.isCredentialsNonExpired());
		assertEquals(true, utente.isEnabled());
		
		assertEquals((long)1, utente.getId());
		
		
		assertEquals(18, Utente.ETA);
		assertEquals(8, Utente.LUNGHEZZA_MINIMA_PASSWORD);
		
		assertEquals(Ruolo.UTENTE.name(), utente.getAuthorities());
		
	}
	
	
	@Test
	public void testCreaUtente2() {		
		Utente utente = new Utente();
		utente.setIdUtente((long)1);
		utente.setNome("Mario");
		utente.setCognome("Rossi");
		utente.setEmail("rossi@gmaill.com");
		utente.setPassword("password");
		utente.setNumeroPunti(0);
		utente.setDataDiNascita(LocalDate.of(1990, 12, 1));
		utente.setNumeroPatente("numeroPatente");
		utente.setEta(Utente.getEta(LocalDate.of(1990, 12, 1)));
		utente.setRuolo(Ruolo.ADMIN);
		utente.setNoleggioInCorso(true);
		
		assertEquals((long)1, utente.idUtente);
		assertEquals("Mario", utente.getNome());
		assertEquals("Rossi", utente.getCognome());
		assertEquals("rossi@gmaill.com", utente.getEmail());
		assertEquals("password", utente.getPassword());
		assertEquals(0, utente.getNumeroPunti());
		assertEquals(LocalDate.of(1990, 12, 1), utente.getDataDiNascita());
		assertEquals("numeroPatente", utente.getNumeroPatente());
		assertEquals(Utente.getEta(LocalDate.of(1990, 12, 1)), utente.getEta());
		assertEquals(Ruolo.ADMIN, utente.getRuolo());
		assertEquals(true, utente.isNoleggioInCorso());
		
	}
	
	
	@Test
	public void testCreaUtente3() {		
		Utente utente = Utente.builder()
			.idUtente((long)1)
			.nome("Mario")
			.cognome("Rossi")
			.email("rossi@gmaill.com")
			.password("password")
			.numeroPunti(0)
			.dataDiNascita(LocalDate.of(1990, 12, 1))
			.numeroPatente("numeroPatente")
			.eta(Utente.getEta(LocalDate.of(1990, 12, 1)))
			.ruolo(Ruolo.ADMIN)
			.noleggioInCorso(true)
			.build();
		
		
		assertEquals((long)1, utente.idUtente);
		assertEquals("Mario", utente.getNome());
		assertEquals("Rossi", utente.getCognome());
		assertEquals("rossi@gmaill.com", utente.getEmail());
		assertEquals("password", utente.getPassword());
		assertEquals(0, utente.getNumeroPunti());
		assertEquals(LocalDate.of(1990, 12, 1), utente.getDataDiNascita());
		assertEquals("numeroPatente", utente.getNumeroPatente());
		assertEquals(Utente.getEta(LocalDate.of(1990, 12, 1)), utente.getEta());
		assertEquals(Ruolo.ADMIN, utente.getRuolo());
		assertEquals(true, utente.isNoleggioInCorso());
		
	}
	
	
	
	@Test
	public void TestEquals() {
		Utente utente = new Utente((long) 1, "Mario", "Rossi", "rossi@gmaill.com", "password", 0, LocalDate.of(1990, 12, 1), "numeroPatente",Utente.getEta(LocalDate.of(1990, 12, 1)), Ruolo.UTENTE, false);
		Utente utente2 = new Utente((long) 1, "Mario", "Rossi", "rossi@gmaill.com", "password", 0, LocalDate.of(1990, 12, 1), "numeroPatente",Utente.getEta(LocalDate.of(1990, 12, 1)), Ruolo.UTENTE, false);
		
		assertEquals(true ,utente.equals(utente2) );
		Object o = new Utente((long) 1, "Mario", "Rossi", "rossi@gmaill.com", "password", 0, LocalDate.of(1990, 12, 1), "numeroPatente",Utente.getEta(LocalDate.of(1990, 12, 1)), Ruolo.UTENTE, false);
		
		assertEquals(true ,utente.equals(o) );
	}
	
	@Test
	public void TestEquals2() {
		Utente utente = new Utente((long) 1, "Mario", "Rossi", "rossi@gmaill.com", "password", 0, LocalDate.of(1990, 12, 1), "numeroPatente",Utente.getEta(LocalDate.of(1990, 12, 1)), Ruolo.UTENTE, false);
		Utente utente2 = new Utente((long) 2, "Mario", "Rossi", "rossi@gmaill.com", "password", 0, LocalDate.of(1990, 12, 1), "numeroPatente2",Utente.getEta(LocalDate.of(1990, 12, 1)), Ruolo.UTENTE, false);
		
		assertEquals(false ,utente.equals(utente2) );
		
		Object o = new Utente((long) 1, "Mario", "Rossi", "rossi@gmaill.com", "password", 0, LocalDate.of(1990, 12, 1), "numeroPatente",Utente.getEta(LocalDate.of(1990, 12, 1)), Ruolo.UTENTE, false);
		
		assertEquals(false ,utente2.equals(o) );

		Object o2 = new Object();
		assertEquals(false , utente.equals(o2));
		
		
		
	}
	
}
