package model;

import java.util.Collection;

public class Sitio {
	Collection<Usuario> usuarios;
	Catalogo catalogo;
	
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
