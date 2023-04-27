package noleggioAuto.exception;

public class NoleggioNonTrovatoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoleggioNonTrovatoException(String messaggio) {
		super(messaggio);
	}

}
