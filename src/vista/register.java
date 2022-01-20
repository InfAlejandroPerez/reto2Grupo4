package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import modelo.Usuarios;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class register {

	JFrame frame;
	private JTextField txtNombre;
	private JTextField txtContraseña;
	
	static register window;
	
	JButton btnVolverLogIn;
	
	JButton btnRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public register() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(147, 26, 166, 41);
		frame.getContentPane().add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(157, 78, 111, 28);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtContraseña = new JTextField();
		txtContraseña.setBounds(157, 131, 111, 28);
		frame.getContentPane().add(txtContraseña);
		txtContraseña.setColumns(10);
		
		btnVolverLogIn = new JButton("Volver");
		btnVolverLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				
				logIn log = new logIn();
				log.frame.setVisible(true);
				
			}
		});
		btnVolverLogIn.setBounds(10, 209, 97, 41);
		frame.getContentPane().add(btnVolverLogIn);
		
		btnRegister = new JButton("Crear nuevo Usuario");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				
				String Contraseña = txtContraseña.getText();
				
				if(nombre.isEmpty() || Contraseña.isEmpty()) {
					
					
					JOptionPane.showMessageDialog(frame, "Debes Rellenar los Campos");
					
					
				}else if(isNumeric(Contraseña) == true) {
					
					int cont = Integer.parseInt(Contraseña);
					
					Usuarios u1 = new Usuarios(nombre, cont);
					
					baseDeDatos.inserts.insertUsuarios(u1);
					
					 JOptionPane.showMessageDialog(frame, "Nuevo Usuario Registrado");
					 
				}else {
		            	
					JOptionPane.showMessageDialog(frame, "La Contraseña es Un Campo Numerico");
		            	
				}
					
				}
			
		});
		btnRegister.setBounds(117, 186, 176, 41);
		frame.getContentPane().add(btnRegister);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(91, 78, 56, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setBounds(65, 137, 82, 17);
		frame.getContentPane().add(lblNewLabel_2);
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
