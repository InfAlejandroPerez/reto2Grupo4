package Controlador;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Municipiospueblos;
import modelo.Usuarios;

public class MainCliente extends JFrame{
	
	private JPanel panelPrincipal;
	private JTextField textField;
	private JTextField textField_1;
	private Cliente c;
	
	static String NombreMuni;
	
	static String dataMeteorPLaya = "";
	
	static ArrayList<String> nombresProvincias = new ArrayList<String>();
	
	static ArrayList<String> top5 = new ArrayList<String>();
	
	static ArrayList<String> top5data = new ArrayList<String>();
	
	static ArrayList<String> dataPlaya = new ArrayList<String>();
	
	static String nombrePlayaSelect = "";
	
	static ArrayList<String> Munis = new ArrayList<String>();
	
	static ArrayList<String> datosMuni = new ArrayList<String>();
	
	static ArrayList<String> playas = new ArrayList<String>();
	
	static Double CoodenadaX = (double) 0;
	static Double CoodenadaY = (double) 0;
	
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
		setBounds(100, 100, 463, 365);
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
			
			JPasswordField passwordField = new JPasswordField();
			passwordField.setBounds(175, 147, 102, 20);
			panelElegido.add(passwordField);
			txtUser.setColumns(10);
			
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
					
					String password = passwordField.getText().toString();
					
					
					if(user.isEmpty() || password.isEmpty()) {
						
						JOptionPane.showMessageDialog(panelElegido, "La Contraseña o Usuario Son Incorrectos");
						
					}else {
						
						
						if(isNumeric(password)) {
							
							
							Cliente.setDatos(user+"/////"+password);
							Cliente.setOpcion(1);
							Cliente.inicar();
							Cliente.getResponse();
							
							if(Cliente.getResponse() == true) {
								
								Cliente.setDatos("");
								Cliente.setOpcion(3);
								Cliente.inicar();
								nombresProvincias = Cliente.getArray();
								
								panelPrincipal.setLocation(0, 0);
								
								panelPrincipal.add(switchPanel(3));
								
							}else {
								
								JOptionPane.showMessageDialog(panelElegido, "Usuario o Contraseña Incorrectos");
								
								txtUser.setText("");
								
								passwordField.setText("");
								
							}							
							
						}else {
							
							JOptionPane.showMessageDialog(panelElegido, "La Contraseña debe ser un Numerico");
						
							passwordField.setText("");
							
						}
						
					}
					
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
			
			JTextField txtContrasenia = new JTextField();
			txtContrasenia.setBounds(157, 131, 111, 28);
			panelElegido.add(txtContrasenia);
			txtContrasenia.setColumns(10);
			
			JButton btnVolverLogIn = new JButton("Volver");
			btnVolverLogIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					
					panelPrincipal.add(switchPanel(1));
					
				}
			});
			btnVolverLogIn.setBounds(10, 209, 97, 41);
			panelElegido.add(btnVolverLogIn);
			
			JButton btnRegister1 = new JButton("Crear nuevo Usuario");
			btnRegister1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String nombre = txtNombre.getText();
					
					String Contrasenia = txtContrasenia.getText();
					
					if(nombre.isEmpty() || Contrasenia.isEmpty()) {
						
						
						JOptionPane.showMessageDialog(panelElegido, "Debes Rellenar los Campos");
						
						
					}else if(isNumeric(Contrasenia) == true) {
						
						int cont = Integer.parseInt(Contrasenia);
						
						Cliente.setDatos(nombre);
						Cliente.setOpcion(6);
						Cliente.inicar();
						Cliente.getResponse();
						
						if(Cliente.getResponse() == false) {
							
							Cliente.setDatos(nombre+"/////"+Contrasenia);
							Cliente.setOpcion(2);
							Cliente.inicar();
							
							JOptionPane.showMessageDialog(panelElegido, "Nuevo Usuario Registrado");
							
							Cliente.setDatos("");
							Cliente.setOpcion(3);
							Cliente.inicar();
							
							nombresProvincias.clear();
							
							if(nombresProvincias.size() > 0) {
								
								Cliente.getArray();
								
							}else {
								
								nombresProvincias = Cliente.getArray();
							}
							
							panelPrincipal.add(switchPanel(3));
							
						}else {
							
							JOptionPane.showMessageDialog(panelElegido, "Usuario ya Existente");
	
							txtNombre.setText("");
							
							txtContrasenia.setText("");
							
						}
						 
					}else {
			            	
						JOptionPane.showMessageDialog(panelElegido, "La Contraseña es Un Campo Numerico");
						
						txtContrasenia.setText("");
			            	
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
			
			
		case 3:
			
			ArrayList<String> repes = new ArrayList<String>();
			
			DefaultListModel<String> dlm = new DefaultListModel<String>();
			
			
			
			panelElegido.setBounds(this.getBounds());
			panelElegido.setLayout(null);
			
			JComboBox<String> comboBoxProvincias = new JComboBox<String>();
			comboBoxProvincias.setModel(new DefaultComboBoxModel<String>(nombresProvincias.toArray(new String[0])));
			comboBoxProvincias.setBounds(26, 41, 124, 22);
			panelElegido.add(comboBoxProvincias);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(193, 11, 220, 239);
			panelElegido.add(scrollPane_1);
			
			JList<String> list = new JList<String>(dlm);
			scrollPane_1.setViewportView(list);
			list.addMouseListener(new java.awt.event.MouseAdapter() {
			      public void mouseClicked(java.awt.event.MouseEvent e) {
			    	  
			    	  JList list1 = (JList)e.getSource();
			    	  
			          if (e.getClickCount() == 2) {
			        	  
			        	  String ventana = list.getSelectedValue().split("\\(")[1];
			        	  
			        	  ventana = ventana.substring(0, ventana.length() - 1);
			        	  
			        	  if(ventana.equals("Datos")) {

			              // Double-click detected 	 
			        		  
			        		  String dato = list.getSelectedValue().split("\\(")[0];
			        	  
			        		  Cliente.setDatos(dato);
			        		  Cliente.setOpcion(5);
			        		  Cliente.inicar();
			        		  datosMuni = Cliente.getDatosMuni();
			        	  
			        		  panelPrincipal.add(switchPanel(4));
			        	  
			        	  }else if (ventana.equals("Playas")) {
			        		  
			        		  String dato = list.getSelectedValue().split("\\(")[0];
			        		  
			        		  NombreMuni = dato;
			        		  
			        		  Cliente.setDatos(dato);
			        		  Cliente.setOpcion(12);
			        		  Cliente.inicar();
			        		  playas = Cliente.getPlayasFromMuni();
			        		  
			        		  panelPrincipal.add(switchPanel(5));
			        		  
			        	  }
			        	  
			              
			          } else if (e.getClickCount() == 3) {

			              // Triple-click detected
			              int index = list.locationToIndex(e.getPoint());
			              
			          }
			      
			      }
			      
			});
			
			JButton btnFill = new JButton("Mostrar Municipios");
			btnFill.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dlm.clear();
				
					boolean repe = false;
					
					Cliente.setDatos(comboBoxProvincias.getSelectedItem().toString());
					Cliente.setOpcion(4);
					Cliente.inicar();
					Munis = Cliente.getMunis();
					
					for(int i = 0; i < Munis.size(); i++) {
						
						for(int x = 0; x < repes.size(); x++) {
							
							if(Munis.get(i).toString().equals(repes.get(x))){
								
								repe = true;
								
							}
						
						}
						
						if(repe == false) {
						
							dlm.addElement(Munis.get(i).toString() + "(Datos)");
							
						}
						
						repes.add(Munis.get(i).toString());
						
						repe = false;
						
					}
					
					repes.clear();
					
				}
			});
			
			btnFill.setBounds(26, 113, 124, 48);
			panelElegido.add(btnFill);
			
			JButton btnNewButton = new JButton("Borrar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dlm.clear();
					
					Munis.clear();
					
				}
			});
			btnNewButton.setBounds(26, 172, 124, 35);
			panelElegido.add(btnNewButton);
			
			JButton btnPlayas = new JButton("Ver Playas");
			btnPlayas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dlm.clear();
					
					boolean repe = false;
					
					Cliente.setDatos("");
					Cliente.setOpcion(11);
					Cliente.inicar();
					Munis = Cliente.getMunisPlayas();
					
					for(int i = 0; i < Munis.size(); i++) {
						
						for(int x = 0; x < repes.size(); x++) {
							
							if(Munis.get(i).toString().equals(repes.get(x))){
								
								repe = true;
								
							}
						
						}
						
						if(repe == false) {
						
							dlm.addElement(Munis.get(i).toString() + "(Playas)" );
							
						}
						
						repes.add(Munis.get(i).toString());
						
						repe = false;
						
					}
					
					repes.clear();
					
				}
			});
			btnPlayas.setBounds(26, 210, 124, 35);
			panelElegido.add(btnPlayas);
			
			JButton btntop = new JButton("Top 5");
			btntop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Cliente.setDatos("");
					Cliente.setOpcion(15);
					Cliente.inicar();
					top5 = Cliente.getTop5();
					
					panelPrincipal.add(switchPanel(7));
					
				}
			});
			
			btntop.setBounds(26, 260, 124, 35);
			panelElegido.add(btntop);
			
			break;
			
		case 4:
			
			DefaultListModel<String> dlm1 = new DefaultListModel<String>();
			
			dlm1.clear();
			
			for(int i = 1; i < datosMuni.size();i++) {
				
				dlm1.addElement(datosMuni.get(i));
				
			}
			
			if(dlm1.getSize() == 0) {
				
				dlm1.addElement("No hay Estaciones Registradas");
				
			}
			
			panelElegido.setBounds(this.getBounds());
			panelElegido.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(238, 59, 172, 178);
			panelElegido.add(scrollPane);
			
			JList<String> listEstaciones = new JList(dlm1);
			scrollPane.setViewportView(listEstaciones);
			listEstaciones.addMouseListener(new java.awt.event.MouseAdapter() {
			      public void mouseClicked(java.awt.event.MouseEvent e) {
			    	  
			          if (e.getClickCount() == 2) {
			        	  
			        	  if(!listEstaciones.getSelectedValue().toString().equals("No hay Estaciones Registradas")) {
			        	  
			        	  	Cliente.setDatos(listEstaciones.getSelectedValue().toString());
							Cliente.setOpcion(7);
							Cliente.inicar();
							CoodenadaX = Cliente.getCoordenadasEstacion();
							
							Cliente.setDatos(listEstaciones.getSelectedValue().toString());
							Cliente.setOpcion(8);
							Cliente.inicar();
							CoodenadaY = Cliente.getCoordenadasEstacion();

			        	  JOptionPane.showMessageDialog(panelElegido, "Coordenada de " + listEstaciones.getSelectedValue().toString() + "\n \nCoordenada X: " + CoodenadaX + "\n\nCoordenadaY : " + CoodenadaY);
			        	  
			        	  }
			        	  
			          }
			      
			      }
			      
			});
			
			JLabel lblNewLabel12 = new JLabel("ESTACIONES");
			lblNewLabel12.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel12.setBounds(238, 11, 172, 37);
			panelElegido.add(lblNewLabel12);
			
			JScrollPane scrollPane_11 = new JScrollPane();
			scrollPane_11.setBounds(10, 59, 172, 178);
			panelElegido.add(scrollPane_11);
			
			JTextPane textPaneDescripcion = new JTextPane();
			scrollPane_11.setViewportView(textPaneDescripcion);
			
			textPaneDescripcion.setText(datosMuni.get(0).toString());
			
			datosMuni.clear();
			
			JLabel lblNewLabel_11 = new JLabel("MUNICIPIO");
			lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel_11.setBounds(10, 11, 172, 37);
			panelElegido.add(lblNewLabel_11);
			
			JButton btnExit = new JButton("EXIT");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelPrincipal.setVisible(false);
					
				}
			});
			btnExit.setBounds(278, 276, 105, 37);
			panelElegido.add(btnExit);
			
			JButton btnVolverMunis = new JButton("VOLVER");
			btnVolverMunis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					/*Cliente.setDatos("");
					Cliente.setOpcion(3);
					Cliente.inicar();
					nombresProvincias = Cliente.getArray();*/
					
					panelPrincipal.add(switchPanel(3));
					
				}
			});
			btnVolverMunis.setBounds(50, 276, 105, 37);
			panelElegido.add(btnVolverMunis);

			break;
			
		case 5:
			
			DefaultListModel<String> dlm2 = new DefaultListModel<String>();
			
			dlm2.clear();
			
			for(int i = 0; i < playas.size();i++) {
				
				dlm2.addElement(playas.get(i));
				
			}
			
			if(dlm2.getSize() == 0) {
				
				dlm2.addElement("No hay Playas Registradas");
				
			}
			
			panelElegido.setBounds(this.getBounds());
			panelElegido.setLayout(null);
			
			JScrollPane scrollPane1 = new JScrollPane();
			scrollPane1.setBounds(24, 48, 257, 179);
			panelElegido.add(scrollPane1);
			
			JList<String> list1 = new JList<String>(dlm2);
			scrollPane1.setViewportView(list1);
			list1.addMouseListener(new java.awt.event.MouseAdapter() {
			      public void mouseClicked(java.awt.event.MouseEvent e) {
			    	  
			          if (e.getClickCount() == 2) {
			        	  
			        	  
			        	  
			        	  Cliente.setDatos(list1.getSelectedValue().toString());
			        	  Cliente.setOpcion(13);
			        	  Cliente.inicar();
			        	  dataMeteorPLaya = Cliente.getMeteorPlayas();
			        	  
			        	  Cliente.setDatos(list1.getSelectedValue().toString());
			        	  Cliente.setOpcion(14);
			        	  Cliente.inicar();
			        	  dataPlaya = Cliente.getDataPlaya();
			        	  
			        	  nombrePlayaSelect = list1.getSelectedValue().toString();
			        	  
			        	  panelPrincipal.add(switchPanel(6));
			        	  
			          }
			      
			      }
			      
			});
			
			JLabel playa = new JLabel("PLAYAS");
			playa.setFont(new Font("Tahoma", Font.BOLD, 22));
			playa.setBounds(147, 11, 215, 26);
			panelElegido.add(playa);
			
			JButton btnNewButton_1 = new JButton("VOLVER");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelPrincipal.add(switchPanel(3));
					
				}
			});
			btnNewButton_1.setBounds(291, 192, 119, 47);
			panelElegido.add(btnNewButton_1);
			
			JLabel lblNewLabel_4 = new JLabel("MUNICIPIO");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_4.setBounds(291, 50, 119, 26);
			panelElegido.add(lblNewLabel_4);
			
			JLabel lblNombreMunicipio = new JLabel(NombreMuni);
			lblNombreMunicipio.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNombreMunicipio.setBounds(291, 73, 107, 42);
			panelElegido.add(lblNombreMunicipio);
			
			break;
			
		case 6:
			
			panelElegido.setBounds(this.getBounds());
			panelElegido.setLayout(null);
			
			JLabel lblTituloPlaya = new JLabel(nombrePlayaSelect);
			lblTituloPlaya.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblTituloPlaya.setBounds(31, 11, 357, 37);
			panelElegido.add(lblTituloPlaya);
			
			JScrollPane scrollPane2 = new JScrollPane();
			scrollPane2.setBounds(10, 59, 194, 191);
			panelElegido.add(scrollPane2);
			
			JTextPane textDescPLaya = new JTextPane();
			scrollPane2.setViewportView(textDescPLaya);
			
			textDescPLaya.setText(dataPlaya.get(0).toString());
			
			JTextField txtLati = new JTextField();
			txtLati.setBounds(280, 59, 108, 28);
			panelElegido.add(txtLati);
			txtLati.setColumns(10);
			
			txtLati.setText(dataPlaya.get(1).toString());
			
			JTextField txtLongi = new JTextField();
			txtLongi.setBounds(280, 115, 108, 28);
			panelElegido.add(txtLongi);
			txtLongi.setColumns(10);
			
			txtLongi.setText(dataPlaya.get(2).toString());
			
			JLabel lblNewLabel_12 = new JLabel("Latitud:");
			lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel_12.setBounds(223, 34, 97, 14);
			panelElegido.add(lblNewLabel_12);
			
			JLabel lblNewLabel_1_1 = new JLabel("Longitud:");
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel_1_1.setBounds(223, 98, 97, 14);
			panelElegido.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel122 = new JLabel("Información Meteorológica");
			lblNewLabel122.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel122.setBounds(223, 163, 201, 14);
			panelElegido.add(lblNewLabel122);
			
			JButton btnVolverListaPlayas = new JButton("Volver");
			btnVolverListaPlayas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelPrincipal.add(switchPanel(5));
					
				}
			});
			btnVolverListaPlayas.setBounds(317, 227, 89, 34);
			panelElegido.add(btnVolverListaPlayas);
			
			JLabel lblDatosMeteo = new JLabel(dataMeteorPLaya);
			lblDatosMeteo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDatosMeteo.setBounds(245, 185, 129, 42);
			panelElegido.add(lblDatosMeteo);
			
			break;
			
		case 7:
			
			DefaultListModel<String> dlm3 = new DefaultListModel<String>();
			
			dlm3.clear();
			
			for(int i = 0; i < top5.size();i++) {
				
				dlm3.addElement(top5.get(i));
				
			}
			
			if(dlm3.getSize() == 0) {
				
				dlm3.addElement("No hay top");
				
			}
			
			panelElegido.setBounds(this.getBounds());
			panelElegido.setLayout(null);
			
			JLabel lbltop = new JLabel("TOP 5 ESPACIOS NATURALES");
			lbltop.setFont(new Font("Tahoma", Font.BOLD, 22));
			lbltop.setBounds(37, 11, 350, 46);
			panelElegido.add(lbltop);
			
			JTextField txtMunicipio = new JTextField();
			txtMunicipio.setBounds(322, 94, 86, 20);
			panelElegido.add(txtMunicipio);
			txtMunicipio.setColumns(10);
			
			JTextField txtProvincia = new JTextField();
			txtProvincia.setBounds(322, 161, 86, 20);
			panelElegido.add(txtProvincia);
			txtProvincia.setColumns(10);
			
			JScrollPane scrollPanetop = new JScrollPane();
			scrollPanetop.setBounds(20, 68, 250, 161);
			panelElegido.add(scrollPanetop);
			
			JList<String> listtop = new JList<String>(dlm3);
			scrollPanetop.setViewportView(listtop);
			listtop.addMouseListener(new java.awt.event.MouseAdapter() {
			      public void mouseClicked(java.awt.event.MouseEvent e) {
			    	  
			          if (e.getClickCount() == 1) {
			        	  
			        	  if(!listtop.getSelectedValue().toString().equals("No hay top")) {
			        	  
			        	  	Cliente.setDatos(listtop.getSelectedValue().toString());
							Cliente.setOpcion(16);
							Cliente.inicar();
							top5data = Cliente.getTop5datos();
							
							if(!(top5data.size() == 0)) {
								
								txtMunicipio.setText(top5data.get(0).toString());
								
								//txtProvincia.setText(top5data.get(1).toString());
								
							}
			        	  
			        	  }
			        	  
			          }
			      
			      }
			      
			});  
			
			JLabel lblMunicipio = new JLabel("Municipio:");
			lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblMunicipio.setBounds(283, 68, 86, 15);
			panelElegido.add(lblMunicipio);
			
			JLabel lblMunicipio_1 = new JLabel("Provincia:");
			lblMunicipio_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblMunicipio_1.setBounds(280, 135, 86, 15);
			panelElegido.add(lblMunicipio_1);
			
			JButton btnVolvertop = new JButton("Volver");
			btnVolvertop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panelPrincipal.add(switchPanel(3));
					
				}
			});
			btnVolvertop.setBounds(298, 206, 110, 44);
			panelElegido.add(btnVolvertop);
			
			break;
			
		default:
			break;
		}

		panelElegido.setLocation(0, 0);
		
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
