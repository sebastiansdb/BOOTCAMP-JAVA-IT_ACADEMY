package S1.Collections.Nivel_3;

import java.io.*;
import java.util.ArrayList;

public class CsvFileHandler {
    // Atributs

    private String fileRoute;               // Path del archivo
    private String line;                    // Carga contenido de cada linea del archivo CSV
    private String[] lineElements;          // Carga elementos separados por comas
    private BufferedReader reader;          // Lee el archivo
   // private File reader1;                   // Prueba: leer archivo con la clase File.

    private BufferedWriter writer;

    // Constructor

    public CsvFileHandler(String fileRoute)
    {
        this.fileRoute = fileRoute;
        this.line = null;
        this.lineElements = null;
    }

    // Getters and setters

    ///////////////////////////////////////////////////////////
    //                  Metodos a im plementar               //
    ///////////////////////////////////////////////////////////
    // Agregar Persona                      (Escribir fichero)

    // ( Estos tal vez estará en el Main ):
    // Mostrar con Orden Nombre (A-Z)       (Leer Fichero)
    // Mostrar con Orden Nombre (Z-A)       (Leer Fichero)
    // Mostrar con Orden Apellido (A-Z)     (Leer Fichero)
    // Mostrar con Orden Apellido (Z-A)     (Leer Fichero)
    // Mostrar con Orden DNI (1-9)          (Leer Fichero)
    // Mostrar con Orden DNI (9-1)          (Leer Fichero)
    ///////////////////////////////////////////////////////////
    // Propuesta para la manipulacion de datos:
    //
    // Cada vez que se solicite mostrar datos, se lee el fichero, cargo los datos en un ArrayList y luego realizo el ordenamiento necesario
    // para mostrar los datos por pantalla.

    public ArrayList<Persona> readCsvFile()
    {
        ArrayList<Persona> datesRead = new ArrayList<>();
        try {
           reader = new BufferedReader(new FileReader(this.fileRoute));
           //reader1 = new File(this.fileRoute);
           while((line =reader.readLine()) != null) {
               // Si hay una linea en blanco en el archivo, la ignoro
               if( !line.equals("") ) {
               //if( line!= "" ) {        // Si lo expreso asi, cuando line = "", esto da TRUE!! ¿Por qué?
                   lineElements = line.split(",");
                   datesRead.add(new Persona(lineElements[0], lineElements[1], lineElements[2]));
               }
           }
           line = null;
           lineElements = null;
        } catch(Exception e) {
        System.out.println("Error al leer el archivo\n");
        e.printStackTrace();
       }
        finally
        {
            if(reader != null)
            {
                try
                {
                    reader.close();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
       return datesRead;
    }

    public void writeCsvFile(String textToWrite)
    {
        File file = new File(this.fileRoute);
        try {
            if(file.exists()) {
                FileWriter fileToWrite = new FileWriter(file,true);
                writer = new BufferedWriter(fileToWrite);
                writer.write("\n");
                writer.write(textToWrite);
                writer.close();
            } else{
                FileWriter fileToWrite = new FileWriter (file);
                writer = new BufferedWriter(fileToWrite);
                writer.write(textToWrite);
            }
        } catch (Exception e) {
            System.out.println("Error al escribir el archivo");
        }
        finally
        {
            if(writer != null)
            {
                try
                {
                    writer.close();
                }catch(IOException e)
                {
                    System.out.println("Error al cerrar el archivo" + e.getMessage());
                }
            }
        }
    }
}
