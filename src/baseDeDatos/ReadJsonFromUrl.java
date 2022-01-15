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
