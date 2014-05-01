package model;

import java.util.Date;

public class Suscripcion {
	
	int idSuscripcion;
	Categoria categoria = new CategoriaInvitado();
	
	public Suscripcion() {}
	
	public Suscripcion(Date date) {
		categoria.fecha=date;
	}
	
	
	public int getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(int idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}

	public Date getFecha(){
		return this.categoria.fecha;
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
