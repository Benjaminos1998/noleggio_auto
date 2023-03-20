package noleggioAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import noleggioAuto.entities.UtenteVip;
import noleggioAuto.services.UtenteVipService;

@RestController
@RequestMapping(path = "utenteVip")
public class UtenteVipController {

	private final UtenteVipService utenteVipService;

	@Autowired
	public UtenteVipController(UtenteVipService utenteVipService) {
		this.utenteVipService = utenteVipService;
	}

	@GetMapping(path = "utentiVip")
	public List<UtenteVip> getUtentiVip() {
		return utenteVipService.getUtentiVip();
	}

	@PostMapping(path = "addUtenteVip")
	public void RegisterNewUtenteVip(@RequestBody UtenteVip utenteVip) throws IllegalAccessException {
		utenteVipService.addNewUtenteVip(utenteVip);
	}

	@DeleteMapping(path = "{id}")
	public void deleteUtenteVip(@PathVariable Integer id) throws IllegalAccessException {
		utenteVipService.deleteUtenteVip(id);
	}
}
