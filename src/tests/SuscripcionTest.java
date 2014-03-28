package tests;

import junit.framework.TestCase;
import model.*;
import java.util.Date;

public class SuscripcionTest extends TestCase {

	private Suscripcion invitado;
	private Categoria categoriaInvitado, categoriaNormal, categoriaVIP;

	protected void setUp() throws Exception {
		invitado = new Suscripcion(new Date());
		categoriaInvitado = new CategoriaInvitado();
		categoriaNormal = new Categoria();
		categoriaVIP = new CategoriaVIP();
	}

	public void testPasarANormal() {
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaInvitado.limiteDeReproducciones());
		invitado.pasarANormal();
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
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaVIP.limiteDeReproducciones());
		invitado.pasarAVIP();
		assertEquals(invitado.limiteDeReproducciones(),
				categoriaVIP.limiteDeReproducciones());
	}
}
