package model;

import java.util.Date;

public class Reproduccion {
	Date fecha;
	Long tiempo;
	Reproducible reproducible;
	
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
