package baseDeDatos;

import java.text.ParseException;
import java.util.ArrayList;

import modelo.Datos;
import modelo.Estaciones;
import modelo.Municipiospueblos;
import modelo.Provincia;

public class LectorEstaciones {
	private static final String ESTACIONES = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json";

	public static void guardarDatosEstaciones() throws ParseException {
		// lee la info de euskalmet e inserta los datos en la bbdd
		String jsonData = ReadJsonFromUrl.readData(ESTACIONES);
		// ArrayList<String> datosMeteo = new ArrayList<String>();
		// System.out.println(jsonData);
		String[] estacion = jsonData.split("\"");
		int codEstacion = 0;
		Municipiospueblos municipiospueblos = null;
		Provincia p = new Provincia();
		String nombre = "";
		Double coordenadaX = (double) 0;
		Double coordenadaY = (double) 0;
		Integer codLocalidad = 0;
		Datos datos = null;

		for (int i = 0; i < estacion.length; i++) {
			System.out.println(estacion[i]);
			if (estacion[i].equalsIgnoreCase("Name")) {
				nombre = estacion[i + 2];
			} else if (estacion[i].equalsIgnoreCase("Province")) {
				//p.setNombre(estacion[i + 2]);
			} else if (estacion[i].equalsIgnoreCase("Town")) {
				//nombre = estacion[i + 2];
			} else if (estacion[i].equalsIgnoreCase("Address")) {
				// data.add(estacion[i + 2]);
			} else if (estacion[i].equalsIgnoreCase("Latitude")) {
				//coordenadaX = Double.parseDouble(estacion[i + 2]);
			} else if (estacion[i].equalsIgnoreCase("Longitude")) {
				//coordenadaY = Double.parseDouble(estacion[i + 2]);
				Estaciones e = new Estaciones();
				e.setCodEstacion(codEstacion);
				e.setNombre(nombre);
				inserts.insertEstaciones(e);
				
				codEstacion++;
				
			}
		}

	}
}
