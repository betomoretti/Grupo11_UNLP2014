package tests;

import junit.framework.TestCase;
import model.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PeliculaTest extends TestCase {

	private Usuario nene, adulto;
	private Pelicula pulp_fiction, shrek, bambi;

	public void setUp() throws Exception {
		// Usuarios
		Catalogo catalogo = new Catalogo();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);
		Date hace5anios = cal.getTime();
		cal.add(Calendar.YEAR, -15);
		Date hace20anios = cal.getTime();
		nene = new Usuario(hace5anios, "nene@db2.edu.ar",
				format.parse("09/09/2013"), catalogo);
		adulto = new Usuario(hace20anios, "adulto@db2.edu.ar",
				format.parse("09/09/2013"), catalogo);

		new Pelicula("Reservoir Dogs", 14, 130000);
		pulp_fiction = new Pelicula("Pulp Fiction", 16, 135000);
		shrek = new Pelicula("shrek", 4, 135000);
		bambi = new Pelicula("bambi", 0, 142000);
	}

	public void testPelicula() {
		Pelicula saw = new Pelicula("saw", 18, 180000000);
		assertEquals(saw.getTitulo(), "saw");
		assertEquals(saw.getEdadMinima(), 18);
		assertEquals(saw.getDuracion(), 180000000);
	}

	public void testEsPelicula() {
		assertTrue(shrek.esPelicula());
	}

	public void testEsEpisodio() {
		assertFalse(shrek.esEpisodio());
	}

	public void testGetEdadMinima() {
		assertEquals(16, pulp_fiction.getEdadMinima());
		assertEquals(0, bambi.getEdadMinima());
	}

	public void testAptoPara() {
		assertFalse(pulp_fiction.aptoPara(nene));
		assertTrue(pulp_fiction.aptoPara(adulto));
		assertTrue(shrek.aptoPara(nene));
		assertTrue(bambi.aptoPara(nene));
	}

}
