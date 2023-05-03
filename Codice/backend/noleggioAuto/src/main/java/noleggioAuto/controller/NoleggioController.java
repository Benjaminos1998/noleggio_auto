//package noleggioAuto.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import noleggioAuto.entities.Noleggio;
//import noleggioAuto.services.NoleggioService;
//
//@RestController
//@RequestMapping(path = "api/noleggio")
//public class NoleggioController {
//
//	private final NoleggioService noleggioService;
//
//	@Autowired
//	public NoleggioController(NoleggioService noleggioService) {
//		this.noleggioService = noleggioService;
//	}
//
//	@GetMapping(path = "noleggi")
//	public List<Noleggio> getNoleggi() {
//		return noleggioService.getNoleggi();
//	}
//
//	@PostMapping(path = "add")
//	public void RegisterNewNoleggio(@RequestBody Noleggio noleggio) {
//		noleggioService.addNoleggio(noleggio.getDataInizio(), noleggio.getDataFine(), noleggio.getPrezzo(),
//				noleggio.getAuto(), noleggio.getUtenteRegistrato());
//	}
//
//	@DeleteMapping(path = "{id}")
//	public void deleteUtente(@PathVariable Long id) throws IllegalAccessException {
//		noleggioService.deleteNoleggio(id);
//	}
//}
