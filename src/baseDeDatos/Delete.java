package baseDeDatos;

import org.hibernate.Transaction;

import java.util.ArrayList;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import Controlador.HibernateUtil;
import modelo.Actualizar;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.FavoritosEspacios;
import modelo.Provincia;

public class Delete {
	public static boolean deleteFav(int user, String place) {
		boolean ret = true;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = (Transaction) session.beginTransaction();
		int id = baseDeDatos.Consultas.gedCodeFavEspacio(user, place);
		FavoritosEspacios de = (FavoritosEspacios) session.load(FavoritosEspacios.class, id);
		System.out.println("id "+id+" user "+user + " place " + place);
		
		try {
			session.delete(de); // elimina el objeto
			tx.commit();
			System.out.println("Departamento eliminado");
		} catch (ObjectNotFoundException o) {
			System.out.println("NO EXISTE EL DEPARTAMENTO...");
			ret = false;
		} catch (ConstraintViolationException c) {
			System.out.println("NO SE PUEDE ELIMINAR, TIENE EMPLEADOS...");
			ret = false;
		} catch (Exception e) {
			System.out.println("ERROR NO CONTROLADO....");
			ret = false;
			e.printStackTrace();
		}
		
		session.close();
		
		return ret;
	}
	
	public static void DeleteAll() {
		
		ArrayList<Integer> codProvincias = new ArrayList<Integer>();
		
		ArrayList<Estaciones> codEstaciones = new ArrayList<Estaciones>();
		
		ArrayList<EspaciosNaturales> codEspacios = new ArrayList<EspaciosNaturales>();
		
		ArrayList<Actualizar> codActualizar = new ArrayList<Actualizar>();
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "SELECT codProvincia from Provincia";
		Query q = (Query) session.createQuery(hql);
		
		codProvincias = new ArrayList<Integer>(q.list());
		
		for(int i = 0; i < codProvincias.size(); i++) {
			
			Transaction tx = (Transaction) session.beginTransaction();
			
			Provincia de = (Provincia) session.load(Provincia.class, codProvincias.get(i));
			
			session.delete(de); // elimina el objeto
			
			tx.commit();
			
		}
		
		hql = "SELECT codEstacion from Estaciones";
		q = (Query) session.createQuery(hql);
		
		codEstaciones = new ArrayList<Estaciones>(q.list());
		
		for(int i = 0; i < codEstaciones.size(); i++) {
			
			Transaction tx = (Transaction) session.beginTransaction();
			
			Estaciones es = (Estaciones) session.load(Estaciones.class, codEstaciones.get(i));
			
			session.delete(es); // elimina el objeto
			
			tx.commit();
			
		}
		
		hql = "SELECT codEspacio from EspaciosNaturales";
		q = (Query) session.createQuery(hql);
		
		codEspacios = new ArrayList<EspaciosNaturales>(q.list());
		
		for(int i = 0; i < codEspacios.size(); i++) {
			
			Transaction tx = (Transaction) session.beginTransaction();
			
			EspaciosNaturales esp = (EspaciosNaturales) session.load(EspaciosNaturales.class, codEspacios.get(i));
			
			session.delete(esp); // elimina el objeto
			
			tx.commit();
			
		}
		
		hql = "SELECT cod from Actualizar";
		q = (Query) session.createQuery(hql);
		
		codActualizar = new ArrayList<Actualizar>(q.list());
		
		for(int i = 0; i < codActualizar.size(); i++) {
			
			Transaction tx = (Transaction) session.beginTransaction();
			
			Actualizar ac = (Actualizar) session.load(Actualizar.class, codActualizar.get(i));
			
			session.delete(ac); // elimina el objeto
			
			tx.commit();
			
		}
		
	}
	
}
