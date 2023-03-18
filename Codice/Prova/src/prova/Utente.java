package prova;

import java.util.ArrayList;
import java.util.List;

public class Utente {

	public String nome;
	public int numeroPunti;
	public List<Noleggio> noleggi = new ArrayList<Noleggio>();
	
	public Utente(String nome, int numeroPunti){
		this.nome=nome;
		this.numeroPunti=numeroPunti;
	} 
	 
	public void incrementaPunti(Utente utente) {
		for(int i =0; i<utente.noleggi.size();i++) {
			utente.numeroPunti+=utente.noleggi.get(i).noleggio.valore;
		}
	} 
	public static void main(String[] args) throws IllegalAccessException {
		Utente utente = new Utente("beniamino",0);
		System.out.println(utente.noleggi);
		Noleggio noleggio1= new Noleggio(100,"CarSharIng");
		utente.noleggi.add(noleggio1);
		System.out.println(utente.noleggi);
		Noleggio noleggio2= new Noleggio(150,"BREVEPeriodO");
		utente.noleggi.add(noleggio2);
		System.out.println(utente.noleggi);
		Noleggio noleggio3= new Noleggio(150,"LungoPeriodO");
		utente.noleggi.add(noleggio3);
		System.out.println(utente.noleggi);
		System.out.println(utente.noleggi.size());
		System.out.println("Numero Punti = "+utente.numeroPunti);
		
		System.out.println();
		System.out.println(utente.noleggi.get(0).noleggio.valore);
		System.out.println(utente.noleggi.get(1).noleggio.valore);
		System.out.println(utente.noleggi.get(2).noleggio.valore);
		
		System.out.println(utente.noleggi.get(0).noleggio);
		System.out.println("___________________________________");
		int numeroPunti=utente.numeroPunti;
		System.out.println(numeroPunti);
		
		
		
		for(int i =0; i<utente.noleggi.size();i++) {
			numeroPunti+=utente.noleggi.get(i).noleggio.valore;
		}
		System.out.println(numeroPunti);
		
		
		
		
		
	}
}
