package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.List;

import Controlador.HibernateUtil;
import modelo.Municipiospueblos;
import modelo.Provincia;
import modelo.Usuarios;

public class Consultas {
	
	public static ArrayList<Provincia> pvs = new ArrayList<Provincia>();
	
	public static ArrayList<Municipiospueblos> munis = new ArrayList<Municipiospueblos>();
	
	public static boolean consultarUsuario(String user, String contra) {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Usuarios where Nombre = '" + user + "' AND Contrasenia = '" + contra + "'";
		Query q = (Query) session.createQuery(hql);
		Usuarios us = (Usuarios) ((org.hibernate.Query) q).uniqueResult();
		
		session.close();
		
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
	
	public static void getProvincias() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "from Provincia";
		Query q = (Query) session.createQuery(hql);
		
		ArrayList<Provincia> pvss = new ArrayList<Provincia>(q.list());
		
		for(int i = 0; i < pvss.size(); i++) {
			
			pvs.add(pvss.get(i));
			
		}
		
	}
	
	public static void getMunicipios(String Provincia) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "from Municipiospueblos Where codProvincia = (Select codProvincia From Provincia Where Nombre = '" + Provincia + "')" ;
		Query q = (Query) session.createQuery(hql);
		
		ArrayList<Municipiospueblos> pvss = new ArrayList<Municipiospueblos>(q.list());
		
		for(int i = 0; i < pvss.size(); i++) {
			
			munis.add(pvss.get(i));
			
		}
		
	}
	
}
