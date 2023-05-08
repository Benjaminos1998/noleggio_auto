package noleggioAuto.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Auto;
import noleggioAuto.entities.Noleggio;
import noleggioAuto.entities.TipologiaNoleggio;
import noleggioAuto.entities.Utente;
import noleggioAuto.exception.AutoNonDisponibilePerIlNoleggioException;
import noleggioAuto.exception.AutoNonTrovataException;
import noleggioAuto.exception.DurataNoleggioNonValidaException;
import noleggioAuto.exception.NoleggioException;
import noleggioAuto.exception.NoleggioNonTrovatoException;
import noleggioAuto.exception.UtenteNonDisponeDelNoleggioException;
import noleggioAuto.exception.UtenteNonTrovatoException;
import noleggioAuto.repository.AutoRepository;
import noleggioAuto.repository.NoleggioRepository;
import noleggioAuto.repository.UtenteRepository;

@Service
public class NoleggioService {

	private final NoleggioRepository noleggioRepository;
	private final AutoRepository autoRepository;
	private final UtenteRepository utenteRepository;

	@Autowired
	public NoleggioService(NoleggioRepository noleggioRepository, AutoRepository autoRepository,
			UtenteRepository utenteRepository) {
		this.noleggioRepository = noleggioRepository;
		this.autoRepository = autoRepository;
		this.utenteRepository = utenteRepository;
	}

	public List<Noleggio> getNoleggi() {
		List<Noleggio> noleggi = this.noleggioRepository.findAll();
		if (noleggi.isEmpty())
			throw new NoleggioException();
		return noleggi;
	}

	public Noleggio getNoleggioById(Long idNoleggio) {
		Optional<Noleggio> noleggio = this.noleggioRepository.findById(idNoleggio);
		if (noleggio.isPresent())
			return noleggio.get();
		else
			throw new NoleggioNonTrovatoException("Noleggio con id " + idNoleggio + " non trovato.");
	}

	public void addNoleggio(LocalDate dataInzio, LocalDate dataFine, double prezzo, Long idAuto,
			Long idUtente) {
		Optional<Auto> auto = this.autoRepository.findById(idAuto);
		Optional<Utente> utente = this.utenteRepository.findById(idUtente);

		Noleggio noleggio = new Noleggio(dataInzio, dataFine, prezzo, null, auto.get(), utente.get());

		// TODO: Controllo se l'utente è iscritto al programma

		int numeroPunti = noleggio.getUtente().getNumeroPunti();
		numeroPunti = (int) (numeroPunti + prezzo);

		// Verifo che non sia stato inserito un prezzo inferiore a zero.
		Noleggio.controlloPrezzoNoleggio(noleggio.getPrezzo());

		// Verifico se l'auto e l'utente registrato esistono nel database
		autoEsiste(noleggio);
		esisteUtenteRegistrato(noleggio);

		// Controllo se la data di fine noleggio sia successiva alla data di inizio
		// noleggio
		NoleggioService.controlloDataInzioConDataFine(noleggio);

		// Imposto la tipologia del noleggio e aggiorno il numero di punti in base al
		// noleggio scelto.
		int durata = Noleggio.durataNoleggio(noleggio);
		if (durata >= 1 && durata <= 7) {
			noleggio.setTipologiaNoleggio(TipologiaNoleggio.CarSharing);
			numeroPunti = numeroPunti + noleggio.getTipologiaNoleggio().getValore();
		} else if (durata > 7 && durata <= 90) {
			noleggio.setTipologiaNoleggio(TipologiaNoleggio.BrevePeriodo);
			numeroPunti = numeroPunti + noleggio.getTipologiaNoleggio().getValore();
		} else if (durata > 90 && durata <= 365) {
			noleggio.setTipologiaNoleggio(TipologiaNoleggio.LungoPerido);
			numeroPunti = numeroPunti + noleggio.getTipologiaNoleggio().getValore();
		} else
			throw new DurataNoleggioNonValidaException("Durata del noleggio non valida.");

		// Controllo che l'utente non abbia nessun noleggio in corso
		if (noleggio.getUtente().isNoleggioInCorso())
			throw new UtenteNonDisponeDelNoleggioException();

		// Controllo che l'auto sia disponibile
		if (noleggio.getAuto().isInUso())
			throw new AutoNonDisponibilePerIlNoleggioException();
	
		// Se l'auto è disponibile, aggiorno che l'auto non è più libera ma noleggiata
		updateAutoInUso(auto.get(), true);

		// Aggiorno che l'utente ha affettuato un noleggio
		updateUtenteNoleggioAttivo(utente.get(), true);

		// Aggiorno i punti dell'utente
		updateUtenteNumeroPunti(utente.get(), numeroPunti);

		// Salvo il noleggio
		noleggioRepository.save(noleggio);
	}

	/**
	 * Metodo che aggiorna se l'utente ha un noleggio in corso
	 * 
	 * @param utenteRegistrato
	 * @param noleggioAttivo
	 */
	private void updateUtenteNoleggioAttivo(Utente utente, boolean noleggioAttivo) {
		utente.setNoleggioInCorso(noleggioAttivo);
		this.utenteRepository.save(utente);
	}

	/**
	 * Metodo che aggiorna il numero di punti di un utente registrato che è iscritto
	 * al programma fedeltà.
	 * 
	 * @param utente      registrato
	 * @param numeroPunti di punti che vengono aggiunti all'utente
	 */
	private void updateUtenteNumeroPunti(Utente utente, int numeroPunti) {
		utente.setNumeroPunti(numeroPunti);
		this.utenteRepository.save(utente);
	}

	/**
	 * Metodo che aggiorna se l'automobile è in uso oppure no.
	 * 
	 * @param auto
	 * @param inUso
	 */
	private void updateAutoInUso(Auto auto, boolean inUso) {
		auto.setInUso(inUso);
		this.autoRepository.save(auto);
	}

	/**
	 * Metodo che controlla se un utente registrato esiste oppure no. Se l'utente
	 * non esiste viene lanciata un eccezione
	 * 
	 * @param noleggio
	 */
	private void esisteUtenteRegistrato(Noleggio noleggio) {
		this.utenteRepository.findById(noleggio.getUtente().getIdUtente())
				.orElseThrow(() -> new UtenteNonTrovatoException(
						"Utente con id " + noleggio.getUtente().getIdUtente() + " non è presente nel DB."));
	}

	/**
	 * Metodo che controlla se un auto esiste oppure no. Se l'auto non esiste viene
	 * lanciata un eccezione
	 * 
	 * @param noleggio
	 */
	private void autoEsiste(Noleggio noleggio) {
		this.autoRepository.findById(noleggio.getAuto().getIdAuto()).orElseThrow(() -> new AutoNonTrovataException(
				"Auto con id " + noleggio.getAuto().getIdAuto() + " non è presente nel DB."));
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
		this.noleggioRepository.findById(idNoleggio).get().getAuto().setInUso(false);
		this.noleggioRepository.deleteById(idNoleggio);
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
