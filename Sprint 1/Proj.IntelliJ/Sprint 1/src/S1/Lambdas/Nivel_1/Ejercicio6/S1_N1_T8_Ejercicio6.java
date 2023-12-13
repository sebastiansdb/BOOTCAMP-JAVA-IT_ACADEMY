package S1.Lambdas.Nivel_1.Ejercicio6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class S1_N1_T8_Ejercicio6
{
    public static void main(String[] args){
        List<Object> cadenas = new ArrayList<>(Arrays.asList("marcelo","juan",1,4,"claudia","valentin"));
        List<String> listaNombres = cadenas.stream()
                                    .filter(o -> o instanceof String)
//                                    .filter(String.class::isInstance)
        /**
        Remember that a filter operation will not change the compile-time type of the Stream's elements. Yes, logically
        we see that everything that makes it past this point will be a STRING, all that the filter sees is a Predicate.
        If that predicate changes later, then that could lead to other compile-time issues.
        If you want to change it to a Stream<String>, you'd need to add a mapper that does a cast for you:
        */
                                    .map(o -> (String) o)
                // Otra opcion a la linea anterior:
                //                  .map(String.class::cast)
                /**String.class::cast is nice, but unfortunately less efficient than c -> (SuitCard) c because Class.cast
                 * performs a redundant isInstance check.
                 */
                // Esta sentencia ordena la lista alfabeticamente.
//                                    .sorted(String::compareTo)
                                    .sorted(Comparator.comparing(s ->s.length()))
                                    .collect(Collectors.toList());
        listaNombres.forEach(System.out::println);
    }
}
