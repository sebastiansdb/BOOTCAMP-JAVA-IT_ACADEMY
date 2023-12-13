package S1.Lambdas.Nivel_1.Ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Una expresion LAMBDA es un metodo anonimo que no necesita de un identificador para ser invocado
 * Estructura LAMBDA:
 * Parámetros      -       Operador (es sólo un separador) - Cuerpo de la expresión lambda (Instrucciones a ejecutar)
 * (parámetros)               ->                                   { instrucciones }
 *
 * Tanto la expresión lambda como la referencia de método se utilizan para proporcionar implementaciones de métodos
 * abstractos en interfaces funcionales. La elección entre una expresión lambda y una referencia de método a menudo
 * depende de la complejidad de la implementación y de las preferencias del desarrollador. En ambos casos, se logra la
 * implementación del método abstracto definido en la interfaz funcional.
 */
public class S1_N1_T8_Ejercicio1 {

    public static void main(String[] args) {
        List<String> listaString  = new ArrayList<> (Arrays.asList("papa","uno", "dos", "tres", "cuatro", "cinco","seis") );
        List<String> listaStringFiltrada = PalabrasConO(listaString);
        // Opcion 1: imprimir en el main
        for(String s: listaStringFiltrada)
        {
            System.out.println(s);
        }
    }

    public static List<String> PalabrasConO(List<String> listaString)
    {
    /**
     * El compilador entiende que el argumento "l" de la Lambda, son cada uno de los elementos del objeto "listaString",
     * o sea, quien invoca al metodo "filter".
     */
        Stream<String> listaStream = listaString.stream().filter(l -> l.contains("o"));
        // Ahora transformo el objeto Stream obtenido en una Lista
        List<String> listaFiltrada = listaStream.toList();
        return listaFiltrada;

        // Opcion 2: imprimir dentro del metodo
        // listaStream.forEach(l->System.out.println(l));
    /**
     * Una vez que se recorre el Stream (con un forEach, por ejemplo), éste se consumirá.
     * En este caso, si ejecutaramos el forEach que está comentado arriba, el Stream se consumiría y cuando
     * intentemos ejecutar la siguiente instruccion, a saber:
     *
     * List<String> listaFiltrada = listaStream.toList();
     * se lanzará una excepción IllegalStateException porque el Stream ya ha sido consumido.
     */

    }
}
