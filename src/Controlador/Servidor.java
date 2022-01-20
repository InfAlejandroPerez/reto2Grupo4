package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.euskalmet.Envio;

public class Servidor {

	private Envio envio;
	private final int PUERTO = 5000;

	public void iniciar() {
		ServerSocket servidor = null;
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		try {
				servidor = new ServerSocket(PUERTO);
				System.out.println("Esperando conexiones del cliente...");
				cliente = servidor.accept();
				System.out.println("Cliente conectado.");
	
				entrada = new ObjectInputStream(cliente.getInputStream());
				salida = new ObjectOutputStream(cliente.getOutputStream());
	
				String linea = (String) entrada.readObject();
				System.out.println("Recibido: " + linea);
				
				int opcion = Integer.parseInt(linea.split("/")[0]);

			/*	Envio envio = new Envio ();
				envio.setUsuario("Patata");
				salida.writeObject(envio);
				salida.flush();
				System.out.println("Respuesta enviada ");*/
				
				// Esto rula!!1
				//				String response = "Hola caracola";
				//				salida.writeObject(response);
				//				salida.flush();
				//				System.out.println("Respuesta enviada " + response);
						
	            switch(opcion) {
	            case 1:
	            	envio.setLogin(comprobarUsuario(linea.split("/")[1], linea.split("/")[2]));
	            	salida.writeObject(envio);
	            	salida.flush();
	            	System.out.println("enviado "+envio.getLogin());
	            }
				
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (servidor != null)
					servidor.close();
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
		// TODO Auto-generated method stub
		if (baseDeDatos.Consultas.consultarUsuario(peticion, peticion2)) {
			 return true;
			  
		} else {
			return false;
		}
		
	}

}
