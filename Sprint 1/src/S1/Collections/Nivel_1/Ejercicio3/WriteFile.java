package S1.Collections.Nivel_1.Ejercicio3;

import java.io.File;
import java.io.FileWriter;

public class WriteFile {

    public static void writeFile (String username, byte userPuctuation, String ruta)
    {
        try {
            //Crear un objeto File se encarga de crear o acceder a un archivo, el cual se especifica en su constructor.
            File punctuations = new File(ruta);
            // Checkeo si el archivo existe o no, para no sobreescrirlo.
            if (punctuations.exists()) {
                //Creo objeto FileWriter que sera el que nos ayude a escribir sobre un archivo.
                FileWriter writer = new FileWriter(punctuations,true);
                writer.write("\n");
                writer.write(username + " - " + userPuctuation);
                writer.close();
            } else {
                FileWriter writer = new FileWriter(punctuations);
                writer.write(username + " - " + userPuctuation);
                writer.close();
            }
        }   //Si existe un problema al escribir cae aqui
        catch (Exception e){
            System.out.println("Error al escribir");
        }
    }
}
