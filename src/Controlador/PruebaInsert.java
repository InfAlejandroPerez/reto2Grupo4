package Controlador;


//import HibernateUtil;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.Datos;

public class PruebaInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// INSERTAR UN DEPARTAMENTO

				/*Datos dt = new Datos();
				dt.setDeptNo((byte) 60);
				dt.setDnombre("informática");
				dt.setLoc("Elorrieta");

				Transaction tx;
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session s = sesion.openSession();
				tx = s.beginTransaction();

				// Guardar objeto en la base de datos
				// s.save(informatica);
				// Actualizar información en la base de datos
				// tx.commit();

				// Creamos una nueva transacción después del commit
				// tx = s.beginTransaction();

				// INSERTAR UN EMPLEADO

				Empleados empleado = new Empleados();
				empleado.setEmpNo((short) 8000);
				empleado.setOficio("DIRECTOR");
				empleado.setApellido("BARRIO");
				empleado.setComision(null);
				empleado.setDir(null);
				Date fechaActual = new Date();
				empleado.setFechaAlt(fechaActual);
				empleado.setSalario((float) 3000);
				empleado.setDepartamentos(informatica);

				// s.save(empleado);
				// tx.commit();

				// tx = s.beginTransaction();
		//
//				String hql = "FROM Empleados as e WHERE e.apellido = 'GIL'";
//				Query q = s.createQuery(hql);
		//
//				Empleados e = (Empleados) q.uniqueResult();
//				e.setSalario((float) 4000);
//				s.update(e);
//				tx.commit();

				tx = s.beginTransaction();

				String hql = "FROM Empleados WHERE salario = (SELECT max(salario) FROM Empleados "
						+ "WHERE departamentos.dnombre = 'CONTABILIDAD') AND departamentos.dnombre = 'CONTABILIDAD' ";

				Query q = s.createQuery(hql);
//				Empleados e2 = (Empleados) q.uniqueResult();
				List<Empleados> empleadosContabilidad = q.list();

				for (Empleados empleado1 : empleadosContabilidad) {
					s.delete(empleado1);
				}

				tx.commit();

				if (s.isConnected())
					s.close();
				if (!sesion.isClosed())
					sesion.close();*/

	}

}
