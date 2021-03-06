package model;

import java.util.Collection;
import java.util.HashSet;

public class Sitio {
	private int idSitio;
	private Collection<Usuario> usuarios = new HashSet<Usuario>();
	private Catalogo catalogo = new Catalogo();
	
	public Sitio() {}
	
	
	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}


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
