package S1.Generics.Nivel_1.Ejercicio2;

public class    Persona {
    String name;
    String surname;
    int age;

    public Persona(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Persona { " +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                " }" ;
    }
}
