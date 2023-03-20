package noleggioAuto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.UtenteVip;
import noleggioAuto.repository.UtenteVipRepository;

@Service
public class UtenteVipService {

	UtenteVipRepository utenteVipRepository;

	@Autowired
	public UtenteVipService(UtenteVipRepository utenteVipRepository) {
		this.utenteVipRepository = utenteVipRepository;
	}

	public List<UtenteVip> getUtentiVip() {
		return utenteVipRepository.findAll();
	}

	public void addNewUtenteVip(UtenteVip utenteVip) throws IllegalAccessException {
		Optional<UtenteVip> utenteByIdCarta = utenteVipRepository.findByIdCarta(utenteVip.getIdCarta());
		Optional<UtenteVip> utenteByNumeroPatente = utenteVipRepository.findByNumeroPatente(utenteVip.getNumeroPatente());
		if (utenteByIdCarta.isPresent()) {
			throw new IllegalAccessException("UtenteVip: Numero carta fedeltà già in uso");
		}
		if (utenteByNumeroPatente.isPresent()) {
			throw new IllegalAccessException("UtenteVip: Numero di patente già in uso");
		}
		if(utenteVip.getIdCarta() == null ) {
			throw new IllegalAccessException("Utente Vip non valido, non puoi inserire un valore null come numero di carta");
		}
		utenteVipRepository.save(utenteVip);
	}

	public void deleteUtenteVip(Integer id) throws IllegalAccessException {
		boolean exist = utenteVipRepository.existsById(id);
		if (!exist) {
			throw new IllegalAccessException("Utente con id " + id + " non esiste");
		}
		utenteVipRepository.deleteById(id);
	}

}
