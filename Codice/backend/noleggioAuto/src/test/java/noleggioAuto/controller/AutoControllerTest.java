package noleggioAuto.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import noleggioAuto.services.AutoService;
import noleggioAuto.entities.Auto;
import noleggioAuto.exception.AutoException;
import noleggioAuto.exception.AutoNonTrovataException;
import noleggioAuto.exception.AutoPresenteException;
import noleggioAuto.exception.TargaAutoNonValidaException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AutoControllerTest {

    @Mock
    private AutoService autoService;

    @InjectMocks
    private AutoController autoController;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAutoById_ValidId() {
        Auto auto = new Auto((long)1, null, null, null);

        when(autoService.getAutoById(1L)).thenReturn(auto);

        ResponseEntity<?> response = autoController.getAutoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(auto, response.getBody());
    }

    @Test
    public void testGetAutoById_InvalidId() {
        when(autoService.getAutoById(2L)).thenThrow(new AutoNonTrovataException());

        ResponseEntity<?> response = autoController.getAutoById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Auto con id 2 non trovata", response.getBody());
    }

    @Test
    public void testGetAllAuto() {
        List<Auto> autos = new ArrayList<>();
        autos.add(new Auto((long)1, null, null, null));

        when(autoService.getAllAuto()).thenReturn(autos);

        List<Auto> result = autoController.getAllAuto();

        assertEquals(autos, result);
    }

    @Test
    public void testAddAuto_ValidAuto() {
        Auto auto = new Auto((long)1, null, null, null);

        ResponseEntity<?> response = autoController.addAuto(auto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(auto, response.getBody());
        verify(autoService).addAuto(auto);
    }

    @Test
    public void testAddAuto_InvalidTarga() {
        Auto auto = new Auto((long)1, null, null, null);
        when(autoService.addAuto(auto)).thenThrow(new TargaAutoNonValidaException());

        ResponseEntity<?> response = autoController.addAuto(auto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Errore nell'inserimento della targa...", response.getBody());
    }

    @Test
    public void testAddAuto_DuplicateTarga() {
        Auto auto = new Auto((long)1, null, null, null);
        when(autoService.addAuto(auto)).thenThrow(new AutoPresenteException());

        ResponseEntity<?> response = autoController.addAuto(auto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Impossibile inserire un auto con una targa già in uso.", response.getBody());
    }

    @Test
    public void testAddAuto_GenericException() {
        Auto auto = new Auto((long)1, null, null, null);
        when(autoService.addAuto(auto)).thenThrow(new AutoException());

        ResponseEntity<?> response = autoController.addAuto(auto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Errore nella creazione dell'automobile.", response.getBody());
    }

    @Test
    public void testDeleteAuto_ValidId() {
        autoController.deleteAuto(1L);

        verify(autoService).deleteAuto(1L);
    }

    @Test
    public void testDeleteAuto_InvalidId() {
        assertThrows(AutoNonTrovataException.class, () -> autoController.deleteAuto(2L));
    }
}
