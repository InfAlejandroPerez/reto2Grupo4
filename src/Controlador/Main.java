package Controlador;

import java.util.ArrayList;

public class Main {
	private static final String DATOSMETEOROLICOS = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json";

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");

		guardarDatosMetereologicos();

		Servidor s = new Servidor();
		s.iniciar();

	}

	private static void guardarDatosMetereologicos() {
		// lee la info de euskalmet e inserta los datos en la bbdd
		String jsonData = readJsonFormUrl.readData(DATOSMETEOROLICOS);
		// System.out.println(jsonData);
		ArrayList<String> readedData = recorrerDatos.ordenarDatos(jsonData);
		for (int i = 0; i < readedData.size(); i++) {
			System.out.println(readedData.get(i).toString());
			jsonData = readJsonFormUrl.readData(readedData.get(i + 1).toString());
			System.out.println(jsonData);
			i++;
		}
	}

}
