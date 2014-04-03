package tests;

import model.Categoria;
import model.CategoriaInvitado;
import junit.framework.TestCase;

public class CategoriaInvitadoTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCategoriaInvitado() {
		Categoria invitado = new CategoriaInvitado();
		assertEquals(invitado.limiteDeReproducciones(), 10);
	}

	public void testCategoriaInvitadoInt() {
		Categoria invitado = new Categoria(140);
		assertEquals(invitado.limiteDeReproducciones(), 140);
	}

}
