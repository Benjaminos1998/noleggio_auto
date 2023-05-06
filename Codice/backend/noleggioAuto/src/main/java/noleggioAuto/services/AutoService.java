package noleggioAuto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Auto;
import noleggioAuto.exception.AutoException;
import noleggioAuto.exception.AutoNonTrovataException;
import noleggioAuto.exception.AutoPresenteException;
import noleggioAuto.repository.AutoRepository;

@Service
public class AutoService {

	private final AutoRepository autoRepository;

	@Autowired
	public AutoService(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}

	/**
	 * Metodo che restituisce la lista di automobili presenti.
	 * 
	 * @return la lista di auto
	 */
	public List<Auto> getAllAuto() {
		List<Auto> automobili = this.autoRepository.findAll();
		if (automobili.isEmpty())
			throw new AutoException("La lista delle automobili è vuota");
		return automobili;
	}

	/**
	 * Metodo che restituisce l'automobile passando l'identificativo come parametro.
	 * 
	 * @param idAuto, l'identificativo dell'automobile
	 * @return l'auto
	 */
	public Auto getAutoById(Long idAuto) {
		return this.autoRepository.findById(idAuto)
				.orElseThrow(() -> new AutoNonTrovataException("Auto non trovata con id " + idAuto));
	}

	/**
	 * Metodo che resituisce l'automobile passando la targa come paramentro.
	 * 
	 * @param targa dell'automobile
	 * @return l'auto
	 */
	public Auto getAutoByTarga(String targa) {
		Optional<Auto> auto = this.autoRepository.findAutoByTarga(targa);
		if (auto.isPresent())
			return auto.get();
		else
			throw new AutoNonTrovataException("Auto non trovata con targa " + targa);
	}

	/**
	 * Metodo che aggiunge un automobile. Per farlo è necessario inserire come
	 * parametri la targa dell'auto, il modello e scegliere la tipologia tra quelle
	 * presenti. Bisogna inserire una targa che rispetti la seguente sequenza:
	 * lettera|lettera|numero|numero|numero|lettera|lettera (esempio CA123DA).
	 * Altrimenti verrà lanciata un'eccezione. Bisogna inserire una tipologia di
	 * auto tra quelle disponibili che sono: 1)Utilitaria 2)Business 3)Luxury;
	 * altrimenti verrà lanciata un'eccezione
	 * 
	 * @param targa         dell'automobile
	 * @param modello       dell'automobile
	 * @param tipologiaAuto
	 */
	public void addAuto(Auto auto) {
		Auto.controlloTarga(auto.getTarga());
		Auto.controlloTipologiaAuto(auto.getTipoAuto().toString());

		Optional<Auto> autoByTarga = this.autoRepository.findAutoByTarga(auto.getTarga());
		if (autoByTarga.isPresent())
			throw new AutoPresenteException();
		this.autoRepository.save(auto);
	}

	/**
	 * Metodo che cancella un automobile
	 * 
	 * @param id dell'automobile
	 */
	public void deleteAuto(Long idAuto) {
		boolean exist = this.autoRepository.existsById(idAuto);
		if (!exist) {
			throw new AutoNonTrovataException("L'auto con id " + idAuto + " non esiste");
		}
		this.autoRepository.deleteById(idAuto);
	}
}
