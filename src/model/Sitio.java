package model;

import java.util.Collection;
import java.util.HashSet;

public class Sitio {
	Collection<Usuario> usuarios = new HashSet<Usuario>();
	Catalogo catalogo = new Catalogo();
	
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
