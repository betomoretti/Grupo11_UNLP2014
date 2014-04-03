package model;

public abstract class Contenido {
	String titulo;
	int edadMinima;
	
	public String getTitulo() {
		return titulo;
	}
	public int getEdadMinima() {
		return edadMinima;
	}
	public boolean esPelicula(){
		return false;
	}
	public boolean esSerie(){
		return false;
	}
	public boolean aptoPara(Usuario usuario){
		return usuario.edad()>=this.getEdadMinima();
	}
}
