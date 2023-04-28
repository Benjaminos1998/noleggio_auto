package noleggioAuto.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noleggioAuto.entities.UtenteRegistrato;
import noleggioAuto.exception.UtenteException;
import noleggioAuto.exception.UtenteNonTrovatoException;
import noleggioAuto.repository.UtenteRegistratoRepository;

@Service
public class UtenteRegistratoService {

	UtenteRegistratoRepository utenteRegistratoRepository;

	@Autowired
	public UtenteRegistratoService(UtenteRegistratoRepository utenteRegistratoRepository) {
		this.utenteRegistratoRepository = utenteRegistratoRepository;
	}

	public List<UtenteRegistrato> getUtentiVip() {
		return this.utenteRegistratoRepository.findAll();
	}

	public UtenteRegistrato getUtenteRegistrato(Long idUtenteRegistrato) {
		return this.utenteRegistratoRepository.findById(idUtenteRegistrato).orElseThrow(
				() -> new UtenteNonTrovatoException("Utente con id " + idUtenteRegistrato + "non è stato trovato."));
	}

	public void addUtenteRegistrato(String nome, String cognome, String username, String password,
			LocalDate dataDiNascita, String numeroPatente) {

		// Controllo se esiste già un utente con lo stesso username
		if (this.utenteRegistratoRepository.findByUsername(username).isPresent())
			throw new UtenteException("Username già inserito.");
		// Controllo se esiste già un utente con lo stesso numero di patente
		if (this.utenteRegistratoRepository.findByNumeroPatente(numeroPatente).isPresent())
			throw new UtenteException("Numero di patente già inserito.");
		// Controllo se l'utente ha almeno 18 anni
		if (UtenteRegistrato.getEta(dataDiNascita) < 18)
			throw new UtenteException("L'utente deve avere almeno 18 anni.");
		// Controllo se la password rispetta la lunghezza minima di 8 caratteri
		if (password.length() < 8)
			throw new PasswordNonValidaException("La password deve avere almeno 8 caratteri.");

		UtenteRegistrato utenteRegistrato = new UtenteRegistrato(nome, cognome, username, password, dataDiNascita,
				numeroPatente);
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
