package baseDeDatos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Controlador.HibernateUtil;
import modelo.Datos;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.FavoritosEspacios;
import modelo.Fotosespacio;
import modelo.Localidad;
import modelo.Municipiospueblos;
import modelo.Provincia;
import modelo.Usuarios;

import java.util.HashSet;
import java.util.Set;

public class Inserts {

	public static void insertImage(int espaciNatural, byte[] imagen) {
		Fotosespacio fotosespacio = new Fotosespacio();
		fotosespacio.setImg(imagen);
		fotosespacio.setCodEspacio(espaciNatural);

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(fotosespacio);
		// Actualizar informaci�n en la base de datos
		tx.commit();

	}

	public static void insertUsuarios(int code, String nombre, int clave) {
		Usuarios us = new Usuarios();
		us.setCodUsuario(code);
		// TODO
		us.setNombre(nombre);
		us.setContrasenia(clave);

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(us);
		// Actualizar informaci�n en la base de datos
		tx.commit();

		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static boolean insertUsuarios(Usuarios us) {

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(us);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		// Visualiza los datos del departamento 30
		String hql = "from Usuarios where Contrasenia = '" + us.getContrasenia() + "'";
		Query q = s.createQuery(hql);
		Usuarios u1 = (Usuarios) q.uniqueResult();

		s.close();

		if (u1 != null) {

			return true;

		} else {

			return false;

		}

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertProvincia(int code, String nombre) {
		Provincia pv = new Provincia();
		pv.setCodProvincia(code);
		pv.setNombre(nombre);
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(pv);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertProvincia(Provincia pv) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(pv);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertMunicipio(int code, String nombre, String descripcion, Set localidad, Provincia provincia,
			Set espaciosNaturaleses, Set estacioneses, Set favoritosMunicipioses) {
		Municipiospueblos mun = new Municipiospueblos();
		mun.setCodMunicipio(code);
		mun.setNombre(nombre);
		mun.setDescripcion(descripcion);
		// TODO
		mun.setLocalidads(localidad);
		mun.setProvincia(provincia);
		// mun.setEspaciosNaturaleses(espaciosNaturaleses);
		mun.setEstacioneses(estacioneses);

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(mun);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertMunicipio(Municipiospueblos mun) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(mun);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertLocalidad(int code, String nombre, String descripcion,
			Municipiospueblos municipiospueblos) {
		Localidad loc = new Localidad();
		loc.setCodLocalidad(code);
		loc.setNombre(nombre);
		// loc.setDescripcion(descripcion);
		loc.setMunicipiospueblos(municipiospueblos);

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(loc);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertLocalidad(Localidad loc) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(loc);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertFavoritosMunicipios() {
	}

	public static boolean insertFavoritosEspacios(int user, String place) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();
		FavoritosEspacios fav = new FavoritosEspacios();
		fav.setCodUsuario(user);
		fav.setNombreEspacio(place);
		// Guardar objeto en la base de datos
		s.save(fav);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */
		return true;
	}

	public static void insertFavoritosEspacios(FavoritosEspacios fav) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(fav);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertEstaciones() {
	}

	public static void insertEstaciones(Estaciones est) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(est);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertEspaciosNaturales() {
	}

	public static void insertEspaciosNaturales(EspaciosNaturales esp) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(esp);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}

	public static void insertDatos(Datos datos) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(datos);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/*
		 * if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close();
		 */
	}

	public static void insertObjeto(Object obj) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(obj);
		// Actualizar informaci�n en la base de datos
		tx.commit();
		// TODO

		/* if (s.isConnected()) s.close(); if (!sesion.isClosed()) sesion.close(); */

	}
}
