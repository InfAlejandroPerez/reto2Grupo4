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
	private static final String SEPARADOR = "/////";

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

			int opcion = Integer.parseInt(linea.split(SEPARADOR)[0]);

			switch (opcion) {
			case 1:
				// Login
				envio.setLogin(comprobarUsuario(linea.split(SEPARADOR)[1], linea.split(SEPARADOR)[2]));
				salida.writeObject(envio);
				salida.flush();
				System.out.println("enviado " + envio.getLogin());

				break;

			case 2:
				// Register
				int pass = Integer.parseInt(linea.split(SEPARADOR)[2]);

				Usuarios u1 = new Usuarios();
				u1.setNombre(linea.split(SEPARADOR)[1]);
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
				// get ??
				ArrayList<String> a = getMunicipios(linea.split(SEPARADOR)[1]);
				salida.writeObject(a);
				salida.flush();
				System.out.println(a.size());
				for (int i = 0; i < a.size(); i++) {
					System.out.println(a.get(i).toString());
				}
				System.out.println("Municipios Enviados");

				break;

			case 5:
				salida.writeObject(getEstacionesYDesc(linea.split(SEPARADOR)[1]));

				salida.flush();
				System.out.println("Datos de Municipio enviados");

				break;

			case 6:
				//Nombre de usuario
				envio.setLogin(consultarNombreUser(linea.split(SEPARADOR)[1]));
				salida.writeObject(envio);
				salida.flush();
				System.out.println("enviado " + envio.getLogin());

				break;

			case 7:
				//Get coordenadas
				salida.writeObject(getMunicipiosCoordenadasEst(linea.split(SEPARADOR)[1]));
				salida.flush();
				System.out.println("Coordenadas de Estacion Enviadas");

				break;

			case 8:
				//Get coordenadas
				salida.writeObject(getMunicipiosCoordenadasEst1(linea.split(SEPARADOR)[1]));
				salida.flush();
				System.out.println("Coordenadas de Estacion Enviadas");

				break;

			case 9:
				//insertar imagen
				Inserts.insertImage(1, linea.split(SEPARADOR)[1].getBytes());
				envio.setLogin(true);
				salida.writeObject(envio);
				salida.flush();
				break;
			case 10:
				//Enviar imagen
				byte[] valorArry = Consultas.getFoto(Integer.parseInt(linea.split(SEPARADOR)[1]));
				String finale = new String(valorArry);
				salida.writeObject(finale);
				salida.flush();
				System.out.println(finale);
				break;
			case 11:
				salida.writeObject(getMunicipioPlayas());
				salida.flush();
				System.out.println("Munis con Playas Mandados");
				break;
				
			case 12:
				salida.writeObject(getPlayas(linea.split(SEPARADOR)[1]));
				salida.flush();
				System.out.println("PLayas Enviadas");
				break;
				
			case 20:
				//Get todas las coordenadas
				ArrayList<Double> choords = getChoords(linea.split(SEPARADOR)[1]);
				salida.writeObject(choords);
				salida.flush();
				System.out.println("coords enviadas: " + choords.get(0) + choords.get(1));
				break;
			case 21:
				String munDesc = Consultas.getDescriptionFromMunicipio(linea.split(SEPARADOR)[1]);
				salida.writeObject(munDesc);
				salida.flush();
				System.out.println("descripcion enviadas: " + munDesc);
				break;
			case 22:
				ArrayList<String> estac = getEstacionesYDesc(linea.split(SEPARADOR)[1]);
				salida.writeObject(estac);
				salida.flush();
				System.out.println("descripcion enviadas: " + estac.get(1).toString());
				break;
			case 23:
				// Insert favs
				envio = new Envio();
				envio.setLogin(
						insertFavs(linea.split(SEPARADOR)[1], linea.split(SEPARADOR)[2], linea.split(SEPARADOR)[3]));
				salida.writeObject(envio);
				salida.flush();
				System.out.println("insercion realizada: " + envio.getLogin());
				break;
			case 24:
				// Delete favs
				envio = new Envio();
				envio.setLogin(
						deleteFav(linea.split(SEPARADOR)[1], linea.split(SEPARADOR)[2], linea.split(SEPARADOR)[3]));
				System.out.println(" ");
				salida.writeObject(envio);
				salida.flush();
				System.out.println("borrado realizado: " + envio.getLogin());
				break;
			case 25:
				// Check if a spaceArea is already in favs;
				envio = new Envio();
				envio.setLogin(
						checkFavs(linea.split(SEPARADOR)[1], linea.split(SEPARADOR)[2], linea.split(SEPARADOR)[3]));
				System.out.println(" ");
				salida.writeObject(envio);
				salida.flush();
				System.out.println("borrado realizado: " + envio.getLogin());
				break;
			case 26:
				// get SpaceArea details;
				ArrayList<String> estac1 = getEstacionesYDesc2(linea.split(SEPARADOR)[1]);
				salida.writeObject(estac1);
				salida.flush();
				System.out.println("descripcion enviadas: " + estac1.get(0).toString());
				break;
			case 30:
				salida.writeObject(EstacionesFav(linea.split(SEPARADOR)[1], linea.split(SEPARADOR)[2]));
				salida.flush();
				System.out.println("Favoritos Espacios");

				break;

			case 31:

				salida.writeObject(getNombreEspacios());
				salida.flush();
				System.out.println("Nombres Espacios");

				break;
				
			case 32:
				
				ArrayList<Double> cor = getLatLon(linea.split(SEPARADOR)[1]);
				salida.writeObject(cor);
				salida.flush();
				System.out.println("Coordenadas de Espacio Natural enviadas");
				
				break;
				
			case 35:
				
				salida.writeObject(getTopRanking());
				salida.flush();
				System.out.println("Top Ranking");
				
				break;
				
			case 36:
				
				salida.writeObject(getDatosMetereologicos());
				salida.flush();
				System.out.println("Datos Metereologicos");
				
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

	private Boolean deleteFav(String userS, String pass, String place) {
		int user = baseDeDatos.Consultas.getUserCode(userS, pass);
		boolean ret = baseDeDatos.Delete.deleteFav(user, place);
		System.out.println("salida");
		return ret;
	}

	private Boolean insertFavs(String userS, String pass, String place) {
		int user;
		int comprobacion;
		boolean ret;
		user = baseDeDatos.Consultas.getUserCode(userS, pass);
		comprobacion = baseDeDatos.Consultas.gedCodeFavEspacio(user, place);
		System.out.println("comprobacion " + comprobacion);
		if (comprobacion == 0) {
			ret = baseDeDatos.Inserts.insertFavoritosEspacios(user, place);
		}
		return true;
	}

	private boolean checkFavs(String userS, String pass, String place) {
		int user;
		int comprobacion;
		boolean ret = true;
		user = baseDeDatos.Consultas.getUserCode(userS, pass);
		comprobacion = baseDeDatos.Consultas.gedCodeFavEspacio(user, place);
		System.out.println("comprobacion " + comprobacion);
		if (comprobacion == 0) {
			ret = false;
		}
		return ret;
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

		if (baseDeDatos.Inserts.insertUsuarios(us)) {

			return true;

		} else {

			return false;

		}

	}

	private static ArrayList<String> getEstacionesYDesc(String municipio) {
		ArrayList<String> ret = baseDeDatos.Consultas.getDataAndStationsFromMunicipio(municipio);
		if (ret.size() < 2) {
			if (ret.get(0) == null) {
				ret.clear();
			}
			ret.add("no se ha encontrado este dato");
			ret.add("no se ha encontrado este dato");
		}
		return ret;

	}

	private static ArrayList<String> getEstacionesYDesc2(String espacio) {

		ArrayList<String> ret = baseDeDatos.Consultas.getDataFromEspacio(espacio);
		if (ret.get(0).contentEquals("no se ha encontrado este espacio")) {
			ret = new ArrayList<String>();
			ret = getEstacionesYDesc(espacio);
		}
		return ret;

	}

	private static ArrayList<Double> getChoords(String municipio) {
		// Municipiospueblos m =
		// baseDeDatos.Consultas.consultarCodigoMunicipio(municipio);
		// System.out.println(m.getCodMunicipio());
		ArrayList<Double> ret = new ArrayList<Double>();
		// ArrayList<Double> ret= baseDeDatos.Consultas.getChoords(m.getCodMunicipio());
		ArrayList<String> est = getEstacionesYDesc(municipio);
		int lastI = est.size() - 1;
		if (est.size() > 0) {
			Double x = baseDeDatos.Consultas.getCoordenadaX(est.get(lastI));
			Double y = baseDeDatos.Consultas.getCoordenadaY(est.get(lastI));
			System.out.println(est.get(lastI) + " choords " + x + " " + y);
			ret.add(x);
			ret.add(y);
		} else {
			ret.add(0.0000);
			ret.add(0.0000);
		}

		return ret;
	}
	
	private static ArrayList<Double> getLatLon(String Espacio) {
		// Municipiospueblos m =
		// baseDeDatos.Consultas.consultarCodigoMunicipio(municipio);
		// System.out.println(m.getCodMunicipio());
		ArrayList<Double> ret = new ArrayList<Double>();
		// ArrayList<Double> ret= baseDeDatos.Consultas.getChoords(m.getCodMunicipio());
		
		if(baseDeDatos.Consultas.getLatitud(Espacio) == 0){
			
			ret.add(0.0000);
			
		}else {
			
			ret.add(baseDeDatos.Consultas.getLatitud(Espacio));
			
		}
		
		if(baseDeDatos.Consultas.getLongitud(Espacio) == 0) {
			
			ret.add(0.0000);
			
		}else {
			
			ret.add(baseDeDatos.Consultas.getLongitud(Espacio));
			
		}

		return ret;
		
	}

	private static Double getMunicipiosCoordenadasEst(String Estacion) {

		return baseDeDatos.Consultas.getCoordenadaX(Estacion);

	}

	private static Double getMunicipiosCoordenadasEst1(String Estacion) {

		return baseDeDatos.Consultas.getCoordenadaY(Estacion);

	}

	private static Boolean consultarNombreUser(String peticion) {

		if (baseDeDatos.Consultas.consultarNombreUser(peticion)) {
			return true;
		} else {
			return false;
		}
	}

	private static ArrayList<String> EstacionesFav(String nombre, String contrasenia) {

		return baseDeDatos.Consultas.getEstacionesFavs(nombre, contrasenia);

	}

	private static ArrayList<String> getNombreEspacios() {

		return baseDeDatos.Consultas.getNombreEspacios();

	}
	

	private static ArrayList<String> getMunicipioPlayas(){
		
		return baseDeDatos.Consultas.getMunisWithPLayas();
		
	}
	
	private static ArrayList<String> getPlayas(String nombre){
		
		return baseDeDatos.Consultas.getPlayasfromMuni(nombre);
		
	}
		

	private static ArrayList<String> getTopRanking() {
		
		return baseDeDatos.Consultas.getTopRanking();

	}
	
	private static ArrayList<String> getDatosMetereologicos() {
		
		return baseDeDatos.Consultas.getDatosMetereologicos();
	}
}
