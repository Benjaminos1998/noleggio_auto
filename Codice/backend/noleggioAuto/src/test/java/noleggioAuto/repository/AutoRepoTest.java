package noleggioAuto.repository;

import noleggioAuto.entities.Auto;
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

