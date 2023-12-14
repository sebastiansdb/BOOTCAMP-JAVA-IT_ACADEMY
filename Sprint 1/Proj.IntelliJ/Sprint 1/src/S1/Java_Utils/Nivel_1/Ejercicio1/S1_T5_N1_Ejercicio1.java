package S1.Java_Utils.Nivel_1.Ejercicio1;

import java.io.File;
import java.util.Arrays;
public class S1_T5_N1_Ejercicio1 {
    public static void main (String [] args) {
        String route = "/Users/sebastian/Coding/Bootcamp IT Academy/Sprint 1/Proj.IntelliJ/Sprint 1/src/S1/Java_Utils" +
                "/Nivel_1/Directorio Ejemplo";
        System.out.println("Contenido del directorio: " + "\n");
        alfaphabeticListerPrinter(route);
    }
    public static void alfaphabeticListerPrinter(String fileRoute)
    {
//        try {
            File actualDirectory = new File(fileRoute);
            // Check que exista el directorio/path a leer y que no sea un archivo
            if (actualDirectory.exists() && !actualDirectory.isFile()) {
                System.out.println("Contenido del directorio:\n'" + actualDirectory.getAbsolutePath() + "'" + "\n");
                //  Cargo cada elemento del directorio con ruta "route" en un Array de objetos File
                File[] files = actualDirectory.listFiles();
                if (files == null || files.length == 0) {
                    System.out.println("La carpeta esta vacia");
                } else {
                    // Ordeno alfabeticamente
                    Arrays.sort(files);
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isFile()) {
                            System.out.println(files[i].getName() + " (File)");
                        } else if (files[i].isDirectory()) {
                            System.out.println(files[i].getName() + " (Directory)");
                        }
                    }
                }
            } else {
                System.out.println("La ruta '" + fileRoute + "' no existe o no es un directorio valido");
            }
        /**
         *                                   Comentario de EXCEPCIONES

         * Los metodos exists() y isFile() de la clase File pueden arrojar una excepcion del tipo SecurityException.
         * Ésta se arrojaria en el caso que el directorio o archivo a acceder tenga el ACCESO RESTRINGIDO, ya sea porque
         * tiene un codigo de acceso, se está operando como usuario y no como administrador, etc.
         * A su vez, SecurityException es de tipo RunTimeException, por lo que debemos evitar que sea lanzada. Esto no
         * quiere decir que no se pueda capturar RunTimeException, pero procuramos que, debido a la logica implementada,
         * no sean lanzadas.
         */
//       } catch (SecurityException e)
//        {
//            System.out.println("Error al leer el directorio: " + e.getMessage());
//        }
    }
}

