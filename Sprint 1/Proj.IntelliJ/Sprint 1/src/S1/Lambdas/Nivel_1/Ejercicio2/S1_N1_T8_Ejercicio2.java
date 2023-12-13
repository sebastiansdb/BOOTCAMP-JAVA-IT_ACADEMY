package S1.Lambdas.Nivel_1.Ejercicio2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class S1_N1_T8_Ejercicio2 {
    public static void main(String[] args) {
        List<String> listaString  = new ArrayList<>(Arrays.asList("papa","uno", "dos", "tres", "cuatro", "cinco","seis") );
        List<String> listaStringFiltrada = PalabrasConOYMasDe5Letras(listaString);
        for(String s: listaStringFiltrada)
        {
            System.out.println(s);
        }
    }
    public static List<String> PalabrasConOYMasDe5Letras(List<String> listaString)
    {
        Stream<String> listaStream = listaString.stream().filter(l -> l.contains("o") && l.length() > 5 );
        List<String> listaFiltrada = listaStream.collect(Collectors.toList());
        return listaFiltrada;
    /**
     * Otra opcion sería anidar "filters":
     *  Stream<String> listaStream = listaString.stream().filter(l -> l.length() > 5 ).filter(l -> l.contains("o"));
     *
     * Creo que es menos eficiente porque haria dos recorridos de listas: uno para filtrar las palabras con mas de
     * 5 letras y luego recorreria dicha sublista para ver cuáles de ellas tienen la letra "o"
     */
    }
}
