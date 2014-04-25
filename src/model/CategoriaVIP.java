package model;

import java.util.Date;

public class CategoriaVIP extends Categoria {

	int limiteDeReproducciones = 500;
	Date date;
	public CategoriaVIP(){}
	
	public CategoriaVIP(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	
	@Override
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}
}
