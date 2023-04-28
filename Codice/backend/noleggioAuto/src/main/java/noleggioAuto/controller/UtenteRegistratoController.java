package noleggioAuto.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import noleggioAuto.DTO.UtenteRegistratoDto;
import noleggioAuto.entities.UtenteRegistrato;
import noleggioAuto.exception.UtenteException;
import noleggioAuto.services.UtenteRegistratoService;

@RestController
@RequestMapping("api/utentiRegistrati")
public class UtenteRegistratoController {

	private UtenteRegistratoService utenteRegistratoService;
	private ModelMapper modelMapper;

	@Autowired
	public UtenteRegistratoController(UtenteRegistratoService utenteRegistratoService, ModelMapper modelMapper) {
		this.utenteRegistratoService = utenteRegistratoService;
		this.modelMapper = modelMapper;
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
	public ResponseEntity<?> getUtenteById(@PathVariable Long id) {
		UtenteRegistrato utenteRegistrato = this.utenteRegistratoService.getUtenteRegistrato(id);
		if (utenteRegistrato == null) {
			return new ResponseEntity<>("Utente non trovato con id " + id, HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<UtenteRegistrato>(utenteRegistrato, HttpStatus.OK);
	}

	@PostMapping("/addUtente")
	public ResponseEntity<?> addUtenteRegistrato(@RequestBody UtenteRegistratoDto utenteRegistratoDto) {
		try {
			UtenteRegistrato utenteRegistrato = this.modelMapper.map(utenteRegistratoDto, UtenteRegistrato.class);
			this.utenteRegistratoService.addUtenteRegistrato(utenteRegistrato.getNome(), utenteRegistrato.getCognome(),
					utenteRegistrato.getUsername(), utenteRegistrato.getPassword(), utenteRegistrato.getDataDiNascita(),
					utenteRegistrato.getNumeroPatente());
			return new ResponseEntity<>(utenteRegistrato, HttpStatus.CREATED);
		} catch (UtenteException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la creazione dell'utente.");
		}
	}

	@DeleteMapping("/utente/{id}")
	public ResponseEntity<String> deleteUtente(@PathVariable Long id) {
		try {
			this.utenteRegistratoService.deleteUtenteRegistrato(id);
			return ResponseEntity.ok().body("Utente elimita con successo");
		} catch (UtenteException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la cancellazione dell'utente.");
		}
	}
}
