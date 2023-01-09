package noleggioAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import noleggioAuto.entities.Utente;
import noleggioAuto.services.UtenteService;

@RestController
@RequestMapping(path = "utente")
public class UtenteController {

	private final UtenteService utenteService;
	
	@Autowired
	public UtenteController(UtenteService utenteService) {
		this.utenteService=utenteService;
	}
	
	@GetMapping
	public List<Utente> getUtenti(){
		return utenteService.getUtenti();
	}
	
	@PostMapping
	public void RegisterNewUtente(@RequestBody Utente utente) throws IllegalAccessException {
		utenteService.addNewUtente(utente);
	}
	
}
