package model;

import java.util.Date;

public class Suscripcion {
	
	Categoria categoria = new CategoriaInvitado();
	Date fecha;
	
	public Suscripcion(Date date) {
		fecha=date;
	}

	public Date getFecha(){
		return fecha;
	}
	
	public void pasarANormal(){
		categoria = new Categoria();
	}
	
	public void pasarAVIP(){
		categoria = new CategoriaVIP();
	}
	
	public int limiteDeReproducciones(){
		return this.categoria.limiteDeReproducciones();
	}
	
}
