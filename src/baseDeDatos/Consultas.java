package baseDeDatos;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Controlador.HibernateUtil;
import modelo.Usuarios;

public class Consultas {
	public static boolean consultarUsuario(String user, String contra) {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Usuarios as us where us.nombre = " + user + "and us.contrasenia " + contra;
		Query q = (Query) session.createQuery(hql);
		Usuarios us = (Usuarios) ((org.hibernate.Query) q).uniqueResult();
		if (us != null) {
			return true;
		} else {
			return false;
		}

	}
}
