package model;

public interface Reproducible {
	public String getTitulo();
	public long getDuracion();
	public int getEdadMinima();
	public boolean aptoPara(Usuario usuario);
	public boolean esPelicula();
	public boolean esEpisodio();
}
