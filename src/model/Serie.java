package model;

import java.util.Collection;
import java.util.HashSet;

public class Serie extends Contenido {
	Collection<Temporada> temporadas = new HashSet<Temporada>();

	public Serie() {}
	
	public Serie(String titulo, int edadMinima) {
		super(titulo, edadMinima);
	}
	public Collection<Temporada> getTemporadas() {
		return temporadas;
	}
	@Override
	public boolean esSerie(){
		return true;
	}
	public void agregarTemporada(Temporada temporada){
		temporadas.add(temporada);
	}
	
	
}
