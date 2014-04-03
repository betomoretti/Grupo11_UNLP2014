package tests;

import java.util.Date;

import model.*;
import junit.framework.TestCase;

public class SesionTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testSesion() {
		Date hoy = new Date();
		Pelicula bambi = new Pelicula("bambi", 0, 142000);
		Reproduccion peli = new Reproduccion(bambi, hoy, 1000000);
		Sesion nueva = new Sesion(peli, hoy);
		assertEquals(nueva.getReproduccion(), peli);
		assertEquals(nueva.getFecha(), hoy);
	}

}
