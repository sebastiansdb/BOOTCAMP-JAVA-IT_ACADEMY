package S1.Testing.Nivel_1.Ejercicio1;

import java.util.ArrayList;

public class App1 {

    public static void main (String [] args) {

        /*
        ArrayList<String> meses = new ArrayList<>();

        meses.add(mes1);
        meses.add(mes2);
        meses.add(mes3);
        meses.add(mes4);
        meses.add(mes5);
        meses.add(mes6);
        meses.add(mes7);
        meses.add(mes8);
        meses.add(mes9);
        meses.add(mes10);
        meses.add(mes11);
        meses.add(mes12);
         */
        String mes1 = "enero";String mes2 = "febrero";String mes3 = "marzo";String mes4 = "abril";String mes5 = "mayo";
        String mes6 = "junio";String mes7 = "julio";String mes8 = "agosto";String mes9 = "septiembre";
        String mes10 = "octubre";String mes11 = "noviembre";String mes12 = "diciembre";
        OrderedMonths months = new OrderedMonths();
        months.insertMonth(mes10,10);
        System.out.println(months.getMonth(10));
    }
}
