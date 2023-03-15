package noleggioAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import noleggioAuto.entities.Noleggio;
import noleggioAuto.services.NoleggioService;

@RestController
@RequestMapping(path = "noleggio")
public class NoleggioController {

	private final NoleggioService noleggioService;
	
	@Autowired
	public NoleggioController(NoleggioService noleggioService) {
		this.noleggioService=noleggioService;
	}
	
	@GetMapping
	public List<Noleggio> getNoleggi(){
		return noleggioService.getNoleggi();
	}
	
	@PostMapping
	public void RegisterNewNoleggio(@RequestBody Noleggio noleggio) throws IllegalAccessException {
		noleggioService.addNoleggio(noleggio);
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteUtente(@PathVariable Integer id) throws IllegalAccessException {
		noleggioService.deleteUtente(id);
	}
}
