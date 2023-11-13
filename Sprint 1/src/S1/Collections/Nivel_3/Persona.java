package S1.Collections.Nivel_3;

public class Persona  {             // implements Comparator<Persona> ??

    private String name;
    private String surname;
    private String id;

    public Persona (String name, String surname, String id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    // Getters and setters

    public String getName ()
    {
        return this.name;
    }
    public String getSurname() {
        return surname;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setId(String id) {
        this.id = id;
    }

    // Métodos
    @Override
    public String toString() {
        return "Persona{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
