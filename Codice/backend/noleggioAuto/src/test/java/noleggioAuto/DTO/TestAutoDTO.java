package noleggioAuto.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import noleggioAuto.entities.TipologiaAuto;

public class TestAutoDTO {

	@Test
	public void test() {
		AutoDTO autoDTO = new AutoDTO();
		autoDTO.setIdAuto((long) 1);
		autoDTO.setTarga("CA123AS");
		autoDTO.setModello("Ferrari");
		autoDTO.setTipoAuto(TipologiaAuto.Luxury);

		assertEquals((long) 1, autoDTO.getIdAuto());
		assertEquals("CA123AS", autoDTO.getTarga());
		assertEquals("Ferrari", autoDTO.getModello());
		assertEquals(TipologiaAuto.Luxury, autoDTO.getTipoAuto());
	}

	@Test
	public void test2() {
		AutoDTO autoDTO = new AutoDTO((long) 1, "CA123AS", "Ferrari", TipologiaAuto.Luxury);
		assertEquals((long) 1, autoDTO.getIdAuto());
		assertEquals("CA123AS", autoDTO.getTarga());
		assertEquals("Ferrari", autoDTO.getModello());
		assertEquals(TipologiaAuto.Luxury, autoDTO.getTipoAuto());

	}

}
