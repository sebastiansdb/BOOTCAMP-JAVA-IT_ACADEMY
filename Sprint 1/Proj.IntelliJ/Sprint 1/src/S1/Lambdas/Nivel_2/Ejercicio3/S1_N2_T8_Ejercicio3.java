package S1.Lambdas.Nivel_2.Ejercicio3;

public class S1_N2_T8_Ejercicio3 {
    public static void main (String[] args)
    {
        operation(5.5f,3.3f);
    }

    public static void operation (float a, float b )
    {
        FunctionalInterface_Ejercicio3 add = (l1, l2) -> l1 + l2 ;
        FunctionalInterface_Ejercicio3 substraction = (l1, l2) -> l1 - l2;
        FunctionalInterface_Ejercicio3 division = (l1, l2) -> l1 / l2;
        FunctionalInterface_Ejercicio3 multiplication = (l1, l2) -> l1 * l2;
        System.out.println("Suma " + add.operation(a,b));
        System.out.println("Resta " + substraction.operation(a,b));
        System.out.println("Division " + division.operation(a,b));
        System.out.println("Multiplicacion " + multiplication.operation(a,b));
    }
}
