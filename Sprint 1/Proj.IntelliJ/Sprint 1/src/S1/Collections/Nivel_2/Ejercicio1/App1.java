package S1.Collections.Nivel_2.Ejercicio1;
import java.util.*;

public class App1 {

    public static void main(String[] args){
        Set<Restaurant> restaurants = new HashSet<>();
        Restaurant r1 = new Restaurant("casa", 9);
        Restaurant r2 = new Restaurant("olla", 7);
        Restaurant r3 = new Restaurant("patio", 10);
        Restaurant r4 = new Restaurant("patio", 10);
        Restaurant r5 = new Restaurant("olla", 5);
        addRestaurant(r1,restaurants);
        addRestaurant(r2,restaurants);
        addRestaurant(r3,restaurants);
        addRestaurant(r5,restaurants);
        System.out.println("Contenido del HashSet<Restaurants>:\n" + restaurants);
        //
        // Agrego un objeto con los mismos atributos que uno ya cargado (r3) en el HashSet: Si no estuvieran los metodos
        // "Equals()" y "HashCode()" sobreescritos, dicho elemento se agregaria al HasSet.
        // Esto es porque se compararian los objetos por referencia ( a su posicion en memoria )
        // y no por el valor de sus atributos, por lo tanto, no serian objetos iguales, sino diferentes.
        //
        System.out.println("\nIntento agregar un restaurant igual a uno que ya está cargado:");
        // r4 = r3
        addRestaurant(r4,restaurants);
        System.out.println("Imprimo nuevamente el contenido del HashSet<Restaurant>. Comprobamos que no se agregó el " +
                "objeto que se intentó cargar porque ya estaba en el Set:");
        System.out.println(restaurants);
    }

    public static void addRestaurant(Restaurant r , Set<Restaurant> restaurants )
    {
        if (restaurants.contains(r) ){
            System.out.println("El restaurante '" + r.getName() + "', con puntuacion '" + r.getPunctuation() + "', ya está en la lista. " +
                    "No será agregado nuevamente.\n");
        } else {
            restaurants.add(r);
        }
    }

}
