package tests;

import model.Categoria;
import model.CategoriaVIP;
import junit.framework.TestCase;

public class CategoriaVIPTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCategoriaVIP() {
		Categoria vip = new CategoriaVIP();
		assertEquals(vip.limiteDeReproducciones(), 500);
	}

	public void testCategoriaVIPInt() {
		Categoria vip = new CategoriaVIP(300);
		assertEquals(vip.limiteDeReproducciones(), 300);
	}

}
