package noleggioAuto.exception;

public class UsernameUtenteException extends UtenteException {

	private static final long serialVersionUID = 1L;

	public UsernameUtenteException() {
	}

	public UsernameUtenteException(String messaggio) {
		super(messaggio);
	}
}
