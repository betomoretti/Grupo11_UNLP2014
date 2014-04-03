package model;

public class CategoriaVIP extends Categoria {

	int limiteDeReproducciones = 500;
	
	public CategoriaVIP(){}
	
	public CategoriaVIP(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	
	@Override
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}
}
