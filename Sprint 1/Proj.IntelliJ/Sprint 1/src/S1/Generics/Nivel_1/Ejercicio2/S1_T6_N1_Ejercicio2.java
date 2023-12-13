package S1.Generics.Nivel_1.Ejercicio2;

public class S1_T6_N1_Ejercicio2 {

    public static void main(String[] args) {

        Persona persona1 = new Persona ("jota","ja",21);
        Persona persona2 = new Persona ("Micu","Ap",23);
        String str = "Hey!";
        Integer in = 77;
        GenericsMethods.printMethod(persona1,str,in);
        GenericsMethods.printMethod(persona1,in,str);
        GenericsMethods.printMethod('A',100,persona1);
        GenericsMethods.printMethod('A',persona2,persona1);
    }
}
