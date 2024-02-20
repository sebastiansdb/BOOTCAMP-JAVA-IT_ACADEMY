package S1.Java_Utils.Nivel_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class TxtWriter {

    // ES CONVENIENTE PASAR EL FILEWRITER WRITER DE METODO EN METODO?
    public static void writeFileContentAtTxt(String outputFileName, String directoryToRead) {
        try (FileWriter writer = new FileWriter(outputFileName)){
            writer.write("Contenido del directorio: " + directoryToRead + "\n");
            alfaphabeticListerFileWriter(directoryToRead, writer);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void alfaphabeticListerFileWriter(String directoryToRead, FileWriter writer) throws IOException
    {
        File results = new File(directoryToRead);
        // Check que exista el directorio/path a leer y que no sea un archivo
        if (results.exists() && !results.isFile())
        {
            File[] files = results.listFiles();
            Arrays.sort(files);
            writeLines(files,writer);
        } else {
            System.out.println("La ruta '" + directoryToRead + "' no existe.");
        }
    }

    public static void writeLines(File[] files, FileWriter writer) throws IOException
    {
        String linetoWrite;
        for (File file : files) {
            if (file.isFile()) {
                linetoWrite = "- " + file.getName() + " (File)" + " - Ultima fecha de modificación: " +
                        lastFileModification(file) + "\n";
                writer.write(linetoWrite);
            } else if (file.isDirectory()) {
                linetoWrite = "\nDirectorio:\n- " + file.getName() + " - Ultima fecha de modificación: " +
                        lastFileModification(file) + "\nContiene: \n";
                writer.write(linetoWrite);
                alfaphabeticListerFileWriter(file.getAbsolutePath(), writer);
            }
        }
    }
    public static String lastFileModification(File file)
    {
        long milisec = file.lastModified();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");//hh:mm:ss
        return simpleDateFormat.format(milisec);
    }
}
