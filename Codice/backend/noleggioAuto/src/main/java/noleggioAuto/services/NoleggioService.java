package noleggioAuto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Noleggio;
import noleggioAuto.entities.TipologiaNoleggio;
import noleggioAuto.repository.NoleggioRepository;

@Service
public class NoleggioService {

	private final NoleggioRepository noleggioRepository;

	@Autowired
	public NoleggioService(NoleggioRepository noleggioRepository) {
		this.noleggioRepository = noleggioRepository;
	}

	public List<Noleggio> getNoleggi() {
		return noleggioRepository.findAll();
	}

	public void addNoleggio(Noleggio noleggio) throws IllegalAccessException {
		Optional<Noleggio> noleggioById = noleggioRepository.findById(noleggio.getIdNoleggio());
		if (noleggioById.isPresent()) {
			throw new IllegalAccessException("Noleggio con id " + noleggio.getIdNoleggio() + " è già presente");
		}
		String carsharing = "CarSharing";
		String breveperiodo = "BrevePeriodo";
		String lungoperiodo = "LungoPeriodo";
		if (noleggio.getSceltaNoleggio().equalsIgnoreCase(carsharing)) {
			noleggio.noleggio = TipologiaNoleggio.CarSharing;
		} else if (noleggio.getSceltaNoleggio().equalsIgnoreCase(breveperiodo)) {
			noleggio.noleggio = TipologiaNoleggio.BrevePeriodo;
		} else if (noleggio.getSceltaNoleggio().equalsIgnoreCase(lungoperiodo)) {
			noleggio.noleggio = TipologiaNoleggio.LungoPerido;
		} else {
			throw new IllegalAccessException("Tipologia noleggio non valido");
		}
		if (noleggio.noleggio == null)
			throw new IllegalAccessException("Noleggio non valido");

		noleggioRepository.save(noleggio);
	}

	public void deleteUtente(Integer id) throws IllegalAccessException {
		boolean exist = noleggioRepository.existsById(id);
		if (!exist) {
			throw new IllegalAccessException("Utente con id " + id + " non esiste");
		}
		noleggioRepository.deleteById(id);
	}
}
