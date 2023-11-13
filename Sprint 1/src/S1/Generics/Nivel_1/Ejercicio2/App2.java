package S1.Generics.Nivel_1.Ejercicio2;

public class App2 {

    public static void main(String[] args) {

        Persona persona1 = new Persona ("jota","ja",21);
        String str = "Hey!";
        Integer in = 77;

//        GenericsMethods ob = new GenericsMethods();
        GenericsMethods.printMethod(persona1,str,in);
        GenericsMethods.printMethod(persona1,in,str);
        GenericsMethods.printMethod('A',100,persona1);
    }
}
