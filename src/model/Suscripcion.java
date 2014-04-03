package model;

import java.util.Date;

public class Suscripcion {
	
	Categoria categoria;
	Date fecha;
	
	public Suscripcion(Date date) {
		fecha=date;
	}

	public Date getFecha(){
		return fecha;
	}
	
	public void pasarANormal(){
	}
	
	public void pasarAVIP(){
	}
	
	public int limiteDeReproducciones(){
		return this.categoria.limiteDeReproducciones();
	}
	
}
