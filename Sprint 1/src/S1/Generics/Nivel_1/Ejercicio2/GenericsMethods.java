package S1.Generics.Nivel_1.Ejercicio2;

public class GenericsMethods {

    // ¿¿ Es buena practica colocar el constructor asi (sabiendo que no necesitare atributos de la clase) o directamente no escribirlo ??
    public GenericsMethods() {

    }
    public static <T> void printMethod (T a,T b, T c)
    {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
