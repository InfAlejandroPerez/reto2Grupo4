package Controlador;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import baseDeDatos.LectorEstaciones;
import baseDeDatos.LectorJsonData;
import baseDeDatos.leerPueblos;
import modelo.Datos;
import modelo.Estaciones;

public class Main {
	
	static Scanner teclado = new Scanner(System.in);
	
	public static ArrayList<Datos> data = new ArrayList<Datos>();

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");

		//System.out.println(baseDeDatos.Consultas.getCodeFromEstacion("ALGORTA_(BBIZI2)"));
		
		String next = null;
		
		do {
			
			System.out.println("numerito:");
			next = teclado.nextLine();
			
			

		try {
			
			//leerPueblos.LeerPueblos();
			
			//LectorEstaciones.guardarDatosEstaciones();
			
			LectorJsonData.guardarDatosMetereologicos();
			
		} catch (ParseException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Servidor s = new Servidor();
		//s.iniciar();
		
		}while(!next.equals("1"));

	}

}
