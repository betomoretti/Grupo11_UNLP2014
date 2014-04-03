package model;

import java.util.Collection;

public class Serie extends Contenido {
	Collection<Temporada> temporadas;

	public Serie(String titulo, int edadMinima) {
		this.titulo=titulo;
		this.edadMinima=edadMinima;
	}
	public Collection<Temporada> getTemporadas() {
		return temporadas;
	}
	@Override
	public boolean esSerie(){
		return true;
	}
	
}
