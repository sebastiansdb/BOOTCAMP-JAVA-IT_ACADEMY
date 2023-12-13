package S1.Java_Utils.Nivel_1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class App1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //String route = "/Users/sebastian/Documents/z_aramba/Easo/Reparaciones/Diciembre 2022"; // Con este LINK, NO FUNCIONA. ¿Por què???
        String route = "/Users/sebastian/Coding/Data Base/pagila";
        String resultsRoute = "/Users/sebastian/Coding/Java/IT_Academy/Java/Java con Spring Framework/Sprint1/Sprint 1" +
                "/src/S1/Java_Utils/Nivel_1/Ejercicio3/ListadoArbolFicheros";

        String serializeRoute = "/Users/sebastian/Coding/Java/IT_Academy/Java/Java con Spring Framework/Sprint 1" +
                "/src/S1/Java_Utils/Nivel_1/serial";
        //File results = new File(resultsRoute);

        // Ejercicio 1
        System.out.println("Orden alfabético del contenido de la carpeta: '" + route + "':\n");
        alfaphabeticListerPrinter(route);
        System.out.println();
        // Ejercicio 2
        System.out.println("Listado de carpetas y archivos de la ruta: '" + route + "':\n");
        alfaphabeticTreeListerAndPrinter(route);
        System.out.println();
        // Ejercicio 3
        alfaphabeticListerFileWriter(route,resultsRoute);
        // Ejercicio 4
        // Leer y mostrar por pantalla archivo de texto
        System.out.println("Leer y mostrar por pantalla archivo de texto:");
        txtToArrayListAndPrint(resultsRoute);
        // Ejercicio 5                              ¡¡ DUDAS !!
//        // Serializar
//        FileData a = new FileData("Juan", 20);
//        serialize(a,serializeRoute);
//        // Deserializar
//        FileData b = (FileData) deserialize(serializeRoute,"a");
//        System.out.println(b.getName() + " " + b.getType());

        // Nivel 2 : Creo que ya está hecho en el ejercicio 3.      ¿¿??¿¿??

    }
    // Metodo para ordenar alfabéticamente e imprimir el contenido de una carpeta.
    public static void alfaphabeticListerPrinter(String route)
    {
        File actualDirectory = new File(route);
        if(actualDirectory.exists()) {
            File[] files = actualDirectory.listFiles();
            Arrays.sort(files);
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    System.out.println(files[i].getName() + " (F)");
                } else if (files[i].isDirectory()) {
                    System.out.println(files[i].getName() + " (D)");
                }
            }
        }
    }
    public static void alfaphabeticTreeListerAndPrinter(String route)
    {
        File actualDirectory = new File(route);
        if(actualDirectory.exists()) {
            //  Cargo cada elemento del directorio con ruta "route" en un Array de objetos File
            File[] files = actualDirectory.listFiles();
            Arrays.sort(files);
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    System.out.println(files[i].getName() + " (F)" + " - Ultima fecha de modificación: " +
                            lastFileModification(files[i]));
                } else if (files[i].isDirectory()) {
                    System.out.println(files[i].getName() + " (D)" + " - Ultima fecha de modificación: " +
                            lastFileModification(files[i]) + "\nContiene: ");
                    alfaphabeticTreeListerAndPrinter(files[i].getAbsolutePath());
                }
            }
        }else{
            System.out.println("La ruta '" + route + "' no existe.");
        }
    }
    public static String lastFileModification(File file)
    {
        String myDate;
        long millisec = file.lastModified();
        Date date = new Date(millisec);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");//hh:mm:ss
        myDate = simpleDateFormat.format(date);
        return myDate;
    }
    public static void alfaphabeticListerFileWriter(String route, String routeToWrite)
    {
        File results;
        File fileOuput;
        FileWriter writer;
        String linetoWrite;
        try {
            results = new File(route);
            fileOuput = new File(routeToWrite);
            if (results.exists() && fileOuput.exists()) {
                writer = new FileWriter(fileOuput, true);
                File[] files = results.listFiles();
                Arrays.sort(files);
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        linetoWrite = files[i].getName() + " (F)" + " - Ultima fecha de modificación: " + lastFileModification(files[i]);
                        writer.write(linetoWrite);
                        writer.write("\n");
                    } else if (files[i].isDirectory()) {
                        linetoWrite = files[i].getName() + " (D)" + " - Ultima fecha de modificación: " + lastFileModification(files[i]) + "\nContiene: ";
                        writer.write("\n");
                        writer.write(linetoWrite);
                        writer.write("\n");
                        // Aqui cierro "writer" porque se vuelve a invocar la misma funcion e intentaremos abrir a "writer" nuevamente.
                        writer.close();
                        alfaphabeticListerFileWriter(files[i].getAbsolutePath(), routeToWrite);
                    }
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("'Error al escribir el archivo'");
        }
    }

    public static ArrayList<String> txtToArrayListAndPrint(String archivo)
    {
        String cadena;
        ArrayList<String> ret = new ArrayList<>();
        try {
            FileReader f = new FileReader(archivo);     // ¿ Diferencia entre usar FileReader y Scanner ??
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                ret.add(cadena);
                System.out.println(cadena);
            }
            b.close();
        }catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
        return ret;
    }

    // Serializar Objeto
    public static void serialize(Object obj, String route) throws IOException, ClassNotFoundException
    {
        FileOutputStream fos = new FileOutputStream(route);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();
    }

    // Deserializar Objeto
    public static Object deserialize(String route, String classToDeserialize) throws IOException, ClassNotFoundException

    // Escribo que puede lanzar excepciones pero no se hace nada para gestionarlas.         ¿Cómo es esto??

    {
        // Para hacerlo generico, coloco como parámetro el nombre de la clase a DESERIALIZAR, pero no sé como manejarlo
        // ¿¿¿¿?????
        FileInputStream fis = new FileInputStream(route);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        ois.close();
        return o;
    }
    /*
       Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
       for(Path name : dirs){
           System.out.println(name);
       }
       */
    // ¿ Cómo hago si quiero definir el "DirestoryStream" dentro del TRY ?: try{DirectoryStream<Path> stream = Files.newDirectoryStream(p1); .....}finally{
    //           stream.close();}

        /*

       try (DirectoryStream<Path> stream = Files.newDirectoryStream(p1)){    // ¿¿"stream" seria un objeto de tipo Interfaz "DirestoryStream" ????
           ArrayList<Path> elementsNames = new ArrayList<>();
           int i = 0;
           for(Path directory : stream){
               elementsNames.add(i,directory.getFileName());
               System.out.println(elementsNames.get(i));
               i++;
           }
           System.out.println(elementsNames);

       } catch (IOException | DirectoryIteratorException e) {
            System.err.println(e);
       }
         */


}
