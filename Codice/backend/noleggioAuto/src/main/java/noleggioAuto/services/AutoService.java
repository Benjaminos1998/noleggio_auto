package noleggioAuto.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Auto;
import noleggioAuto.entities.TipologiaAuto;
import noleggioAuto.exception.AutoNonTrovataException;
import noleggioAuto.exception.TipologiaAutoNonValidaException;
import noleggioAuto.repository.AutoRepository;

@Service
public class AutoService {

	private final AutoRepository autoRepository;

	@Autowired
	public AutoService(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}

	public List<Auto> getAllAuto() {
		return autoRepository.findAll();
	}

	public Auto getAutoById(Long idAuto) throws AutoNonTrovataException {
		Optional<Auto> auto = this.autoRepository.findById(idAuto);
		if (auto.isPresent())
			return auto.get();
		else
			throw new AutoNonTrovataException("Auto non trovata con id " + idAuto);
	}

	public Auto getAutoByTarga(String targa) throws AutoNonTrovataException {
		Optional<Auto> auto = this.autoRepository.findAutoByTarga(targa);
		if (auto.isPresent())
			return auto.get();
		else
			throw new AutoNonTrovataException("Auto non trovata con targa " + targa);
	}

	public void addAuto(String targa, String modello, String tipologiaAuto) throws TipologiaAutoNonValidaException {
		Auto.controlloTarga(targa);
		//controllo tipologia Auto
		String tipologiaFormatted = tipologiaAuto.toLowerCase();
		if (!Arrays.stream(TipologiaAuto.values()).map(Enum::toString).map(String::toLowerCase)
				.collect(Collectors.toList()).contains(tipologiaFormatted)) {
			throw new TipologiaAutoNonValidaException("La tipologia " + tipologiaAuto + " non è valida");
		}
		Auto nuovaAuto = new Auto(null, targa, modello, TipologiaAuto.valueOf(tipologiaAuto));
		autoRepository.save(nuovaAuto);
	}

	public void deleteAuto(Long idAuto) {
		boolean exist = autoRepository.existsById(idAuto);
		if (!exist) {
			throw new IllegalStateException("L'auto con id " + idAuto + " non esiste");
		}
		autoRepository.deleteById(idAuto);
	}
}
