package tests;

import model.Sitio;
import junit.framework.TestCase;

public class SitioTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testSitio() {
		Sitio sitio = new Sitio();
		assertNotNull(sitio.getCatalogo());
		assertNotNull(sitio.getUsuarios());
		assertEquals(sitio.getUsuarios().size(), 0);
	}

}
