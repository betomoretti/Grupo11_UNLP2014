package model;

import java.util.Date;

public class Categoria {
	int idCategoria;
	int limiteDeReproducciones = 100;
	Date fecha;
	
	public Categoria(){}
	
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
