package noleggioAuto.controller;
/*import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import noleggioAuto.entities.Utente;
import noleggioAuto.services.UtenteService;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class UtenteControllerTest {
	
	@MockBean
    private UtenteService utenteService;

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup () {
		Utente e = new Utente();
		Utente b = new Utente();
		Utente c = new Utente();
	}

	
	@Test
	void addUtenteTest() throws Exception{
		String uri = "/utente";
		Utente u = new Utente(); 
		u.idUtente = (long) 1L;
		utenteService.getUtente(u.idUtente);
		Mockito.when(utenteService.getUtente(any())).thenReturn(u);

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
			        .content(objectMapper.writeValueAsString(u)))
			        .andExpect(status().isCreated())
			        .andDo(print());
	}
	@Test
	void deleteUtenteTest() throws Exception {
		String uri = "/utente";
		Utente u = new Utente();
		u.idUtente = (long)1L;
		
		Mockito.doNothing().when(utenteService).deleteUtente(anyLong());
		this.mockMvc.perform(delete("/utente/{id}", 1L))
		.andExpect(status().isNoContent());
	}
	@Test
	void getAllUtentiTest() throws Exception {
		Utente e = new Utente();
		Utente b = new Utente();
		Utente c = new Utente();
		List<Utente> list = new ArrayList<>();
		list.add(e);
		list.add(b);
		list.add(c);
		
		Mockito.when(utenteService.getUtenti()).thenReturn(list);
		this.mockMvc.perform(get("/utenti"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(list.size())));
	}
	
	@Test
	void getUtenteTest() throws Exception{
		    Utente e = new Utente();
		    e.idUtente = (long)1L;
			when(utenteService.getUtente(anyLong())).thenReturn(e);
			
			this.mockMvc.perform(get("/utente/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username", is(e.getUsername())));
	}

}*/

import noleggioAuto.DTO.UtenteDTO;
import noleggioAuto.entities.Utente;
import noleggioAuto.exception.*;
import noleggioAuto.services.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UtenteControllerTest {

    @Mock
    private UtenteService utenteService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UtenteController utenteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUtenti_EmptyList() {
        List<Utente> utenti = new ArrayList<>();

        when(utenteService.getUtenti()).thenReturn(utenti);

        ResponseEntity<?> response = utenteController.getUtenti();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("La lista Utenti è vuota", response.getBody());
    }

    @Test
    public void testGetUtenti_NonEmptyList() {
        List<Utente> utenti = new ArrayList<>();
        utenti.add(new Utente());

        when(utenteService.getUtenti()).thenReturn(utenti);

        ResponseEntity<?> response = utenteController.getUtenti();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(utenti, response.getBody());
    }

    @Test
    public void testGetUtenteById_ValidId() throws UtenteNonTrovatoException {
        Utente utente = new Utente();
        utente.idUtente=1L;

        when(utenteService.getUtente(1L)).thenReturn(utente);

        ResponseEntity<?> response = utenteController.getUtenteById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(utente, response.getBody());
    }

    @Test
    public void testGetUtenteById_InvalidId() throws UtenteNonTrovatoException {
        when(utenteService.getUtente(2L)).thenThrow(new UtenteNonTrovatoException());

        ResponseEntity<?> response = utenteController.getUtenteById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Utente con id 2 non è stato trovato.", response.getBody());
    }

    @Test
    public void testAddUtente_ValidUtente() throws EmailUtenteException, PasswordNonValidaException, PatenteUtenteException,
            EtaUtenteException, UtenteException {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setNome("John");
        utenteDTO.setCognome("Doe");
        utenteDTO.setEmail("john.doe@example.com");
        utenteDTO.setPassword("password");
        utenteDTO.setDataDiNascita("1990-01-01");
        utenteDTO.setNumeroPatente("ABC123");

        Utente utente = new Utente();
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(utenteDTO.getPassword());
        utente.setDataDiNascita(utenteDTO.getDataDiNascita());
        utente.setNumeroPatente(utenteDTO.getNumeroPatente());

        when(modelMapper.map(utenteDTO, Utente.class)).thenReturn(utente);
        doNothing().when(utenteService).addUtente(utente.getNome(), utente.getCognome(), utente.getEmail(),
                utente.getPassword(), utente.getDataDiNascita(), utente.getNumeroPatente());

        ResponseEntity<?> response = utenteController.addUtente(utenteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(utente, response.getBody());
        verify(utenteService).addUtente(utente.getNome(), utente.getCognome(), utente.getEmail(),
                utente.getPassword(), utente.getDataDiNascita(), utente.getNumeroPatente());
    }

    @Test
    public void testAddUtente_EmailUtenteException() throws EmailUtenteException, PasswordNonValidaException,
            PatenteUtenteException, EtaUtenteException, UtenteException {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setNome("John");
        utenteDTO.setCognome("Doe");
        utenteDTO.setEmail("john.doe@example.com");
        utenteDTO.setPassword("password");
        utenteDTO.setDataDiNascita("1990-01-01");
        utenteDTO.setNumeroPatente("ABC123");

        when(modelMapper.map(utenteDTO, Utente.class)).thenReturn(new Utente());
        doThrow(new EmailUtenteException()).when(utenteService).addUtente(any(), any(), any(), any(), any(), any());

        ResponseEntity<?> response = utenteController.addUtente(utenteDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Errore durante la creazione dell'utente: Username già inserito.", response.getBody());
    }

    @Test
    public void testAddUtente_PasswordNonValidaException() throws EmailUtenteException, PasswordNonValidaException,
            PatenteUtenteException, EtaUtenteException, UtenteException {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setNome("John");
        utenteDTO.setCognome("Doe");
        utenteDTO.setEmail("john.doe@example.com");
        utenteDTO.setPassword("pass");
        utenteDTO.setDataDiNascita("1990-01-01");
        utenteDTO.setNumeroPatente("ABC123");

        when(modelMapper.map(utenteDTO, Utente.class)).thenReturn(new Utente());
        doThrow(new PasswordNonValidaException()).when(utenteService).addUtente(any(), any(), any(), any(), any(), any());

        ResponseEntity<?> response = utenteController.addUtente(utenteDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Errore durante la creazione dell'utente: La password deve avere almeno 8 caratteri. ",
                response.getBody());
    }
}

