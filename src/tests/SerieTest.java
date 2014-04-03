package tests;

import junit.framework.TestCase;
import model.*;

public class SerieTest extends TestCase {

	private Temporada barneyS01;
	private Serie barney;
	private Temporada barneyS02;

	public void setUp() throws Exception {
		barney = new Serie("barney", 0);
		barneyS01 = new Temporada(barney, 1);
		barneyS02 = new Temporada(barney, 2);
	}

	public void testSerie() {
		Serie lost = new Serie("lost", 12);
		assertEquals(lost.getTitulo(), "lost");
		assertEquals(lost.getEdadMinima(), 12);
		assertNotNull(lost.getTemporadas());
		assertEquals(lost.getTemporadas().size(), 0);
	}

	public void testEsSerie() {
		assertTrue(barney.esSerie());
	}

	public void testGetTemporadas() {
		assertEquals(barney.getTemporadas().size(), 2);
		assertTrue(barney.getTemporadas().contains(barneyS01));
		assertTrue(barney.getTemporadas().contains(barneyS02));
	}

	public void testAgregarTemporada() {
		assertEquals(barney.getTemporadas().size(), 2);
		Temporada bbS03 = new Temporada(barney, 3);
		assertEquals(barney.getTemporadas().size(), 3);
		assertTrue(barney.getTemporadas().contains(bbS03));
	}

}
