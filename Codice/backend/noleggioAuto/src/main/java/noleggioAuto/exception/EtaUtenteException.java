package noleggioAuto.exception;

public class EtaUtenteException extends UtenteException {

	private static final long serialVersionUID = 1L;

	public EtaUtenteException() {
		super();
	}
	
	public EtaUtenteException(String messaggio){
		super(messaggio);
	}
}
