package noleggioAuto.services;

import noleggioAuto.exception.UtenteException;

public class PasswordNonValidaException extends UtenteException {

	private static final long serialVersionUID = 1L;

	public PasswordNonValidaException(String messaggio) {
		super(messaggio);
	}

}
