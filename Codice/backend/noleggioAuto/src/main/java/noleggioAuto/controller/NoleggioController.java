package noleggioAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import noleggioAuto.DTO.NoleggioDTO;
import noleggioAuto.entities.Noleggio;
import noleggioAuto.exception.AutoNonDisponibilePerIlNoleggioException;
import noleggioAuto.exception.NoleggioException;
import noleggioAuto.exception.UtenteNonDisponeDelNoleggioException;
import noleggioAuto.services.NoleggioService;

@RestController
@RequestMapping(path = "api/noleggi")
public class NoleggioController {

	private final NoleggioService noleggioService;

	@Autowired
	public NoleggioController(NoleggioService noleggioService) {
		this.noleggioService = noleggioService;
	}

	@GetMapping("/noleggio/{id}")
	public ResponseEntity<?> getNoleggioById(@PathVariable Long id) {
		if (this.noleggioService.getNoleggioById(id) != null)
			return new ResponseEntity<Noleggio>(this.noleggioService.getNoleggioById(id), HttpStatus.OK);
		else
			return new ResponseEntity<String>("Noleggio non trovato", HttpStatus.NOT_FOUND);
	}

	@GetMapping("")
	public ResponseEntity<?> getNoleggi() {
		try {
			return new ResponseEntity<List<Noleggio>>(this.noleggioService.getNoleggi(), HttpStatus.OK);
		} catch (NoleggioException e) {
			return new ResponseEntity<String>("Non è presente nessun noleggio.", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addNoleggio")
	public ResponseEntity<?> addNoleggio(@RequestBody NoleggioDTO noleggio) {
		try {
			this.noleggioService.addNoleggio(noleggio.getDataInizio(), noleggio.getDataFine(), noleggio.getPrezzo(),
					noleggio.idAuto, noleggio.getIdUtenteRegistrato());
			Noleggio noleggio1 = new Noleggio(noleggio.getDataInizio(), noleggio.getDataFine(), noleggio.getPrezzo());
			return new ResponseEntity<Noleggio>(noleggio1, HttpStatus.CREATED);
		} catch (AutoNonDisponibilePerIlNoleggioException e) {
			return new ResponseEntity<String>("Auto in uso. Scegliere un'altra automobile per completare l'operazione.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UtenteNonDisponeDelNoleggioException e) {
			return new ResponseEntity<String>("L'utente non può effettuare due noleggi contemporaneamente.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NoleggioException e) {
			return new ResponseEntity<String>("Errore durante la creazione di un nuovo noleggio",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/deleteNoleggio/{id}")
	public ResponseEntity<?> deleteNoleggio(@PathVariable Long id) {
		try {
			this.noleggioService.deleteNoleggio(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cancellazione del noleggio avvenuta con successo");
		} catch (NoleggioException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore nella cancellazione del noleggio.");
		}
	}
}
