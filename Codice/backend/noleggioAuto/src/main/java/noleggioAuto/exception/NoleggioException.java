package noleggioAuto.exception;

public class NoleggioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoleggioException() {
		super();
	}

	public NoleggioException(String messaggio) {
		super(messaggio);
	}

}
