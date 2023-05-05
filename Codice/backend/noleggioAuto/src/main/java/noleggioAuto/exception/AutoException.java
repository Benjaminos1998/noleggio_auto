package noleggioAuto.exception;

public class AutoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AutoException() {
	}

	public AutoException(String messaggio) {
		super(messaggio);
	}

}
