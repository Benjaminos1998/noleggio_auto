package noleggioAuto.controller;

/*
 * import static org.hamcrest.CoreMatchers.is; import
 * org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import
 * org.mockito.Mockito; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.mock.mockito.MockBean; import
 * org.springframework.test.web.servlet.MockMvc;
 * 
 * import static org.mockito.ArgumentMatchers.any; import static
 * org.mockito.ArgumentMatchers.anyLong; import static org.mockito.Mockito.when;
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * import noleggioAuto.entities.Noleggio; import
 * noleggioAuto.services.NoleggioService; import java.util.ArrayList; import
 * java.util.List;
 * 
 * import org.springframework.http.MediaType; import static
 * org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
 * import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
 * 
 * import com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * @WebMvcTest(NoleggioController.class) public class NoleggioControllerTest {
 * 
 * @MockBean private NoleggioService noleggioService;
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Autowired private ObjectMapper objectMapper;
 * 
 * @BeforeEach void setup () {
 * 
 * }
 * 
 * @Test void addNoleggioTest() throws Exception{ String uri = "api/noleggi";
 * Noleggio u = new Noleggio(null, null, 0, null, null, null);
 * Mockito.when(noleggioService.getNoleggioById(any())).thenReturn(u);
 * 
 * mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
 * .content(objectMapper.writeValueAsString(u)))
 * .andExpect(status().isCreated()) .andDo(print()); }
 * 
 * @Test void deleteNoleggioTest() throws Exception { String uri = "/noleggio";
 * Noleggio u = new Noleggio(null, null, 0, null, null, null);
 * 
 * Mockito.doNothing().when(noleggioService).deleteNoleggio(anyLong());
 * this.mockMvc.perform(delete("/deleteNoleggio/{id}", 1L))
 * .andExpect(status().isNoContent()); }
 * 
 * @Test void getAllNoleggiTest() throws Exception { Noleggio e = new
 * Noleggio(null, null, 0, null, null, null); Noleggio b = new Noleggio(null,
 * null, 0, null, null, null); Noleggio c = new Noleggio(null, null, 0, null,
 * null, null); List<Noleggio> list = new ArrayList<>(); list.add(e);
 * list.add(b); list.add(c);
 * 
 * Mockito.when(noleggioService.getNoleggi()).thenReturn(list);
 * this.mockMvc.perform(get("/noleggi")) .andExpect(status().isOk())
 * .andExpect(jsonPath("$.size()", is(list.size()))); }
 * 
 * @Test void getNoleggioTest() throws Exception{ Noleggio u = new
 * Noleggio(null, null, 0, null, null, null);
 * when(noleggioService.getNoleggioById(anyLong())).thenReturn(u);
 * 
 * this.mockMvc.perform(get("/noleggio/{id}", 1L)) .andExpect(status().isOk());
 * } }
 */

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

