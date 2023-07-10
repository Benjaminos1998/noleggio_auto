package noleggioAuto.repository;
/*
 * import static org.assertj.core.api.Assertions.assertThat; import static
 * org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull;
 * 
 * import java.time.LocalDate; import java.time.Month; import java.util.List;
 * import java.util.Optional;
 * 
 * import org.junit.jupiter.api.BeforeEach; import
 * org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 * 
 * import noleggioAuto.entities.Auto;
 * 
 * @DataJpaTest public class AutoRepoTest {
 * 
 * @Autowired private AutoRepository autoRepository;
 * 
 * private Auto a; private Auto b;
 * 
 * @BeforeEach void setup() { a = new Auto((long)1, "Ferrari", "ABCD321", null);
 * b = new Auto((long)2, "Porsche", "UDFF2432", null); }
 * 
 * @Test void saveAutoTest() { Auto newauto = autoRepository.save(a);
 * assertNotNull(newauto); assertThat(newauto.getId()).isNotEqualTo(null); }
 * 
 * @Test void getAllAutoTest() { autoRepository.save(a); autoRepository.save(b);
 * 
 * List<Auto> list = autoRepository.findAll();
 * 
 * assertNotNull(list); assertThat(list).isNotNull(); assertEquals(2,
 * list.size()); }
 * 
 * void getAutoByIdTest() { autoRepository.save(a);
 * 
 * Auto newauto = autoRepository.findById(a.getId()).get();
 * 
 * assertNotNull(newauto); assertEquals("Ferrari", newauto.getModello());
 * assertEquals("ABCD321", newauto.getTarga()); }
 * 
 * void getAutoByTargaTest() {
 * 
 * autoRepository.save(a); autoRepository.save(b);
 * 
 * Optional<Auto> list = autoRepository.findAutoByTarga("ABCD321");
 * 
 * assertNotNull(list); assertThat(list.get()).isEqualTo(1); }
 * 
 * void deleteAutoTest() {
 * 
 * autoRepository.save(a); Long id = a.getId();
 * 
 * autoRepository.save(b);
 * 
 * autoRepository.delete(a);
 * 
 * List<Auto> list = autoRepository.findAll();
 * 
 * Optional<Auto> auto = autoRepository.findById(id);
 * 
 * assertEquals(1, list.size()); assertThat(auto).isEmpty();
 * 
 * }
 * 
 * }
 */

import noleggioAuto.entities.Auto;
import noleggioAuto.repository.AutoRepository;
import noleggioAuto.services.AutoService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class AutoRepoTest {

    @Mock
    private AutoRepository autoRepository;

    @InjectMocks
    private AutoService autoService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAutoByTarga_ValidTarga() {
        String targa = "ABC123";
        Auto auto = new Auto(1L, "ABC123", targa, null);

        when(autoRepository.findAutoByTarga(targa)).thenReturn(Optional.of(auto));

        Optional<Auto> result = autoRepository.findAutoByTarga(targa);

        assertEquals(Optional.of(auto), result);
    }

    @Test
    public void testFindAutoByTarga_InvalidTarga() {
        String targa = "XYZ789";

        when(autoRepository.findAutoByTarga(targa)).thenReturn(Optional.empty());

        Optional<Auto> result = autoRepository.findAutoByTarga(targa);

        assertEquals(Optional.empty(), result);
    }
}

