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
}
