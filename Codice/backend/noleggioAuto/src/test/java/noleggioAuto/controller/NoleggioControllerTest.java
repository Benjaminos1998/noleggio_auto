package noleggioAuto.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import noleggioAuto.services.NoleggioService;
import noleggioAuto.entities.*;
import noleggioAuto.DTO.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoleggioControllerTest {

    @Mock
    private NoleggioService noleggioService;

    @InjectMocks
    private NoleggioController noleggioController;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNoleggioById_ValidId() {
        Long noleggioId = 1L;
        Noleggio e = new Noleggio(null, null, 0);

        when(noleggioService.getNoleggioById(eq(noleggioId))).thenReturn(e);

        ResponseEntity<?> response = noleggioController.getNoleggioById(noleggioId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(e, response.getBody());
    }

    @Test
    public void testGetNoleggioById_InvalidId() {
        Long noleggioId = 1L;

        when(noleggioService.getNoleggioById(eq(noleggioId))).thenReturn(null);

        ResponseEntity<?> response = noleggioController.getNoleggioById(noleggioId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Noleggio non trovato", response.getBody());
    }

    @Test
    public void testGetNoleggi() {
        List<Noleggio> e = new ArrayList<>();

        when(noleggioService.getNoleggi()).thenReturn(e);

        ResponseEntity<?> response = noleggioController.getNoleggi();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(e, response.getBody());
    }

    @Test
    public void testAddNoleggio() {
        NoleggioDTO noleggioDTO = new NoleggioDTO();
        Noleggio e = new Noleggio(null, null, 0);

        when(noleggioService.getNoleggioById((anyLong()))).thenReturn(e);

        ResponseEntity<?> response = noleggioController.addNoleggio(noleggioDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(e, response.getBody());
    }

    @Test
    public void testDeleteNoleggio() {
        Long noleggioId = 1L;

        ResponseEntity<?> response = noleggioController.deleteNoleggio(noleggioId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cancellazione del noleggio avvenuta con successo", response.getBody());
    }
}

