package model;

import java.util.Date;

public class Reproduccion {
	int idReproduccion;
	Date fecha;
	Long tiempo;
	Reproducible reproducible;
	
	public Reproduccion() {}
	
	
	public int getIdReproduccion() {
		return idReproduccion;
	}


	public void setIdReproduccion(int idReproduccion) {
		this.idReproduccion = idReproduccion;
	}


	public Reproduccion(Reproducible reproducible, Date fecha, long tiempo){
		this.reproducible=reproducible;
		this.fecha=fecha;
		this.tiempo=tiempo;
	}
	public Date getFecha() {
		return fecha;
	}
	public long getTiempo() {
		return tiempo;
	}
	public Reproducible getReproducible() {
		return reproducible;
	}
	
}
