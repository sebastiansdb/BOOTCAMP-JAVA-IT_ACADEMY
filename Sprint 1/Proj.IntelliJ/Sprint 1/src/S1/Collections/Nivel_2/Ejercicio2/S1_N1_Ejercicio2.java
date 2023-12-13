package S1.Collections.Nivel_2.Ejercicio2;
import java.util.*;

public class S1_N1_Ejercicio2 {

    public static void main(String[] args){
        Set<Restaurant> restaurants = new TreeSet<>();
        Restaurant r1 = new Restaurant("casa", 9);
        Restaurant r2 = new Restaurant("olla", 7);
        Restaurant r3 = new Restaurant("patio", 10);
        Restaurant r4 = new Restaurant("Patio", 9);
        Restaurant r5 = new Restaurant("olla", 5);
        addRestaurant(r1,restaurants);
        addRestaurant(r2,restaurants);
        addRestaurant(r3,restaurants);
        addRestaurant(r4,restaurants);
        addRestaurant(r5,restaurants);
        System.out.println("\nSet 'restaurants' ordenado por Nombre y Puntuacion ascendente:");
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