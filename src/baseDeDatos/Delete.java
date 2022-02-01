package baseDeDatos;

import org.hibernate.Transaction;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import Controlador.HibernateUtil;
import modelo.FavoritosEspacios;

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
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "ON DELETE CASCADE FROM MunicipiosPueblos";
		Query q = (Query) session.createQuery(hql);
		
		
	}
	
}
