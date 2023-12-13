package S1.Collections.Nivel_1.Ejercicio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class ReadFile {

    public static HashMap<String,String> txtFileToHashMap(String archivo)
    {
        String cadena;
        FileReader f = null;
        BufferedReader b = null;
        HashMap<String,String> countriesMap = new HashMap<>();
        try {
            f = new FileReader(archivo);
            b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                String[] words = cadena.split(" ");
                countriesMap.put(words[0], words[1]);
            }
        //Si existe un problema al leer cae aqui
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
        finally
        {
            try
            {
                b.close();
                f.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return countriesMap;
    }

}
