package noleggioAuto.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import noleggioAuto.DTO.AutoDto;
import noleggioAuto.entities.Auto;
import noleggioAuto.exception.AutoException;
import noleggioAuto.exception.AutoNonTrovataException;
import noleggioAuto.services.AutoService;

@RestController
@RequestMapping(path = "/auto")
public class AutoController {

	private AutoService autoService;
	private ModelMapper modelMapper;

	@Autowired
	public AutoController(AutoService autoService, ModelMapper modelMapper) {
		this.autoService = autoService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/auto/{id}")
	public ResponseEntity<?> getAutoById(@PathVariable Long id) {
		try {
			return new ResponseEntity<Auto>(this.autoService.getAutoById(id), HttpStatus.OK);
		} catch (AutoNonTrovataException e) {
			return new ResponseEntity<String>("Auto con id " + id + " ,non Trovata", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllAuto() {
		List<Auto> automobili = this.autoService.getAllAuto();
		if (automobili.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Non sono presenti automobili nella lista.");
		else
			return new ResponseEntity<List<Auto>>(automobili, HttpStatus.OK);
	}

	@PostMapping("/addAuto")
	public ResponseEntity<?> addAuto(@RequestBody AutoDto autoDto) {
		try {
			Auto auto = modelMapper.map(autoDto, Auto.class);
			return new ResponseEntity<Auto>(auto, HttpStatus.CREATED);
		} catch (AutoException e) {
			return new ResponseEntity<String>("Errore nella creazione dell'automobile.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
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
