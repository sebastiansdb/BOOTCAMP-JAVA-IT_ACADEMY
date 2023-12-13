package S1.Java_Utils.Nivel_1.Ejercicio5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Esta clase DESERIALIZA un objeto. Se llama Input porque entra datos a nuestro programa/sistema desde un archivo donde
 * los datos están serializados.
 */
public class FileDataInput {
    /**
     * Reads an object from a file.
     *
     * @param fileToOpen the path of the file to open
     * @return the object read from the file
     */
    public Object read(String fileToOpen) {
        Object ret = null;
        /**
         *   ObjectInputStream Class is used to recover those objects previously serialized.
         */
        try (FileInputStream file = new FileInputStream(fileToOpen);
             ObjectInputStream input = new ObjectInputStream(file))
        {
            ret = input.readObject();
        // Debo capturar también la "ClassNotFoundException" porque el metodo readObject() puede lanzar esta excepcion.
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al abrir, leer o escribir el archivo" + e.getMessage());
        }
        return ret;
    }
}

