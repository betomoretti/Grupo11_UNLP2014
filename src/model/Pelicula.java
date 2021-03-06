package model;

public class Pelicula extends Contenido implements Reproducible{
	private int idPelicula;
	private long duracion;
	
	public Pelicula() {}
	
	public int getIdPelicula() {
		return idPelicula;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

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
