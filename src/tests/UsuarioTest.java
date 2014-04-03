package tests;

import junit.framework.TestCase;
import model.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UsuarioTest extends TestCase {

	private Usuario miguel;
	private Usuario juan;
	private Usuario andres;
	private Usuario santiago;
	private Usuario valentino;
	private Date hace20anios;
	private Date hace12anios;
	private Date hace5anios;
	private Date hace20aniosMenosUnDia;
	private Date hace20aniosMasUnDia;

	public void setUp() throws Exception {
		Catalogo catalogo = new Catalogo();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);
		hace5anios = cal.getTime();
		cal.add(Calendar.YEAR, -7);
		hace12anios = cal.getTime();
		cal.add(Calendar.YEAR, -8);
		hace20anios = cal.getTime();
		cal.add(Calendar.DATE, +1);
		hace20aniosMenosUnDia = cal.getTime();
		cal.add(Calendar.DATE, -2);
		hace20aniosMasUnDia = cal.getTime();
		miguel = new Usuario(hace20anios, "miguel@db2.edu.ar",
				format.parse("09/09/2013"), catalogo);
		juan = new Usuario(hace20aniosMenosUnDia, "juan@db2.edu.ar",
				format.parse("09/09/2013"), catalogo);
		andres = new Usuario(hace20aniosMasUnDia, "andres@db2.edu.ar",
				format.parse("09/09/2013"), catalogo);
		santiago = new Usuario(hace12anios, "santiago@db2.edu.ar",
				format.parse("09/09/2013"), catalogo);
		valentino = new Usuario(hace5anios, "valentino@db2.edu.ar",
				format.parse("09/09/2013"), catalogo);
	}

	public void testUsuario() {
		String email = "carlos@carlos.net";
		Usuario carlos = new Usuario(hace20anios, email, hace5anios,
				new Catalogo());
		assertEquals(carlos.getEmail(), email);
		assertEquals(carlos.getFechaNacimiento(), hace20anios);
		assertNotNull(carlos.getGestor());
		assertNotNull(carlos.getSuscripcion());
	}

	public void testEdad() {
		assertEquals(20, miguel.edad());
		assertEquals(19, juan.edad());
		assertEquals(20, andres.edad());
		assertEquals(12, santiago.edad());
		assertEquals(5, valentino.edad());
	}
}
