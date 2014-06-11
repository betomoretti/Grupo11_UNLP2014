package hibernate;

import java.io.IOException;
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

public class BetoQueries {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/hibernate.cfg.xml");
		consultaD(cfg);
	}
	
	public static void consultaD(Configuration cfg) throws IOException {
//		Informar la película más vista en un determinado año (donde el año es parametrizable
// 		Imprimir en consola: "El título de la Película más vista en el año: "..." es: "
		
		int year = System.in.read();
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
			if (result.size() > 0) {
				System.out.println("Pelicula mas vista " + year + " "+result.get(0)[0] + " reproducciones" + result.get(1)[1]);
			} else {
				System.out.println(result);
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
	
	public static void consultaG(Configuration cfg) throws IOException {
//		Listar las n películas más vistas en el sistema. Imprimir en consola: "La Película: "..."ha sido
//		vista: "..."veces"

		int year = System.in.read();
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "select p.titulo, count(*) as cant from model.Reproduccion r, model.Pelicula p where r.reproducible.class = 'Pelicula' and r.reproducible.id = p.id and year(r.fecha) = :year group by p.id order by cant desc";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setParameter("year", year).setMaxResults(3);
			List<Object[]> result=(List<Object[]>) hqlQuery.list();
			session.flush();
			tx.commit();
			if (result.size() > 0) {
            	for (Object elem : result){
                	System.out.println("Pelicula mas vista " + year + " "+result.get(0)[0] + " reproducciones" + result.get(1)[1]);   
                }  
			} else {
				System.out.println(result);
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
