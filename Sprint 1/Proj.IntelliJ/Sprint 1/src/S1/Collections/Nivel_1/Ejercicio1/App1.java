package S1.Collections.Nivel_1.Ejercicio1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class App1 {

    public static void main(String[] args) {
        Month m1 = new Month("Enero");
        Month m2 = new Month("Febrero");
        Month m3 = new Month("Marzo");
        Month m4 = new Month("Abril");
        Month m5 = new Month("Mayo");
        Month m6 = new Month("Junio");
        Month m7 = new Month("Julio");
        Month m8 = new Month("Agosto");
        Month m9 = new Month("Septiembre");
        Month m10 = new Month("Octubre");
        Month m11 = new Month("Noviembre");
        Month m12 = new Month("Diciembre");
        ArrayList<Month> months = new ArrayList<>();
        months.add(m1);
        months.add(m2);
        months.add(m3);
        months.add(m4);
        months.add(m5);
        months.add(m6);
        months.add(m7);
        months.add(m9);
        months.add(m10);
        months.add(m11);
        months.add(m12);
        System.out.println("ArrayList sin el mes de Agosto");
        System.out.println(months);
        System.out.println("\nAgrego el mes de Agosto");
        months.add(7,m8);
        System.out.println(months);


        months.add(m1);
        System.out.println("\nAgrego el mes de Enero una vez mas al ArrayList");
        System.out.println(months);
        /* Transformo ArrayList en HashSet */
        Set<Month> setMonths = new HashSet<>(months);
        System.out.println("\nTransformo ArrayList en HashSet. No se admiten duplicados");
        System.out.println(setMonths);
        System.out.println(
                "\nIterate HashSet using For-Each : \n");

        // Recorro el HashSep
        for(Month month : setMonths){
            System.out.println(month);
        }

        System.out.println(
                "\nIterate HashSet using iterator : \n");

        Iterator<Month> it = setMonths.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
