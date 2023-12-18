package S1.Lambdas.Nivel_2.Ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S1_N2_T8_Ejercicio1 {
    public static void main(String[] args)
    {
            List<String> listaString = new ArrayList<>(Arrays.asList("papa", "Ana", "ana", "jose", "Amilcar",
                    "amilcar", "Aik","Anaa"));
        System.out.println(filteredList(listaString));
    }

    public static List<String> filteredList (List<String> listaString)
    {
        List<String> filtered;
        filtered = listaString.stream().filter(l -> ( l.startsWith("a") || l.startsWith("A") ) && l.length() == 3 )
                                .toList();
        return filtered;
    }

}
