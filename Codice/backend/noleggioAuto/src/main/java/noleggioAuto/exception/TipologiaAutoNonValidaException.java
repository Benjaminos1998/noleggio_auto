package noleggioAuto.exception;

public class TipologiaAutoNonValidaException extends AutoException {

	private static final long serialVersionUID = 1L;

	public TipologiaAutoNonValidaException() {
		super();
	}

	public TipologiaAutoNonValidaException(String messaggio) {
		super(messaggio);
	}
}
