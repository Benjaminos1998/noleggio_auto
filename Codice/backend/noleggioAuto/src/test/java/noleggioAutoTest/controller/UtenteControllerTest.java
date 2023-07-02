package noleggioAutoTest.controller;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import noleggioAuto.controller.UtenteController;
import noleggioAuto.entities.Utente;
import noleggioAuto.services.UtenteService;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UtenteController.class)	
public class UtenteControllerTest {
	
	@MockBean
    private UtenteService utenteService;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	

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
			
			this.mockMvc.perform(get("/movies/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username", is(e.getUsername())));
	}

}
