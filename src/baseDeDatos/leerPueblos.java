package baseDeDatos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import modelo.Localidad;
import modelo.Municipiospueblos;
import modelo.Provincia;

public class leerPueblos {

	public static ArrayList<Provincia> Provincias = new ArrayList<Provincia>();

	public static ArrayList<Municipiospueblos> Municipios = new ArrayList<Municipiospueblos>();

	public static ArrayList<Localidad> localidad = new ArrayList<Localidad>();

	int codLocalidad = 1;

	public static void LeerPueblos() {

		Provincias.clear();

		Municipios.clear();

		localidad.clear();

		System.setProperty("file.encoding", "UTF-8");
		// Servidor s = new Servidor();
		// s.iniciar();

		JsonParser parser = new JsonParser();
		final String url = "src/JSONs/pueblos1.json";

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

				documentName = documentName.substring(1, documentName.length() - 1);

				JsonPrimitive valor2 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor2.getAsString());

				String turism = valor2.toString();

				turism = turism.substring(4, turism.length() - 4);

				if (turism.length() > 3000) {

					turism = "TO LONG";

				}

				iter2.next();

				JsonPrimitive valor4 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor4.getAsString());

				String localdadNombre = valor4.toString();

				localdadNombre = localdadNombre.substring(1, localdadNombre.length() - 1);

				for (int i = 0; i < 45; i++) {

					iter2.next();

				}

				JsonPrimitive valor3 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor3.getAsString());

				int codMunicipio = 0;

				if (valor3.toString().contains(" ")) {

					String codMunicipioS = valor3.toString();

					System.out.println(codMunicipioS.split(" ")[0]);

					codMunicipioS = codMunicipioS.split(" ")[0];

					char comilla = codMunicipioS.charAt(0);

					char blanco = 0;

					codMunicipioS = codMunicipioS.replace(comilla, blanco);

					codMunicipioS = codMunicipioS.substring(1, codMunicipioS.length());

					System.out.println(codMunicipioS);

					codMunicipio = Integer.parseInt(codMunicipioS);

				} else {

					codMunicipio = valor3.getAsInt();

				}

				iter2.next();

				JsonPrimitive valor5 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor5.getAsString());

				String territory = null;

				if (valor5.toString().contains(" ")) {

					territory = valor5.toString().split(" ")[0];

					territory = territory.substring(1, territory.length());

				} else {

					territory = valor5.toString();

					territory = territory.substring(1, territory.length() - 1);

				}

				JsonPrimitive valor6 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println(valor6.getAsString());

				int codProvincia = 0;

				if (valor6.toString().contains(" ")) {

					String codProvinciaS = valor6.toString();

					System.out.println(codProvinciaS.split(" ")[0]);

					codProvinciaS = codProvinciaS.split(" ")[0];

					char comilla = codProvinciaS.charAt(0);

					char blanco = 0;

					codProvinciaS = codProvinciaS.replace(comilla, blanco);

					codProvinciaS = codProvinciaS.substring(1, codProvinciaS.length());

					System.out.println(codProvinciaS);

					codProvincia = Integer.parseInt(codProvinciaS);

				} else {

					codProvincia = valor6.getAsInt();

				}

				boolean alredyExistP = false;

				for (int i = 0; i < Provincias.size(); i++) {

					if (Provincias.get(i).getCodProvincia() == codProvincia) {

						alredyExistP = true;

					}

				}

				Provincia p1 = new Provincia();
				p1.setCodProvincia(codProvincia);
				p1.setNombre(territory);

				if (!alredyExistP) {

					Provincias.add(p1);

					inserts.insertProvincia(p1);

				} else {

					alredyExistP = false;

				}

				boolean codAlreadyExist = false;

				Municipiospueblos m1 = new Municipiospueblos();
				m1.setCodMunicipio(codMunicipio);
				m1.setProvincia(p1);
				m1.setNombre(documentName);
				m1.setDescripcion(turism);

				for (int i = 0; i < Municipios.size(); i++) {

					if (m1.getCodMunicipio() == (Municipios.get(i).getCodMunicipio())) {

						codAlreadyExist = true;

					}

				}

				if (codAlreadyExist == false) {

					inserts.insertMunicipio(m1);

					Municipios.add(m1);

				}

				boolean localExist = false;

				Localidad l1 = new Localidad();
				l1.setMunicipiospueblos(m1);
				l1.setNombre(localdadNombre);
				for (int i = 0; i < localidad.size(); i++) {

					if (l1.getNombre().equals(localidad.get(i).getNombre())) {

						localExist = true;

					}

				}

				if (localExist == false) {

					localidad.add(l1);

					inserts.insertLocalidad(l1);

				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LeerEspaciosNaturales.Espacios();

	}

}
