package model;

import java.util.HashSet;
import java.util.Collection;

public class Catalogo {
	int idCatalogo;
	private Collection<Contenido> contenidos = new HashSet<Contenido>();
	
	public void setContenidos(Collection<Contenido> contenidos) {
		this.contenidos = contenidos;
	}

	public Catalogo(){}
	
	public int getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(int id) {
		this.idCatalogo = id;
	}
	
	public Collection<Contenido> getContenidos() {
		return contenidos;
	}
	public Collection<Pelicula> getPeliculas(){
		Collection<Pelicula> peliculas = new HashSet<Pelicula>();
		for (Contenido contenido: contenidos){
			if (contenido.esPelicula())
				peliculas.add((Pelicula)contenido);
		}
		return peliculas;
	}
	public Collection<Serie> getSeries(){
		Collection<Serie> series = new HashSet<Serie>();
		for (Contenido contenido: contenidos){
			if (contenido.esSerie())
				series.add((Serie)contenido);
		}
		return series;
	}
	public void agregar(Contenido contenido){
		contenidos.add(contenido);
	}
}
