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
 * import noleggioAuto.entities.Utente;
 * 
 * @DataJpaTest public class UtenteRepoTest {
 * 
 * @Autowired private UtenteRepository utenteRepository;
 * 
 * private Utente a; private Utente b;
 * 
 * @BeforeEach void setup() { a = new Utente(); b = new Utente();
 * a.idUtente=(long)1; b.idUtente=(long)2; a.nome = "Alex"; b.nome = "Ben";
 * 
 * }
 * 
 * @Test void saveUtenteTest() { Utente newutente = utenteRepository.save(a);
 * assertNotNull(newutente); assertThat(newutente.getId()).isNotEqualTo(null); }
 * 
 * @Test void getAllUtenteTest() { utenteRepository.save(a);
 * utenteRepository.save(b);
 * 
 * List<Utente> list = utenteRepository.findAll();
 * 
 * assertNotNull(list); assertThat(list).isNotNull(); assertEquals(2,
 * list.size()); }
 * 
 * void getUtenteByIdTest() { utenteRepository.save(a);
 * 
 * Utente newutente = utenteRepository.findById(a.getId()).get();
 * 
 * assertNotNull(newutente); assertEquals("Alex", newutente.getNome()); }
 * 
 * void deleteUtenteTest() {
 * 
 * utenteRepository.save(a); Long id = a.getId();
 * 
 * utenteRepository.save(b);
 * 
 * utenteRepository.delete(a);
 * 
 * List<Utente> list = utenteRepository.findAll();
 * 
 * Optional<Utente> u = utenteRepository.findById(id);
 * 
 * assertEquals(1, list.size()); assertThat(u).isEmpty();
 * 
 * } }
 */

import noleggioAuto.entities.Utente;
import noleggioAuto.services.UtenteService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class UtenteRepoTest {

    @Mock
    private UtenteRepository utenteRepository;

    @InjectMocks
    private UtenteService utenteService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByNumeroPatente_ValidNumeroPatente() {
        String numeroPatente = "ABC123";
        Utente utente = new Utente();
        utente.idUtente=1L;

        when(utenteRepository.findByNumeroPatente(numeroPatente)).thenReturn(Optional.of(utente));

        Optional<Utente> result = utenteRepository.findByNumeroPatente(numeroPatente);

        assertEquals(Optional.of(utente), result);
    }

    @Test
    public void testFindByNumeroPatente_InvalidNumeroPatente() {
        String numeroPatente = "XYZ789";

        when(utenteRepository.findByNumeroPatente(numeroPatente)).thenReturn(Optional.empty());

        Optional<Utente> result = utenteRepository.findByNumeroPatente(numeroPatente);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testFindByEmail_ValidEmail() {
        String email = "abcde@esempio.it";
        Utente utente = new Utente();
        utente.idUtente=1L;

        when(utenteRepository.findByEmail(email)).thenReturn(Optional.of(utente));

        Optional<Utente> result = utenteRepository.findByEmail(email);

        assertEquals(Optional.of(utente), result);
    }

    @Test
    public void testFindByEmail_InvalidEmail() {
        String email = "abcde@esempio.it";

        when(utenteRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Utente> result = utenteRepository.findByEmail(email);

        assertEquals(Optional.empty(), result);
    }
}

