package noleggioAuto.exception;

public class UtenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UtenteException() {
	}

	public UtenteException(String messaggio) {
		super(messaggio);
	}
}
