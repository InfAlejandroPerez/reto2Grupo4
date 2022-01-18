package baseDeDatos;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Controlador.HibernateUtil;
import modelo.Municipiospueblos;

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

	static Municipiospueblos consultarCodigoMunicipio(String municipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		// Visualiza los datos del departamento 30
		String hql = "from Municipiospueblos as m where m.nombre = '" + municipio + "'";
		Query q = session.createQuery(hql);
		Municipiospueblos m = (Municipiospueblos) q.uniqueResult();

		session.close();

		return m;
	}
}
