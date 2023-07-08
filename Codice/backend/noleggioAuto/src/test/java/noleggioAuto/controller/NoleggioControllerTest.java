package noleggioAuto.controller;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import noleggioAuto.controller.NoleggioController;
import noleggioAuto.entities.Noleggio;
import noleggioAuto.services.NoleggioService;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NoleggioController.class)	
public class NoleggioControllerTest {
	
	@MockBean
    private NoleggioService noleggioService;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup () {

	}
	@Test
	void addNoleggioTest() throws Exception{
		String uri = "api/noleggi";
		Noleggio u = new Noleggio(null, null, 0, null, null, null);
		Mockito.when(noleggioService.getNoleggioById(any())).thenReturn(u);

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
			        .content(objectMapper.writeValueAsString(u)))
			        .andExpect(status().isCreated())
			        .andDo(print());
	}
	@Test
	void deleteNoleggioTest() throws Exception {
		String uri = "/noleggio";
		Noleggio u = new Noleggio(null, null, 0, null, null, null); 
		
		Mockito.doNothing().when(noleggioService).deleteNoleggio(anyLong());
		this.mockMvc.perform(delete("/deleteNoleggio/{id}", 1L))
		.andExpect(status().isNoContent());
	}
	@Test
	void getAllNoleggiTest() throws Exception {
		Noleggio e = new Noleggio(null, null, 0, null, null, null); 
		Noleggio b = new Noleggio(null, null, 0, null, null, null); 
		Noleggio c = new Noleggio(null, null, 0, null, null, null); 
		List<Noleggio> list = new ArrayList<>();
		list.add(e);
		list.add(b);
		list.add(c);
		
		Mockito.when(noleggioService.getNoleggi()).thenReturn(list);
		this.mockMvc.perform(get("/noleggi"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(list.size())));
	}
	@Test
	void getNoleggioTest() throws Exception{
		Noleggio u = new Noleggio(null, null, 0, null, null, null); 
			when(noleggioService.getNoleggioById(anyLong())).thenReturn(u);
			
			this.mockMvc.perform(get("/auto/{id}", 1L))
				.andExpect(status().isOk());
	}
}
