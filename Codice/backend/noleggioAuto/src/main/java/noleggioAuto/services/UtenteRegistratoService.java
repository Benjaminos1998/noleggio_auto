package noleggioAuto.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.UtenteRegistrato;
import noleggioAuto.exception.EtaUtenteException;
import noleggioAuto.exception.PasswordNonValidaException;
import noleggioAuto.exception.PatenteUtenteException;
import noleggioAuto.exception.UsernameUtenteException;
import noleggioAuto.exception.UtenteNonTrovatoException;
import noleggioAuto.repository.UtenteRegistratoRepository;

@Service
public class UtenteRegistratoService {

	UtenteRegistratoRepository utenteRegistratoRepository;

	@Autowired
	public UtenteRegistratoService(UtenteRegistratoRepository utenteRegistratoRepository) {
		this.utenteRegistratoRepository = utenteRegistratoRepository;
	}

	public List<UtenteRegistrato> getUtentiRegistrati() {
		return this.utenteRegistratoRepository.findAll();
	}

	public UtenteRegistrato getUtenteRegistrato(Long idUtenteRegistrato) {
		return this.utenteRegistratoRepository.findById(idUtenteRegistrato)
				.orElseThrow(() -> new UtenteNonTrovatoException());
	}

	public void addUtenteRegistrato(String nome, String cognome, String username, String password,
			LocalDate dataDiNascita, String numeroPatente) {

		// Controllo se esiste già un utente con lo stesso username
		if (this.utenteRegistratoRepository.findByUsername(username).isPresent())
			throw new UsernameUtenteException();
		// Controllo se esiste già un utente con lo stesso numero di patente
		if (this.utenteRegistratoRepository.findByNumeroPatente(numeroPatente).isPresent())
			throw new PatenteUtenteException();
		// Controllo se l'utente ha almeno 18 anni
		if (UtenteRegistrato.getEta(dataDiNascita) < UtenteRegistrato.ETA)
			throw new EtaUtenteException();
		// Controllo se la password rispetta la lunghezza minima di 8 caratteri
		if (password.length() < UtenteRegistrato.LUNGHEZZA_MINIMA_PASSWORD)
			throw new PasswordNonValidaException();

		UtenteRegistrato utenteRegistrato = new UtenteRegistrato(nome, cognome, username, password, dataDiNascita,
				numeroPatente, UtenteRegistrato.getEta(dataDiNascita));

		// Salvo l'utente
		this.utenteRegistratoRepository.save(utenteRegistrato);
	}


	public void deleteUtenteRegistrato(Long idUtente) {
		boolean exist = utenteRegistratoRepository.existsById(idUtente);
		if (!exist) {
			throw new UtenteNonTrovatoException("Utente con id " + idUtente + " non esiste");
		}
		this.utenteRegistratoRepository.deleteById(idUtente);
	}

}
