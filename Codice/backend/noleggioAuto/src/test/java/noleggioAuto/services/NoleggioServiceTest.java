package noleggioAuto.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import noleggioAuto.entities.Noleggio;
import noleggioAuto.repository.NoleggioRepository;

@ExtendWith(MockitoExtension.class)
public class NoleggioServiceTest {
	
	@Mock
	private NoleggioRepository noleggioRepository;
	
	@InjectMocks
	private NoleggioService noleggioService;
	
	private Noleggio a;
	private Noleggio b;
	@BeforeEach
	void setup() {
		a = new Noleggio(null, null, 0, null, null, null);
		b = new Noleggio(null, null, 0, null, null, null);
		
		a.idNoleggio = (long)1;
		b.idNoleggio = (long)2;
	}
	
        void addNoleggioTest() {
		
		when(noleggioRepository.save(any(Noleggio.class))).thenReturn(a);
		
		Noleggio newnoleggio = noleggioService.save(a);
		
		assertNotNull(newnoleggio);
		assertThat(newnoleggio.getId()).isEqualTo("1");
	}
	
	@Test
	void getAllNoleggioTest() {
		List<Noleggio> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		
		Mockito.when(noleggioRepository.findAll()).thenReturn(list);
		
		List<Noleggio> nole = noleggioService.getNoleggi();
		assertEquals(2, nole.size());
		assertNotNull(nole);
	}
	
	@Test
	void getNoleggioByIdTest() {
		
		Mockito.when(noleggioRepository.findById(anyLong())).thenReturn(Optional.of(a));
		Noleggio c = noleggioService.getNoleggioById(a.getId());
		assertNotNull(c);
		assertThat(c.getId()).isNotEqualTo(null);
	}
	
	@Test
	void deleteNoleggioTest() {
		
		Long noleggioId = 1L;
		when(noleggioRepository.findById(anyLong())).thenReturn(Optional.of(a));
		doNothing().when(noleggioRepository).delete(any(Noleggio.class));
		
		noleggioService.deleteNoleggio(noleggioId);
		
		verify(noleggioRepository, times(1)).delete(a);
		
	}
}
