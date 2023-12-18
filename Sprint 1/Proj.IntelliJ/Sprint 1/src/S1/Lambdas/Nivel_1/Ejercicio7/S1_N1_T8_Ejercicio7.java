package S1.Lambdas.Nivel_1.Ejercicio7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class S1_N1_T8_Ejercicio7 {
    public static void main(String[] args){
        List<Object> cadenas = new ArrayList<>(Arrays.asList("marcelo","juan",1,4,"claudia","valentin"));
        List<String> listaNombres = cadenas.stream()
                .filter(o -> o instanceof String)
                // Aunque los objetos obtenidos serán de tipo String, estarán almacenados como clase object. Por lo
                // tanto, es necesario un cast.
                .map(o -> (String) o)
                .sorted( Comparator.comparing (String::length).reversed() )
                /**
                 * Esto se lee asì:
                 * Se comparan objetos de clase String. Atributo de comparación: length().
                 * En este caso, el metodo reversed() indica que la comparacion se haga en sentido inverso.
                 */
                // ¿ Por que así no anda :
                // .sorted( Comparator.comparing(s -> s.length()).reversed() )  ?
                // Porque toma al parametro "s" de tipo "Object" y no "String", por lo tanto, no le puede aplicar el
                // método "length()"
                // La forma de hacerlo es:
                // .sorted( Comparator.comparing(s -> s.toString().length()).reversed() )

                .toList();
        listaNombres.forEach(System.out::println);
    }
}
