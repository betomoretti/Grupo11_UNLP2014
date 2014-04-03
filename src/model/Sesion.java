package model;

import java.util.Date;

public class Sesion {
	Date fecha;
	Reproduccion reproduccion;
	
	public Sesion(Reproduccion reproduccion, Date fecha) {
		this.reproduccion=reproduccion;
		this.fecha=fecha;
	}
	public Date getFecha() {
		return fecha;
	}
	public Reproduccion getReproduccion() {
		return reproduccion;
	}
	
}
