package noleggioAuto.exception;

public class DurataNoleggioNonValidaException extends RuntimeException {

	private static final long serialVersionUID = 2596367745555288880L;

	public DurataNoleggioNonValidaException(String messaggio) {
		super(messaggio);
	}
}
