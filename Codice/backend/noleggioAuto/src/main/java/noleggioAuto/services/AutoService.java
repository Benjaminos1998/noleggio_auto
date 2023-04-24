package noleggioAuto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Auto;
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
	
	public Auto getAutoById(Long idAuto) {
		try {
		return autoRepository.findById(idAuto);
		}catch() {
			
		}
	}

	public void addNewAuto(Auto auto) throws IllegalAccessException {
		auto.targa.toUpperCase();
		Optional<Auto> autoByTarga = autoRepository.findAutoByTarga(auto.getTarga());
		if (autoByTarga.isPresent()) {
			throw new IllegalAccessException("Auto già inserita");
		}
		if (!(auto.targa.length() == 7)) {
			throw new IllegalAccessException("Targa non valida");
		}

		String lettere1 = auto.targa.substring(0, 2);
		String lettere2 = auto.targa.substring(5, 7);
		String numeri = auto.targa.substring(2, 5);
		String lettere = lettere1.concat(lettere2);

		for (int i = 0; i < lettere.length(); i++) {
			if (!(Character.isLetter(lettere.charAt(i)) && !(Character.isLowerCase(lettere.charAt(i))))) {
				throw new IllegalAccessException("Targa non valida");
			}

		}
		for (int i = 0; i < numeri.length(); i++) {
			if (!(Character.isDigit(numeri.charAt(i)))) {
				throw new IllegalAccessException("Targa non valida");
			}
		}
		autoRepository.save(auto);
	}

	public void deleteAuto(Long idAuto) {
		boolean exist = autoRepository.existsById(idAuto);
		if (!exist) {
			throw new IllegalStateException("L'auto con id " + idAuto + " non esiste");
		}
		autoRepository.deleteById(idAuto);
	}
}
