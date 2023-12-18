package S1.Lambdas.Nivel_2.Ejercicio2;

import java.util.Arrays;
import java.util.List;

public class S1_N2_T8_Ejercicio2 {
    public static void main(String[] args)
    {
        // El enunciado habla de una lista de Integers. Comencé haciendolo con un Integer[]Array y surgió algo
        // interesante, lo dejo así. Hacerlo con una lista de Integers seria muy similar.
        Integer[] numbers = new Integer[] {33,54,56,57,58,59,0,60,61,62};
        System.out.println(parImpar(numbers));
    }
    public static List<String> parImpar (Integer[] numbers)
    {
        // Creo un objeto Stream de "Integer[]", para poder usar los metodos de dicha clase. Para esto uso la clase
        // "Arrays", la cual tiene justamente un metodo llamado "stream", el cual recibe un "T[]array"  como argumento y
        // devuelve un stream de dicho array.
        List<String> formatedList = Arrays.stream(numbers)
                                    .map(n -> n == 0? n.toString() : n % 2 == 0 ? String.format("e%d", n):
                                            String.format("o%d",n) ).toList();
        return formatedList;
    }
}
