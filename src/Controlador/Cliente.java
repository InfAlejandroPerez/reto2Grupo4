package Controlador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.example.euskalmet.Envio;

import Vista.ListaMunicipios;
import Vista.register;

public class Cliente extends JFrame {

	private final int PUERTO = 5000;
	private final String IP = "127.0.0.1";
	private boolean seguir;
	private String peticion;
	private String datos ="markel/123";
	private Envio recibido;
	private int opcion = 0;
	ObjectInputStream entrada = null;
	ObjectOutputStream salida = null;

	public void inicar() {
		while (true) {
			try {
				// necesitamos una IP y un PUERTO para establecer la comunicacion
				Socket cliente = new Socket(IP, PUERTO);

				System.out.println("Conexion establecida con el servidor");

				salida = new ObjectOutputStream(cliente.getOutputStream());
				entrada = new ObjectInputStream(cliente.getInputStream());
				do {
					switch (opcion) {

					case 1:
						peticion = opcion + "/" + datos;
						salida.writeObject(peticion);
						salida.flush();
						recibido = (Envio) entrada.readObject();
						System.out.println(recibido.getLogin());
						opcion = 0;
						break;
					}
				} while (seguir);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}
