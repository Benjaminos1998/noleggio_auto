package noleggioAuto.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Auto;
import noleggioAuto.entities.Noleggio;
import noleggioAuto.entities.TipologiaNoleggio;
import noleggioAuto.entities.UtenteRegistrato;
import noleggioAuto.exception.AutoNonTrovataException;
import noleggioAuto.exception.DurataNoleggioNonValidaException;
import noleggioAuto.exception.NoleggioNonTrovatoException;
import noleggioAuto.exception.UtenteNonTrovatoException;
import noleggioAuto.repository.AutoRepository;
import noleggioAuto.repository.NoleggioRepository;
import noleggioAuto.repository.UtenteRegistratoRepository;

@Service
public class NoleggioService {

	private final NoleggioRepository noleggioRepository;
	private final AutoRepository autoRepository;
	private final UtenteRegistratoRepository utenteRegistratoRepository;

	@Autowired
	public NoleggioService(NoleggioRepository noleggioRepository, AutoRepository autoRepository,
			UtenteRegistratoRepository utenteRegistratoRepository) {
		this.noleggioRepository = noleggioRepository;
		this.autoRepository = autoRepository;
		this.utenteRegistratoRepository = utenteRegistratoRepository;
	}

	public List<Noleggio> getNoleggi() {
		return noleggioRepository.findAll();
	}

	public Noleggio getNoleggioById(Long idNoleggio) {
		Optional<Noleggio> noleggio = this.noleggioRepository.findNoleggioById(idNoleggio);
		if (noleggio.isPresent())
			return noleggio.get();
		else
			throw new NoleggioNonTrovatoException("Noleggio con id " + idNoleggio + " non trovato.");
	}

	public void addNoleggio(LocalDate dataInzio, LocalDate dataFine, double prezzo, Auto auto,
			UtenteRegistrato utenteRegistrato) {
		Noleggio noleggio = new Noleggio(dataInzio, dataFine, prezzo, null, auto, utenteRegistrato);
		//Verifo che non sia stato inserito un prezzo inferiore a zero. 
		Noleggio.controlloPrezzoNoleggio(noleggio.getPrezzo());
		// Verifico se l'auto e l'utente registrato esistono nel database
		this.autoRepository.findById(noleggio.getAuto().getIdAuto()).orElseThrow(() -> new AutoNonTrovataException(
				"Auto con id " + noleggio.getAuto().getIdAuto() + " non è presente nel DB."));
		this.utenteRegistratoRepository.findById(noleggio.getUtenteRegistrato().getIdUtente())
				.orElseThrow(() -> new UtenteNonTrovatoException(
						"Utente con id " + noleggio.getUtenteRegistrato().getIdUtente() + " non è presente nel DB."));

		NoleggioService.controlloDataInzioConDataFine(noleggio);
		// Imposto la tipologia del noleggio
		int durata = Noleggio.durataNoleggio(noleggio);
		if (durata >= 1 && durata <= 7)
			noleggio.setTipologiaNoleggio(TipologiaNoleggio.CarSharing);
		else if (durata > 7 && durata <= 90)
			noleggio.setTipologiaNoleggio(TipologiaNoleggio.BrevePeriodo);
		else if (durata < 90 && durata <= 365)
			noleggio.setTipologiaNoleggio(TipologiaNoleggio.LungoPerido);
		else
			throw new DurataNoleggioNonValidaException("Durata del noleggio non valida.");
		// Salvo il noleggio
		noleggioRepository.save(noleggio);
	}

	/**
	 * Meotodo che cancella un noleggio passando come parametro l'identificativo.
	 * 
	 * @param idNoleggio
	 */
	public void deleteNoleggio(Long idNoleggio) {
		boolean exist = noleggioRepository.existsById(idNoleggio);
		if (!exist) {
			throw new NoleggioNonTrovatoException("Noleggio con id " + idNoleggio + " non esiste");
		}
		noleggioRepository.deleteById(idNoleggio);
	}

	/**
	 * Metodo statico creato per verificare che la data di fine noleggio sia
	 * successiva alla data di inizio noleggio
	 * 
	 * @param noleggio
	 */
	private static void controlloDataInzioConDataFine(Noleggio noleggio) {
		if (noleggio.getDataInizio().isAfter(noleggio.getDataFine()))
			throw new IllegalArgumentException(
					"La data di fine noleggio deve essere successiva alla data di inizio noleggio");
	}
}
