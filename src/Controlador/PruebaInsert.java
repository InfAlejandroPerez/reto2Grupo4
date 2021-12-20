package Controlador;


import HibernateUtil.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import modelo.Datos;

public class PruebaInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		/*String hql = "from Departamentos as dep where dep.deptNo = 10";
		Query q = session.createQuery(hql);
		Datos datos = (Datos) q.uniqueResult();
		
		System.out.println("Nombre: " +datos.getId());
		System.out.println("Location: " +datos.getFecha());
		System.out.println("Location: " +datos.getHora());
		System.out.println("No: " +datos.getPrecipitaciones());
		System.out.println("No: " +datos.getTemperatura());
		System.out.println("No: " +datos.getVelocidadViento());*/
		
	//	Select * FROM empleados where salario in (select max(salario) FROM empleados)
		
		System.out.println("*****");
		String hql2 = "INSERT INTO datos VALUES (1, '000-01-01', '20:00:00','muchas', 1,2, 3,1)";
		Query q2 = session.createQuery(hql2);
		Datos data = (Datos) q2.uniqueResult();
		System.out.println(data.getId());
		
		
		session.close();
		sesion.close();

	}

}
