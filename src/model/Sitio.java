package model;

import java.util.Collection;
import java.util.HashSet;

public class Sitio {
	int idSitio;
	Collection<Usuario> usuarios = new HashSet<Usuario>();
	Catalogo catalogo = new Catalogo();
	
	public Sitio() {}
	
	
	public int getIdSitio() {
		return idSitio;
	}


	public void setIdSitio(int idSitio) {
		this.idSitio = idSitio;
	}


	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}
	public void registrarUsuario(Usuario usuario){
		usuarios.add(usuario);
	}
	public Catalogo getCatalogo() {
		return catalogo;
	}

}
