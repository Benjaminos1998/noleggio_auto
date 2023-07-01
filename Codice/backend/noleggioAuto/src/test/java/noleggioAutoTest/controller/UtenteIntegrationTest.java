package noleggioAutoTest.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import noleggioAuto.entities.Auto;
import noleggioAuto.entities.Noleggio;
import noleggioAuto.entities.Utente;
import noleggioAuto.repository.AutoRepository;
import noleggioAuto.repository.NoleggioRepository;
import noleggioAuto.repository.UtenteRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UtenteIntegrationTest {
	@LocalServerPort
	private int port;
	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	
	private Utente a;
	private Utente b;
	private Utente c;
	
	@BeforeEach
	void setup() {
		baseUrl = baseUrl + ":" + port + "/noleggio";
		a = new Utente();
		b = new Utente();
		c = new Utente();
		
		a.nome="Alex";
		b.nome= "Ben";
		c.nome = "Ibra";
		a.idUtente=(long)1;
		b.idUtente=(long)2;
		c.idUtente=(long)3;
		
		a = utenteRepository.save(a);
		b = utenteRepository.save(b);
		c = utenteRepository.save(c);
	}
	
	@AfterEach
	public void afterSetup() {
		utenteRepository.deleteAll();
	}
	@Test
	void shouldCreateUtenteTest() {
		Utente d = new Utente();
		
		Utente newutente = restTemplate.postForObject(baseUrl, d, Utente.class);
		
		assertNotNull(newutente);
		assertThat(newutente.getId()).isNotNull();
	}
	
	@Test
	void shouldFetchOneUtenteByIdTest() {
		
		Utente existingutente = restTemplate.getForObject(baseUrl+"/"+a.getId(), Utente.class);
		
		assertNotNull(existingutente);
	}
	
	void shouldFetchUtentiTest() {
		
		List<Utente> list = restTemplate.getForObject(baseUrl, List.class);
		
		assertThat(list.size()).isEqualTo(3);
	}
	
	@Test
	void shouldDeleteUtentiTest() {
		
		restTemplate.delete(baseUrl+"/"+a.getId());
		
		int count = utenteRepository.findAll().size();
		
		assertEquals(1, count);
	}
}
