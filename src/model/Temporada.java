package model;

import java.util.Collection;
import java.util.HashSet;

public class Temporada {
	private int idTemporada;
	private int numero;
	private Serie serie;
	private Collection<Episodio> episodios = new HashSet<Episodio>();
	
	public Temporada() {}
	
	
	public int getIdTemporada() {
		return idTemporada;
	}


	public void setIdTemporada(int idTemporada) {
		this.idTemporada = idTemporada;
	}


	public Temporada(Serie serie, int numero){
		this.serie = serie;
		this.numero = numero;
		this.serie.agregarTemporada(this);
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero( int numero ) {
		this.numero = numero;
	}
	
	public Serie getSerie() {
		return serie;
	}
	
	public void setSerie( Serie serie ) {
		this.serie = serie;
	}

	public Collection<Episodio> getEpisodios() {
		return episodios;
	}
	
	public void setEpisodios( Collection<Episodio> episodios ) {
		this.episodios = episodios;
	}
	
	public void agregarEpisodio(Episodio episodio){
		episodios.add(episodio);
	}
	public int getEdadMinima(){
		return serie.getEdadMinima();
	}
}
