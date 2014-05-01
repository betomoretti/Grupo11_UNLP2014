package model;

import java.util.Date;

public class Sesion {
	int idSesion;
	Date fecha;
	Reproduccion reproduccion;
	
	public Sesion() {
		super();
	}

	public int getIdSesion() {
		return this.idSesion;
	}
	
	public void setIdSesion(int id) {
		this.idSesion = id;
	}
	
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
