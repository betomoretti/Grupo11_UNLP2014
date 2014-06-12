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
		consultaD(cfg);
		consultaE(cfg,new Long(10));
		consultaG(cfg);
		consultaJ(cfg);
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
			tx = session.beginTransaction();
			Query hqlQuery = session.createQuery("from model.Serie");
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
			tx = session.beginTransaction();
			Query hqlQuery = session.createQuery("from model.Serie s where s.titulo like (:title)");
			hqlQuery.setParameter("title", "%"+title+"%");
			List<Serie> list = (List<Serie>) hqlQuery.list();
			session.flush();
			tx.commit();
			System.out.println("\nb. Listar las series cuyo t�tulo contenga la secuencia de caracteres: " +title);
			for (Serie serie : list) {
				System.out.println("Titulo de serie: " + serie.getTitulo());
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
	 */
	public static void consultaD(Configuration cfg) throws IOException{
		
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
			System.out.println("Pelicula mas vista de " + year + ":"+result.get(0)[0] + "(" + result.get(0)[1] + " reproducciones)");						
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
	 * Lista el email de los usuarios que reprodujeron m�s de number pel�culas.
	 * @param cfg
	 * @param number
	 */
	public static void consultaE(Configuration cfg, Long number) {
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
			System.out.println("\ne. Listar los usuarios que reprodujeron m�s de "+number+" pel�culas");
			for (String string : list) {
				System.out.println("El usuario con email: " + string + " ha reproducido m�s de "+number+" pel�culas.");
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
		session.disconnect() ;
	}
	
	/**
	 * Listar los usuarios que estén a menos de una cantidad dada de reproducciones para llegar
	 * al límite de las mismas para su categoría. Imprimir en consola:"Mail del usuario: "
	 * @param cfg
	 * @throws IOException
	 */
	public static void consultaJ(Configuration cfg) throws IOException{
		
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingrese la cantidad de diferencia del limite de reproducciones: ");
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
		session.disconnect() ;
	}

}
