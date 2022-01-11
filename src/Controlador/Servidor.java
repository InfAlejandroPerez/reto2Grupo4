package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private final int PUERTO = 5000;
	private int clientes = 0;

	public void iniciar() {
		// punto de comunicacion del servidor
		ServerSocket servidor = null;
		// punto de comunicacion del cliente
		Socket cliente = null;
		
			try {

				servidor = new ServerSocket(PUERTO);

				System.out.println("Servicio iniciado| Esperando clientes...");

				cliente = servidor.accept();

				System.out.println("Cliente aceptado");
				clientes++;
				System.out.println("Número de clientes: " + clientes);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
	}

}
