package model;

import java.util.Date;

public class CategoriaInvitado extends Categoria {
	int limiteDeReproducciones = 10;
	Date date;
	public CategoriaInvitado(){}
	
	public CategoriaInvitado(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	
	@Override
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}
	
}
