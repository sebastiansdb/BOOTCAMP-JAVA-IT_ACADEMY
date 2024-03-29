package S1.Java_Utils.Nivel_1.Ejercicio2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class S1_T5_N1_Ejercicio2 {

    public static void main (String [] args) {
        String route = "Directorio Ejemplo Java Utils";
        alfaphabeticTreeListerAndPrinter(route);
    }
    public static void alfaphabeticTreeListerAndPrinter(String fileRoute)
    {
           File actualDirectory = new File(fileRoute);
           // Check que exista el directorio/path a leer y que no sea un archivo
           if (actualDirectory.exists() && !actualDirectory.isFile()) {
               //  Cargo cada elemento del directorio con ruta "route" en un Array de objetos File
               File[] files = actualDirectory.listFiles();
               // Ordeno alfabeticamente
               Arrays.sort(files);
               System.out.println("Listado de carpetas y archivos de la ruta:\n'" + fileRoute + "':\n");
               for (int i = 0; i < files.length; i++) {
                   if (files[i].isFile()) {
                       System.out.println("- " + files[i].getName() + " (File)" + " - Ultima fecha de modificación: " +
                               lastFileModification(files[i]));
                   } else if (files[i].isDirectory()) {
                       System.out.println("\nDirectorio:\n- " + files[i].getName() + " - Ultima fecha de modificación: " +
                               lastFileModification(files[i]) + "\nContiene: ");
                       alfaphabeticTreeListerAndPrinter(files[i].getAbsolutePath());
                   }
               }
           } else {
               System.out.println("La ruta '" + fileRoute + "' no existe o no es un directorio valido");
           }
    }

    public static String lastFileModification(File file)
    {
        long milisec = file.lastModified();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");//hh:mm:ss
        return simpleDateFormat.format(milisec);
    }

}
