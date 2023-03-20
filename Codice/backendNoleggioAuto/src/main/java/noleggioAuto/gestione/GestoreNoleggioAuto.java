package noleggioAuto.gestione;


import noleggioAuto.entities.Noleggio;
import noleggioAuto.entities.Utente;
import noleggioAuto.entities.UtenteVip;

public class GestoreNoleggioAuto {
	private static GestoreNoleggioAuto gestoreNoleggioAuto;

	public GestoreNoleggioAuto() {
	}

	public static GestoreNoleggioAuto getInstance() {
		if (gestoreNoleggioAuto == null)
			gestoreNoleggioAuto = new GestoreNoleggioAuto();
		return gestoreNoleggioAuto;
	}

	public void incrementaPunti(UtenteVip utenteVip) {
		for(int i =0; i<utenteVip.getNoleggi().size();i++) {
			utenteVip.numeroPunti+=utenteVip.getNoleggi().get(i).noleggio.valore;
		}
	}
	public void addNoleggioAUtente(Noleggio noleggio,Utente utente) {
		utente.getNoleggi().add(noleggio);
	}
	public void addNoleggioAUtenteVip(Noleggio noleggio,UtenteVip utenteVip) {
		utenteVip.getNoleggi().add(noleggio);
	}
	
}
