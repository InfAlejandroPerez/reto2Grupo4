package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.euskalmet.Envio;

public class Servidor {

	// private Envio envio;
	private final int PUERTO = 5000;
	private static int contador = 0;

	public void iniciar() {
		while (true) {
			ServerSocket servidor = null;
			Socket cliente = null;
			try {
				servidor = new ServerSocket(PUERTO);
				System.out.println("Esperando conexiones del cliente...");
				cliente = servidor.accept();
				System.out.println("Cliente conectado.");

				// envio = new Envio();
				ServerThread hilo = new ServerThread(cliente);
				
				contador++;
				

			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				try {
					if (servidor != null)
						servidor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
