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

import noleggioAuto.entities.Auto;
import noleggioAuto.repository.AutoRepository;

@ExtendWith(MockitoExtension.class)
public class AutoServiceTest {
	
	@Mock
	private AutoRepository autoRepository;
	
	@InjectMocks
	private AutoService autoService;
	
	private Auto a;
	private Auto b;
	@BeforeEach
	void setup() {
		a = new Auto((long)1, "Ferrari", null, null);
		b = new Auto((long)2, "Porsche", null, null);
	}
	
        void addAutoTest() {
		
		when(autoRepository.save(any(Auto.class))).thenReturn(a);
		
		Auto newauto = autoService.save(a);
		
		assertNotNull(newauto);
		assertThat(newauto.getModello()).isEqualTo("Ferrari");
		assertThat(newauto.getId()).isEqualTo("1");
	}
	
	@Test
	void getAllAutosTest() {
		List<Auto> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		
		Mockito.when(autoRepository.findAll()).thenReturn(list);
		
		List<Auto> auto = autoService.getAllAuto();
		assertEquals(2, auto.size());
		assertNotNull(auto);
	}
	
	@Test
	void getAutoById() {
		
		Mockito.when(autoRepository.findById(anyLong())).thenReturn(Optional.of(a));
		Auto c = autoService.getAutoById(a.getId());
		assertNotNull(c);
		assertThat(c.getId()).isNotEqualTo(null);
	}
	
	@Test
	void deleteAutoTest() {
		
		Long autoId = 1L;
		when(autoRepository.findById(anyLong())).thenReturn(Optional.of(a));
		doNothing().when(autoRepository).delete(any(Auto.class));
		
		autoService.deleteAuto(autoId);
		
		verify(autoRepository, times(1)).delete(a);
		
	}
}
