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
                // Para hacer cast y obtener objeto tipo Stream<String>
                                    .map(o -> (String) o)
                // Esta sentencia ordena la lista alfabeticamente.
                                    .sorted(Comparator.comparing(s ->s.length()))
                                    .collect(Collectors.toList());
        listaNombres.forEach(System.out::println);
    }
}
