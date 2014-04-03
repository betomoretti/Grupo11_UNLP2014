package tests;

import java.util.Date;

import model.*;
import junit.framework.TestCase;

public class ReproduccionTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testReproduccion() {
		Pelicula saw = new Pelicula("saw", 18, 180000000);
		Date hoy = new Date();
		Reproduccion reproduccion = new Reproduccion(saw, hoy, 4000000);
		assertEquals(reproduccion.getReproducible(), saw);
		assertEquals(reproduccion.getFecha(), hoy);
		assertEquals(reproduccion.getTiempo(), 4000000);
	}

}
