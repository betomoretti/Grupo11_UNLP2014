package model;

import java.lang.String; 
import java.util.Calendar;
import java.util.Date;

public class Usuario {

	String email;
	Date fechaDeNacimiento;
	Suscripcion suscripcion;
	GestorDeContenidos gestor;
	
	public String getEmail() {
		return email;
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public Suscripcion getSuscripcion() {
		return suscripcion;
	}
	public GestorDeContenidos getGestor() {
		return gestor;
	}
	public int edad(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaDeNacimiento);
	}
}
