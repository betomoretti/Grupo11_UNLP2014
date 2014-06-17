package hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;



import java.util.logging.Level;

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
import org.jboss.logging.Logger;

public class Queries {
	
	public static void main(String[] args) throws IOException {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/hibernate.cfg.xml");
		consultaA(cfg);
		consultaB(cfg, "%Sim%");
		consultaC(cfg);
		consultaD(cfg, 2013);
		consultaE(cfg, new Long(10));
		consultaF(cfg);
		consultaG(cfg, 3);
		consultaH(cfg, "Be a Friend");
		consultaI(cfg);
		consultaJ(cfg, 30);
	}
	
	/**
	 * Lista el nombre de todas las series del sistema
	 * @param cfg
	 */
	public static void consultaA(Configuration cfg) {
		System.out.println("\nA) Listar el nombre de todas las series del sistema.\n");
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			Query hqlQuery = session.createQuery("from model.Serie");
			tx = session.beginTransaction();
			List<Serie> list = (List<Serie>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (Serie serie : list){
				System.out.println("Titulo de serie: " + serie.getTitulo());
			}
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null){
				tx.rollback();
			}
		} finally {
			session.close();
		}

	}
	/**
	 * Lista el nombre de todas las series del sistema que contienen el string title.
	 * @param cfg
	 * @param title
	 */
	public static void consultaB(Configuration cfg, String title) {
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			Query hqlQuery = session.createQuery("from model.Serie s where s.titulo like (:title)");
			tx = session.beginTransaction();
			hqlQuery.setParameter("title", title);
			List<Serie> list = (List<Serie>) hqlQuery.list();
			session.flush();
			tx.commit();

			System.out.println("\nB) Listar las series cuyo título contenga la secuencia de caracteres '" + title + "'\n");
			for (Serie serie : list) {
				System.out.println("Título de serie: " + serie.getTitulo());
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	/**
	* Listar los 5 episodios de series mÃ¡s vistos en el sistema.
	* Imprimir en consola: "La Serie:" ..."ha sido vista "...."veces"
	* @param cfg
	**/
	public static void consultaC(Configuration cfg) throws IOException{
		System.out.println("\nC) Listar los 5 episodios de series mÃ¡s vistos en el sistema.\n");
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
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
	
	/**
	 * Informar la película más vista en un determinado año (donde el año es parametrizable
	 * Imprimir en consola: "El título de la Película más vista en el año: "..." es: "
	 * @param cfg
	 * @param year
	 */
	public static void consultaD(Configuration cfg, int year) throws IOException{
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			String hql = "select p.titulo, count(*) as cant from model.Reproduccion r, model.Pelicula p where r.reproducible.class = 'Pelicula' and r.reproducible.id = p.id and year(r.fecha) = :year group by p.id order by cant desc";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setParameter("year", year).setMaxResults(1);
			tx = session.beginTransaction();
			List<Object[]> result=(List<Object[]>) hqlQuery.list();
			session.flush();
			tx.commit();
			System.out.println("\nD) Informar la película más vista en el año "+year+".\n\nPelicula más vista de " + year + ": "+result.get(0)[0] + " (" + result.get(0)[1] + " reproducciones).\n");
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
	
	/**
	 * Lista el email de los usuarios que reprodujeron mas de number peliculas.
	 * @param cfg
	 * @param number
	 */
	public static void consultaE(Configuration cfg, Long number) {
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			Query hqlQuery = session.createQuery("select u.email, count(*) from Usuario u inner join u.gestor g inner join g.reproducciones r where r.reproducible.class = 'PELICULA' group by u.email having count(*)>:number order by count(*) desc ");
			hqlQuery.setParameter("number", number);
			tx = session.beginTransaction();
			List<Object[]> list = hqlQuery.list();
			session.flush();
			tx.commit();

			System.out.println("\nE) Listar los usuarios que reprodujeron más de "+number+" películas\n");
			for (Object[] object : list) {
				System.out.println("El usuario '" + object[0] + "' ha realizado "+object[1]+" reproducciones.");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	/**
	 * Listar los usuarios que vieron al menos un episodio por menos de 65 segundos.
	 * @param cfg
	 * @param number
	 */
	public static void consultaF(Configuration cfg) {
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			Query hqlQuery = session.createQuery("select u.email from Usuario u inner join u.gestor g inner join g.reproducciones r where r.reproducible.class = 'EPISODIO' and r.tiempo<(:tiempo) group by u.email");
			hqlQuery.setParameter("tiempo", new Long(65000));
			tx = session.beginTransaction();
			List<String> list = hqlQuery.list();
			session.flush();
			tx.commit();
			System.out.println("\nF) Listar los usuarios que vieron al menos un episodio por menos de 65 segundos.\n");
			for (String string : list) {
				System.out.println("Mail del usuario: " + string);
			}			
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
	
	/**
	 *  Listar las n películas más vistas en el sistema. Imprimir en consola: "La Película: "..."ha sido
	 *	vista: "..." veces"
	 * 
	 * @param cfg
	 * @param cant
	 */
	public static void consultaG(Configuration cfg, int cant) throws IOException {
		
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			String hql = "select p.titulo, count(*) as cant from model.Reproduccion r, model.Pelicula p where r.reproducible.class = 'Pelicula' and r.reproducible.id = p.id group by p.id order by cant desc";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setMaxResults(cant);
			tx = session.beginTransaction();
			List<Object[]> result=(List<Object[]>) hqlQuery.list();
			session.flush();
			tx.commit();
			System.out.println("\nG) Listar las "+cant+" películas más vistas en el sistema\n");
        	for (Object[] elem : result){
            	System.out.println("Pelicula '" + elem[0] + "' vista " + elem[1] + " veces");   
            }
            System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	/*
	* Listar los usuarios que vieron un episodio cuyo título se ingresa por parámetro.
	* Imprimir en consola:"Mail del usuario: "
	* @param cfg
	* @param title
	*/
	public static void consultaH(Configuration cfg, String title) throws IOException{
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			System.out.println("\nH) Listar los usuarios que vieron un episodio cuyo titulo se ingresa por parametro\n");
			tx = session.beginTransaction();
			String hql = "select distinct u.email from Usuario u inner join u.gestor g inner join g.reproducciones r, model.Episodio p where r.reproducible.class = 'EPISODIO' and p.id = r.reproducible.id and p.titulo = :title";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setParameter("title", title);
			List<String> result = (List<String>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (String email : result) {
				System.out.println("Mail del usuario: '" + email);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
	


	/**
	* Listar usuarios que reprodujeron al menos una pelÃ­cula cuya edad mÃ­nima sea 12 aÃ±os.
	* Imprimir en consola: "Mail del usuario: "
	*
	* @param cfg
	**/
	public static void consultaI(Configuration cfg) throws IOException{
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			System.out.println("\nI) Listar usuarios que reprodujeron al menos una pelÃ­cula cuya edad minima sea 12 anos\n");
			tx = session.beginTransaction();
			String hql = "select distinct u.email from Usuario u inner join u.gestor g inner join g.reproducciones r, model.Pelicula p where r.reproducible.class = 'PELICULA' and p.id = r.reproducible.id and p.edadMinima = 12";
			Query hqlQuery = session.createQuery(hql);
			List<String> result = (List<String>) hqlQuery.list();
			session.flush();
			tx.commit();

			for (String email : result) {
				System.out.println("Mail del usuario: '" + email);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
	
	/**
	 * Listar los usuarios que están a menos de una cantidad dada de reproducciones para llegar
	 * al límite de las mismas para su categoría. Imprimir en consola:"Mail del usuario: "
	 * @param cfg
	 * @throws IOException
	 */
	public static void consultaJ(Configuration cfg, long limiteIngresado) throws IOException{
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			String hql = "select u.email from model.Usuario u where :limiteIngresado > (u.suscripcion.categoria.limiteDeReproducciones - (select count(*) from model.Reproduccion r where r in elements(u.gestor.reproducciones)))";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setParameter("limiteIngresado", limiteIngresado);
			tx = session.beginTransaction();
			List<String> result=(List<String>) hqlQuery.list();
			session.flush();
			tx.commit();
			System.out.println("\nJ) Listar usuarios que estén a menos de "+limiteIngresado+" reproducciones de llegar al límite de su categoría.\n");
			for (String elem : result){
            	System.out.println("Mail del usuario: " + elem);
            }
            System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

}
