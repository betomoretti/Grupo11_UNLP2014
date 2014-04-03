package model;

public class Categoria {
	int limiteDeReproducciones = 100;
	
	public Categoria(){}
	
	public Categoria(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}

}
