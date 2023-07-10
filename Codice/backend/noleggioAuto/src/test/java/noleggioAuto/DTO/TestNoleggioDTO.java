package noleggioAuto.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TestNoleggioDTO {

	@Test
	public void test() {
		NoleggioDTO noleggioDTO = new NoleggioDTO();

		noleggioDTO.setDataInizio(LocalDate.of(1990, 12, 1));
		noleggioDTO.setDataFine(LocalDate.of(1990, 12, 10));
		noleggioDTO.setPrezzo(10);
		noleggioDTO.setIdAuto((long) 1);
		noleggioDTO.setIdUtenteRegistrato((long) 1);

		assertEquals(LocalDate.of(1990, 12, 1), noleggioDTO.getDataInizio());
		assertEquals(LocalDate.of(1990, 12, 10), noleggioDTO.getDataFine());
		assertEquals(10, noleggioDTO.getPrezzo());
		assertEquals((long) 1, noleggioDTO.getIdAuto());
		assertEquals((long) 1, noleggioDTO.getIdUtenteRegistrato());
	}

	@Test
	public void test2() {
		NoleggioDTO noleggioDTO = new NoleggioDTO(LocalDate.of(1990, 12, 1), LocalDate.of(1990, 12, 10), 10, (long) 1,
				(long) 1);

		assertEquals(LocalDate.of(1990, 12, 1), noleggioDTO.getDataInizio());
		assertEquals(LocalDate.of(1990, 12, 10), noleggioDTO.getDataFine());
		assertEquals(10, noleggioDTO.getPrezzo());
		assertEquals((long) 1, noleggioDTO.getIdAuto());
		assertEquals((long) 1, noleggioDTO.getIdUtenteRegistrato());
	}

}
