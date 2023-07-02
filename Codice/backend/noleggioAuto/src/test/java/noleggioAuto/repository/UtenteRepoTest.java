package noleggioAuto.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import noleggioAuto.entities.Utente;

@DataJpaTest
public class UtenteRepoTest {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	private Utente a;
	private Utente b;
	
	@BeforeEach
	void setup() {
		a = new Utente();
		b = new Utente();
		a.idUtente=(long)1;
		b.idUtente=(long)2;
		a.nome = "Alex";
		b.nome = "Ben";
		
	}
	
	@Test
	void saveUtenteTest() {
		Utente newutente = utenteRepository.save(a);
		assertNotNull(newutente);
		assertThat(newutente.getId()).isNotEqualTo(null);
	}
	
	@Test
	void getAllUtenteTest() {
		utenteRepository.save(a);
		utenteRepository.save(b);
		
		List<Utente> list = utenteRepository.findAll();
		
		assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(2, list.size());
	}
	
	void getUtenteByIdTest() {
		utenteRepository.save(a);
		
		Utente newutente = utenteRepository.findById(a.getId()).get();
		
		assertNotNull(newutente);
		assertEquals("Alex", newutente.getNome());
	}
    
   void deleteUtenteTest() {
		
		utenteRepository.save(a);
		Long id = a.getId();
		
		utenteRepository.save(b);
		
		utenteRepository.delete(a);
		
		List<Utente> list = utenteRepository.findAll();
		
		Optional<Utente> u = utenteRepository.findById(id);
		
		assertEquals(1, list.size());
		assertThat(u).isEmpty();
		
	}
}
