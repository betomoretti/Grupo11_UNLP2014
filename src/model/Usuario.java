package model;

import java.lang.String; 
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;

public class Usuario {

	String email;
	Date fechaDeNacimiento;
	GestorDeContenidos gestor;
	Suscripcion suscripcion;
	Sesion sesionActual;

	public String getEmail() {
		return email;
	}
	public int edad(){
		DateTime dt = new DateTime(fechaDeNacimiento);
		LocalDate birthdate = dt.toLocalDate();
		return Years.yearsBetween(birthdate, new LocalDate()).getYears();
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public GestorDeContenidos getGestor() {
		return gestor;
	}
	public Suscripcion getSuscripcion() {
		return suscripcion;
	}


}
