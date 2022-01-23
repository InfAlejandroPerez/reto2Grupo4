package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.euskalmet.Envio;

import baseDeDatos.Consultas;
import baseDeDatos.Inserts;
import modelo.Municipiospueblos;
import modelo.Usuarios;

public class ServerThread implements Runnable {
	private Envio envio;
	private Socket cliente;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;

	public ServerThread(Socket cliente) {
		// Inicializamos un nuevo pojo envio
		this.envio = envio = new Envio();
		this.cliente = cliente;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		
		try {
			this.salida = new ObjectOutputStream(cliente.getOutputStream());
			this.entrada = new ObjectInputStream(cliente.getInputStream());

			String linea = (String) entrada.readObject();
			System.out.println("Recibido: " + linea);

			int opcion = Integer.parseInt(linea.split("///")[0]);

			switch (opcion) {
			case 1:
				envio.setLogin(comprobarUsuario(linea.split("///")[1], linea.split("///")[2]));
				salida.writeObject(envio);
				salida.flush();
				System.out.println("enviado " + envio.getLogin());

				break;
				
			case 2:
				
				int pass = Integer.parseInt(linea.split("///")[2]);
				
				Usuarios u1 = new Usuarios();
				u1.setNombre(linea.split("///")[1]);
				u1.setContrasenia(pass);
				salida.writeObject(insertUsuarios(u1));
				salida.flush();
				System.out.println("Usuario Insertado");

				break;
				
			case 3:
				
				salida.writeObject(pvs());
				salida.flush();
				System.out.println("Provincias Enviadas");

				break;
				
			case 4:
				
				salida.writeObject(getMunicipios(linea.split("///")[1]));
				salida.flush();
				System.out.println("Municipios Enviados");

				break;
				
			case 5:
				
				salida.writeObject(getEstacionesYDesc(linea.split("///")[1]));
				salida.flush();
				System.out.println("Datos de Municipio enviados");

				break;
				
			case 6:
				
				envio.setLogin(consultarNombreUser(linea.split("///")[1]));
				salida.writeObject(envio);
				salida.flush();
				System.out.println("enviado " + envio.getLogin());

				break;
				
			case 7:
				
				salida.writeObject(getMunicipiosCoordenadasEst(linea.split("///")[1]));
				salida.flush();
				System.out.println("Coordenadas de Estacion Enviadas");

				break;
				
			case 8:
				
				salida.writeObject(getMunicipiosCoordenadasEst1(linea.split("///")[1]));
				salida.flush();
				System.out.println("Coordenadas de Estacion Enviadas");

				break;
				
			case 9:
				Inserts.insertImage(1, linea.split("///")[1].getBytes());
				envio.setLogin(true);
				salida.writeObject(envio);
				salida.flush();
				break;
			case 10:
				String valor=Base64.getEncoder().encodeToString(Consultas.getFoto(Integer.parseInt(linea.split("///")[1])));
				
				salida.writeObject(valor);
				salida.flush();
				System.out.println(valor);
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (cliente != null)
					cliente.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static Boolean comprobarUsuario(String peticion, String peticion2) {
		if (baseDeDatos.Consultas.consultarUsuario(peticion, peticion2)) {
			return true;
		} else {
			return false;
		}
	}
	
	private static ArrayList<String> pvs() {
		
		return baseDeDatos.Consultas.getProvincias();
		
	}
	
	public static ArrayList<String> getMunicipios(String Provincia) {
		
		return baseDeDatos.Consultas.getMunicipios(Provincia);
		
	}
	
	public static boolean insertUsuarios(Usuarios us) {

		if(baseDeDatos.Inserts.insertUsuarios(us)) {
			
			return true;
			
		}else {
			
			return false;
			
		}

	}
	
	private static ArrayList<String> getEstacionesYDesc(String municipio){
		
		return baseDeDatos.Consultas.getDataAndStationsFromMunicipio(municipio);
		
	}
	
	private static Double getMunicipiosCoordenadasEst(String Estacion){
		
		return baseDeDatos.Consultas.getCoordenadaX(Estacion);
		
	}
	
	private static Double getMunicipiosCoordenadasEst1(String Estacion){
		
		return baseDeDatos.Consultas.getCoordenadaY(Estacion);
		
	}
	
	private static Boolean consultarNombreUser(String peticion) {
		
		if (baseDeDatos.Consultas.consultarNombreUser(peticion)) {
			return true;
		} else {
			return false;
		}
	}
	
}
