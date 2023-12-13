package S1.Collections.Nivel_1.Ejercicio2;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class App2 {

    public static void main(String[] args){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }
        /**
         *
         * Cargo elementos de la lista 1 en la lista 2, en orden invertido.
         */
        for (int j= 0,i = list1.size()-1 ; i >= 0 ; i--) {
            list2.add(j,list1.get(i));
            j++;
        }
        System.out.println(list1);
        System.out.println(list2);
        /**
         *  Empra els objectes ListIterator per a llegir els elements de la primera llista i inserir-los en la segona.
         */
       ListIterator<Integer> iter = list1.listIterator();
       while (iter.hasNext()){
           list2.add(iter.next());
       }
       System.out.println(list2);
    }
}
