package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JList;

public class ListaMunicipios {

	JFrame frame;
	
	JComboBox comboBoxProvincias;

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
		
		comboBoxProvincias.add(comboBoxProvincias, baseDeDatos.Consultas.pvs.get(0).getNombre().toString());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBoxProvincias = new JComboBox();
		comboBoxProvincias.setBounds(26, 41, 124, 22);
		frame.getContentPane().add(comboBoxProvincias);
		
		JList list = new JList();
		list.setBounds(197, 11, 188, 218);
		frame.getContentPane().add(list);
	}
}
