package S1.Lambdas.Nivel_1.Ejercicio4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S1_N1_T8_Ejercicio4 {
    public static void main(String[] args){
        List<String> meses = new ArrayList(Arrays.asList("enero", "febrero", "marzo", "abril", "mayo", "junio",
                "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"));
        imprimirMesesMetodoReferencia(meses);
    }
    public static void imprimirMesesMetodoReferencia(List<String> meses){
        meses.forEach(System.out::println);
        /**
         * "System.out::println" es una referencia a método que se pasa como argumento a forEach. Aquí, println es un
         * método estático de la clase "System", y "out" es un objeto de tipo PrintStream.
         * System.out::println se puede leer como "referencia al método println de la clase "PrintStream" (System es
         * una classe y "out" es un atributo de la misma, el cual es de tipo "PrintStream". La Clase "PrintStream" tiene
         * el metodo "println".
         * Bosquejo de las clases:
         *
         * //the System class belongs to java.lang package
         * class System {
         *   public static final PrintStream out;
         *   //...
         * }
         *
         * //the Prinstream class belongs to java.io package
         * class PrintStream{
         * public void println();
         * //...
         * }
         *
         * En este caso (metodo forEach), la referencia a método se utiliza para proporcionar la implementación de la interfaz funcional
         * Consumer. Dicha interfaz tiene un método abstracto llamado accept, que toma un argumento y
         * realiza alguna acción sin devolver ningún resultado. En este caso, la instruccion System.out::println
         * implementa ese método.
         */
    }
}

/**
 **                                                        DOCUMENTACION                                              **

 * (Podemos ver la implementacion de este código en el paquete "Ejemplo")

 * Cuando una interfaz es funcional, significa que tiene exactamente un método abstracto. A partir de Java 8, las
 * interfaces funcionales son esenciales para el soporte de expresiones lambda y referencias de método.
 * Ejemplo:

  @FunctionalInterface
  interface MiInterfazFuncional {
      void miMetodoAbstracto();

      // Otros métodos (default o estáticos) no afectan la "functionalidad" de la interfaz funcional
      default void otroMetodo() {
          System.out.println("Este es otro método en la interfaz funcional.");
      }
  }

 *  La anotación @FunctionalInterface es opcional, pero ayuda a indicar que esta interfaz está diseñada para ser
 *  funcional. Se puede usar esta interfaz funcional en combinación con expresiones lambda o referencias de método.
 *  Por ejemplo:
 *
   public class EjemploLambda {
      public static void main(String[] args) {
          // Utilizando una expresión lambda
          MiInterfazFuncional lambda = () -> System.out.println("Implementación mediante expresión lambda");
          lambda.miMetodoAbstracto();

          // Utilizando una referencia de método
          EjemploLambda ej = new EjemploLambda();
          MiInterfazFuncional referenciaMetodo = ej::miMetodoImplementado;
          referenciaMetodo.miMetodoAbstracto();
      }

      // Método que implementa el método abstracto de la interfaz
      public void miMetodoImplementado() {
          System.out.println("Implementación mediante referencia de método.");
      }
  }

 * La interfaz MiInterfazFuncional tiene un único método abstracto llamado miMetodoAbstracto.

 * La expresión lambda () -> System.out.println("Implementación mediante expresión lambda") proporciona una
 * implementación para ese método abstracto.

 * La referencia de método ej::miMetodoImplementado también proporciona una implementación para ese método abstracto.

 * Ambos métodos, ya sea a través de la expresión lambda o la referencia de método, se pueden asignar a una variable del
 * tipo de la interfaz funcional, demostrando así la utilidad de las interfaces funcionales en el contexto de
 * expresiones lambda y referencias de método en Java.
 *
 */
