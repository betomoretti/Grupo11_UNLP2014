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

public class BetoQueries {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/hibernate.cfg.xml");
		consultaA(cfg);
	}
	
	
	public static void consultaA(Configuration cfg) {
		
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query hqlQuery = session.createQuery("from model.Serie");
			List<Serie> list = (List<Serie>) hqlQuery.list();
			for ( Serie serie : list) {
				System.out.println("Titulo de serie:" + serie.getTitulo());
				
			}
			session.flush();
			tx.commit();
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
