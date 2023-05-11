package noleggioAuto.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import noleggioAuto.entities.Auto;
import noleggioAuto.exception.AutoException;
import noleggioAuto.exception.AutoNonTrovataException;
import noleggioAuto.exception.AutoPresenteException;
import noleggioAuto.exception.TargaAutoNonValidaException;
import noleggioAuto.services.AutoService;

@RestController
@RequestMapping(path = "api/auto")
public class AutoController {

	private AutoService autoService;

	@Autowired
	public AutoController(AutoService autoService, ModelMapper modelMapper) {
		this.autoService = autoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAutoById(@PathVariable Long id) {
		try {
			return new ResponseEntity<Auto>(this.autoService.getAutoById(id), HttpStatus.OK);
		} catch (AutoNonTrovataException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Auto con id " + id + " non trovata");
		}
	}

	@GetMapping("/parcoAuto")
	public ResponseEntity<?> getAllAuto() {
		List<Auto> automobili = this.autoService.getAllAuto();
		if (!automobili.isEmpty())
			return new ResponseEntity<List<Auto>>(automobili, HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Non sono presenti automobili nella lista.");
	}

	@PostMapping("/addAuto")
	public ResponseEntity<?> addAuto(@RequestBody Auto auto) {
		try {
			this.autoService.addAuto(auto);
			return new ResponseEntity<Auto>(auto, HttpStatus.CREATED);
		} catch (TargaAutoNonValidaException e) {
			return new ResponseEntity<String>(
					"Errore nell'inserimento della targa. Deve rispettare la numerazione delle targhe italiane. (Esempio: AA000AA LETTERA|LETTERA|NUMERO|NUMERO|NUMERO|LETTERA|LETTERA ",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (AutoPresenteException e) {
			return new ResponseEntity<String>("Impossibile inserire un auto con una targa già in uso.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (AutoException e) {
			return new ResponseEntity<String>("Errore nella creazione dell'automobile.",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/deleteAuto/{id}")
	public ResponseEntity<String> deleteAuto(@PathVariable("id") Long id) {
		try {
			this.autoService.deleteAuto(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cancellazione avvenuta con successo.");
		} catch (AutoNonTrovataException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Errore nella cancellazione dell'automobile.");
		}
	}
}
