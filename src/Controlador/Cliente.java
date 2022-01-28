package Controlador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.example.euskalmet.Envio;

import modelo.Municipiospueblos;



public class Cliente {

	private static final int PUERTO = 5000;
	private static final String IP = "127.0.0.1";
	private static boolean seguir;
	private static String peticion;
	private static String datos ;
	private static Envio recibido;
	private static ArrayList<String> arrayProvincias;
	private static ArrayList<String> Munis;
	private static ArrayList<String> datosMuni;
	private static ArrayList<String> MunisPlayas;
	private static ArrayList<String> playas;
	private static Double CoordenadasEstacion;
	private static int opcion = 0;
	static ObjectInputStream entrada = null;
	static ObjectOutputStream salida = null;
	private static boolean newUser = false;

	public static void inicar() {
		//while (true) {
			try {
				// necesitamos una IP y un PUERTO para establecer la comunicacion
				Socket cliente = new Socket(IP, PUERTO);

				System.out.println("Conexion establecida con el servidor");

				salida = new ObjectOutputStream(cliente.getOutputStream());
				entrada = new ObjectInputStream(cliente.getInputStream());
				
				do {
					switch (opcion) {

					case 1:
						peticion = opcion + "/////" + datos;
						salida.writeObject(peticion);
						salida.flush();
						recibido = (Envio) entrada.readObject();
						System.out.println(recibido.getLogin());
						opcion = 0;
						break;
						
					case 2:	
						
						peticion = opcion + "/////" + datos;
						salida.writeObject(peticion);
						salida.flush();
						
						
						opcion = 0;
						
						break;
						
					case 3:	
						
						peticion = opcion + "";
						salida.writeObject(peticion);
						salida.flush();
						arrayProvincias = (ArrayList<String>) entrada.readObject();
						opcion = 0;
						
						break;
						
					case 4:	
						
						peticion = opcion + "/////" + datos;
						salida.writeObject(peticion);
						salida.flush();
						Munis = (ArrayList<String>) entrada.readObject();
						opcion = 0;
						
						System.out.println("Provincias Cargadas: " + Munis.size());
						
						break;
					
					case 5:	
						
						peticion = opcion + "/////" + datos;
						salida.writeObject(peticion);
						salida.flush();
						datosMuni = (ArrayList<String>) entrada.readObject();
						opcion = 0;
						
						System.out.println("Provincias Cargadas: " + Munis.size());
						
						break;
						
					case 6:	
						
						peticion = opcion + "/////" + datos;
						salida.writeObject(peticion);
						salida.flush();
						recibido = (Envio) entrada.readObject();
						System.out.println(recibido.getLogin());
						opcion = 0;

						
						break;
						
					case 7:	
						
						peticion = opcion + "/////" + datos;
						salida.writeObject(peticion);
						salida.flush();
						CoordenadasEstacion = (Double) entrada.readObject();
						opcion = 0;
						
						System.out.println("Coordenada X Cargada");
						
						break;	
						
					case 8:	
						
						peticion = opcion + "/////" + datos;
						salida.writeObject(peticion);
						salida.flush();
						CoordenadasEstacion = (Double) entrada.readObject();
						opcion = 0;
						
						System.out.println("Coordenada Y Cargada");
						
						break;
						
					case 11:
						
						peticion = opcion + "";
						salida.writeObject(peticion);
						salida.flush();
						MunisPlayas = (ArrayList<String>) entrada.readObject();
						opcion = 0;
						
						System.out.println("Municipios con playas Mostrados");
						
						break;	

					case 12:
						
						peticion = opcion + "/////" + datos;
						salida.writeObject(peticion);
						salida.flush();
						playas = (ArrayList<String>) entrada.readObject();
						opcion = 0;
						
						System.out.println("Playas Enviadas");
						
						break;	
						
					}	
					
				} while (seguir);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	//}

	public static void setDatos(String da) {
		//los datos necesarios para realizar la peticion
		datos = da;
	}

	public static void apagarHilo() {
		seguir = false;
	}

	public static void setOpcion(Integer num) {
		//define el tipo de peticion para el servidor
		opcion = num;
	}

	public static boolean getResponse() {
		//devuelve la respuesta del server
		return recibido.getLogin();
	}
	
	public static ArrayList<String> getArray() {
		//devuelve la respuesta del server
		return arrayProvincias;
	}
	
	public static ArrayList<String> getMunis() {
		//devuelve la respuesta del server
		return Munis;
	}
	
	public static ArrayList<String> getDatosMuni() {
		//devuelve la respuesta del server
		return datosMuni;
	}
	
	public static Double getCoordenadasEstacion() {
		//devuelve la respuesta del server
		return CoordenadasEstacion;
	}
	
	public static boolean newUser() {
		
		return newUser;
		
	}
	
	public static ArrayList<String> getMunisPlayas() {
		
		return MunisPlayas;
	
	}

	public static ArrayList<String> getPlayasFromMuni() {
		
		return playas;
	
	}
	
}
