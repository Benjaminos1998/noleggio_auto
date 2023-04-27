package noleggioAuto.exception;

public class AutoNonTrovataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AutoNonTrovataException(String messaggio) {
		super(messaggio);
	}

}
