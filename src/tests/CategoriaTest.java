package tests;

import model.Categoria;
import junit.framework.TestCase;

public class CategoriaTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCategoria() {
		Categoria normal = new Categoria();
		assertEquals(normal.limiteDeReproducciones(), 100);
	}

	public void testCategoriaInt() {
		Categoria normal = new Categoria(140);
		assertEquals(normal.limiteDeReproducciones(), 140);
	}

}
