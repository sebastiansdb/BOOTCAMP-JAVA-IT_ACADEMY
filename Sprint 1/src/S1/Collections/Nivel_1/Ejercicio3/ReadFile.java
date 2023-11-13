package S1.Collections.Nivel_1.Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class ReadFile {

    public static HashMap<String,String> txtFileToHashMap(String archivo)
    {
        String cadena;
        HashMap<String,String> countriesMap = new HashMap<>();
        try {
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                String[] words = cadena.split(" ");
                countriesMap.put(words[0], words[1]);
            }
            b.close();
        }catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
        return countriesMap;
    }


}
