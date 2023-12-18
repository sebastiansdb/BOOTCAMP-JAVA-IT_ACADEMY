package S1.Collections.Nivel_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App3 {

    public static void main(String[] args) {
        String fileRoute = "Lista.txt";
        CsvFileHandler csvHandler = new CsvFileHandler(fileRoute);
        String userDates;

        //userDates =readString("Ingresa el nombre de la persona") +","+ readString("Ingresa el apellido de la persona") +","+
        //        readString("Ingresa el DNI de la persona");


        //csvHandler.writeCsvFile(userDates);

        ArrayList<Persona> alp = csvHandler.readCsvFile();
        Collections.sort(alp,new PersonNameComparator());
        System.out.println(alp);
        Collections.sort(alp,new PersonSurnameComparator());
        System.out.println(alp);
        Collections.sort(alp,new PersonIdComparator());
        System.out.println(alp);

        /** Falta:
         * Armar el MENU.
         * Ver cómo implementar la comparacion descendente y ascendente: Creo una clase para ASC y otra para Desc? O se puede
         * hacer sólo con una y le indico con un parámetro el orden que quiero?.
         * Manejo de EXCEPCIONES.
         **/
    }

    public static String readString(String message){
        Scanner sc = new Scanner(System.in);                // Qué ventaja o funcionalidad tiene definir el Scanner dentro de la funcion
                                                            // y no hacerlo com variable global de esta clase? (Si lo vou a usar en distintos sitios a sc)
        System.out.println(message);
        return sc.nextLine();
    }


}
