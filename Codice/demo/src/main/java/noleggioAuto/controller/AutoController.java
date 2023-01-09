package noleggioAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import noleggioAuto.entities.Auto;
import noleggioAuto.services.AutoService;

@RestController
@RequestMapping(path = "auto")
public class AutoController {

	
	private AutoService autoService;

	@Autowired
	public AutoController(AutoService autoService) {
		this.autoService = autoService;
	}
	
	
	@GetMapping
	public List<Auto> getAuto(){
		return autoService.getAuto();
	}
	
	@PostMapping
	public void RegisterNewAutoUtilitaria(@RequestBody Auto auto) throws IllegalAccessException {
		autoService.addNewAuto(auto);
	}
	
	@DeleteMapping(path = "{targa}")
	public void deleteAuto(@PathVariable("targa")String targa) {
		autoService.deleteAuto(targa);
	}
	
	
	

	

}
