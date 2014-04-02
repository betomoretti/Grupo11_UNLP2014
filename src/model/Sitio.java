package model;

import java.util.Collection;

public class Sitio {
	Collection<Usuario> usuarios;
	Catalogo catalogo;
	
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}
	public Catalogo getCatalogo() {
		return catalogo;
	}
	public void registrarUsuario(Usuario usuario){
		usuarios.add(usuario);
	}
}
