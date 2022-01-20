package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.example.euskalmet.Envio;

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

			int opcion = Integer.parseInt(linea.split("/")[0]);

			switch (opcion) {
			case 1:
				envio.setLogin(comprobarUsuario(linea.split("/")[1], linea.split("/")[2]));
				salida.writeObject(envio);
				salida.flush();
				System.out.println("enviado " + envio.getLogin());
				
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
}
