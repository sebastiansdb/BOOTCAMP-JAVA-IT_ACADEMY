package S1.Collections.Nivel_2.Ejercicio2;

import java.util.Objects;

public class Restaurant implements Comparable<Restaurant>  {

    private String name;
    private int punctuation;

    //Constructor
    public Restaurant(String name, int punctuation) {
        this.name = name;
        this.punctuation = punctuation;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public int getPunctuation() {
        return punctuation;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return punctuation == that.punctuation && name.equals(that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, punctuation);
    }

    public int compareTo (Restaurant restaurant)
    {

        int nameFlag = this.name.compareToIgnoreCase(restaurant.getName());
        if ( nameFlag == 0 )
        {
            // Si los nombres son iguales, entonces:
            return Integer.compare(this.punctuation, restaurant.punctuation);
            /**
             * ESTO NO ES PARTE DEL EJERCICIO.
             Encontré otra manera de la comparacion de la "puntuación", que es con la resta de los atributos. Lo dejo
             comentado para acordarme:

            //return restaurant.getPunctuation() - this.punctuation ; // Si lo pongo así, me los ordena en orden ascendente ¿POR QUE?
            //return this.punctuation - restaurant.getPunctuation();  // Si lo pongo así, me los ordena en orden descendente ¿POR QUE?

            */
        }
        return nameFlag;

    }

    @Override
    public String toString()
    {
        return "Restaurant '" + name + '\'' +
                ", punctuation = " + punctuation;
    }
}
