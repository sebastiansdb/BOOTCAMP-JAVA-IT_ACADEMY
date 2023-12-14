package S1.Java_Utils.Nivel_1.Ejercicio4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class S1_T5_N1_Ejercicio4 {
    /**
     * Leer y mostrar por pantalla archivo de texto
     */
    public static void main(String[] args) {
        String fileToRead = "/Users/sebastian/Coding/Bootcamp IT Academy/Sprint 1/Proj.IntelliJ/Sprint 1/src/S1" +
                "/Java_Utils/Nivel_1/Ejercicio3/ListadoArbolFicheros.txt";
        System.out.println("Leer y mostrar por pantalla archivo de texto 'ListadoArbolFicheros.txt':\n");
        txtToArrayListAndPrint(fileToRead);
    }

    public static ArrayList<String> txtToArrayListAndPrint(String fileRoute) {
        String cadena;
        ArrayList<String> ret = new ArrayList<>();
        /**
        * Defino el BufferedReader fuera del Try para que no sea una variable local del propio Try y de esta manera pueda
        * usarse dicha variable en el bloque FINALLY.
        */
        BufferedReader b = null;
        try {
            FileReader f = new FileReader(fileRoute);
            b = new BufferedReader(f);
            // Instancio un objeto File para poder checkear que se esté proporcionando un archivo y que sea ".TXT"
            File fileToRead = new File(fileRoute);
            if (fileToRead.isFile() && fileToRead.getName().toLowerCase().endsWith("txt")) {
                while ((cadena = b.readLine()) != null) {
                    ret.add(cadena);
                    System.out.println(cadena);
                }
            } else
            {
                System.out.println("La ruta proporcionada no corresponde a un archivo '.TXT'");
            }
        }catch (IOException e) {
            System.out.println("Error al leer el archivo" + e.getMessage());
        }
        finally{
            /**
             * Bloque exclusivo para cerrar el Buffer de lectura de archivo.
             *
             * Siempre se debe CERRAR EL LECTOR o ESCRITOR de archivos en el bloque FINALLY, porque si surge una excepcion
             * después de haber abierto el lector/escritor, puede darse el caso que no se cierre el buffer correspondiente.
             */
            try {
                /**
                // Si intento cerrar un archivo que es "NULL", se lanzará una "UNCHECKED EXCEPTION", que son las que el
                // programador tiene que procurar que no sucedan, ya que no son CAPTURABLES. Son excepciones que nunca
                // entrarian en un bloque CATCH.
                // Ejemplos de estas excepciones son:
                // Dividir por CERO
                // Acceder a una posicion de un Array mayor que su propio tamaño.
                */
                if (b!=null) {
                    b.close();
                }
            } catch(IOException e){
                System.out.println("Error al cerrar el archivo" + e.getMessage());
            }
        }
        return ret;
    }
}
