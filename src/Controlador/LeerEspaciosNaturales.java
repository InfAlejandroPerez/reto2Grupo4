package Controlador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import baseDeDatos.inserts;
import modelo.EspaciosNaturales;
import modelo.Localidad;
import modelo.Municipiospueblos;
import modelo.Provincia;

public class LeerEspaciosNaturales {

	static ArrayList<EspaciosNaturales> espaciosNaturales = new ArrayList<EspaciosNaturales>();
	
	static int codEspacios = 0;
	
	public static void Espacios() {
		
		espaciosNaturales.clear();
		
		System.out.println(leerPueblos.localidad.size());
		
		System.out.println(leerPueblos.Municipios.size());
		
		System.out.println(leerPueblos.Provincias.size());
		
		espaciosNaturales.clear();
		
		System.setProperty("file.encoding", "UTF-8");
		//Servidor s = new Servidor();
		//s.iniciar();
		
		JsonParser parser = new JsonParser();
		final String url = "src/JSONs/espacios_naturales1.json";
		

		try {

			FileReader fr = new FileReader(url);
			JsonElement datos = parser.parse(fr);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			
			while (iter.hasNext()) {
				
				JsonElement entrada = iter.next();
				JsonObject objeto = entrada.getAsJsonObject();
				
				Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();
				
				JsonPrimitive valor = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor.getAsString());
				
				String documentName = valor.toString();
				
				documentName = documentName.substring(1, documentName.length() -1);
				
				JsonPrimitive valor2 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor2.getAsString());
				
				String turism = valor2.toString();
				
				turism = turism.substring(4, turism.length() -4);
				
				if(turism.length() > 3000) {
					
					turism = "TO LONG";
					
				}
				
				iter2.next();
				
				JsonPrimitive valor4 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor4.getAsString());
				
				String localdadNombre = valor4.toString();
				
				localdadNombre = localdadNombre.substring(1, localdadNombre.length() -1);
				
				boolean noLocalidad = false;
				
				if(localdadNombre.equals("")) {
					
					noLocalidad = true;
					
				}
				
				for(int i = 0; i < 28; i++) {
					
					iter2.next();
					
				}
				
				JsonPrimitive valor7 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor7.getAsString());
				
				String nombreMunicipio = valor7.getAsString();
			
				nombreMunicipio = nombreMunicipio.substring(1, nombreMunicipio.length() -1);
				
				JsonPrimitive valor3 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor3.getAsString());
				
				int codMunicipio = 0;
				
				if(valor3.toString().contains(" ")) {
					
					String codMunicipioS = valor3.toString();
					
					System.out.println(codMunicipioS.split(" ")[0]);
					
					codMunicipioS = codMunicipioS.split(" ")[0];
					
					char comilla = codMunicipioS.charAt(0);
					
					char blanco = 0;
					
					codMunicipioS = codMunicipioS.replace(comilla, blanco);
					
					codMunicipioS = codMunicipioS.substring(1, codMunicipioS.length());
					
					System.out.println(codMunicipioS);
					
					codMunicipio = Integer.parseInt(codMunicipioS);
					
				}else {
					
					codMunicipio = valor3.getAsInt();
					
					System.out.println("Cod MUNICIPIO: " + codMunicipio);
					
				}
			
				iter2.next();
				
				JsonPrimitive valor5 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor5.getAsString());
				
				String territory = null;
				
				Municipiospueblos p1 = new Municipiospueblos();
				
				for(int i = 0; i < leerPueblos.Municipios.size(); i++) {
					
					if(leerPueblos.Municipios.get(i).getNombre().equalsIgnoreCase(nombreMunicipio)) {
						
						System.out.println(nombreMunicipio);
						
						p1 =  leerPueblos.Municipios.get(i);
						
					}else {
						
						
						
					}
					
					
					
				}
				
				int codLocalidad = 0;
				
				Municipiospueblos m1 = null;
				
				Localidad l1 = null;
				
				for(int x = 0; x < leerPueblos.Municipios.size(); x++) {
					
					if(leerPueblos.Municipios.get(x).getCodMunicipio() == (codMunicipio)) {
						
						m1 = leerPueblos.Municipios.get(x);
						
						System.out.println(m1.getCodMunicipio());
						
					}
			
				}
				
				if(noLocalidad == false) {
				
				for(int i = 0; i < leerPueblos.localidad.size(); i++) {
					
					if(leerPueblos.localidad.get(i).getNombre().equalsIgnoreCase(localdadNombre)) {
					
						SessionFactory sesion = HibernateUtil.getSessionFactory();
						Session session = sesion.openSession();
						
						String hql = "from Localidad where Nombre = '" + localdadNombre + "'";
						Query q = session.createQuery(hql);
						q.setMaxResults(1).uniqueResult();
						Localidad loc = (Localidad) q.uniqueResult();
					
						codLocalidad = loc.getCodLocalidad();
						
						System.out.println(codLocalidad);
						
						session.close();
					
					}else {
						
						l1 = new Localidad(m1, localdadNombre);
						
					}
					
				}
				
				leerPueblos.localidad.add(l1);
				
				System.out.println(l1.getNombre() + l1.getCodLocalidad());
				
				inserts.insertLocalidad(l1);
				
				}
				
				if(noLocalidad == true) {
					
					codLocalidad = 0;
					
				}
				
				System.out.println(m1 + documentName + turism + codLocalidad);
				
				EspaciosNaturales e1 = new EspaciosNaturales(codEspacios, documentName, turism, codMunicipio, codLocalidad);
				
				inserts.insertEspaciosNaturales(e1);
				
				espaciosNaturales.add(e1);
				
				codEspacios++;
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
