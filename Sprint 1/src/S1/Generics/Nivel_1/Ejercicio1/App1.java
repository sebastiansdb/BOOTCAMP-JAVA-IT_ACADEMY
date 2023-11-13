package S1.Generics.Nivel_1.Ejercicio1;

public class App1 {

    public static void main(String[] args) {
        NoGenericMethods<String> noGe1 = new NoGenericMethods<>("x", "y", "z");
        System.out.println(noGe1.getObA());
        System.out.println(noGe1.getObB());
        System.out.println(noGe1.getObC());
    }
}

