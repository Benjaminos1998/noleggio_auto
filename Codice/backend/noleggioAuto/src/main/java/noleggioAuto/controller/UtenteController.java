package noleggioAuto.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import noleggioAuto.DTO.UtenteDTO;
import noleggioAuto.entities.Utente;
import noleggioAuto.exception.EtaUtenteException;
import noleggioAuto.exception.PasswordNonValidaException;
import noleggioAuto.exception.PatenteUtenteException;
import noleggioAuto.exception.EmailUtenteException;
import noleggioAuto.exception.UtenteException;
import noleggioAuto.exception.UtenteNonTrovatoException;
import noleggioAuto.services.UtenteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/utenti")
public class UtenteController {

	private final UtenteService utenteService;
	private final ModelMapper modelMapper;


	@GetMapping("")
	public ResponseEntity<?> getUtenti() {
		List<Utente> utenti = this.utenteService.getUtenti();
		if (utenti.isEmpty())
			return new ResponseEntity<String>("La lista Utenti è vuota", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Utente>>(utenti, HttpStatus.OK);
	}

	@GetMapping("/utente/{id}")
	public ResponseEntity<?> getUtenteById(@PathVariable Long id) {
		try {
			return new ResponseEntity<Utente>(
					this.utenteService.getUtente(id), HttpStatus.OK);
		} catch (UtenteNonTrovatoException e) {
			return new ResponseEntity<String>("Utente con id " + id + " non è stato trovato.",
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addUtente")
	public ResponseEntity<?> addUtente(@RequestBody UtenteDTO utenteDTO) {
		try {
			Utente utente = this.modelMapper.map(utenteDTO, Utente.class);
			this.utenteService.addUtente(utente.getNome(), utente.getCognome(),
					utente.getEmail(), utente.getPassword(), utente.getDataDiNascita(),
					utente.getNumeroPatente());
			return new ResponseEntity<>(utente, HttpStatus.CREATED);
		} catch (EmailUtenteException e) {
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
			this.utenteService.deleteUtente(id);
			return ResponseEntity.ok().body("Utente eliminato con successo");
		} catch (UtenteException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore durante la cancellazione dell'utente.");
		}
	}
}
