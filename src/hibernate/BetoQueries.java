package hibernate;

import java.io.*;
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
		consultaJ(cfg);
	}
	
	

	/**
	 * Listar los usuarios que estén a menos de una cantidad dada de reproducciones para llegar
	 * al límite de las mismas para su categoría. Imprimir en consola:"Mail del usuario: "
	 * @param cfg
	 * @throws IOException
	 */
	public static void consultaJ(Configuration cfg) throws IOException{
		
		BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
		int limiteIngresado;
		System.out.println("Ingrese la cantidad de diferencia del limite de reproducciones: ");
		limiteIngresado = Integer.parseInt(lectura.readLine());
		SessionFactory sessions = cfg.buildSessionFactory();
		Session session = sessions.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "select u.email from model.Usuario u where :limiteIngresado > (u.suscripcion.categoria.limiteDeReproducciones - (select count(*) from model.Reproduccion r where r in elements(u.gestor.reproducciones)))";
			Query hqlQuery = session.createQuery(hql);
			hqlQuery.setParameter("limiteIngresado", limiteIngresado);
			List<Object[]> result=(List<Object[]>) hqlQuery.list();
			session.flush();
			tx.commit();
			for (Object[] elem : result){
            	System.out.println("Mail del usuario: " + String.valueOf(elem[0]));   
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
