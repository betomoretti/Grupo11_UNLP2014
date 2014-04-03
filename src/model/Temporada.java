package model;

import java.util.Collection;
import java.util.HashSet;

public class Temporada {
	int numero;
	Serie serie;
	Collection<Episodio> episodios = new HashSet<Episodio>();
	
	public Temporada(Serie serie, int numero){
		this.serie = serie;
		this.numero = numero;
		this.serie.agregarTemporada(this);
	}
	
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
