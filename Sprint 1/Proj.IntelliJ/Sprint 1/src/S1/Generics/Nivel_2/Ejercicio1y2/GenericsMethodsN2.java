package S1.Generics.Nivel_2.Ejercicio1y2;
import java.util.List;
public class GenericsMethodsN2 {
    /**                                                     TEORIA                                                **

     * ¿¿ Es buena práctica declarar el constructor vacio, sabiendo que no necesitaré atributos de la clase o
     * ejecutar codigo alguno dentro del mismo?
     * ¿¿ O mejor no escribirlo en estos casos??
     *
     * EL compilador agrega un constructor por defecto. O sea que si no necesitamos ejecutar código dentro del
     * constructor, no es necesario escribirlo.
     *
     * NO es una mala práctica no escribir el constructor en este caso.
     */
    public GenericsMethodsN2() {
    }
    public static <T> void printMethod (String a,T b, T c)
    {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public static <T> void printMethodList (List<T> lst)
    {
        System.out.println(lst);
    }
}
