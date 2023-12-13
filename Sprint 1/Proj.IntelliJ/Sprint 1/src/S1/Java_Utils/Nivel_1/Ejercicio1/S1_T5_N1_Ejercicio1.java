package S1.Java_Utils.Nivel_1.Ejercicio1;
import java.io.File;
import java.util.Arrays;

// CHECKEADO. ENTREGABLE.
public class S1_T5_N1_Ejercicio1 {
    public static void main (String [] args) {
        String route = "/Users/sebastian/Coding/Bootcamp IT Academy/Sprint 1/src/S1/Java_Utils/Nivel_1/Ejercicio3/Directorio Ejemplo";
        if (args.length > 0) {
            route = args[0];
        }

        // Es null o que vlor tiene args?
        if (args == null) {
            System.out.println("Contenido del directorio: " + "\n");
        }
        alfaphabeticListerPrinter(route);
    }
    public static void alfaphabeticListerPrinter(String fileRoute)
    {
        try {
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
        } catch (Exception e)
        {
            System.out.println("Error al leer el directorio: " + e.getMessage());
        }
    }
}

