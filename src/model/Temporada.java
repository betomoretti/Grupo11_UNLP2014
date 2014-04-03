package model;

import java.util.Collection;

public class Temporada {
	int numero;
	Serie serie;
	Collection<Episodio> episodios;
	
	public int getNumero() {
		return numero;
	}
	public Serie getSerie() {
		return serie;
	}
	public Collection<Episodio> getEpisodios() {
		return episodios;
	}
	public void agregarEpisodio(Episodio episodio){
		episodios.add(episodio);
	}
	public int getEdadMinima(){
		return serie.getEdadMinima();
	}
}
