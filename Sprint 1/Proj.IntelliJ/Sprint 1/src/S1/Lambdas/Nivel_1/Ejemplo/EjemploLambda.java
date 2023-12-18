package S1.Lambdas.Nivel_1.Ejemplo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EjemploLambda {
        public static void main(String[] args) {
            // Utilizando una expresión lambda
            MiInterfazFuncionalEjemplo lambda = () -> System.out.println("Implementación mediante expresión lambda");
            lambda.miMetodoAbstracto();

            // Utilizando una referencia de método
            EjemploLambda ej = new EjemploLambda();
            MiInterfazFuncionalEjemplo referenciaMetodo = ej::miMetodoImplementado;
            referenciaMetodo.miMetodoAbstracto();

            // "miMetodoImplementado" está escrito fuera del main

            // Ejercicio 6
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
                    /**String.class::cast is nice, but unfortunately less efficient than c -> (String) c because Class.cast
                     * performs a redundant isInstance check.
                     */
                    // Esta sentencia ordena la lista alfabeticamente.
//                                    .sorted(String::compareTo)
                    .sorted(Comparator.comparing(s ->s.length()))
                    .collect(Collectors.toList());
            listaNombres.forEach(System.out::println);
        }

        // Método que implementa el método abstracto de la interfaz
        public void miMetodoImplementado() {
            System.out.println("Implementación mediante referencia de método.");
        }
    }

