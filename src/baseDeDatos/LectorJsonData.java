package baseDeDatos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelo.Datos;
import modelo.Estaciones;

public class LectorJsonData {
	private static final String DATOSMETEOROLICOS = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json";

	public static void guardarDatosMetereologicos() throws ParseException {
		// lee la info de euskalmet e inserta los datos en la bbdd
		String jsonData = ReadJsonFromUrl.readData(DATOSMETEOROLICOS);
		// ArrayList<String> datosMeteo = new ArrayList<String>();
		// System.out.println(jsonData);
		String estacion;
		String estacion2;
		ArrayList<String> readedData = ordenarDatos(jsonData);
		for (int i = 0; i < readedData.size(); i++) {
			estacion = readedData.get(i).toString();
			if (readedData.size() >= i + 6) {
				estacion2 = readedData.get(i + 4).toString();
				// System.out.println(estacion);
				if (estacion.equalsIgnoreCase(estacion2)) {
						jsonData = ReadJsonFromUrl.readData(readedData.get(i + 5).toString());
						// System.out.println(jsonData);
						volcarDatos(estacion, jsonData);
						i = i + 5;
						// Avanzo 5 posiciones hasta la siguiente estacion metereologica}
					
				}
			}
		}
	}

	private static void volcarDatos(String estacion, String jsonData) throws ParseException {
		// los datos
		String readedData[] = jsonData.split("\"");
		Estaciones estaciones = new Estaciones();
		estaciones.setNombre(estacion);
		estaciones.setCodEstacion(Consultas.getCodeFromEstacion(estacion));

		Date fecha = null;
		Date hora = null;
		String comgm3 = null;
		String co8hmgm3 = null;
		String nogm3 = null;
		String no2 = null;
		String no2ica = null;
		String noxgm3 = null;
		String pm10ica = null;
		String pm25 = null;
		String pm25ica = null;
		String so2 = null;
		String so2ica = null;
		String icaestacion = null;

		for (int i = 0; i < readedData.length; i++) {
			System.out.println(readedData[i]);
			if (readedData[i].equalsIgnoreCase("Date")) {
				fecha = new SimpleDateFormat("dd/MM/yyyy").parse(readedData[i + 2]);
				if (fecha == null) {
					i = i + 20;
				}
			} else if (readedData[i].equalsIgnoreCase("HourGMT")) {
				// hora = readedData[i];
			} else if (readedData[i].equalsIgnoreCase("comgm3")) {
				comgm3 = readedData[i + 2];
			} else if (readedData[i].equalsIgnoreCase("nogm3")) {
				nogm3 = readedData[i + 2];
			} else if (readedData[i].equalsIgnoreCase("no2")) {
				no2 = readedData[i + 2];
			} else if (readedData[i].equalsIgnoreCase("no2ica")) {
				no2ica = readedData[i + 2];
			} else if (readedData[i].equalsIgnoreCase("noxgm3")) {
				noxgm3 = readedData[i + 2];
			} else if (readedData[i].equalsIgnoreCase("pm10ica")) {
				pm10ica = readedData[i + 2];
			} else if (readedData[i].equalsIgnoreCase("so2")) {
				so2 = readedData[i + 2];
			} else if (readedData[i].equalsIgnoreCase("so2ica")) {
				so2ica = readedData[i + 2];
			} else if (readedData[i].equalsIgnoreCase("icaestacion")) {
				icaestacion = readedData[i + 2];
				Datos d = new Datos(estaciones, fecha, hora, comgm3, co8hmgm3, nogm3, no2, no2ica, noxgm3, pm10ica,
						pm25, pm25ica, so2, so2ica, icaestacion);
				Inserts.insertDatos(d);
				// i=readedData.length;

			}
		}

	}

	public static ArrayList<String> ordenarDatos(String json) {
		// separa el json con los datos metereologicos, devuelve la estacion y la url de
		// los datos
		String readedData[] = json.split("\"");
		ArrayList<String> data = new ArrayList<String>();
		// String name = "\""+"name"+"\"";
		// String name = "\""+"url"+"\"";
		for (int i = 0; i < readedData.length; i++) {
			if (readedData[i].equalsIgnoreCase("name")) {
				data.add(readedData[i + 2]);
			} else if (readedData[i].equalsIgnoreCase("url")) {
				data.add(readedData[i + 2]);
			}
		}
		return data;
	}

}
