package noleggioAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import noleggioAuto.entities.UtenteRegistrato;
import noleggioAuto.services.UtenteRegistratoService;

@RestController
@RequestMapping("api/utentiRegistrati")
public class UtenteRegistratoController {

	private UtenteRegistratoService utenteRegistratoService;

	@Autowired
	public UtenteRegistratoController(UtenteRegistratoService utenteRegistratoService) {
		this.utenteRegistratoService = utenteRegistratoService;
	}

	@GetMapping("/")
	public ResponseEntity<?> getUtentiRegistrati() {
		List<UtenteRegistrato> utentiRegistrati = this.utenteRegistratoService.getUtentiRegistrati();
		if (utentiRegistrati.isEmpty())
			return new ResponseEntity<String>("La lista Utenti è vuota", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<UtenteRegistrato>>(utentiRegistrati, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUtenteById(@PathVariable Long idUtenteRegistrato) {
		UtenteRegistrato utenteRegistrato = this.utenteRegistratoService.getUtenteRegistrato(idUtenteRegistrato);
		if (utenteRegistrato == null) {
			return new ResponseEntity<>("Utente non trovato con id " + idUtenteRegistrato, HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<UtenteRegistrato>(utenteRegistrato, HttpStatus.OK);
	}

}
