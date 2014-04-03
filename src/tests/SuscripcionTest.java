package tests;

import junit.framework.TestCase;
import model.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SuscripcionTest extends TestCase {

	private Suscripcion invitado;
	private Categoria categoriaInvitado, categoriaNormal, categoriaVIP;
	private Usuario nene;
	private Catalogo catalogo;
	private Pelicula bambi;
	private Date hace5anios;
	private GestorDeContenidos gestorNene;

	protected void setUp() throws Exception {
		catalogo = new Catalogo();

		bambi = new Pelicula("bambi", 0, 142000);
		// Usuarios
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);
		hace5anios = cal.getTime();
		nene = new Usuario(hace5anios, "nene@db2.edu.ar",
				format.parse("09/09/2013"), catalogo);
		gestorNene = nene.getGestor();

		invitado = nene.getSuscripcion();
		categoriaInvitado = new CategoriaInvitado();
		categoriaNormal = new Categoria();
		categoriaVIP = new CategoriaVIP();
	}

	public void testSuscripcion() {
		Suscripcion nueva = new Suscripcion(hace5anios);
		assertEquals(nueva.getFecha(), hace5anios);
		assertEquals(nueva.limiteDeReproducciones(),
				categoriaInvitado.limiteDeReproducciones());
	}

	public void testPasarANormal() {
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaInvitado.limiteDeReproducciones());
		for (int i = 0; i < 20; i++) {
			gestorNene.registrarReproduccion(bambi, new Date(), 100000);
		}
		assertEquals(gestorNene.getReproducciones().size(), 10);
		invitado.pasarANormal();
		for (int i = 0; i < 100; i++) {
			gestorNene.registrarReproduccion(bambi, new Date(), 100000);
		}
		assertEquals(gestorNene.getReproducciones().size(),
				categoriaNormal.limiteDeReproducciones());
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaNormal.limiteDeReproducciones());
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaNormal.limiteDeReproducciones());
		invitado.pasarANormal();
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaNormal.limiteDeReproducciones());
	}

	public void testPasarAVIP() {
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaInvitado.limiteDeReproducciones());
		invitado.pasarAVIP();
		for (int i = 0; i < 800; i++) {
			gestorNene.registrarReproduccion(bambi, new Date(), 100000);
		}
		assertEquals(gestorNene.getReproducciones().size(),
				categoriaVIP.limiteDeReproducciones());
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaVIP.limiteDeReproducciones());
		invitado.pasarAVIP();
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaVIP.limiteDeReproducciones());
	}
}
