package S1.Testing.Nivel_1.Ejercicio3;

import java.util.ArrayList;


public class Exception_ArrayIndexOutOfBounds {
    static  ArrayList<String> al = new ArrayList<>();

    static{
        al.add("Oso");
        al.add("Perro");
        al.add("Gato");
    }
    public static void readArrayElement(int index)
    {
        try {
            System.out.println(al.get(index));
        } catch ( IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }
    }

}
