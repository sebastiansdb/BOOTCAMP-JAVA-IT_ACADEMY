package S1.Lambdas.Nivel_1.Ejercicio8;

public class S1_N1_T8_Ejercicio8 {

    public static void main(String[] args){

       ReverseInterface8 r = s -> ( new StringBuilder(s) ).reverse().toString();;
        /**
         * // Mi idea fue esta:
         *       ReverseInterface8 r = s -> return (new StringBuilder(s)).reverse().toString();
         *
         * Cuando se una expresión lambda con una sola instrucción, no es necesario utilizar la palabra clave "return".
         * En una expresión lambda, la expresión misma (en este caso, (new StringBuilder(s)).reverse().toString()) se
         * evalúa y el resultado se devuelve automáticamente. No es necesario usar return en este contexto.
         */
       System.out.println(r.reverse("Tetera"));
    }
}
