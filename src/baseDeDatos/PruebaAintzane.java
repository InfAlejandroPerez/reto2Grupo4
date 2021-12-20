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

public class PruebaAintzane {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "from Departamentos as dep where dep.deptNo = 10";
		Query q = session.createQuery(hql);
		Departamentos dep = (Departamentos) q.uniqueResult();
		
		System.out.println("Nombre: " +dep.getDnombre());
		System.out.println("Location: " +dep.getLoc());
		System.out.println("No: " +dep.getDeptNo());
		
	//	Select * FROM empleados where salario in (select max(salario) FROM empleados)
		
		System.out.println("*****");
		String hql2 = "FROM Empleados where salario in (select max(salario) FROM Empleados)";
		Query q2 = session.createQuery(hql2);
		Empleados emp = (Empleados) q2.uniqueResult();
		System.out.println(emp.getApellido());
		
		System.out.println("*****");
		String hql3 = "FROM Empleados as e where e.departamentos.dnombre = 'CONTABILIDAD'";
		Query q3 = session.createQuery(hql3);
		List<Empleados> emple =  q3.list();
		for(int i=0;i<emple.size();i++)
		{
			Empleados em2 = emple.get(i);
			System.out.println(em2.getApellido());
		}
		System.out.println("*****");
		Iterator it = q3.list().iterator();
		while(it.hasNext())
		{
			Empleados em2 = (Empleados) it.next();
			System.out.println(em2.getApellido());
		}
		System.out.println("*****");
		Departamentos dep4 = (Departamentos) session.get(Departamentos.class, (byte) 20);
		System.out.println(dep4.getDnombre());
		System.out.println("*****");
		Departamentos dep5 = (Departamentos) session.load(Departamentos.class, (byte) 10);
		System.out.println(dep5.getDnombre());
		
		System.out.println("*****");
		String hql6 = "from Departamentos as dep where dep.deptNo = :depNO and dep.dnombre = :depNOMBRE";
		Query q6 = session.createQuery(hql6);
		q6.setParameter("depNO", (byte) 20);
		q6.setParameter("depNOMBRE", "INVESTIGACION");
		Departamentos dep6 = (Departamentos) q6.uniqueResult();
		System.out.println(dep6.getDnombre());
		
		
		System.out.println("*****");
		String hql7 = "from Departamentos as dep where dep.deptNo = ? and dep.dnombre = ?";
		Query q7 = session.createQuery(hql7);
		q7.setParameter(0, (byte) 20);
		q7.setParameter(1, "INVESTIGACION");
		Departamentos dep7 = (Departamentos) q7.uniqueResult();
		System.out.println(dep7.getDnombre());
		
		
		System.out.println("*****");
		String hql8 = "from Departamentos as dep where dep.deptNo in (:listaDEP)";
		Query q8 = session.createQuery(hql8);
		List<Byte> listaDep = new ArrayList<Byte>();
		listaDep.add((byte) 10);
		listaDep.add((byte) 20);
		q8.setParameterList("listaDEP", listaDep);
		List<Departamentos> deps =  q8.list();
		for(int i=0;i<deps.size();i++)
		{
			Departamentos em2 = deps.get(i);
			System.out.println(em2.getDnombre());
		}
		
		
		session.close();
		sesion.close();
}
