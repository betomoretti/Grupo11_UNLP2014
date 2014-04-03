package tests;

import junit.framework.TestCase;
import model.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EpisodioTest extends TestCase {

	private Serie breaking_bad, barney;
	private Temporada bbS01, barneyS01;
	private Episodio pilot, queen;
	private Usuario nene, adulto;

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

		// Breaking Bad
		breaking_bad = new Serie("Breaking Bad", 14);
		bbS01 = new Temporada(breaking_bad, 1);
		pilot = new Episodio(40000, 1, "Pilot", bbS01);

		// Barney
		barney = new Serie("barney", 0);
		barneyS01 = new Temporada(barney, 1);
		queen = new Episodio(20000, 1, "The Queen of Make Believe", barneyS01);
	}

	public void testEpisodio() {
		Episodio nuevo = new Episodio(2000000, 2, "New episode", barneyS01);
		assertEquals(nuevo.getDuracion(), 2000000);
		assertEquals(nuevo.getNumero(), 2);
		assertEquals(nuevo.getTitulo(), "New episode");
		assertEquals(nuevo.getTemporada(), barneyS01);
		assertTrue(barneyS01.getEpisodios().contains(nuevo));
	}

	public void testEsPelicula() {
		assertFalse(queen.esPelicula());
	}

	public void testEsEpisodio() {
		assertTrue(pilot.esEpisodio());
	}

	public void testGetTemporada() {
		assertEquals(pilot.getTemporada(), bbS01);
	}

	public void testGetEdadMinima() {
		assertEquals(breaking_bad.getEdadMinima(), pilot.getEdadMinima());
		assertEquals(bbS01.getEdadMinima(), pilot.getEdadMinima());
		assertEquals(barney.getEdadMinima(), queen.getEdadMinima());
		assertEquals(barneyS01.getEdadMinima(), queen.getEdadMinima());
	}

	public void testAptoPara() {
		assertFalse(pilot.aptoPara(nene));
		assertTrue(pilot.aptoPara(adulto));
		assertTrue(queen.aptoPara(nene));
	}

}
