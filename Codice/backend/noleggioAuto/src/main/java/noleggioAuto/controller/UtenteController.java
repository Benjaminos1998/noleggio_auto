package noleggioAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import noleggioAuto.entities.Utente;

import noleggioAuto.services.UtenteService;

@RestController
@RequestMapping(path = "utente")
public class UtenteController {

	private final UtenteService utenteService;

	@Autowired
	public UtenteController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@GetMapping(path = "utenti")
	public List<Utente> getUtenti() {
		return utenteService.getUtenti();
	}

	@PostMapping(path = "addUtente")
	public void RegisterNewUtente(@RequestBody Utente utente) throws IllegalAccessException {
		utenteService.addNewUtente(utente);
	}

	@DeleteMapping(path = "{id}")
	public void deleteUtente(@PathVariable Integer id) throws IllegalAccessException {
		utenteService.deleteUtente(id);
	}
}
