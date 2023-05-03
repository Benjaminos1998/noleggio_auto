package noleggioAuto.exception;

public class PasswordNonValidaException extends UtenteException {

	private static final long serialVersionUID = 1L;

	public PasswordNonValidaException() {
	}

	public PasswordNonValidaException(String messaggio) {
		super(messaggio);
	}

}
