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
	
	public void setFecha( Date fecha ) {
		this.fecha = fecha;
	}
	
	public long getTiempo() {
		return tiempo;
	}
	
	public void setTiempo( long tiempo ) {
		this.tiempo = tiempo;
	}
	
	public Reproducible getReproducible() {
		return reproducible;
	}
	
	public void setReproducible( Reproducible reproducible ) {
		this.reproducible = reproducible;
	}
	
}
