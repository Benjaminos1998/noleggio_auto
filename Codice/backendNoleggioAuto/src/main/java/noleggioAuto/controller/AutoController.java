package noleggioAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import noleggioAuto.entities.Auto;
import noleggioAuto.entities.Business;
import noleggioAuto.entities.Luxury;
import noleggioAuto.entities.Utilitaria;
import noleggioAuto.services.AutoService;

@RestController
@RequestMapping(path = "auto")
public class AutoController {

	private AutoService autoService;

	@Autowired
	public AutoController(AutoService autoService) {
		this.autoService = autoService;
	}

	@GetMapping(path = "automobili")
	public List<Auto> getAuto() {
		return autoService.getAuto();
	}

	@PostMapping(path = "addUtilitaria")
	public void RegisterNewAutoUtilitaria(@RequestBody Utilitaria auto) throws IllegalAccessException {
		autoService.addNewAuto(auto);
	}

	@PostMapping(path = "addBusiness")
	public void RegisterNewAutoBusiness(@RequestBody Business auto) throws IllegalAccessException {
		autoService.addNewAuto(auto);
	}

	@PostMapping(path = "addLuxury")
	public void RegisterNewAutoLuxury(@RequestBody Luxury auto) throws IllegalAccessException {
		autoService.addNewAuto(auto);
	}

	@DeleteMapping(path = "{id}")
	public void deleteAuto(@PathVariable("id") Integer id) {
		autoService.deleteAuto(id);
	}
}
