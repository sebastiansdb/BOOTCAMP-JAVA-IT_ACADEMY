package S1.Java_Utils.Nivel_1;

import java.io.Serializable;

public class ToSerialize implements Serializable {

    private String name;
    private int age;

    public ToSerialize(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
