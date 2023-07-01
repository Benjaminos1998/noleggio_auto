package noleggioAuto.repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import noleggioAuto.entities.Auto;

@DataJpaTest
public class AutoRepoTest {
	
	@Autowired
	private AutoRepository autoRepository;
	
	private Auto a;
	private Auto b;
	
	@BeforeEach
	void setup() {
		a = new Auto((long)1, "Ferrari", "ABCD321", null);
		b = new Auto((long)2, "Porsche", "UDFF2432", null);
	}
	
	@Test
	void saveAutoTest() {
		Auto newauto = autoRepository.save(a);
		assertNotNull(newauto);
		assertThat(newauto.getId()).isNotEqualTo(null);
	}
	
	@Test
	void getAllAutoTest() {
		autoRepository.save(a);
		autoRepository.save(b);
		
		List<Auto> list = autoRepository.findAll();
		
		assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(2, list.size());
	}
	
	void getAutoByIdTest() {
		autoRepository.save(a);
		
		Auto newauto = autoRepository.findById(a.getId()).get();
		
		assertNotNull(newauto);
		assertEquals("Ferrari", newauto.getModello());
		assertEquals("ABCD321", newauto.getTarga());
	}
	
    void getAutoByTargaTest() {
		
		autoRepository.save(a);
		autoRepository.save(b);
		
		List<Auto> list = autoRepository.findAutoByTargaList("ABCD321");
		
		assertNotNull(list);
		assertThat(list.size()).isEqualTo(1);
	}
    
   void deleteAutoTest() {
		
		autoRepository.save(a);
		Long id = a.getId();
		
		autoRepository.save(b);
		
		autoRepository.delete(a);
		
		List<Auto> list = autoRepository.findAll();
		
		Optional<Auto> auto = autoRepository.findById(id);
		
		assertEquals(1, list.size());
		assertThat(auto).isEmpty();
		
	}

}
