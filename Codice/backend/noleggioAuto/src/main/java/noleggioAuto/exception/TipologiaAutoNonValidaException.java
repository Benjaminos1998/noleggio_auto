package noleggioAuto.exception;

public class TipologiaAutoNonValidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TipologiaAutoNonValidaException(String messaggio) {
		super(messaggio);
	}
}
