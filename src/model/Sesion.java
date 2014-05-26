package model;

import java.util.Date;

public class Sesion {
	private int idSesion;
	private Date fecha;
	private Reproduccion reproduccion;
	
	public Sesion() {
		super();
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setReproduccion(Reproduccion reproduccion) {
		this.reproduccion = reproduccion;
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
