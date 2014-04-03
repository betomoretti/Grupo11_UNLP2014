package tests;

import junit.framework.TestCase;
import model.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GestorDeContenidosTest extends TestCase {

	private GestorDeContenidos gestorNene, gestorAdulto;
	private Usuario nene, adulto;
	private Catalogo catalogo;
	private Pelicula reservoir_dogs, pulp_fiction, shrek, bambi;
	private Serie breaking_bad, barney;
	private Temporada bbS01, barneyS01, barneyS02;
	private Episodio pilot, catInTheBag, queen, family, autumn;

	public void setUp() throws Exception {
		catalogo = new Catalogo();

		// Peliculas
		reservoir_dogs = new Pelicula("Reservoir Dogs", 14, 130000);
		pulp_fiction = new Pelicula("Pulp Fiction", 16, 135000);
		shrek = new Pelicula("shrek", 4, 135000);
		bambi = new Pelicula("bambi", 0, 142000);

		// Breaking Bad
		breaking_bad = new Serie("Breaking Bad", 14);
		bbS01 = new Temporada(breaking_bad, 1);
		pilot = new Episodio(40000, 1, "Pilot", bbS01);
		catInTheBag = new Episodio(40000, 1, "The Cat's In The Bag", bbS01);

		// Barney
		barney = new Serie("barney", 0);
		barneyS01 = new Temporada(barney, 1);
		barneyS02 = new Temporada(barney, 2);
		queen = new Episodio(20000, 1, "The Queen of Make Believe", barneyS01);
		family = new Episodio(20000, 1, "My Family's Just Right for Me",
				barneyS01);
		autumn = new Episodio(20000, 1, "Falling For Autumn", barneyS02);

		// Catalogo
		catalogo.agregar(pulp_fiction);
		catalogo.agregar(reservoir_dogs);
		catalogo.agregar(pulp_fiction);
		catalogo.agregar(shrek);
		catalogo.agregar(bambi);
		catalogo.agregar(breaking_bad);
		catalogo.agregar(barney);

		// Usuarios
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
		gestorNene = nene.getGestor();
		gestorAdulto = adulto.getGestor();
	}
	
	public void testGestorDeContenidos(){
		GestorDeContenidos nuevo = new GestorDeContenidos(nene, catalogo);
		assertEquals(nuevo.getUsuario(), nene);
		assertEquals(nuevo.getCatalogo(), catalogo);
		assertNotNull(nuevo.getReproducciones());
		assertEquals(nuevo.getReproducciones().size(), 0);
	}

	public void testEpisodiosVistos() {
		assertEquals(0, gestorNene.episodiosVistos().size());
		gestorNene.registrarReproduccion(queen, new Date(), 100000);
		assertEquals(1, gestorNene.episodiosVistos().size());
		assertTrue(gestorNene.episodiosVistos().contains(queen));
		gestorNene.registrarReproduccion(autumn, new Date(), 100000);
		assertEquals(2, gestorNene.episodiosVistos().size());
		assertTrue(gestorNene.episodiosVistos().contains(autumn));
		assertTrue(gestorNene.episodiosVistos().contains(queen));
	}

	public void testPeliculasVistas() {
		assertEquals(0, gestorNene.peliculasVistas().size());
		gestorNene.registrarReproduccion(shrek, new Date(), 100000);
		assertEquals(1, gestorNene.peliculasVistas().size());
		assertTrue(gestorNene.peliculasVistas().contains(shrek));
		gestorNene.registrarReproduccion(bambi, new Date(), 100000);
		assertEquals(2, gestorNene.peliculasVistas().size());
		assertTrue(gestorNene.peliculasVistas().contains(bambi));
		assertTrue(gestorNene.peliculasVistas().contains(shrek));
	}

	public void testEpisodiosSinVerDe() {
		assertEquals(2, gestorNene.episodiosSinVerDe(barneyS01).size());
		assertTrue(gestorNene.episodiosSinVerDe(barneyS01).contains(queen));
		assertTrue(gestorNene.episodiosSinVerDe(barneyS01).contains(family));
		// Mira el primero
		gestorNene.registrarReproduccion(queen, new Date(), 100000);
		assertEquals(1, gestorNene.episodiosSinVerDe(barneyS01).size());
		assertFalse(gestorNene.episodiosSinVerDe(barneyS01).contains(queen));
		assertTrue(gestorNene.episodiosSinVerDe(barneyS01).contains(family));
		// Mira el segundo
		gestorNene.registrarReproduccion(family, new Date(), 100000);
		assertEquals(0, gestorNene.episodiosSinVerDe(barneyS01).size());
		assertFalse(gestorNene.episodiosSinVerDe(barneyS01).contains(queen));
		assertFalse(gestorNene.episodiosSinVerDe(barneyS01).contains(family));
		// Temporada 2 intacta
		assertEquals(1, gestorNene.episodiosSinVerDe(barneyS02).size());
		assertTrue(gestorNene.episodiosSinVerDe(barneyS02).contains(autumn));
	}

	public void testPeliculasAptas() {
		assertEquals(4, gestorAdulto.peliculasAptas().size());
		assertEquals(2, gestorNene.peliculasAptas().size());
		assertFalse(gestorNene.peliculasAptas().contains(pulp_fiction));
		assertFalse(gestorNene.peliculasAptas().contains(reservoir_dogs));
		assertTrue(gestorNene.peliculasAptas().contains(shrek));
		assertTrue(gestorNene.peliculasAptas().contains(bambi));
		assertTrue(gestorAdulto.peliculasAptas().contains(pulp_fiction));
		assertTrue(gestorAdulto.peliculasAptas().contains(reservoir_dogs));
		assertTrue(gestorAdulto.peliculasAptas().contains(shrek));
		assertTrue(gestorAdulto.peliculasAptas().contains(bambi));
	}

	public void testRegistrarReproduccion() {
		assertTrue(gestorAdulto.getReproducciones().isEmpty());
		assertTrue(gestorNene.getReproducciones().isEmpty());
		gestorAdulto.registrarReproduccion(shrek, new Date(), 100000);
		assertEquals(1, gestorAdulto.getReproducciones().size());
		gestorAdulto.registrarReproduccion(catInTheBag, new Date(), 100000);
		assertEquals(2, gestorAdulto.getReproducciones().size());
		gestorNene.registrarReproduccion(catInTheBag, new Date(), 100000);
		assertTrue(gestorNene.getReproducciones().isEmpty());
	}

	public void testPuedeReproducir() {
		assertTrue(gestorNene.puedeReproducir(shrek));
		assertTrue(gestorNene.puedeReproducir(bambi));
		assertFalse(gestorNene.puedeReproducir(pulp_fiction));
		assertTrue(gestorAdulto.puedeReproducir(shrek));
		assertTrue(gestorAdulto.puedeReproducir(bambi));
		assertTrue(gestorAdulto.puedeReproducir(pulp_fiction));
		for (int i = 0; i < 9; i++) {
			gestorNene.registrarReproduccion(shrek, new Date(), 100000);
			gestorAdulto.registrarReproduccion(shrek, new Date(), 100000);
		}
		assertTrue(gestorNene.puedeReproducir(bambi));
		assertFalse(gestorNene.puedeReproducir(pulp_fiction));
		assertTrue(gestorAdulto.puedeReproducir(bambi));
		gestorNene.registrarReproduccion(shrek, new Date(), 100000);
		gestorAdulto.registrarReproduccion(shrek, new Date(), 100000);
		assertFalse(gestorNene.puedeReproducir(bambi));
		assertFalse(gestorNene.puedeReproducir(pulp_fiction));
		assertFalse(gestorAdulto.puedeReproducir(bambi));
	}

	public void testGetReproducciones() {
		assertEquals(0, gestorNene.getReproducciones().size());
		assertEquals(0, gestorAdulto.getReproducciones().size());
		gestorNene.registrarReproduccion(shrek, new Date(), 100000);
		gestorNene.registrarReproduccion(bambi, new Date(), 100000);
		gestorNene.registrarReproduccion(queen, new Date(), 100000);
		gestorNene.registrarReproduccion(autumn, new Date(), 100000);
		assertEquals(4, gestorNene.getReproducciones().size());
		gestorNene.registrarReproduccion(pilot, new Date(), 100000);
		gestorAdulto.registrarReproduccion(pilot, new Date(), 100000);
		assertEquals(4, gestorNene.getReproducciones().size());
		assertEquals(1, gestorAdulto.getReproducciones().size());
	}
}
