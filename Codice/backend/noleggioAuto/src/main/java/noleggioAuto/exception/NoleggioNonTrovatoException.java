package noleggioAuto.exception;

public class NoleggioNonTrovatoException extends NoleggioException {

	private static final long serialVersionUID = 1L;
	
	public NoleggioNonTrovatoException() {
		super();
	}

	public NoleggioNonTrovatoException(String messaggio) {
		super(messaggio);
	}

}
