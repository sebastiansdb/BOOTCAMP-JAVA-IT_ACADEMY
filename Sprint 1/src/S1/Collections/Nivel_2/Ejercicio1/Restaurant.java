package S1.Collections.Nivel_2.Ejercicio1;

public class Restaurant implements Comparable<Restaurant> {

    private String name;
    private int punctuation;

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
    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public boolean equals(Object restaurant) {
        boolean ret = false;
        if ( this.name == ( (Restaurant) restaurant ).getName() && this.punctuation == ( (Restaurant) restaurant ).getPunctuation() ){
            ret = true;
        }
        return ret;
    }

    public int compareTo(Restaurant restaurant) {       // ESTO NO LO TENGO CLARO ¿??¿?¿?¿?¿
        if ( this.name != restaurant.getName() ){
            return this.name.compareTo(restaurant.getName());
        }
        //return this.punctuation - restaurant.getPunctuation();    // Si lo pongo así, me los ordena en orden descendente ¿POR QUE?
        return restaurant.getPunctuation() - this.punctuation ;
    }
    @Override
    public String toString() {
        return "Restaurant '" + name + '\'' +
                ", punctuation = " + punctuation;
    }
}
