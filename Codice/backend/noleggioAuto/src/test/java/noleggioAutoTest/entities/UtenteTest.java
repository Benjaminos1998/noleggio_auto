package noleggioAutoTest.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import noleggioAuto.entities.Ruolo;
import noleggioAuto.entities.Utente;
import noleggioAuto.repository.UtenteRepository;

public class UtenteTest {
	
	@MockBean
    private UtenteRepository utenteRepository;

    @BeforeEach
    void setup(){
        Utente u = new Utente();
        u.idUtente = 1L;
        Mockito.when(utenteRepository.findById(1L)).thenReturn(java.util.Optional.empty());
    }

//	/**
//	 * Test per il costruttore con argomenti nome, cognome, numeroPatente e dob dell'utente.
//	 */
	@Test
	public void testCreaUtente() {		
		Utente utente = new Utente((long) 1, "Mario", "Rossi", "rossi@gmaill.com", "password", 0, LocalDate.of(1990, 12, 1), "numeroPatente",Utente.getEta(LocalDate.of(1990, 12, 1)), Ruolo.UTENTE, false);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		assertEquals((long)1, utente.idUtente);
		
	
	}
}
