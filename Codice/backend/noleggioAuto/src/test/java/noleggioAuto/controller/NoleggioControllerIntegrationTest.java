package noleggioAuto.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mysql.cj.util.TestUtils;

import noleggioAuto.DTO.NoleggioDTO;
import noleggioAuto.services.NoleggioService;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { NoleggioController.class })
class NoleggioControllerIntegrationTest {

	@Autowired
	NoleggioController underTest;
	@MockBean
	NoleggioService noleggioService;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
	}

	@Test
	void testGetNoleggioById_MVC() throws Exception {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		String actual = mockMvc.perform(get("api/noleggi/noleggio/" + id).accept("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("NoleggioController/getNoleggioById.json"));
	}

	@Test
	void testGetNoleggi_MVC() throws Exception {
		// given
		// when
		String actual = mockMvc.perform(get("api/noleggi").accept("application/json")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("NoleggioController/getNoleggi.json"));
	}

	@Test
	void testAddNoleggio_MVC() throws Exception {
		// given
		NoleggioDTO noleggio = TestValueFactory.getValueForType(NoleggioDTO.class, "noleggio");
		// when
		String actual = mockMvc
				.perform(post("api/noleggi/addNoleggio").content(TestUtils.objectToJson(noleggio))
						.accept("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("NoleggioController/addNoleggio.json"));
	}

	@Test
	void testDeleteNoleggio_MVC() throws Exception {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		String actual = mockMvc.perform(delete("api/noleggi/deleteNoleggio/" + id).accept("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("NoleggioController/deleteNoleggio.json"));
	}
}