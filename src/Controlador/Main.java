package Controlador;

public class Main {
	private static final String DATOSMETEOROLICOS = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json";

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		Servidor s = new Servidor();
		s.iniciar();
	}

	private static void leerDatosMetereologicos() {
		String jsonData = readJsonFormUrl.readData(DATOSMETEOROLICOS);

	}

}
