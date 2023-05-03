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
import noleggioAuto.exception.EtaUtenteException;
import noleggioAuto.exception.PasswordNonValidaException;
import noleggioAuto.exception.PatenteUtenteException;
import noleggioAuto.exception.UsernameUtenteException;
import noleggioAuto.exception.UtenteException;
import noleggioAuto.exception.UtenteNonTrovatoException;
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

	@GetMapping("")
	public ResponseEntity<?> getUtentiRegistrati() {
		List<UtenteRegistrato> utentiRegistrati = this.utenteRegistratoService.getUtentiRegistrati();
		if (utentiRegistrati.isEmpty())
			return new ResponseEntity<String>("La lista Utenti è vuota", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<UtenteRegistrato>>(utentiRegistrati, HttpStatus.OK);
	}

	@GetMapping("/utente/{id}")
	public ResponseEntity<?> getUtenteById(@PathVariable Long id) {
		try {
			return new ResponseEntity<UtenteRegistrato>(
					this.utenteRegistratoService.getUtenteRegistrato(id), HttpStatus.OK);
		} catch (UtenteNonTrovatoException e) {
			return new ResponseEntity<String>("Utente con id " + id + " non è stato trovato.",
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addUtente")
	public ResponseEntity<?> addUtenteRegistrato(@RequestBody UtenteRegistratoDto utenteRegistratoDto) {
		try {
			UtenteRegistrato utenteRegistrato = this.modelMapper.map(utenteRegistratoDto, UtenteRegistrato.class);
			this.utenteRegistratoService.addUtenteRegistrato(utenteRegistrato.getNome(), utenteRegistrato.getCognome(),
					utenteRegistrato.getUsername(), utenteRegistrato.getPassword(), utenteRegistrato.getDataDiNascita(),
					utenteRegistrato.getNumeroPatente());
			return new ResponseEntity<>(utenteRegistrato, HttpStatus.CREATED);
		} catch (UsernameUtenteException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la creazione dell'utente: Username già inserito.");
		}catch (PasswordNonValidaException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la creazione dell'utente: La password deve avere almeno 8 caratteri. ");
		}catch (PatenteUtenteException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la creazione dell'utente: Numero di patente già inserito.");
		}catch (EtaUtenteException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la creazione dell'utente: L'utente deve avere almeno 18 anni ");
		}catch (UtenteException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la creazione dell'utente.");
		}
	}

	@DeleteMapping("/deleteUtente/{id}")
	public ResponseEntity<String> deleteUtente(@PathVariable Long id) {
		try {
			this.utenteRegistratoService.deleteUtenteRegistrato(id);
			return ResponseEntity.ok().body("Utente eliminato con successo");
		} catch (UtenteException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la cancellazione dell'utente.");
		}
	}
}
