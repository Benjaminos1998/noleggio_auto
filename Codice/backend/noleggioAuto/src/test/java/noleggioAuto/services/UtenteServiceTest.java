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

import noleggioAuto.entities.Utente;
import noleggioAuto.repository.UtenteRepository;

@ExtendWith(MockitoExtension.class)
public class UtenteServiceTest {
	
	@Mock
	private UtenteRepository utenteRepository;
	
	@InjectMocks
	private UtenteService utenteService;
	
	private Utente a;
	private Utente b;
	private Utente c;
	
	@BeforeEach
	void setup() {
		a = new Utente();
		b = new Utente();
		c = new Utente();
		
		a.idUtente = (long)1;
		b.idUtente = (long)2;
		a.nome = "Oronzo";
		b.nome = "Canà";
	}
	
        void addUtenteTest() {
		
		when(utenteRepository.save(any(Utente.class))).thenReturn(a);
		
		Utente newutente = utenteService.save(a);
		
		assertNotNull(newutente);
		assertThat(newutente.getUsername()).isEqualTo("OronzoCanà555");
		assertThat(newutente.getId()).isEqualTo("1");
	}
	
	@Test
	void getAllUtentiTest() {
		List<Utente> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		
		Mockito.when(utenteRepository.findAll()).thenReturn(list);
		
		List<Utente> auto = utenteService.getUtenti();
		assertEquals(3, auto.size());
		assertNotNull(auto);
	}
	
	@Test
	void getUtenteByIdTest() {
		
		Mockito.when(utenteRepository.findById(anyLong())).thenReturn(Optional.of(a));
		Utente c = utenteService.getUtente(a.getId());
		assertNotNull(c);
		assertThat(c.getId()).isNotEqualTo(null);
	}
	
	@Test
	void deleteUtenteTest() {
		
		Long utenteId = 1L;
		when(utenteRepository.findById(anyLong())).thenReturn(Optional.of(a));
		doNothing().when(utenteRepository).delete(any(Utente.class));
		
		utenteService.deleteUtente(utenteId);
		
		verify(utenteRepository, times(1)).delete(a);
		
	}
}
