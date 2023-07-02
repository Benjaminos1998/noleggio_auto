package noleggioAutoTest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
import noleggioAuto.repository.AutoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutoIntegrationTest {
	@LocalServerPort
	private int port;
	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;
	
	@Autowired
	private AutoRepository autoRepository;
	
	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	
	private Auto a;
	private Auto b;
	
	@BeforeEach
	void setup() {
		baseUrl = baseUrl + ":" + port + "/noleggio";
		a = new Auto((long)1, "Ferrari", null, null);
		b = new Auto((long)2, "Porsche", null, null);
		
		a = autoRepository.save(a);
		b = autoRepository.save(b);
	}
	
	@AfterEach
	public void afterSetup() {
		autoRepository.deleteAll();
	}
	@Test
	void shouldCreateAutoTest() {
		Auto c = new Auto((long) 3, baseUrl, baseUrl, null);
		
		Auto newauto = restTemplate.postForObject(baseUrl, c, Auto.class);
		
		assertNotNull(newauto);
		assertThat(newauto.getId()).isNotNull();
	}
	
	@Test
	void shouldFetchOneAutoByIdTest() {
		
		Noleggio existingnole = restTemplate.getForObject(baseUrl+"/"+a.getId(), Noleggio.class);
		
		assertNotNull(existingnole);
	}
	
	void shouldFetchNoleggiTest() {
		
		
	}
	
	@Test
	void shouldDeleteAutoTest() {
		
		restTemplate.delete(baseUrl+"/"+a.getId());
		
		int count = autoRepository.findAll().size();
		
		assertEquals(1, count);
	}
}
