package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class GestorDeContenidos {
	int idGestorDeContenidos;
	Usuario usuario;
	Collection<Reproduccion> reproducciones = new ArrayList<Reproduccion>();
	Catalogo catalogo = new Catalogo();
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setReproducciones(Collection<Reproduccion> reproducciones) {
		this.reproducciones = reproducciones;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public GestorDeContenidos() {}
	
	public int getIdGestorDeContenidos() {
		return idGestorDeContenidos;
	}

	public void setIdGestorDeContenidos(int idGestorDeContenidos) {
		this.idGestorDeContenidos = idGestorDeContenidos;
	}

	public GestorDeContenidos(Usuario usuario, Catalogo catalogo) {
		this.usuario=usuario;
		this.catalogo=catalogo;
	}

	private int cantidadDeReproducciones(){
		return reproducciones.size();
	}
	
	public Collection<Episodio> episodiosVistos(){
		Collection<Episodio> episodios = new ArrayList<Episodio>();
		for (Reproduccion reproduccion: reproducciones){
			if (reproduccion.getReproducible().esEpisodio())
				episodios.add((Episodio)reproduccion.getReproducible());
		}
		return episodios;
	}
	public Collection<Pelicula> peliculasVistas(){
		Collection<Pelicula> peliculas = new ArrayList<Pelicula>();
		for (Reproduccion reproduccion: reproducciones){
			if (reproduccion.getReproducible().esPelicula())
				peliculas.add((Pelicula)reproduccion.getReproducible());
		}
		return peliculas;
	}
	public Collection<Episodio> episodiosSinVerDe(Temporada temporada){
		Collection<Episodio> episodios = new ArrayList<Episodio>();
		for(Episodio episodio: temporada.getEpisodios()){
			if (!episodiosVistos().contains(episodio))
				episodios.add(episodio);
		}
		return episodios;
	}
	public Collection<Pelicula> peliculasAptas(){
		Collection<Pelicula> peliculas = new ArrayList<Pelicula>();
		for(Pelicula pelicula: catalogo.getPeliculas()){
			if (pelicula.aptoPara(usuario))
				peliculas.add(pelicula);
		}
		return peliculas;
	}
	public void registrarReproduccion(Reproducible reproducible, Date fecha, long tiempo){
		if (puedeReproducir(reproducible)){
			Reproduccion reproduccion = new Reproduccion(reproducible, fecha, tiempo);
			reproducciones.add(reproduccion);
		}
	}
	public boolean puedeReproducir(Reproducible reproducible){
		return ((reproducible.aptoPara(usuario)&&
				(usuario.getSuscripcion().limiteDeReproducciones()>cantidadDeReproducciones())));
	}
	public Collection<Reproduccion> getReproducciones() {
		return reproducciones;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public Catalogo getCatalogo() {
		return catalogo;
	}
}
