package model;

import java.util.Date;

public class CategoriaVIP extends Categoria {

	private int limiteDeReproducciones = 500;
	private Date date;
	
	public CategoriaVIP(){
		super();
		this.setLimiteDeReproducciones(limiteDeReproducciones);
	}
	
	public CategoriaVIP(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	
	@Override
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}
}
