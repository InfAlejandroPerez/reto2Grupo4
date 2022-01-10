package baseDeDatos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Controlador.HibernateUtil;

public class PruebaAintzane {
	public static void main(String[] args) {
		insertarProvincia(1111111, "Idhun");
}
	
 public static void insertarProvincia(int codigo, String nombre) {
	 SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		String hqlInsert = "insert into provincia (CodProvincia, Nombre) VALUES ( " +
		codigo + ", " + nombre + ");";
		Query cons=session.createQuery( hqlInsert );
		int filascreadas = cons.executeUpdate();
		System.out.printf("FILAS INSERTADAS: %d%n",filascreadas);
		tx.commit(); // valida la transacción
		session.close();
		System.exit(0);
 }
 
 public void insertarMunicipios(int codigo, String nombre, String descripcion, int codProvincia) {
	 SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		String hqlInsert = "insert into municipiosPueblos (CodMunicipio, Nombre, Descripcion, CodProvincia) VALUES ( " +
		codigo + ", " + nombre + ", " + descripcion + ", " + codProvincia + ");";
		Query cons=session.createQuery( hqlInsert );
		int filascreadas = cons.executeUpdate();
		System.out.printf("FILAS INSERTADAS: %d%n",filascreadas);
		tx.commit(); // valida la transacción
		session.close();
		System.exit(0);
 }
 
 public void insertarLocalidad(int codigo, String nombre, String descripcion, int codMunicipio) {
	 SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		String hqlInsert = "insert into localidad (CodLocalidad, Nombre, Descripcion, CodMunicipio) VALUES ( " +
		codigo + ", " + nombre + ", " + descripcion + ", " + codMunicipio + " );";
		Query cons=session.createQuery( hqlInsert );
		int filascreadas = cons.executeUpdate();
		System.out.printf("FILAS INSERTADAS: %d%n",filascreadas);
		tx.commit(); // valida la transacción
		session.close();
		System.exit(0);
 }
 
 public void insertarEspaciosNaturales(int codigo, String nombre, String descripcion, int CodMunicipio, int CodLocalidad) {
	 SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		String hqlInsert = "insert into localidad (CodEspacio, Nombre, Descripcion, CodMunicipio, CodLocalidad) VALUES ( " +
		codigo + ", " + nombre + ", " + descripcion + ", " + CodMunicipio +", " + CodLocalidad +  " );";
		Query cons=session.createQuery( hqlInsert );
		int filascreadas = cons.executeUpdate();
		System.out.printf("FILAS INSERTADAS: %d%n",filascreadas);
		tx.commit(); // valida la transacción
		session.close();
		System.exit(0);
 }
 
 
 
}