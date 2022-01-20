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

	private final int PUERTO = 4444;
	private final String IP = "127.0.0.1";
	private boolean seguir;
	private String peticion;
	private String datos;
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
						opcion = 0;
						break;
					}
				} while (seguir);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void ventanaLogIn() {

		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JTextField txtUser = new JTextField();
		txtUser.setBounds(175, 89, 102, 20);
		this.getContentPane().add(txtUser);
		txtUser.setColumns(10);

		JTextField txtPassword = new JTextField();
		txtPassword.setBounds(176, 147, 101, 20);
		this.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setBounds(97, 92, 68, 14);
		this.getContentPane().add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(87, 150, 79, 14);
		this.getContentPane().add(lblPassword);

		JLabel lblNewLabel_1 = new JLabel("LOG IN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(146, 31, 101, 37);
		this.getContentPane().add(lblNewLabel_1);

		JButton btnLogIn = new JButton("Iniciar Sesion");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String user = txtUser.getText().toString();
				try {
					salida.writeObject(user);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String pass = txtPassword.getText().toString();
				try {
					salida.writeObject(pass);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				String user = txtUser.getText().toString();
//				
//				String password = txtPassword.getText().toString();

				if (baseDeDatos.Consultas.consultarUsuario(user, pass)) {

					ListaMunicipios m = new ListaMunicipios();
//					m.frame.setVisible(true);
//					
//					frame.setVisible(false);	

				} else {

					JOptionPane.showInputDialog(this, "La Contraseña o Usuario Son Incorrectos");

				}

			}
		});
		btnLogIn.setBounds(75, 200, 129, 37);
		this.getContentPane().add(btnLogIn);

		JButton btnRegister = new JButton("Registrarme");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				this.setVisible(false);	
//				
//				register reg = new register();
//				reg.frame.setVisible(true);

			}
		});
		btnRegister.setBounds(229, 200, 121, 37);
		this.getContentPane().add(btnRegister);

	}

	public void setOpcion(int opc) {
		this.opcion = opc;
	}

	public void setDatos(String dat) {
		datos = dat;
	}

	public Envio getResponse() {
		return recibido;
	}
}
