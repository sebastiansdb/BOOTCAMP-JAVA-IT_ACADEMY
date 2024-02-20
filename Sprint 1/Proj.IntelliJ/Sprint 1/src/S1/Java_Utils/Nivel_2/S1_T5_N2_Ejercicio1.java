package S1.Java_Utils.Nivel_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class S1_T5_N2_Ejercicio1 {
    public static void main(String[] args){

    }

    public static void alfaphabeticListerFileWriter(String fileRoute, FileWriter writer)
    {
        File results = new File(fileRoute);
        String linetoWrite;
        // Check que exista el directorio/path a leer y que no sea un archivo
        if (results.exists() && !results.isFile())
        {
            File[] files = results.listFiles();
            Arrays.sort(files);
            for (int i = 0; i < files.length; i++)
            {
                try
                {
                    if (files[i].isFile())
                    {
                        linetoWrite = "- " + files[i].getName() + " (File)" + " - Ultima fecha de modificación: " +
                                lastFileModification(files[i]) + "\n";
                        writer.write(linetoWrite);
                    } else if (files[i].isDirectory())
                    {
                        linetoWrite = "\nDirectorio:\n- " + files[i].getName() + " - Ultima fecha de modificación: " +
                                lastFileModification(files[i]) + "\nContiene: \n";
                        writer.write(linetoWrite);
                        alfaphabeticListerFileWriter(files[i].getAbsolutePath(), writer);
                    }
                } catch (IOException e)
                {
                    System.out.println("'Error al escribir el archivo'");
                }
            }
        } else {
            System.out.println("La ruta '" + fileRoute + "' no existe.");
        }
    }
    public static String lastFileModification(File file)
    {
        long milisec = file.lastModified();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");//hh:mm:ss
        return simpleDateFormat.format(milisec);
    }
}
