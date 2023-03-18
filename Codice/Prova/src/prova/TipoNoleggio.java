package prova;

public enum TipoNoleggio {
	
	CarSharing(50), BrevePeriodo(100), LungoPerido(200);

	int valore;
	 
	TipoNoleggio(int i){
		this.valore=i;
	}
}
 