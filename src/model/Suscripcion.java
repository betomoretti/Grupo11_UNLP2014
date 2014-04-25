package model;

import java.util.Date;

public class Suscripcion {
	
	Categoria categoria = new CategoriaInvitado();
	
	public Suscripcion(Date date) {
		categoria.date=date;
	}

	public Date getFecha(){
		return this.categoria.date;
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
