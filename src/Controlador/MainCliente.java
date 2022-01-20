package Controlador;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Usuarios;
import vista.ListaMunicipios;
import vista.logIn;
import vista.register;

public class MainCliente extends JFrame{
	
	private JPanel panelPrincipal;
	private JTextField textField;
	private JTextField textField_1;
	private Cliente c;
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainCliente frame = new MainCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//temporal.ventanaLogIn();
	}
	
	public MainCliente() {
		
		 c = new Cliente();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel(new CardLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
		
		panelPrincipal.add(switchPanel(1));
	}

	public JPanel switchPanel(int panel) {

		if(panelPrincipal.getComponents().length > 0) {
			
			panelPrincipal.removeAll();
			
			panelPrincipal.validate();
			
			panelPrincipal.repaint();
			
		}
		
		JPanel panelElegido = new JPanel();
		switch (panel) {
		case 1:
			
			panelElegido.setBounds(this.getBounds());
			panelElegido.setLayout(null);
			
			JTextField txtUser = new JTextField();
			txtUser.setBounds(175, 89, 102, 20);
			panelElegido.add(txtUser);
			txtUser.setColumns(10);
			
			JTextField txtPassword = new JTextField();
			txtPassword.setBounds(176, 147, 101, 20);
			panelElegido.add(txtPassword);
			txtPassword.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("User:");
			lblNewLabel.setBounds(97, 92, 68, 14);
			panelElegido.add(lblNewLabel);
			
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setBounds(87, 150, 79, 14);
			panelElegido.add(lblPassword);
			
			JLabel lblNewLabel_1 = new JLabel("LOG IN");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel_1.setBounds(146, 31, 101, 37);
			panelElegido.add(lblNewLabel_1);
			
			JButton btnLogIn = new JButton("Iniciar Sesion");
			btnLogIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String user = txtUser.getText().toString();
					
					String password = txtPassword.getText().toString();
					
					/*if(baseDeDatos.Consultas.consultarUsuario(user, password)) {

						try {
							Cliente.salida.writeObject("1/" + user + "/" + password);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							System.out.println(Cliente.entrada.readObject());
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else {
						
						JOptionPane.showMessageDialog(panelElegido, "La Contraseña o Usuario Son Incorrectos");
						
					}*/
					Cliente.setDatos(user+"/"+password);
					Cliente.setOpcion(1);
					Cliente.inicar();
					Cliente.getResponse();
					
				}
			});
			btnLogIn.setBounds(75, 200, 129, 37);
			panelElegido.add(btnLogIn);
			
			JButton btnRegister = new JButton("Registrarme");
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelPrincipal.add(switchPanel(2));
					
				}
			});
			btnRegister.setBounds(229, 200, 121, 37);
			panelElegido.add(btnRegister);
		
				/*public void actionPerformed(ActionEvent e) {
					try {
						System.out.println("click");
						String usuario = textField.getText();
						String pass = textField_1.getText();

						Cliente.salida.writeObject("1/" + usuario + "/" + pass);
						System.out.println(Cliente.entrada.readObject());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}*/
			break;
			
		case 2:
			
			panelElegido.setBounds(this.getBounds());
			panelElegido.setLayout(null);
			
			JLabel lblNewLabel1 = new JLabel("REGISTER");
			lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel1.setBounds(147, 26, 166, 41);
			panelElegido.add(lblNewLabel1);
			
			JTextField txtNombre = new JTextField();
			txtNombre.setBounds(157, 78, 111, 28);
			panelElegido.add(txtNombre);
			txtNombre.setColumns(10);
			
			JTextField txtContraseña = new JTextField();
			txtContraseña.setBounds(157, 131, 111, 28);
			panelElegido.add(txtContraseña);
			txtContraseña.setColumns(10);
			
			JButton btnVolverLogIn = new JButton("Volver");
			btnVolverLogIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
				}
			});
			btnVolverLogIn.setBounds(10, 209, 97, 41);
			panelElegido.add(btnVolverLogIn);
			
			JButton btnRegister1 = new JButton("Crear nuevo Usuario");
			btnRegister1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String nombre = txtNombre.getText();
					
					String Contraseña = txtContraseña.getText();
					
					if(nombre.isEmpty() || Contraseña.isEmpty()) {
						
						
						JOptionPane.showMessageDialog(panelElegido, "Debes Rellenar los Campos");
						
						
					}else if(isNumeric(Contraseña) == true) {
						
						int cont = Integer.parseInt(Contraseña);
						
						Usuarios u1 = new Usuarios(nombre, cont);
						
						 JOptionPane.showMessageDialog(panelElegido, "Nuevo Usuario Registrado");
						 
					}else {
			            	
						JOptionPane.showMessageDialog(panelElegido, "La Contraseña es Un Campo Numerico");
			            	
					}
						
					}
				
			});
			btnRegister1.setBounds(117, 186, 176, 41);
			panelElegido.add(btnRegister1);
			
			JLabel lblNewLabel_3 = new JLabel("Nombre:");
			lblNewLabel_3.setBounds(91, 78, 56, 20);
			panelElegido.add(lblNewLabel_3);
			
			JLabel lblNewLabel_2 = new JLabel("Contraseña:");
			lblNewLabel_2.setBounds(65, 137, 82, 17);
			panelElegido.add(lblNewLabel_2);
			
			break;

		default:
			break;
		}

		return panelElegido;
	}
	
	public static boolean isNumeric(String numero) {
		
		boolean error = false;
		
		try {
			
			Integer.parseInt(numero);
			
			error = true;
			
		}catch(Exception E) {
			
			error = false;
			
		}
		
		return error;
		
	}
	
}
