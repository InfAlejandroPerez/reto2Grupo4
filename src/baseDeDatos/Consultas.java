package baseDeDatos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Controlador.HibernateUtil;
import modelo.Municipiospueblos;

public class Consultas {

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
