package hibernate;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/hibernate.cfg.xml");
		consultaA(cfg);
		consultaB(cfg, "Simpsons");
		consultaE(cfg, 2);
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
			System.out.println("\nb. Listar las series cuyo título contenga la secuencia de caracteres: " +title);
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
	 * Lista el email de los usuarios que reprodujeron más de number películas.
	 * @param cfg
	 * @param number
	 */
	public static void consultaE(Configuration cfg, int number) {
//		SessionFactory sessions = cfg.buildSessionFactory();
//		Session session = sessions.openSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			Query hqlQuery = session.createQuery("select u.email from model.Usuario.GestorDeContenidos c where c.class = 'PELICULA'");
//			hqlQuery.setParameter("number", number);
//			List<String> list = (List<String>) hqlQuery.list();
//			session.flush();
//			tx.commit();
//			System.out.println("\ne. Listar los usuarios que reprodujeron más de "+number+" películas");
//			for (String string : list) {
//				System.out.println("El usuario con email: " + string + " ha realizado "+number+" películas.");
//			}			
//
//		}catch (Exception e){
//			e.printStackTrace();
//			if (tx != null) {
//				tx.rollback();
//			}
//			session.close();
//		}
//		session.disconnect() ;
	}

}
