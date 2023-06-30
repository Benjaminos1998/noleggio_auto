package noleggioAuto.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.mysql.cj.util.TestUtils;

import noleggioAuto.DTO.NoleggioDTO;
import noleggioAuto.services.NoleggioService;

@ExtendWith(MockitoExtension.class)
class NoleggioControllerTest {

	@InjectMocks
	NoleggioController underTest;
	@Mock
	NoleggioService noleggioService;

	@Test
	void testGetNoleggioById() {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		ResponseEntity<?> actual = underTest.getNoleggioById(id);
		// then
		assertThat(TestUtils.objectToJson(actual.getBody()))
				.isEqualTo(TestUtils.readTestFile("NoleggioController/getNoleggioById.json"));
	}

	@Test
	void testGetNoleggi() {
		// given
		// when
		ResponseEntity<?> actual = underTest.getNoleggi();
		// then
		assertThat(TestUtils.objectToJson(actual.getBody()))
				.isEqualTo(TestUtils.readTestFile("NoleggioController/getNoleggi.json"));
	}

	@Test
	void testAddNoleggio() {
		// given
		NoleggioDTO noleggio = TestValueFactory.getValueForType(NoleggioDTO.class, "noleggio");
		// when
		ResponseEntity<?> actual = underTest.addNoleggio(noleggio);
		// then
		assertThat(TestUtils.objectToJson(actual.getBody()))
				.isEqualTo(TestUtils.readTestFile("NoleggioController/addNoleggio.json"));
	}

	@Test
	void testDeleteNoleggio() {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		ResponseEntity<?> actual = underTest.deleteNoleggio(id);
		// then
		assertThat(TestUtils.objectToJson(actual.getBody()))
				.isEqualTo(TestUtils.readTestFile("NoleggioController/deleteNoleggio.json"));
	}
}