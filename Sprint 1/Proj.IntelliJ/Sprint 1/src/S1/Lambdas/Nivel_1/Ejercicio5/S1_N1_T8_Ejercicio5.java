package S1.Lambdas.Nivel_1.Ejercicio5;

public class S1_N1_T8_Ejercicio5 {

    public static void main (String[] args){
       // Opcion 1:Implementacion con una expresión Lambda

       // Implemento el metodo abstracto de la interfaz MiInterfaceFuncional a través de una expresión Lambda.
       MiInterfaceFuncional5 inter = () -> 3.1415;
       System.out.println(inter.getPiValue());
        /**
         *  La expresión lambda "() -> 3.1415" se puede leer como "una función sin parámetros que devuelve 3.1415".
         *  Esta expresión lambda es equivalente a la implementación de la clase anónima que se muestra debajo.
         */
        /**

         // Implementación tradicional utilizando una clase anónima:
         MiInterfaceFuncional inter = new MiInterfaceFuncional() {
         @Override
         public double getPiValue(){
         return 3.1415;
         }
         };
         System.out.println(inter.getPiValue());
         */

       // Opcion 2: Implementación con una referencia de método
       S1_N1_T8_Ejercicio5 ej = new S1_N1_T8_Ejercicio5();
       MiInterfaceFuncional5 referenciaAMetodo = ej::miMetodoImplementado;
        System.out.println(referenciaAMetodo.getPiValue());
       /**
        * La operacion de arriba también se podria hacer sin instanciar un objeto de la clase S1_N1_T8_Ejercicio5. Así:

        MiInterfaceFuncional referenciaAMetodo = S1_N1_T8_Ejercicio5::miMetodoImplementado;

        * (Solo que habria que hacer STATIC al metodo "miMetodoImplementado()")
        */
    }
    public double miMetodoImplementado()
    {
        return 3.1415;
    }
}
