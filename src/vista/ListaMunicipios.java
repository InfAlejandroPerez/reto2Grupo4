package vista;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class ListaMunicipios {

	JFrame frame;
	
	JComboBox<String> comboBoxProvincias;
	
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	
	JList<String> list;
	
	ArrayList<String> repes = new ArrayList<String>();
		
	public static int lastIndex = 0;
	
	JScrollPane scrollPane_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaMunicipios window = new ListaMunicipios();
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
	public ListaMunicipios() {
		initialize();
		
		baseDeDatos.Consultas.getProvincias();
		
		ArrayList<String> ls = new ArrayList<String>(); 
		
		for(int i = 0; i < baseDeDatos.Consultas.pvs.size(); i++) {
			
			ls.add(baseDeDatos.Consultas.pvs.get(i).getNombre());
			
		}
		
		comboBoxProvincias.setModel(new DefaultComboBoxModel<String>(ls.toArray(new String[0])));
		
		JButton btnFill = new JButton("Mostrar Municipios");
		btnFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean repe = false;
				
				baseDeDatos.Consultas.getMunicipios(comboBoxProvincias.getSelectedItem().toString());
				
				for(int i = 0; i < baseDeDatos.Consultas.munis.size(); i++) {
					
					for(int x = 0; x < repes.size(); x++) {
						
						if(baseDeDatos.Consultas.munis.get(i).getNombre().toString().equals(repes.get(x))){
							
							repe = true;
							
						}
					
					}
					
					if(repe == false) {
					
						dlm.addElement(baseDeDatos.Consultas.munis.get(i).getNombre().toString());
						
					}
					
					repes.add(baseDeDatos.Consultas.munis.get(i).getNombre().toString());
					
					repe = false;
					
				}
				
				repes.clear();
				
				lastIndex = baseDeDatos.Consultas.munis.size() - 1;
				
			}
		});
		
		btnFill.setBounds(26, 113, 124, 48);
		frame.getContentPane().add(btnFill);
		
		btnNewButton = new JButton("Borrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dlm.clear();
				
			}
		});
		btnNewButton.setBounds(26, 172, 124, 35);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBoxProvincias = new JComboBox<String>();
		comboBoxProvincias.setBounds(26, 41, 124, 22);
		frame.getContentPane().add(comboBoxProvincias);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(193, 11, 220, 239);
		frame.getContentPane().add(scrollPane_1);
		
		list = new JList<String>(dlm);
		scrollPane_1.setViewportView(list);
		
	}
}
