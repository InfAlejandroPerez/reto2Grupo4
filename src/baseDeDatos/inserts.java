package baseDeDatos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Controlador.HibernateUtil;
import modelo.Datos;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.FavoritosEspacios;
import modelo.FavoritosMunicipios;
import modelo.Localidad;
import modelo.Municipiospueblos;
import modelo.Provincia;
import modelo.Usuarios;

import java.util.HashSet;
import java.util.Set;

public class inserts {
	public void insertUsuarios(int code, String nombre, int clave) {
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
		// Actualizar información en la base de datos
		tx.commit();

		// TODO
		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public void insertUsuarios(Usuarios us) {

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(us);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO
		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public void insertProvincia(int code, String nombre) {
		Provincia pv = new Provincia();
		pv.setCodProvincia(code);
		pv.setNombre(nombre);
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(pv);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public static void insertProvincia(Provincia pv) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(pv);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		/*if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();*/

	}

	public void insertMunicipio(int code, String nombre, String descripcion, Set localidad, Provincia provincia,
			Set espaciosNaturaleses, Set estacioneses, Set favoritosMunicipioses) {
		Municipiospueblos mun = new Municipiospueblos();
		mun.setCodMunicipio(code);
		mun.setNombre(nombre);
		mun.setDescripcion(descripcion);
		// TODO
		mun.setLocalidads(localidad);
		mun.setProvincia(provincia);
		//mun.setEspaciosNaturaleses(espaciosNaturaleses);
		mun.setEstacioneses(estacioneses);
		mun.setFavoritosMunicipioses(favoritosMunicipioses);

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(mun);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public static void insertMunicipio(Municipiospueblos mun) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(mun);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		/*if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();*/

	}

	public void insertLocalidad(int code, String nombre, String descripcion, Municipiospueblos municipiospueblos) {
		Localidad loc = new Localidad();
		loc.setCodLocalidad(code);
		loc.setNombre(nombre);
		//loc.setDescripcion(descripcion);
		loc.setMunicipiospueblos(municipiospueblos);

		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(loc);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public static void insertLocalidad(Localidad loc) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(loc);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		/*if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();*/

	}

	public void insertFavoritosMunicipios() {
	}

	public void insertFavoritosMunicipios(FavoritosMunicipios fav) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(fav);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public void insertFavoritosEspacios() {
	}

	public void insertFavoritosEspacios(FavoritosEspacios fav) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(fav);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public void insertEstaciones() {
	}

	public void insertEstaciones(Estaciones est) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(est);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public void insertEspaciosNaturales() {
	}

	public static void insertEspaciosNaturales(EspaciosNaturales esp) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(esp);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		/*if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();*/

	}

	public void insertDatos(Datos datos) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(datos);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}

	public void insertObjeto(Object obj) {
		Transaction tx;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session s = sesion.openSession();
		tx = s.beginTransaction();

		// Guardar objeto en la base de datos
		s.save(obj);
		// Actualizar información en la base de datos
		tx.commit();
		// TODO

		if (s.isConnected())
			s.close();
		if (!sesion.isClosed())
			sesion.close();

	}
}
