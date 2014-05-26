package model;

import java.util.Date;

public class CategoriaInvitado extends Categoria {
	private int limiteDeReproducciones = 10;
	private Date date;
	public CategoriaInvitado(){}
	
	public CategoriaInvitado(int limiteDeReproducciones){
		this.limiteDeReproducciones=limiteDeReproducciones;
	}
	
	@Override
	public int limiteDeReproducciones(){
		return limiteDeReproducciones; 
	}
	
}
