package S1.Testing.Nivel_1.Ejercicio3;

import java.io.IOException;
import java.util.ArrayList;

public class App3 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> al = new ArrayList<>();
        al.add("Oso");
        al.add("Perro");
        al.add("Gato");
        readArrayElement(al,al.size()+1);
    }

    public static void readArrayElement(ArrayList<String> al, int index) throws IOException

    {
        if (al.size() < index){
            throw new IOException("Indice fuera de Rango");
            // continue o return .... Como se hace? Es buena practica?
        } else {
            System.out.println(al.get(index));
        }
    }

}
