package noleggioAuto.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import noleggioAuto.services.AutoService;
import noleggioAuto.entities.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Long autoId = 1L;
        Auto expectedAuto = new Auto(1L, null, null, null);

        when(autoService.getAutoById(eq(autoId))).thenReturn(expectedAuto);

        ResponseEntity<?> response = autoController.getAutoById(autoId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAuto, response.getBody());
    }

    @Test
    public void testGetAllAuto() {
        List<Auto> expectedAutoList = new ArrayList<>();

        when(autoService.getAllAuto()).thenReturn(expectedAutoList);

        List<Auto> response = autoController.getAllAuto();
        assertEquals(expectedAutoList, response);
    }

    @Test
    public void testAddAuto() {
        Auto auto = new Auto(1L, null, null, null);
        Auto e = new Auto(1L, null, null, null);

        when(autoService.getAutoById((anyLong()))).thenReturn(e);

        ResponseEntity<?> response = autoController.addAuto(auto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(e, response.getBody());
    }

    @Test
    public void testDeleteAuto() {
        Long autoId = 1L;

        autoController.deleteAuto(autoId);

        verify(autoService).deleteAuto(autoId);
    }
}

