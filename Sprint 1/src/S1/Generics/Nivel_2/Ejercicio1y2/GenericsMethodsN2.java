package S1.Generics.Nivel_2.Ejercicio1y2;
import java.util.List;
public class GenericsMethodsN2 {

    // ¿¿ Es buena practica colocar el constructor asi (sabiendo que no necesitare atributos de la clase) o directamente no escribirlo ??
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
