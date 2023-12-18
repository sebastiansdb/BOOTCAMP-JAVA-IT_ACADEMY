package S1.Generics.Nivel_2.Ejercicio1y2;

import S1.Generics.Nivel_1.Ejercicio2.Persona;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S1_T6_N2_Ejercicio1y2 {

    public static void main(String[] args){

        // Ejecicio 1
        Persona persona1 = new Persona ("jota","ja",21);
        String str = "Hey!";
        Integer in = 77;
        GenericsMethodsN2.printMethod("AA",100,persona1);
        GenericsMethodsN2.printMethod("AA",persona1,100);
        // Ejercicio 2
        List<Integer> lst1 = new ArrayList<>(Arrays.asList(10,20,30));
        List<String> lst2 = new ArrayList<>(Arrays.asList("a","b","c"));
        GenericsMethodsN2.printMethodList("Hola",10);
        GenericsMethodsN2.printMethodList(lst2,22,lst1);
    }
}



