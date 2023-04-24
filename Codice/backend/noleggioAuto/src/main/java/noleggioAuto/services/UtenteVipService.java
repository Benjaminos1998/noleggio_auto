package noleggioAuto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.UtenteRegistrato;
import noleggioAuto.repository.UtenteRegistratoRepository;

@Service
public class UtenteVipService {

	UtenteRegistratoRepository utenteVipRepository;

	@Autowired
	public UtenteVipService(UtenteRegistratoRepository utenteVipRepository) {
		this.utenteVipRepository = utenteVipRepository;
	}

	public List<UtenteRegistrato> getUtentiVip() {
		return utenteVipRepository.findAll();
	}

	public void addNewUtenteVip(UtenteRegistrato utenteVip) throws IllegalAccessException {
		Optional<UtenteRegistrato> utenteByIdCarta = utenteVipRepository.findByIdCarta(utenteVip.getIdCarta());
		Optional<UtenteRegistrato> utenteByNumeroPatente = utenteVipRepository.findByNumeroPatente(utenteVip.getNumeroPatente());
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
