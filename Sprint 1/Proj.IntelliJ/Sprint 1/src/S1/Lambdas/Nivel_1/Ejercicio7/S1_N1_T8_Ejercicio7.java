package S1.Lambdas.Nivel_1.Ejercicio7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class S1_N1_T8_Ejercicio7 {
    public static void main(String[] args){
        List<Object> cadenas = new ArrayList<>(Arrays.asList("marcelo","juan",1,4,"claudia","valentin"));
        List<String> listaNombres = cadenas.stream()
                .filter(o -> o instanceof String)
                .map(o -> (String) o)
                // ¿ Por que así no anda???
//                .sorted( Comparator.comparing(s -> s.length()).reversed )
//                .sorted( Comparator.comparing((s1, s2) -> s2.length() - s1.length()) )
                /**
                 * Esto se lee asì:
                 * Se comparan objetos de clase String. Atributo de comparación: length().
                 * En este caso, el metodo reversed() indica que la comparacion se haga en sentido inverso.
                 */
                .sorted( Comparator.comparing(String::length).reversed() )
                .collect(Collectors.toList());
        listaNombres.forEach(System.out::println);
    }
}
