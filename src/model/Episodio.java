package model;

public class Episodio implements Reproducible{
	long duracion;
	int numero;
	String titulo;
	Temporada temporada;

	public Episodio(long duracion, int numero, String titulo, Temporada temporada){
		this.duracion=duracion;
		this.numero=numero;
		this.titulo=titulo;
		this.temporada=temporada;
		this.temporada.agregarEpisodio(this);
	}
	
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
		return false;
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
