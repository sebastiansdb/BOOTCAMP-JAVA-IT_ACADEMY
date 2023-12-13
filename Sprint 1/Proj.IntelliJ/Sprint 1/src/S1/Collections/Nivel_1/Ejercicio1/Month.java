package S1.Collections.Nivel_1.Ejercicio1;

public class Month {

    private String name;

    // Construct
    public Month (String name) {
        this.name = name;
    }

    // Getters and setters
    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  name;
    }

}
