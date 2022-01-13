package baseDeDatos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import Controlador.httpCertifi;
import modelo.Municipiospueblos;
import modelo.Provincia;

public class ReadJsonFromUrl {

	public static String readData(String url) {

		httpCertifi.validCert();

		String euskalmetAdjuntos = url;
		String json = readJsonFromUrl(euskalmetAdjuntos);

		// System.out.println(json);
		return json;
	}

	public static String readJsonFromUrl(String url) {
		InputStream is = null;

		try {

			is = new URL(url).openStream();

		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		try {

			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = null;

			try {

				jsonText = readAll(rd);

			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

			}
			return jsonText;

		} finally {

			try {

				is.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

			}
		}
	}

	private static String readAll(Reader rd) throws IOException {

		StringBuilder sb = new StringBuilder();
		int cp;

		while ((cp = rd.read()) != -1) {

			sb.append((char) cp);

		}

		return sb.toString();

	}

	static ArrayList<String> leerJsonParserFromUrl(String url) {
		ArrayList<String> ret = new ArrayList<String>();
		JsonParser parser = new JsonParser();
		// Lee el Json
		String fr = readJsonFromUrl(url);

		JsonElement datos = parser.parse(fr);

		JsonArray array = datos.getAsJsonArray();
		Iterator<JsonElement> iter = array.iterator();

		while (iter.hasNext()) {
			// ITera el Json y devuelve cada linea
			JsonElement entrada = iter.next();
			JsonObject objeto = entrada.getAsJsonObject();

			Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();
			// Recorta el json y devuelve el dato

			JsonPrimitive valor = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor.getAsString());

			String date = valor.toString();

			date = date.substring(1, date.length() - 1);
			ret.add(date);
			// Valor 2
			JsonPrimitive valor2 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor2.getAsString());

			String HourGMT = valor2.toString();

			HourGMT = HourGMT.substring(1, HourGMT.length() - 1);

			// Valor 3
			JsonPrimitive valor3 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor3.getAsString());

			String COmgm3 = valor3.toString();

			HourGMT = COmgm3.substring(1, COmgm3.length() - 1);

			// Valor 4
			JsonPrimitive valor4 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor4.getAsString());

			String CO8hmgm3 = valor4.toString();

			HourGMT = CO8hmgm3.substring(1, CO8hmgm3.length() - 1);

			// Valor 5
			JsonPrimitive valor5 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor5.getAsString());

			String NOgm3 = valor5.toString();

			HourGMT = NOgm3.substring(1, NOgm3.length() - 1);

			// Valor 6
			JsonPrimitive valor6 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor6.getAsString());

			String NO2 = valor6.toString();

			HourGMT = NO2.substring(1, NO2.length() - 1);

			// Valor 7
			JsonPrimitive valor7 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor7.getAsString());

			String NO2ICA = valor7.toString();

			HourGMT = NO2ICA.substring(1, NO2ICA.length() - 1);

			// Valor 8
			JsonPrimitive valor8 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor8.getAsString());

			String NOXgm3 = valor8.toString();

			HourGMT = NOXgm3.substring(1, NOXgm3.length() - 1);

			// Valor 9
			JsonPrimitive valor9 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor9.getAsString());

			String PM10 = valor9.toString();

			HourGMT = PM10.substring(1, PM10.length() - 1);

			// Valor 10
			JsonPrimitive valor10 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor10.getAsString());

			String PM10ICA = valor10.toString();

			HourGMT = PM10ICA.substring(1, PM10ICA.length() - 1);

			// Valor 11
			JsonPrimitive valor11 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor11.getAsString());

			String PM25 = valor11.toString();

			HourGMT = PM25.substring(1, PM25.length() - 1);

			// Valor 12
			JsonPrimitive valor12 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor12.getAsString());

			String PM25ICA = valor12.toString();

			HourGMT = PM25ICA.substring(1, PM25ICA.length() - 1);

			// Valor 13
			JsonPrimitive valor13 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor13.getAsString());

			String SO2 = valor13.toString();

			HourGMT = SO2.substring(1, SO2.length() - 1);

			// Valor 14
			JsonPrimitive valor14 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor13.getAsString());

			String SO2ICA = valor14.toString();

			HourGMT = SO2ICA.substring(1, SO2ICA.length() - 1);

			// Valor 15
			JsonPrimitive valor15 = iter2.next().getValue().getAsJsonPrimitive();
			System.out.println(valor15.getAsString());

			String ICAEstacion = valor15.toString();

			HourGMT = ICAEstacion.substring(1, ICAEstacion.length() - 1);
		}

		return ret;

	}

	public static JSONObject readJsonElementFromUrl(String url) {
		InputStream is = null;
		JSONObject json = null;

		try {

			is = new URL(url).openStream();

		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		try {

			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = null;

			try {

				jsonText = readAll(rd);
				json = new JSONObject(jsonText);

			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

			}
			return json;

		} finally {

			try {

				is.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

			}
		}
	}

}
