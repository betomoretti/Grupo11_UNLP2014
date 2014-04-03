package model;

public class CategoriaInvitado extends Categoria {
	int limiteDeReproducciones = 10;
	
	public CategoriaInvitado(){}
	
	public CategoriaInvitado(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	
	@Override
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}
	
}
