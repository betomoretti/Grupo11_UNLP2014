package model;

import java.util.Date;

public class Categoria {
	protected int idCategoria;
	protected int limiteDeReproducciones = 100;
	protected Date fecha;
	
	public Categoria(){}
	
	public int getLimiteDeReproducciones() {
		return limiteDeReproducciones;
	}

	public void setLimiteDeReproducciones(int limiteDeReproducciones) {
		this.limiteDeReproducciones = limiteDeReproducciones;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int id) {
		this.idCategoria = id;
	}


	public Categoria(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}

}
