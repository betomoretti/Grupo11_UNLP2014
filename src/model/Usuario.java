package model;

import java.lang.String; 
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;

public class Usuario {

	String email;
	int idUsuario;
	Date fechaDeNacimiento;
	GestorDeContenidos gestor;
	Suscripcion suscripcion;
	Sesion sesionActual;
	
	public Usuario() {}
	
	
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}


	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}


	public Sesion getSesionActual() {
		return sesionActual;
	}


	public void setSesionActual(Sesion sesionActual) {
		this.sesionActual = sesionActual;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setGestor(GestorDeContenidos gestor) {
		this.gestor = gestor;
	}


	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public Usuario(Date fechaDeNacimiento, String email, Date fechaSuscripcion, Catalogo catalogo) {
		this.fechaDeNacimiento=fechaDeNacimiento;
		this.email=email;
		this.suscripcion=new Suscripcion(fechaSuscripcion);
		this.gestor=new GestorDeContenidos(this, catalogo);
	}
	public String getEmail() {
		return email;
	}
	public int edad(){
		DateTime dt = new DateTime(fechaDeNacimiento);
		LocalDate birthdate = dt.toLocalDate();
		return Years.yearsBetween(birthdate, new LocalDate()).getYears();
	}
	public Date getFechaNacimiento() {
		return fechaDeNacimiento;
	}
	public GestorDeContenidos getGestor() {
		return gestor;
	}
	public Suscripcion getSuscripcion() {
		return suscripcion;
	}
	
	
	public void setSesion(Sesion sesion) {
		this.sesionActual = sesion;
	}
	
	public Sesion getSesion() {
		return sesionActual;
	}
}
