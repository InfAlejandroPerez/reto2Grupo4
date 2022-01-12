package Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONObject;

public class readJsonFormUrl {

	public static void main(String args[]) {
		
		httpCertifi.validCert();
		
		String euskalmetAdjuntos = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json";
		String json = readJsonFromUrl(euskalmetAdjuntos);
		
		System.out.println(json);
		
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
		
		private static void LeerJson(String json) {
			
			
			
			
			
		}
	
}