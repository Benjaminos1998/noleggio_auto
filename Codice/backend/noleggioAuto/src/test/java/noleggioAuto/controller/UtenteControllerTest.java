package noleggioAuto.controller;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
 class UtenteControllerTest{

	@InjectMocks
	UtenteController underTest;
	@Mock
	ModelMapper modelMapper;
	@Mock
	UtenteService utenteService;
	@Test
	 void testGetUtenti() throws Exception {
	// given
	// when
	ResponseEntity<?> actual=underTest.getUtenti();
	// then
	assertThat(TestUtils.objectToJson(actual.getBody())).isEqualTo(TestUtils.readTestFile("UtenteController/getUtenti.json"));
	}
	@Test
	 void testGetUtenteById() throws Exception {
	// given
	Long id = TestValueFactory.getValueForType(Long.class, "id");
	// when
	ResponseEntity<?> actual=underTest.getUtenteById(id);
	// then
	assertThat(TestUtils.objectToJson(actual.getBody())).isEqualTo(TestUtils.readTestFile("UtenteController/getUtenteById.json"));
	}
	@Test
	 void testAddUtente() throws Exception {
	// given
	UtenteDTO utenteDTO = TestValueFactory.getValueForType(UtenteDTO.class, "utenteDTO");
	// when
	ResponseEntity<?> actual=underTest.addUtente(utenteDTO);
	// then
	assertThat(TestUtils.objectToJson(actual.getBody())).isEqualTo(TestUtils.readTestFile("UtenteController/addUtente.json"));
	}
	@Test
	 void testDeleteUtente() throws Exception {
	// given
	Long id = TestValueFactory.getValueForType(Long.class, "id");
	// when
	ResponseEntity<String> actual=underTest.deleteUtente(id);
	// then
	assertThat(TestUtils.objectToJson(actual.getBody())).isEqualTo(TestUtils.readTestFile("UtenteController/deleteUtente.json"));
	} 
}