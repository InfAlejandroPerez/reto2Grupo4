package Controlador;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private final int PUERTO = 5000;
	private final String IP = "127.0.0.1";

	public void inicar() {

		try {
			// necesitamos una IP y un PUERTO para establecer la comunicacion
			Socket cliente = new Socket(IP, PUERTO);
			ObjectInputStream entrada = null;
			ObjectOutputStream salida = null;

			System.out.println("Conexión establecida con el servidor");

			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
