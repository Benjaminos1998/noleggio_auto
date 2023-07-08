package noleggioAuto.controller;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import noleggioAuto.controller.AutoController;
import noleggioAuto.entities.Auto;
import noleggioAuto.services.AutoService;

import java.net.URI;
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

@WebMvcTest(AutoController.class)	
public class AutoControllerTest {
	
	@MockBean
    private AutoService autoService;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private HttpStatus http;
	
	@BeforeEach
	void setup () {

	}
	@Test
	void addAutoTest() throws Exception{
		String uri = "/addAuto";
		Auto u = new Auto((long)1, uri, uri, null); 
		Mockito.when(autoService.getAutoById(any())).thenReturn(u);

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
			        .content(objectMapper.writeValueAsString(u)))
			        .andExpect(status().isCreated())
			        .andDo(print());
	}
	@Test
	void deleteAutoTest() throws Exception {
		String uri = "/auto";
		Auto u = new Auto((long)1L, uri, uri, null);
		
		Mockito.doNothing().when(autoService).deleteAuto(anyLong());
		this.mockMvc.perform(delete("/auto/{id}", 1L))
		.andExpect(status().isNoContent());
	}
	@Test
	void getAllAutoTest() throws Exception {
		Auto e = new Auto((long)1, null, null, null);
		Auto b = new Auto((long)2, null, null, null);
		Auto c = new Auto((long)3, null, null, null);
		List<Auto> list = new ArrayList<>();
		list.add(e);
		list.add(b);
		list.add(c);
		
		Mockito.when(autoService.getAllAuto()).thenReturn(list);
		this.mockMvc.perform(get("/api/auto/parcoauto"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(list.size())));
	}
	
	@Test
	void getAutoTest() throws Exception{
		    Auto e = new Auto((long)1L, null, null, null);
			when(autoService.getAutoById(anyLong())).thenReturn(e);
			
			    verify(autoService,times(1)).getAutoById(anyLong());
                MvcResult mvcResult = this.mockMvc.perform(get("api/auto/{id}", 1L))
				.andExpect(status().isOk()).andReturn();
                assertEquals(HttpStatus.OK, mvcResult.getResponse().getStatus());
	}
}
