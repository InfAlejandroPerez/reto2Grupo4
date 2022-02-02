package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.List;

import Controlador.HibernateUtil;
import modelo.Datos;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.Fotosespacio;
import modelo.Municipiospueblos;
import modelo.Provincia;
import modelo.Usuarios;

public class Consultas {

	public static Double CoordenadasEstacion = (double) 0;

	public static ArrayList<String> pvs = new ArrayList<String>();

	public static ArrayList<String> munis = new ArrayList<String>();

	public static ArrayList<byte[]> getFoto(int codeMuni) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Fotosespacio  where codEspacio = '" + codeMuni + "' ORDER BY cod desc";
		Query q = (Query) session.createQuery(hql);
		q.setMaxResults(1);
		// Fotosespacio f = (Fotosespacio) ((org.hibernate.Query) q).uniqueResult();
		ArrayList<Fotosespacio> f = new ArrayList<Fotosespacio>(q.list());
		// TODO
		session.close();
		ArrayList<byte[]> ret = new ArrayList<byte[]>();

		for (int i = 0; i < f.size(); i++) {
			ret.add(f.get(i).getImg());
		}

		return ret;
	}

	public static int getUserCode(String user, String pass) {
		int ret = 0;
		try {// TODO Auto-generated method stub
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();

			String hql = "Select codUsuario from Usuarios where Nombre = '" + user + "' AND Contrasenia = '" + pass
					+ "'";
			Query q = (Query) session.createQuery(hql);
			ret = (int) ((org.hibernate.Query) q).uniqueResult();

			session.close();
		} catch (Exception e) {
			System.out.println("No hay entradas en getCodeUSer");
		}
		return ret;
	}

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

	public static boolean consultarNombreUser(String user) {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Usuarios where Nombre = '" + user + "'";
		Query q = (Query) session.createQuery(hql);
		Usuarios us = (Usuarios) ((org.hibernate.Query) q).uniqueResult();

		session.close();

		if (us != null) {
			return true;
		} else {
			return false;
		}

	}

	public static Municipiospueblos consultarCodigoMunicipio(String municipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		// Visualiza los datos del departamento 30
		String hql = "from Municipiospueblos as m where m.nombre = '" + municipio + "'";
		Query q = session.createQuery(hql);
		Municipiospueblos m = (Municipiospueblos) q.uniqueResult();

		session.close();

		return m;
	}

	public static ArrayList<String> getProvincias() {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Provincia";
		Query q = (Query) session.createQuery(hql);

		ArrayList<Provincia> pvss = new ArrayList<Provincia>();

		pvss.clear();

		pvss = new ArrayList<Provincia>(q.list());

		pvs.clear();

		for (int i = 0; i < pvss.size(); i++) {

			pvs.add(pvss.get(i).getNombre());

		}
		return pvs;

	}

	public static ArrayList<String> getMunicipios(String Provincia) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Municipiospueblos Where codProvincia = (Select codProvincia From Provincia Where Nombre = '"
				+ Provincia + "')";
		Query q = (Query) session.createQuery(hql);

		munis.clear();

		ArrayList<Municipiospueblos> pvss = new ArrayList<Municipiospueblos>(q.list());

		for (int i = 0; i < pvss.size(); i++) {

			munis.add(pvss.get(i).getNombre());

		}

		return munis;

	}
	
	public static Provincia getPVFromMunicipio(int mun) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select provincia from Municipiospueblos Where codMunicipio = '"+ mun + "')";
		Query q = (Query) session.createQuery(hql);

		Provincia p =(Provincia) ((org.hibernate.Query) q).uniqueResult();
		System.out.println(mun +" "+ p.getNombre());
		return p;

	}

	public static ArrayList<String> getEspaciosNaturales(String Municipio) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from EspaciosNaturales Where codMunicipio = (Select codProvincia From Provincia Where Nombre = '"
				+ Municipio + "')";
		Query q = (Query) session.createQuery(hql);

		munis.clear();

		ArrayList<Municipiospueblos> pvss = new ArrayList<Municipiospueblos>(q.list());

		for (int i = 0; i < pvss.size(); i++) {

			munis.add(pvss.get(i).getNombre());

		}

		return munis;

	}

	public static ArrayList<String> getDataAndStationsFromMunicipio(String municipio) {

		ArrayList<String> arr = new ArrayList<String>();

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select descripcion from Municipiospueblos Where Nombre = '" + municipio + "')";
		Query q = (Query) session.createQuery(hql);

		String descripcion = (String) ((org.hibernate.Query) q).uniqueResult();
		arr.add(descripcion);

		hql = "Select nombre from Estaciones Where codMunicipio=(Select codMunicipio from Municipiospueblos Where Nombre = '"
				+ municipio + "')";
		q = (Query) session.createQuery(hql);
		// La ultima posicion es la descripcion del municipio, el resto son nombres de
		// estaciones
		ArrayList<String> estaciones = new ArrayList<String>(q.list());

		for (int i = 0; i < estaciones.size(); i++) {

			arr.add(estaciones.get(i).toString());

		}

		return arr;

	}

	public static ArrayList<String> getDataFromEspacio(String espacio) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select descripcion from EspaciosNaturales Where Nombre = '" + espacio + "')";
		Query q = (Query) session.createQuery(hql);

		ArrayList<String> estaciones = new ArrayList<String>(q.list());

		if (estaciones.size() == 0) {
			estaciones.add("no se ha encontrado este espacio");
		}
		return estaciones;

	}

	public static int getCodeFromEspacio(String espacio) {
		int code = 1;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select codEspacio from EspaciosNaturales Where Nombre = '" + espacio + "')";
		Query q = (Query) session.createQuery(hql);

		try {
			code = (int) ((org.hibernate.Query) q).uniqueResult();
		} catch (Exception e) {
			System.out.println("No hay espacio con ese nombre getCodeFromEspacio, null pointer.");
		}
		return code;
	}

	public static int getCodeMunFromEspacio(String espacio) {
		int code = 1;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select codMunicipio from EspaciosNaturales Where Nombre = '" + espacio + "')";
		Query q = (Query) session.createQuery(hql);

		try {
			code = (int) ((org.hibernate.Query) q).uniqueResult();
			System.out.println("codigo del espacio " + espacio + " " + code);
		} catch (Exception e) {
			System.out.println("No hay espacio con ese nombre getCodeFromEspacio, null pointer.");
		}
		return code;
	}

	public static ArrayList<String> getDataAndStationsFromMunicipio2(String municipio) {

		ArrayList<String> ret = new ArrayList<String>();

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from EspaciosNaturales Where codMunicipio = (Select codMunicipio from Municipiospueblos where nombre ='"
				+ municipio + "')";
		Query q = (Query) session.createQuery(hql);

		q = (Query) session.createQuery(hql);

		// La ultima posicion es la descripcion del municipio, el resto son nombres de
		// estaciones
		ArrayList<EspaciosNaturales> arrNat = new ArrayList<EspaciosNaturales>(q.list());
		ret.add(arrNat.get(0).getNombre());
		ret.add(arrNat.get(0).getDescripcion());
		return ret;

	}

	public static String getDescriptionFromMunicipio(String municipio) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select descripcion from Municipiospueblos Where Nombre = '" + municipio + "')";
		Query q = (Query) session.createQuery(hql);

		String descripcion = (String) ((org.hibernate.Query) q).uniqueResult();

		return descripcion;

	}

	public static ArrayList<Double> getChoords(int codMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select coordenadaX, coordenadaY from Estaciones Where codMunicipio = '" + codMunicipio + "'";
		Query q = (Query) session.createQuery(hql);

		ArrayList<Double> f = new ArrayList<Double>(q.list());
		// TODO
		session.close();

		return f;

	}

	public static Double getCoordenadaX(String Estacion) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select coordenadaX from Estaciones Where nombre = '" + Estacion + "'";
		Query q = (Query) session.createQuery(hql);

		Double CoordenadaX = (Double) ((org.hibernate.Query) q).uniqueResult();

		return CoordenadaX;

	}

	public static Double getCoordenadaY(String Estacion) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select coordenadaY from Estaciones Where nombre = '" + Estacion + "'";
		Query q = (Query) session.createQuery(hql);

		Double CoordenadaY = (Double) ((org.hibernate.Query) q).uniqueResult();

		return CoordenadaY;

	}

	public static Double getLongitud(String Espacio) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select longitud from EspaciosNaturales Where nombre = '" + Espacio + "'";
		Query q = (Query) session.createQuery(hql);

		Double Longitud = (Double) ((org.hibernate.Query) q).uniqueResult();

		return Longitud;

	}

	public static Double getLatitud(String Espacio) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select latitud from EspaciosNaturales Where nombre = '" + Espacio + "'";
		Query q = (Query) session.createQuery(hql);

		Double Latitud = (Double) ((org.hibernate.Query) q).uniqueResult();

		return Latitud;

	}

	public static int gedCodeFavEspacio(int user, String nombre) {

		int cod = 0;
		try {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();

			String hql = "Select codFav from FavoritosEspacios Where nombreEspacio = '" + nombre
					+ "' AND codUsuario = '" + user + "'";
			Query q = (Query) session.createQuery(hql);

			cod = (int) ((org.hibernate.Query) q).uniqueResult();
		} catch (Exception e) {
			System.out.println("No hay entradas en getCodeFavEspacio");
		}
		return cod;

	}

	public static ArrayList<String> getEstacionesFavs(String usuario, String contrasenia) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select codUsuario from Usuarios Where nombre = '" + usuario + "' AND contrasenia = '"
				+ contrasenia + "'";
		Query q = (Query) session.createQuery(hql);

		int codUser = (Integer) ((org.hibernate.Query) q).uniqueResult();

		hql = "Select nombreEspacio from FavoritosEspacios Where codUsuario = " + codUser;
		q = (Query) session.createQuery(hql);

		ArrayList<String> Espacios = new ArrayList<String>(q.list());

		return Espacios;

	}

	public static ArrayList<String> getNombreEspacios() {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select nombre from EspaciosNaturales";
		Query q = (Query) session.createQuery(hql);

		ArrayList<String> EspaciosNombres = new ArrayList<String>(q.list());

		return EspaciosNombres;

	}

	public static ArrayList<String> getDataEstacionesMasDesc(String municipio) {

		ArrayList<String> arr = new ArrayList<String>();

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select descripcion from Municipiospueblos Where Nombre = '" + municipio + "')";
		Query q = (Query) session.createQuery(hql);

		String descripcion = (String) ((org.hibernate.Query) q).uniqueResult();
		arr.add(descripcion);

		hql = "Select nombre from Estaciones Where codMunicipio=(Select codMunicipio from Municipiospueblos Where Nombre = '"
				+ municipio + "')";
		q = (Query) session.createQuery(hql);
		// La ultima posicion es la descripcion del municipio, el resto son nombres de
		// estaciones
		ArrayList<String> playas = new ArrayList<String>(q.list());

		for (int i = 0; i < playas.size(); i++) {

			arr.add(playas.get(i).toString());

		}

		return arr;

	}

	public static ArrayList<String> getMunisWithPLayas() {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select nombre from Municipiospueblos Where codMunicipio in (Select DISTINCT codMunicipio from EspaciosNaturales Where tipo LIKE 'Playas')";
		Query q = (Query) session.createQuery(hql);

		ArrayList<String> Espacios = new ArrayList<String>(q.list());

		return Espacios;

	}

	public static ArrayList<String> getPlayasfromMuni(String Muni) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select nombre from EspaciosNaturales Where codMunicipio = (Select codMunicipio from Municipiospueblos Where nombre LIKE '"
				+ Muni + "')";
		Query q = (Query) session.createQuery(hql);

		ArrayList<String> Espacios = new ArrayList<String>(q.list());

		return Espacios;

	}

	public static String getDatosPLaya(String Playa) {

		ArrayList<String> playas = new ArrayList<String>();

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		/*
		 * String hql =
		 * "Select icaestacion from Datos Where codEstacion = (Select codEstacion From Estaciones Where codMunicipio = (Select codMuicipio from EspaciosNaturales Where nombre LIKE '"
		 * + Playa + "'))"; Query q = (Query) session.createQuery(hql).uniqueResult();
		 * 
		 * String dato = q.toString();
		 * 
		 * return playas;
		 */

		String hql = "Select icaestacion from Datos Where codEstacion = (Select codEstacion From Estaciones Where codMunicipio in (Select codMunicipio from EspaciosNaturales Where nombre LIKE '"
				+ Playa + "'))";
		Query q = (Query) session.createQuery(hql);

		String dato = (String) ((org.hibernate.Query) q).uniqueResult();

		return dato;

	}

	public static ArrayList<String> getTopRanking() {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select nombreEspacio from TopRanking";
		Query q = (Query) session.createQuery(hql);

		ArrayList<String> topRanking = new ArrayList<String>(q.list());

		return topRanking;

	}
	

	public static int getCodeFromEstacion(String estacion) {

		if (estacion.contains("_BBIZI2")) {

			estacion = estacion.replaceAll("_BBIZI2", " (BBIZI2)");

		} else if (estacion.contains("_Monte")) {

			estacion = estacion.replaceAll("_Monte", " (Monte)");

		} else if (estacion.contains("AV_")) {

			estacion = estacion.replaceAll("AV_", "AV. ");

		} else if (estacion.contains("AVDA_")) {

			estacion = estacion.replaceAll("AVDA_", "AVDA. ");

		} else if (estacion.contains("Ñ")) {

			estacion = estacion.replaceAll("Ñ", "N");

		} else if (estacion.contains("Ñ")) {

			estacion = estacion.replaceAll("Ñ", "N");

		} else if (estacion.contains("_meteo")) {

			estacion = estacion.replaceAll("_meteo", " (meteo)");

		} else if (estacion.contains(" METEO")) {

			estacion = estacion.replaceAll(" METEO", "");

		} else if (estacion.contains("M_DIAZ_HARO")) {

			estacion = estacion.replaceAll("M_DIAZ_HARO", "Mª DIAZ HARO");

		} else if (estacion.contains("_Puerto")) {

			estacion = estacion.replaceAll("_Puerto", " (Puerto)");

		} else if (estacion.contains("_")) {

			estacion = estacion.replaceAll("_", " ");

		}

		int code = 0;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "SELECT codEstacion from Estaciones WHERE nombre LIKE '" + estacion + "'";
		Query q = (Query) session.createQuery(hql);

		try {
			code = (int) ((org.hibernate.Query) q).uniqueResult();
		} catch (Exception e) {
			System.out.println("No hay espacio con ese nombre getCodeFromEspacio, null pointer.");
		}
		return code;
	}

	public static ArrayList<String> getDatosMetereologicos(String playa) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select no2ica, pm10ica, so2ica, icaestacion from Datos Where codEstacion = (Select codEstacion From Estaciones Where codMunicipio = (Select codMunicipio from EspaciosNaturales Where nombre LIKE '"
				+ playa + "'))";
		Query q = (Query) session.createQuery(hql);

		ArrayList<String> datosMetereologicos = new ArrayList<String>(q.list());
		for (int i = 0; i < datosMetereologicos.size(); i++) {
			System.out.println("datos meteorologicos");
			System.out.println(datosMetereologicos.get(i));
		}
		return datosMetereologicos;
	}

	public static ArrayList<Datos> getDatosMeteorologicos(Estaciones est) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Datos Where codEstacion = '" + est.getCodEstacion() + "'))";
		Query q = (Query) session.createQuery(hql);

		ArrayList<Datos> datosMetereologicos = new ArrayList<Datos>(q.list());

		if (datosMetereologicos.size() == 0) {
			System.out.println("No se han encontrado datos");
			Datos d = new Datos();
			datosMetereologicos = new ArrayList<Datos>();
			datosMetereologicos.add(d);
		}

		for (int i = 0; i < datosMetereologicos.size(); i++) {
			System.out.println("datos meteorologicos leidos");
			//System.out.println(datosMetereologicos.get(i).toString());
		}
		return datosMetereologicos;
	}

	public static String getLastUpdate() {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "SELECT fecha from Actualizar";
		Query q = (Query) session.createQuery(hql);

		String lastUpdate = (String) ((org.hibernate.Query) q).uniqueResult();

		return lastUpdate;
	}

	public static ArrayList<Integer> getCodEstacionesFromMunicipio(int mun) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "Select codEstacion from Estaciones Where codMunicipio = '" + mun + "'))";
		Query q = (Query) session.createQuery(hql);

		ArrayList<Integer> ret = new ArrayList<Integer>(q.list());

		return ret;
	}

	public static ArrayList<Estaciones> getEstacionesFromMunicipio(int mun) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Estaciones Where codMunicipio = '" + mun + "'))";
		Query q = (Query) session.createQuery(hql);

		ArrayList<Estaciones> ret = new ArrayList<Estaciones>(q.list());

		if (ret.size() == 0) {
			System.out.println("No se han encontrado estaciones en el municipio" + mun);
		} else {
			System.out.println("Estacion " + ret.get(0).getNombre()+ " "+ ret.get(0).getCodEstacion());
		}
		return ret;
	}

}
