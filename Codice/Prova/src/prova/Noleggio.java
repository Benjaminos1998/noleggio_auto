package prova;


public class Noleggio {

	static private int id = 1;
	public int idNoleggio;
	public double prezzo;
	public TipoNoleggio noleggio;

	public Noleggio(double prezzo, String sceltaNoleggio) throws IllegalAccessException {
		idNoleggio = id++;
		this.prezzo = prezzo;
		String carsharing="CarSharing";
		String breveperiodo="BrevePeriodo";
		String lungoperiodo="LungoPeriodo"; 
		if(sceltaNoleggio.equalsIgnoreCase(carsharing)) {
			this.noleggio=TipoNoleggio.CarSharing;
		}else if(sceltaNoleggio.equalsIgnoreCase(breveperiodo)) {
			this.noleggio=TipoNoleggio.BrevePeriodo;	
		}else if(sceltaNoleggio.equalsIgnoreCase(lungoperiodo)) {
			this.noleggio=TipoNoleggio.LungoPerido;
		}else {
			throw new IllegalAccessException("Tipologia noleggio non valido");
		}

	}

	public double calcolaPrezzo() {
		return 0;
	}

	@Override
	public String toString() {
		return "Noleggio: " + this.idNoleggio + " Prezzo: " + this.prezzo + "0€";
	}
}
