package Controlador;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import baseDeDatos.LectorEstaciones;
import baseDeDatos.LectorJsonData;
import modelo.Estaciones;

public class Main {
	
	
	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");

		try {
			
			leerPueblos.LeerPueblos();
			
			LectorEstaciones.guardarDatosEstaciones();
			
			//LectorJsonData.guardarDatosMetereologicos();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Servidor s = new Servidor();
		s.iniciar();

	}
	
}
