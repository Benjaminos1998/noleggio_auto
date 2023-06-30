package noleggioAuto.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mysql.cj.util.TestUtils;

import noleggioAuto.DTO.UtenteDTO;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { UtenteController.class })
class UtenteControllerIntegrationTest {

	@Autowired
	UtenteController underTest;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
	}

	@Test
	void testGetUtenti_MVC() throws Exception {
		// given
		// when
		String actual = mockMvc.perform(get("api/utenti").accept("application/json")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("UtenteController/getUtenti.json"));
	}

	@Test
	void testGetUtenteById_MVC() throws Exception {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		String actual = mockMvc.perform(get("api/utenti/utente/" + id).accept("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("UtenteController/getUtenteById.json"));
	}

	@Test
	void testAddUtente_MVC() throws Exception {
		// given
		UtenteDTO utenteDTO = TestValueFactory.getValueForType(UtenteDTO.class, "utenteDTO");
		// when
		String actual = mockMvc
				.perform(post("api/utenti/addUtente").content(TestUtils.objectToJson(utenteDTO))
						.accept("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("UtenteController/addUtente.json"));
	}

	@Test
	void testDeleteUtente_MVC() throws Exception {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		String actual = mockMvc.perform(delete("api/utenti/deleteUtente/" + id).accept("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("UtenteController/deleteUtente.json"));
	}
}