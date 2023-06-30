package noleggioAuto.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mysql.cj.util.TestUtils;

import noleggioAuto.entities.Auto;
import noleggioAuto.services.AutoService;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { AutoController.class })
class AutoControllerIntegrationTest {

	@Autowired
	AutoController underTest;
	@MockBean
	AutoService autoService;
	@MockBean
	ModelMapper modelMapper;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
	}

	@Test
	void testGetAutoById_MVC() throws Exception {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		String actual = mockMvc.perform(get("api/auto/" + id).accept("application/json")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("AutoController/getAutoById.json"));
	}

	@Test
	void testGetAllAuto_MVC() throws Exception {
		// given
		// when
		String actual = mockMvc.perform(get("api/auto/parcoAuto").accept("application/json")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isNotEmpty();
		assertThat(actual).isEqualTo(TestUtils.readTestFile("AutoController/getAllAuto.json"));
	}

	@Test
	void testAddAuto_MVC() throws Exception {
		// given
		Auto auto = TestValueFactory.getValueForType(Auto.class, "auto");
		// when
		String actual = mockMvc
				.perform(post("api/auto/addAuto").content(TestUtils.objectToJson(auto)).accept("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("AutoController/addAuto.json"));
	}

	@Test
	void testDeleteAuto_MVC() throws Exception {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		mockMvc.perform(delete("api/auto/deleteAuto/" + id).accept("application/json")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		// then
		// TODO check for expected side effect (i.e. service call, changed parameter or
		// exception thrown)
		// verify(mock).methodcall();
		// assertThat(TestUtils.objectToJson(param)).isEqualTo(TestUtils.readTestFile("someMethod/ParamType_updated.json"));
		// assertThrows(SomeException.class, () -> underTest.someMethod());
	}

	@Test
	void testGetAutoById_MVC_1() throws Exception {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		String actual = mockMvc.perform(get("api/auto/" + id).accept("application/json")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("AutoController/getAutoById.json"));
	}

	@Test
	void testGetAllAuto_MVC_1() throws Exception {
		// given
		// when
		String actual = mockMvc.perform(get("api/auto/parcoAuto").accept("application/json")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isNotEmpty();
		assertThat(actual).isEqualTo(TestUtils.readTestFile("AutoController/getAllAuto.json"));
	}

	@Test
	void testAddAuto_MVC_1() throws Exception {
		// given
		Auto auto = TestValueFactory.getValueForType(Auto.class, "auto");
		// when
		String actual = mockMvc
				.perform(post("api/auto/addAuto").content(TestUtils.objectToJson(auto)).accept("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		// then
		assertThat(actual).isEqualTo(TestUtils.readTestFile("AutoController/addAuto.json"));
	}

	@Test
	void testDeleteAuto_MVC_1() throws Exception {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		mockMvc.perform(delete("api/auto/deleteAuto/" + id).accept("application/json")).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		// then
		// TODO check for expected side effect (i.e. service call, changed parameter or
		// exception thrown)
		// verify(mock).methodcall();
		// assertThat(TestUtils.objectToJson(param)).isEqualTo(TestUtils.readTestFile("someMethod/ParamType_updated.json"));
		// assertThrows(SomeException.class, () -> underTest.someMethod());
	}
}