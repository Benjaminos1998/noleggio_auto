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

import noleggioAuto.services.NoleggioService;
import noleggioAuto.entities.Noleggio;
import noleggioAuto.DTO.NoleggioDTO;
import noleggioAuto.exception.AutoNonDisponibilePerIlNoleggioException;
import noleggioAuto.exception.NoleggioException;
import noleggioAuto.exception.UtenteNonDisponeDelNoleggioException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        Noleggio noleggio = new Noleggio(null, null, 0, null, null, null);

        when(noleggioService.getNoleggioById(1L)).thenReturn(noleggio);

        ResponseEntity<?> response = noleggioController.getNoleggioById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(noleggio, response.getBody());
    }

    @Test
    public void testGetNoleggioById_InvalidId() {
        when(noleggioService.getNoleggioById(2L)).thenReturn(null);

        ResponseEntity<?> response = noleggioController.getNoleggioById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Noleggio non trovato", response.getBody());
    }

    @Test
    public void testGetNoleggi() throws NoleggioException {
        List<Noleggio> noleggi = new ArrayList<>();
        noleggi.add(new Noleggio(null, null, 0, null, null, null));

        when(noleggioService.getNoleggi()).thenReturn(noleggi);

        ResponseEntity<?> response = noleggioController.getNoleggi();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(noleggi, response.getBody());
    }

    @Test
    public void testGetNoleggi_NoleggioException() throws NoleggioException {
        when(noleggioService.getNoleggi()).thenThrow(new NoleggioException());

        ResponseEntity<?> response = noleggioController.getNoleggi();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Non è presente nessun noleggio.", response.getBody());
    }

    @Test
    public void testAddNoleggio_ValidNoleggio() throws NoleggioException, AutoNonDisponibilePerIlNoleggioException,
            UtenteNonDisponeDelNoleggioException {
        NoleggioDTO noleggioDTO = new NoleggioDTO();
        noleggioDTO.setDataInizio("2023-07-01");
        noleggioDTO.setDataFine("2023-07-05");
        noleggioDTO.setPrezzo(100.0);
        noleggioDTO.setIdAuto(1L);
        noleggioDTO.setIdUtenteRegistrato(1L);

        ResponseEntity<?> response = noleggioController.addNoleggio(noleggioDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(noleggioDTO.getDataInizio(), ((Noleggio) response.getBody()).getDataInizio());
        assertEquals(noleggioDTO.getDataFine(), ((Noleggio) response.getBody()).getDataFine());
        assertEquals(noleggioDTO.getPrezzo(), ((Noleggio) response.getBody()).getPrezzo());
        verify(noleggioService).addNoleggio(noleggioDTO.getDataInizio(), noleggioDTO.getDataFine(),
                noleggioDTO.getPrezzo(), noleggioDTO.getIdAuto(), noleggioDTO.getIdUtenteRegistrato());
    }

    @Test
    public void testAddNoleggio_AutoNonDisponibilePerIlNoleggioException() throws NoleggioException,
            AutoNonDisponibilePerIlNoleggioException, UtenteNonDisponeDelNoleggioException {
        NoleggioDTO noleggioDTO = new NoleggioDTO();
        noleggioDTO.setDataInizio("2023-07-01");
        noleggioDTO.setDataFine("2023-07-05");
        noleggioDTO.setPrezzo(100.0);
        noleggioDTO.setIdAuto(1L);
        noleggioDTO.setIdUtenteRegistrato(1L);

        when(noleggioService.addNoleggio(noleggioDTO.getDataInizio(), noleggioDTO.getDataFine(),
                noleggioDTO.getPrezzo(), noleggioDTO.getIdAuto(), noleggioDTO.getIdUtenteRegistrato()))
                .thenThrow(new AutoNonDisponibilePerIlNoleggioException());

        ResponseEntity<?> response = noleggioController.addNoleggio(noleggioDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Auto in uso. Scegliere un'altra automobile per completare l'operazione.", response.getBody());
    }
}
