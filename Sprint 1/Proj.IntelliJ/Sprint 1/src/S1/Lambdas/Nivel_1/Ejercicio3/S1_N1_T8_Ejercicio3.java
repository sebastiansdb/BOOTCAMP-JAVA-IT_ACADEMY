package S1.Lambdas.Nivel_1.Ejercicio3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S1_N1_T8_Ejercicio3 {
    public static void main(String[] args){
        List<String> meses = new ArrayList(Arrays.asList("enero", "febrero", "marzo", "abril", "mayo", "junio",
                "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"));
        imprimirMesesLambda(meses);
    }
    public static void imprimirMesesLambda(List<String> meses)
    {
        /**
         * En este caso, forEach es un metodo de la interface Iterable, la cual es extendida por la interface List.
         * Por lo tanto el objeto "meses" puede llamar al dicho método.
         * El argumento del método forEach es de tipo CONSUMER, la cual es una interface funcional, por lo que se usa
         * como destino de una expresión lambda o referencia de método.
         */
        meses.forEach(l ->System.out.println(l));
    }
}
