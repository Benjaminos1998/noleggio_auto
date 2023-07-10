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
 * import noleggioAuto.entities.Noleggio;
 * 
 * @DataJpaTest public class NoleggioRepoTest {
 * 
 * @Autowired private NoleggioRepository noleggioRepository;
 * 
 * private Noleggio a; private Noleggio b;
 * 
 * @BeforeEach void setup() { a = new Noleggio(null, null, 0, null, null, null);
 * b = new Noleggio(null, null, 0, null, null, null);
 * 
 * }
 * 
 * @Test void saveNoleggioTest() { Noleggio newnoleggio =
 * noleggioRepository.save(a); assertNotNull(newnoleggio);
 * assertThat(newnoleggio.getId()).isNotEqualTo(null); }
 * 
 * @Test void getAllNoleggioTest() { noleggioRepository.save(a);
 * noleggioRepository.save(b);
 * 
 * List<Noleggio> list = noleggioRepository.findAll();
 * 
 * assertNotNull(list); assertThat(list).isNotNull(); assertEquals(2,
 * list.size()); }
 * 
 * void getNoleggioByIdTest() { noleggioRepository.save(a);
 * 
 * Noleggio newnoleggio = noleggioRepository.findById(a.getId()).get();
 * 
 * assertNotNull(newnoleggio); assertEquals("BrevePeriodo",
 * newnoleggio.getTipoNoleggio().toString()); }
 * 
 * void deleteNoleggioTest() {
 * 
 * noleggioRepository.save(a); Long id = a.getId();
 * 
 * noleggioRepository.save(b);
 * 
 * noleggioRepository.delete(a);
 * 
 * List<Noleggio> list = noleggioRepository.findAll();
 * 
 * Optional<Noleggio> nole = noleggioRepository.findById(id);
 * 
 * assertEquals(1, list.size()); assertThat(nole).isEmpty();
 * 
 * } }
 */

import noleggioAuto.entities.Noleggio;
import noleggioAuto.services.NoleggioService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class NoleggioRepoTest {

    @Mock
    private NoleggioRepository noleggioRepository;

    @InjectMocks
    private NoleggioService noleggioService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindNoleggioByAuto_ValidAutoId() {
        Long autoId = 1L;
        Noleggio noleggio = new Noleggio(null, null, 0, null, null, null);
        noleggio.idNoleggio = 1L;

        when(noleggioRepository.findNoleggioByAuto(autoId)).thenReturn(Optional.of(noleggio));

        Optional<Noleggio> result = noleggioRepository.findNoleggioByAuto(autoId);

        assertEquals(Optional.of(noleggio), result);
    }

    @Test
    public void testFindNoleggioByAuto_InvalidAutoId() {
        Long autoId = 2L;

        when(noleggioRepository.findNoleggioByAuto(autoId)).thenReturn(Optional.empty());

        Optional<Noleggio> result = noleggioRepository.findNoleggioByAuto(autoId);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testFindNoleggioByUtente_ValidUtenteId() {
        Long utenteId = 1L;
        Noleggio noleggio = new Noleggio(null, null, 0, null, null, null);
        noleggio.idNoleggio=1L;

        when(noleggioRepository.findNoleggioByUtente(utenteId)).thenReturn(Optional.of(noleggio));

        Optional<Noleggio> result = noleggioRepository.findNoleggioByUtente(utenteId);

        assertEquals(Optional.of(noleggio), result);
    }

    @Test
    public void testFindNoleggioByUtente_InvalidUtenteId() {
        Long utenteId = 2L;

        when(noleggioRepository.findNoleggioByUtente(utenteId)).thenReturn(Optional.empty());

        Optional<Noleggio> result = noleggioRepository.findNoleggioByUtente(utenteId);

        assertEquals(Optional.empty(), result);
    }
}
