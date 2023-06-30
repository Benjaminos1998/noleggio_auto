package noleggioAuto.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import com.mysql.cj.util.TestUtils;

import noleggioAuto.entities.Auto;
import noleggioAuto.services.AutoService;

@ExtendWith(MockitoExtension.class)
class AutoControllerTest {

	@InjectMocks
	AutoController underTest;
	@Mock
	AutoService autoService;
	@Mock
	ModelMapper modelMapper;

	@Test
	void testGetAutoById() {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		ResponseEntity<?> actual = underTest.getAutoById(id);
		// then
		assertThat(TestUtils.objectToJson(actual.getBody()))
				.isEqualTo(TestUtils.readTestFile("AutoController/getAutoById.json"));
	}

	@Test
	void testGetAllAuto() {
		// given
		// when
		List<Auto> actual = underTest.getAllAuto();
		// then
		assertThat(actual).isNotEmpty();
		assertThat(TestUtils.objectToJson(actual)).isEqualTo(TestUtils.readTestFile("AutoController/getAllAuto.json"));
	}

	@Test
	void testAddAuto() {
		// given
		Auto auto = TestValueFactory.getValueForType(Auto.class, "auto");
		// when
		ResponseEntity<?> actual = underTest.addAuto(auto);
		// then
		assertThat(TestUtils.objectToJson(actual.getBody()))
				.isEqualTo(TestUtils.readTestFile("AutoController/addAuto.json"));
	}

	@Test
	void testDeleteAuto() {
		// given
		Long id = TestValueFactory.getValueForType(Long.class, "id");
		// when
		underTest.deleteAuto(id);
		// then
		// TODO check for expected side effect (i.e. service call, changed parameter or
		// exception thrown)
		// verify(mock).methodcall();
		// assertThat(TestUtils.objectToJson(param)).isEqualTo(TestUtils.readTestFile("someMethod/ParamType_updated.json"));
		// assertThrows(SomeException.class, () -> underTest.someMethod());
	}
}