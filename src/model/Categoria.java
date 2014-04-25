package model;

import java.util.Date;

public class Categoria {
	int limiteDeReproducciones = 100;
	Date date;
	public Categoria(){}
	
	public Categoria(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}

}
