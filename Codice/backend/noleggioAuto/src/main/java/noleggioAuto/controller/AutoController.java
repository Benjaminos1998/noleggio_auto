package noleggioAuto.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import noleggioAuto.services.AutoService;

@RestController
@RequestMapping(path = "auto")
public class AutoController {

	private AutoService autoService;

	@Autowired
	public AutoController(AutoService autoService) {
		this.autoService = autoService;
	}

	@DeleteMapping(path = "{id}")
	public void deleteAuto(@PathVariable("id") Long id) {
		autoService.deleteAuto(id);
	}
}
