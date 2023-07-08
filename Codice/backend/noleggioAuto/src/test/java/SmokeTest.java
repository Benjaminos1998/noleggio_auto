

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import noleggioAuto.controller.AutoController;
import noleggioAuto.controller.NoleggioController;
import noleggioAuto.controller.UtenteController;

public class SmokeTest {

	@Autowired
	private NoleggioController noleggio;
	private AutoController auto;
	private UtenteController utente;

	@Test
	public void contextLoads() throws Exception {
		assertThat(noleggio).isNotNull();
		assertThat(auto).isNotNull();
		assertThat(utente).isNotNull();
	}
	
}
