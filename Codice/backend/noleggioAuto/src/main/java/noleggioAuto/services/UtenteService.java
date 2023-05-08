package noleggioAuto.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.Utente;
import noleggioAuto.exception.EtaUtenteException;
import noleggioAuto.exception.PasswordNonValidaException;
import noleggioAuto.exception.PatenteUtenteException;
import noleggioAuto.exception.UsernameUtenteException;
import noleggioAuto.exception.UtenteNonTrovatoException;
import noleggioAuto.repository.UtenteRepository;

@Service
public class UtenteService {

	UtenteRepository utenteRepository;

	@Autowired
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	public List<Utente> getUtenti() {
		return this.utenteRepository.findAll();
	}

	public Utente getUtente(Long idUtenteRegistrato) {
		return this.utenteRepository.findById(idUtenteRegistrato).orElseThrow(() -> new UtenteNonTrovatoException());
	}

	public void addUtente(String nome, String cognome, String email, String password, LocalDate dataDiNascita,
			String numeroPatente) {

		// Controllo se esiste già un utente con lo stesso username
		if (this.utenteRepository.findByEmail(email).isPresent())
			throw new UsernameUtenteException();
		// Controllo se esiste già un utente con lo stesso numero di patente
		if (this.utenteRepository.findByNumeroPatente(numeroPatente).isPresent())
			throw new PatenteUtenteException();
		// Controllo se l'utente ha almeno 18 anni
		if (Utente.getEta(dataDiNascita) < Utente.ETA)
			throw new EtaUtenteException();
		// Controllo se la password rispetta la lunghezza minima di 8 caratteri
		if (password.length() < Utente.LUNGHEZZA_MINIMA_PASSWORD)
			throw new PasswordNonValidaException();

		Utente utente = new Utente(nome, cognome, email, password, dataDiNascita, numeroPatente,
				Utente.getEta(dataDiNascita));

		// Salvo l'utente
		this.utenteRepository.save(utente);
	}

	public void deleteUtente(Long idUtente) {
		boolean exist = utenteRepository.existsById(idUtente);
		if (!exist) {
			throw new UtenteNonTrovatoException("Utente con id " + idUtente + " non esiste");
		}
		this.utenteRepository.deleteById(idUtente);
	}

}
