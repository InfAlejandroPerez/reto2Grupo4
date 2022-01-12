package Controlador;

import java.util.ArrayList;

//import com.google.gson.JsonParser;

public class recorrerDatos {
//limpia los datos json que vienen en formato string
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
