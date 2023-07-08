package noleggioAuto.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import noleggioAuto.entities.Noleggio;
import noleggioAuto.repository.NoleggioRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoleggioIntegrationTest {
	@LocalServerPort
	private int port;
	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;
	
	@Autowired
	private NoleggioRepository noleggioRepository;
	
	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	
	private Noleggio a;
	private Noleggio b;
	
	@BeforeEach
	void setup() {
		baseUrl = baseUrl + ":" + port + "/api/noleggi";
		a = new Noleggio(null, null, 0, null, null, null);
		b = new Noleggio(null, null, 0, null, null, null);
		
		a.idNoleggio = (long)1;
		b.idNoleggio = (long)2;
		
		a = noleggioRepository.save(a);
		b = noleggioRepository.save(b);
	}
	
	@AfterEach
	public void afterSetup() {
		noleggioRepository.deleteAll();
	}
	@Test
	void shouldCreateNoleggioTest() {
		Noleggio c = new Noleggio(null, null, port, null, null, null);
		c.idNoleggio = (long)3;
		
		
		Noleggio newnole = restTemplate.postForObject(baseUrl, c, Noleggio.class);
		
		assertNotNull(newnole);
		assertThat(newnole.getId()).isNotNull();
	}
	
	@Test
	void shouldFetchOneNoleggioByIdTest() {
		
		Noleggio existingnole = restTemplate.getForObject(baseUrl+"/"+a.getId(), Noleggio.class);
		
		assertNotNull(existingnole);
	}
	
	void shouldFetchNoleggiTest() {
		
		List<Noleggio> list = restTemplate.getForObject(baseUrl, List.class);
		
		assertThat(list.size()).isEqualTo(2);
	}
	
	@Test
	void shouldDeleteNoleggiTest() {
		
		restTemplate.delete(baseUrl+"/"+a.getId());
		
		int count = noleggioRepository.findAll().size();
		
		assertEquals(1, count);
	}
}
