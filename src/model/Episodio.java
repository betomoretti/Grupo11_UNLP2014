package model;

public class Episodio implements Reproducible{
	long duracion;
	int numero;
	String titulo;
	Temporada temporada;
	
	public String getTitulo() {
		return titulo;
	}
	public long getDuracion() {
		return duracion;
	}
	public int getEdadMinima(){
		return temporada.getEdadMinima();
	}
	public boolean aptoPara(Usuario usuario){
		return temporada.getSerie().aptoPara(usuario);
	}
	public boolean esPelicula(){
		return temporada.getSerie().esPelicula();
	}
	public boolean esEpisodio(){
		return true;
	}
	public int getNumero() {
		return numero;
	}
	public Temporada getTemporada() {
		return temporada;
	}

	
}
