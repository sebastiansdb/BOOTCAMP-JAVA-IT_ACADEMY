package S1.Collections.Nivel_2.Ejercicio1;
import java.util.*;

public class App1 {

    public static void main(String[] args){
        Set<Restaurant> restaurants = new HashSet<>();

        Restaurant r1 = new Restaurant("casa", 9);
        Restaurant r2 = new Restaurant("olla", 7);
        Restaurant r3 = new Restaurant("patio", 10);
        Restaurant r5 = new Restaurant("olla", 5);
        addRestaurant(r1,restaurants);
        addRestaurant(r2,restaurants);
        addRestaurant(r3,restaurants);
        addRestaurant(r5,restaurants);
        System.out.println("Contenido del HashSet<Restaurant>:\n" + restaurants);
        // r4 = r3
        System.out.println("\nIntento agregar un restaurant igual a uno que ya está cargado:");
        Restaurant r4 = new Restaurant("patio", 10);

        // Agrego un objeto con los mismos atributos que uno ya cargado (r3) en el HashSet. Si no estuviera la funcion "existsRestaurant",
        // lo agregaria. Esto es porque se comparan objetos por la referencia de su posicion en memoria y no por el valor de sus atributos.
        addRestaurant(r4,restaurants);
        System.out.println("Imprimo nuevamente el contenido del HashSet<Restaurant>. Comprobamos que no se agregó el objeto que " +
                "se intentó cargar porque ya estaba en el Set:");
        System.out.println(restaurants);

        // Ordenamiento restaurants por Nombre y Puntuacion ascendente          // NO  SE COMO HACERLO!!!!!
        System.out.println("\nOrdenamiento restaurants por Nombre y Puntuacion ascendente:");
        List<Restaurant> sortedRestaurants = new ArrayList<>(restaurants);
        //  ¿Por qué el TreeSet elije el atributo "name" para ordenar los objetos?
        System.out.println("ArrayList:\n" + sortedRestaurants);
        Collections.sort(sortedRestaurants);
        System.out.println(restaurants.contains(r4));
        System.out.println(sortedRestaurants);
    }

    public static boolean contains (Set<Restaurant> restaurants, Restaurant restaurant){
        boolean found = false;
        Restaurant temp;
        Iterator<Restaurant> it = restaurants.iterator();
        while (it.hasNext() && !found ) {
            //temp = it.next();
            //if (temp.equals(restaurant)){

            // it.next() es un objeto clase RESTAURANT, por lo tanto, se usa el "equals" @override
            if (it.next().equals(restaurant)){
                found = true;
            }
        }
        return found;

    }


    public static void addRestaurant(Restaurant r , Set<Restaurant> restaurants ){
        //if (! (restaurants.contains(r)) ){      // No sé cómo comparar dos elementos Restaurant dentro del set.. ¿¿¿??? ¿Cómom lo hago?
        if(existsRestaurant(r,restaurants)){
            System.out.println("El restaurante '" + r.getName() + "', con puntuacion '" + r.getPunctuation() + "', ya está en la lista. " +
                    "No será agregado nuevamente.\n");
        } else {
            restaurants.add(r);
        }

    }
    public static boolean existsRestaurant (Restaurant r , Set<Restaurant> restaurants)
    {
        boolean ret = false;
        //Iterator<Restaurant> it = restaurants.iterator();   // ¿Diferencia entre usar un Iterador o un For each a nivel de eficiencia?
        for(Restaurant indexRestaurant : restaurants ){
            if (indexRestaurant.getName() == r.getName() && indexRestaurant.getPunctuation() == r.getPunctuation() )
            {
                ret = true;
            }
        }
        return ret;
    }

}
