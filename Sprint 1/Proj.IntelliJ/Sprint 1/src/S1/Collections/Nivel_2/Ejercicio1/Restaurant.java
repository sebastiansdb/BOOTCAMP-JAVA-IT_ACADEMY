package S1.Collections.Nivel_2.Ejercicio1;

import java.util.Objects;

public class Restaurant {

    private String name;
    private int punctuation;

    //Constructor
    public Restaurant(String name, int punctuation)
    {
        this.name = name;
        this.punctuation = punctuation;
    }

    // Getters and setters
    public String getName()
    {
        return name;
    }
    public int getPunctuation()
    {
        return punctuation;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
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

    @Override
    public String toString()
    {
        return "Restaurant '" + name + '\'' +
                ", punctuation = " + punctuation;
    }
}
