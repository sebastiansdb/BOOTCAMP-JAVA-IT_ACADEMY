package S1.Lambdas.Nivel_2.Ejercicio4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class S1_N2_T8_Ejercicio4 {
    public static void main(String[] args)
    {
        List<?> listaValores = new ArrayList<>(Arrays.asList(1,2,3,4,"Jose","Ana","Andrea","eriberto","Erneston"));
        List<String> listaValores4 = new ArrayList<>(Arrays.asList("1","2","3","4","Jose","Ana","Andrea","eriberto","Erneston"));
        ordenAlfabetico(listaValores);
        comienzanConE(listaValores);
        modificaLaA(listaValores);
        muestraSoloNumeros(listaValores4);
    }
    public static void ordenAlfabetico(List<?> listaValores)
    {
        listaValores.stream().filter(l -> l instanceof String).map(l -> l.toString())
                .sorted( Comparator.comparing( str-> str.charAt(0)) ).forEach(System.out::println);
        System.out.println();
    }

    public static void comienzanConE(List<?> listaValores)
    {
       listaValores.stream().filter(o -> o instanceof String).map(str -> str.toString())
                .filter(str -> str.startsWith("e")|| str.startsWith("E")).toList().forEach(System.out::println);
        System.out.println();
    }

    public static void modificaLaA(List<?> listaValores)
    {
        listaValores.stream().filter(l -> l instanceof String).map(Object::toString)
                .map(str -> str.replace('a','4')).forEach(System.out::println);
        System.out.println();
    }
    public static void muestraSoloNumeros(List<String> listaValores)
    {
        listaValores.stream().filter(l->l.matches("\\d")).forEach(System.out::print);
        System.out.println();
    }
}
