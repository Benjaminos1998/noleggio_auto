package noleggioAuto.exception;

public class TargaAutoNonValidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TargaAutoNonValidaException(String messaggio) {
		super(messaggio);
	}
}
