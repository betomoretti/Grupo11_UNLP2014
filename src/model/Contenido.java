package model;

public abstract class Contenido {
	protected String titulo;
	protected int edadMinima;
	protected int idContenido;
	
	public Contenido(){};

	
	public int getIdContenido() {
		return idContenido;
	}



	public void setIdContenido(int id) {
		this.idContenido = id;
	}



	public Contenido(String titulo, int edadMinima){
		this.titulo=titulo;
		this.edadMinima=edadMinima;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo( String titulo ) {
		this.titulo = titulo;
	}
	
	public int getEdadMinima() {
		return edadMinima;
	}
	
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}
	
	public boolean esPelicula(){
		return false;
	}
	public boolean esSerie(){
		return false;
	}
	public boolean aptoPara(Usuario usuario){
		return (usuario.edad()>=this.getEdadMinima());
	}
}
