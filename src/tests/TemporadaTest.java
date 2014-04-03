package tests;

import model.Serie;
import model.Temporada;
import junit.framework.TestCase;

public class TemporadaTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testTemporada() {
		Serie breaking_bad = new Serie("Breaking Bad", 14);
		Temporada bbS01 = new Temporada(breaking_bad, 1);
		assertTrue(breaking_bad.getTemporadas().contains(bbS01));
		assertEquals(bbS01.getSerie(), breaking_bad);
		assertEquals(bbS01.getNumero(), 1);
		assertNotNull(bbS01.getEpisodios());
		assertEquals(bbS01.getEpisodios().size(), 0);
	}

}
