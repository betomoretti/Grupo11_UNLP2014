package hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import model.Serie;

import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Queries {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/hibernate.cfg.xml");
		consultaA(cfg);
		consultaB(cfg, "Sim");
		consultaC(cfg);
		consultaD(cfg);
		consultaE(cfg,new Long(10));
		consultaF(cfg);
		consultaG(cfg);
		consultaI(cfg);
		consultaJ(cfg);
	}
	
	/**
	 * Lista el nombre de todas las series del sistema
	 * @param cfg
	 */
	public static void consultaA(Configuration cfg) {
		System.out.println("\nA) Listar el nombre de todas las series del sistema.");
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query hqlQuery = session.createQuery("from model.Serie");
			List<Serie> list = (List<Serie>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (Serie serie : list){
				System.out.println("Titulo de serie: " + serie.getTitulo());
			}

		}catch (Exception e){
			e.printStackTrace();
			if (tx != null){
				tx.rollback();
			}
			session.close();
		}

		System.out.println();
		session.disconnect() ;
	}
	/**
	 * Lista el nombre de todas las series del sistema que contienen el string title.
	 * @param cfg
	 * @param title
	 */
	public static void consultaB(Configuration cfg, String title) {
		System.out.println("\nB) Listar las series cuyo título contenga la secuencia de caracteres: " + title);
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query hqlQuery = session.createQuery("from model.Serie s where s.titulo like (:title)");
			hqlQuery.setParameter("title", "%"+title+"%");
			List<Serie> list = (List<Serie>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (Serie serie : list) {
				System.out.println("Titulo de serie: " + serie.getTitulo());
			}

		}catch (Exception e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}

		System.out.println();
		session.disconnect() ;
	}

	/**
	* Listar los 5 episodios de series más vistos en el sistema.
	* Imprimir en consola: "La Serie:" ..."ha sido vista "...."veces"
	* @param cfg
	**/
	public static void consultaC(Configuration cfg) throws IOException{
		System.out.println("\nC) Listar los 5 episodios de series más vistos en el sistema.");
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			String hql = "select e.titulo, count(*) as cant from model.Reproduccion r, model.Episodio e where r.reproducible.class = 'Episodio' and r.reproducible.id = e.id group by e.id order by cant desc";
			tx = session.beginTransaction();
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setMaxResults(5);
			List<Object[]> result=(List<Object[]>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (Object []r : result) {
				System.out.println("Episodio '" + r[0] +"' has sido visto " + r[1] + " veces");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}

		System.out.println();
		session.disconnect();
	}
	
	/**
	 * Informar la película más vista en un determinado año (donde el año es parametrizable
	 * Imprimir en consola: "El título de la Película más vista en el año: "..." es: "
	 * @param cfg
	 */
	public static void consultaD(Configuration cfg) throws IOException{
		System.out.println("\nD)Informar la película más vista en un determinado año (donde el año es parametrizable).");
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		int year;
		System.out.println("Ingrese año: ");
		year = Integer.parseInt(lectura.readLine());
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "select p.titulo, count(*) as cant from model.Reproduccion r, model.Pelicula p where r.reproducible.class = 'Pelicula' and r.reproducible.id = p.id and year(r.fecha) = :year group by p.id order by cant desc";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setParameter("year", year).setMaxResults(1);
			List<Object[]> result=(List<Object[]>) hqlQuery.list();
			session.flush();
			tx.commit();

			System.out.println("Pelicula mas vista de " + year + ":" + result.get(0)[0] + "(" + result.get(0)[1] + " reproducciones)\n");

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}
		session.disconnect();
	}
	
	/**
	 * Lista el email de los usuarios que reprodujeron m�s de number pel�culas.
	 * @param cfg
	 * @param number
	 */
	public static void consultaE(Configuration cfg, Long number) {
		System.out.println("\nE) Listar los usuarios que reprodujeron mas de " + number + " peliculas.");

		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query hqlQuery = session.createQuery("select u.email from Usuario u inner join u.gestor g inner join g.reproducciones r where r.reproducible.class = 'PELICULA' group by u.email having count(u.email)>:number");
			hqlQuery.setParameter("number", number);
			List<String> list = (List<String>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (String string : list) {
				System.out.println("El usuario con email: " + string + " ha reproducido mas de " + number + " peliculas.");
			}

		}catch (Exception e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}

		System.out.println();
		session.disconnect() ;
	}

	/**
	* Listar los usuarios que vieron al menos un episodio por menos de 65 segundos (65000 milisegundos).
	* Imprimir en consola: "Mail del usuario: "
	*
	* @param cfg
	**/
	public static void consultaF(Configuration cfg) throws IOException{
		System.out.println("\nD) Listar los usuarios que vieron al menos un episodio por menos de 65 segundos (65000 milisegundos)");
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "select distinct u.email from Usuario u inner join u.gestor g inner join g.reproducciones r where r.reproducible.class = 'EPISODIO' and r.tiempo < 65000";
			Query hqlQuery = session.createQuery(hql);
			List<String> result = (List<String>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (String email : result) {
				System.out.println("Mail del usuario: '" + email);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}

		System.out.println();
		session.disconnect();
	}

	/**
	 *  Listar las n películas más vistas en el sistema. Imprimir en consola: "La Película: "..."ha sido
	 *	vista: "..."veces"
	 * 
	 * @param cfg
	 */
	public static void consultaG(Configuration cfg) throws IOException {
		
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		int cant;
		System.out.println("Cantidad de peliculas a listar: ");
		cant = Integer.parseInt(lectura.readLine());
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "select p.titulo, count(*) as cant from model.Reproduccion r, model.Pelicula p where r.reproducible.class = 'Pelicula' and r.reproducible.id = p.id group by p.id order by cant desc";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setMaxResults(cant);
			List<Object[]> result=(List<Object[]>) hqlQuery.list();
			session.flush();
			tx.commit();

        	for (Object[] elem : result){
            	System.out.println("Pelicula '" + elem[0] + "' vista " + elem[1] + " veces");   
            }

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}

		System.out.println();
		session.disconnect() ;
	}

	/**
	* Listar usuarios que reprodujeron al menos una película cuya edad mínima sea 12 años.
	* Imprimir en consola: "Mail del usuario: "
	*
	* @param cfg
	**/
	public static void consultaI(Configuration cfg) throws IOException{
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			System.out.println("\nI) Listar usuarios que reprodujeron al menos una película cuya edad mínima sea 12 años");
			tx = session.beginTransaction();
			String hql = "select distinct u.email from Usuario u inner join u.gestor g inner join g.reproducciones r, model.Pelicula p where r.reproducible.class = 'PELICULA' and p.id = r.reproducible.id and p.edadMinima = 12";
			Query hqlQuery = session.createQuery(hql);
			List<String> result = (List<String>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (String email : result) {
				System.out.println("Mail del usuario: '" + email);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}

		System.out.println();
		session.disconnect();
	}
	
	/**
	 * Listar los usuarios que estén a menos de una cantidad dada de reproducciones para llegar
	 * al límite de las mismas para su categoría. Imprimir en consola:"Mail del usuario: "
	 * @param cfg
	 * @throws IOException
	 */
	public static void consultaJ(Configuration cfg) throws IOException{
		System.out.println("Ingrese la cantidad de diferencia del limite de reproducciones: ");

		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		long limiteIngresado = Long.valueOf(lectura.readLine());
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "select u.email from model.Usuario u where :limiteIngresado > (u.suscripcion.categoria.limiteDeReproducciones - (select count(*) from model.Reproduccion r where r in elements(u.gestor.reproducciones)))";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setParameter("limiteIngresado", limiteIngresado);
			List<String> result=(List<String>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (String elem : result){
            	System.out.println("Mail del usuario: " + elem);   
            } 
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}

		System.out.println();
		session.disconnect() ;
	}

}
