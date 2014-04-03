package tests;

import junit.framework.TestCase;
import model.*;
import java.util.HashSet;

public class CatalogoTest extends TestCase {
	private Catalogo catalogo;
	private Catalogo catalogoVacio;
	private Pelicula pulp_fiction;

	private Pelicula reservoir_dogs;
	private Pelicula four_rooms;
	private Pelicula jackie_brown;
	private Serie breaking_bad;
	private Serie lost;

	public void setUp() throws Exception {
		catalogo = new Catalogo();
		catalogoVacio = new Catalogo();
		reservoir_dogs = new Pelicula("Reservoir Dogs", 14, 130000);
		pulp_fiction = new Pelicula("Pulp Fiction", 16, 135000);
		four_rooms = new Pelicula("Four Rooms", 18, 135000);
		jackie_brown = new Pelicula("Jackie Brown", 12, 142000);
		breaking_bad = new Serie("Breaking Bad", 14);
		lost = new Serie("Lost", 10);
		catalogo.agregar(pulp_fiction);
		catalogo.agregar(reservoir_dogs);
		catalogo.agregar(pulp_fiction);
		catalogo.agregar(four_rooms);
		catalogo.agregar(jackie_brown);
		catalogo.agregar(breaking_bad);
		catalogo.agregar(lost);
	}
	
	public void testCatalogo(){
		Catalogo nuevo = new Catalogo();
		assertNotNull(nuevo.getContenidos());
		assertEquals(nuevo.getContenidos().size(), 0);
	}

	public void testGetContenidos() {
		assertTrue(catalogo.getContenidos().contains(pulp_fiction));
		assertTrue(catalogo.getContenidos().contains(reservoir_dogs));
		assertTrue(catalogo.getContenidos().contains(four_rooms));
		assertTrue(catalogo.getContenidos().contains(jackie_brown));
		assertTrue(catalogo.getContenidos().contains(breaking_bad));
		assertTrue(catalogo.getContenidos().contains(lost));
		assertEquals(6, catalogo.getContenidos().size());
		assertEquals(0, catalogoVacio.getContenidos().size());
	}

	public void testGetSeries() {
		assertFalse(catalogo.getSeries().contains(pulp_fiction));
		assertFalse(catalogo.getSeries().contains(reservoir_dogs));
		assertFalse(catalogo.getSeries().contains(four_rooms));
		assertFalse(catalogo.getSeries().contains(jackie_brown));
		assertTrue(catalogo.getSeries().contains(breaking_bad));
		assertTrue(catalogo.getSeries().contains(lost));
		assertEquals(2, catalogo.getSeries().size());
		assertEquals(0, catalogoVacio.getSeries().size());
	}

	public void testGetPeliculas() {
		assertTrue(catalogo.getPeliculas().contains(pulp_fiction));
		assertTrue(catalogo.getPeliculas().contains(reservoir_dogs));
		assertTrue(catalogo.getPeliculas().contains(four_rooms));
		assertTrue(catalogo.getPeliculas().contains(jackie_brown));
		assertFalse(catalogo.getPeliculas().contains(breaking_bad));
		assertFalse(catalogo.getPeliculas().contains(lost));
		assertEquals(4, catalogo.getPeliculas().size());
		assertEquals(0, catalogoVacio.getPeliculas().size());
	}

	public void testAgregar() {
		assertTrue(catalogo.getContenidos().getClass().equals(HashSet.class));
		catalogoVacio.agregar(pulp_fiction);
		assertTrue(catalogoVacio.getContenidos().contains(pulp_fiction));
		assertEquals(1, catalogoVacio.getContenidos().size());
		catalogoVacio.agregar(breaking_bad);
		assertTrue(catalogoVacio.getContenidos().contains(breaking_bad));
		assertTrue(catalogoVacio.getContenidos().contains(pulp_fiction));
		assertEquals(2, catalogoVacio.getContenidos().size());
		catalogoVacio.agregar(breaking_bad);
		catalogoVacio.agregar(pulp_fiction);
		assertEquals(2, catalogoVacio.getContenidos().size());
	}

}
