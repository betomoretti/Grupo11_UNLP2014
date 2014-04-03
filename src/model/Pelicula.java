package model;

public class Pelicula extends Contenido implements Reproducible{
	long duracion;

	public Pelicula(String titulo, int edadMinima, long duracion) {
		super(titulo, edadMinima);
		this.duracion=duracion;
	}
	public long getDuracion() {
		return duracion;
	}
	@Override
	public boolean esPelicula(){
		return true;
	}
	public boolean esEpisodio(){
		return false;
	}

	
}
