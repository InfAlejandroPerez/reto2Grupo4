package vista;

import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class logIn {

	JFrame frame;
	private JTextField txtUser;
	private JTextField txtPassword;

	JButton btnLogIn;
	
	static logIn window;
	
	JButton btnRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new logIn();
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
	public logIn() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(175, 89, 102, 20);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(176, 147, 101, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setBounds(97, 92, 68, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(87, 150, 79, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblNewLabel_1 = new JLabel("LOG IN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(146, 31, 101, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnLogIn = new JButton("Iniciar Sesion");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user = txtUser.getText().toString();
				
				String password = txtPassword.getText().toString();
			
				if(baseDeDatos.Consultas.consultarUsuario(user, password)) {
					
					ListaMunicipios m = new ListaMunicipios();
					m.frame.setVisible(true);
					
					window.frame.setVisible(false);	
					
				}else {
					
					JOptionPane.showMessageDialog(frame, "La Contraseña o Usuario Son Incorrectos");
					
				}
				
			}
		});
		btnLogIn.setBounds(75, 200, 129, 37);
		frame.getContentPane().add(btnLogIn);
		
		btnRegister = new JButton("Registrarme");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				register m = new register();
				m.frame.setVisible(true);
				
				window.frame.setVisible(false);	
				
			}
		});
		btnRegister.setBounds(229, 200, 121, 37);
		frame.getContentPane().add(btnRegister);
	}
}
