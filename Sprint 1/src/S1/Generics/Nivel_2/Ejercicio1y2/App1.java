package S1.Generics.Nivel_2.Ejercicio1y2;

import S1.Generics.Nivel_1.Ejercicio2.Persona;

import java.util.ArrayList;
import java.util.List;

public class App1 {


    public static void main(String[] args){

        Persona persona1 = new Persona ("jota","ja",21);
        String str = "Hey!";
        Integer in = 77;

        List<Integer> lst = new ArrayList<>();
        lst.add(10);
        lst.add(20);
        lst.add(30);


//        GenericsMethodsN2.printMethod(persona1,str,in);   ERROR
//        GenericsMethodsN2.printMethod(persona1,in,str);   ERROR
        GenericsMethodsN2.printMethod("AA",100,persona1);
        GenericsMethodsN2.<Integer>printMethodList(lst);
    }

}



