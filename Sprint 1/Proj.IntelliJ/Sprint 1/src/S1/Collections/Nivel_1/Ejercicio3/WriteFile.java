package S1.Collections.Nivel_1.Ejercicio3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static void writeFile (String username, byte userPuctuation, String ruta)
    {
        //Crear un objeto File. Se encarga de crear o acceder a un archivo, el cual se especifica en su constructor.
        File punctuations;
        //Creo objeto FileWriter que sera el que nos ayude a escribir sobre un archivo.
        FileWriter writer = null;
        try {
            punctuations = new File(ruta);
            // Checkeo si el archivo existe o no, para no sobreescrirlo.
            if (punctuations.exists()) {
                writer = new FileWriter(punctuations,true);
                writer.write("\n");
                writer.write(username + " - " + userPuctuation);
            } else {
                writer = new FileWriter(punctuations);
                writer.write(username + " - " + userPuctuation);
            }
        }   //Si existe un problema al escribir cae aqui
        catch (IOException e){
            System.out.println("Error al escribir" + e.getMessage());
        }
        finally
        {
            if (writer != null) {
                try
                {
                    writer.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
