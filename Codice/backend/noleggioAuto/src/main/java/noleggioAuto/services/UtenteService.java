package noleggioAuto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Utente;
import noleggioAuto.repository.UtenteRepository;

@Service
public class UtenteService {

	UtenteRepository utenteRepository;

	@Autowired
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	public List<Utente> getUtenti() {
		return utenteRepository.findAll();
	}
	

	public void addNewUtente(Utente utente) throws IllegalAccessException {
		Optional<Utente> utenteByNumeroPatente = utenteRepository.findByNumeroPatente(utente.getNumeroPatente());
		if (utenteByNumeroPatente.isPresent()) {
			throw new IllegalAccessException("Utente già registrato");
		}
		utenteRepository.save(utente);
	}

	public void deleteUtente(Integer id) throws IllegalAccessException {
		boolean exist = utenteRepository.existsById(id);
		if (!exist) {
			throw new IllegalAccessException("Utente con id " + id + " non esiste");
		}
		utenteRepository.deleteById(id);
	}
}
