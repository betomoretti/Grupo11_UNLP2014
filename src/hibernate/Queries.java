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
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/hibernate.cfg.xml");
		consultaA(cfg);
		consultaB(cfg, "%Sim%");
		consultaD(cfg, 2013);
		consultaE(cfg, new Long(10));
		consultaF(cfg);
		consultaG(cfg, 3);
		consultaJ(cfg, 30);
	}
	
	/**
	 * Lista el nombre de todas las series del sistema
	 * @param cfg
	 */
	public static void consultaA(Configuration cfg) {
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			Query hqlQuery = session.createQuery("from model.Serie");
			tx = session.beginTransaction();
			List<Serie> list = (List<Serie>) hqlQuery.list();
			session.flush();
			tx.commit();
			System.out.println("\na. Listar el nombre de todas las series del sistema.");
			for (Serie serie : list){
				System.out.println("Titulo de serie: " + serie.getTitulo());
			}
			System.out.println();
		}catch (Exception e){
			e.printStackTrace();
			if (tx != null){
				tx.rollback();
			}
			session.close();
		}
		session.disconnect() ;
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
			System.out.println("\nb. Listar las series cuyo título contenga la secuencia de caracteres '" +title+"'");
			for (Serie serie : list) {
				System.out.println("Título de serie: " + serie.getTitulo());
			}			
			System.out.println();
		}catch (Exception e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}
		session.disconnect() ;
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
			System.out.println("\nd. Informar la película más vista en el año "+year+". \nPelicula más vista de " + year + ": "+result.get(0)[0] + " (" + result.get(0)[1] + " reproducciones).");						
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}
		session.disconnect() ;
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
			System.out.println("\ne. Listar los usuarios que reprodujeron más de "+number+" películas");
			for (Object[] object : list) {
				System.out.println("El usuario '" + object[0] + "' ha realizado "+object[1]+" reproducciones.");
			}			

		}catch (Exception e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}
		session.disconnect() ;
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
			System.out.println("\nf. Listar los usuarios que vieron al menos un episodio por menos de 65 segundos.");
			for (String string : list) {
				System.out.println("Mail del usuario: " + string);
			}			
			
		}catch (Exception e){
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			session.close();
		}
		session.disconnect() ;
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
			System.out.println("\ng. Listar las "+cant+" películas más vistas en el sistema");
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
		session.disconnect() ;
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
			System.out.println("\nj. Listar usuarios que estén a menos de "+limiteIngresado+" reproducciones de llegar al límite de su categoría.");
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
		session.disconnect() ;
	}

}
