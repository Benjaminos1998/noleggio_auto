package noleggioAuto.exception;

public class UtenteNonTrovatoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UtenteNonTrovatoException(String messaggio) {
		super(messaggio);
	}
}
