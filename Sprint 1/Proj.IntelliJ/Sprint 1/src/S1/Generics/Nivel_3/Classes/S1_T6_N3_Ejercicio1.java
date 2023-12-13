package S1.Generics.Nivel_3.Classes;
public class S1_T6_N3_Ejercicio1 {

    public static void main (String[] args) {
        Smartphone sP1 = new Smartphone();
        System.out.println("Calling Method limited by Phone Interface:\n");
        Generic.callCallMethod(sP1);
        System.out.println("\nCalling Method limited by Smartphone class:\n");
        Generic.callSmartPhoneClassMethods(sP1);

        // El mètode limitat per la interfície Telefon, de la classe Generica, pot cridar al mètode ferFotos()?
        // La respuesta es no, porque en la interface Telefon no está declarado el metodo ferFotos().
    }
}




                            ////////////////////////////////////////////////////////////////

                                                        // DUDAS //
        /**
         * import java.util.ArrayList;
        Integer in = 10;
        Number din = 10;
//         ¿¿ Por qué no se puede hacer esto???
//        ArrayList<Number> nN = new ArrayList<>(din);
        // por que asi SI se puede?
        ArrayList<Number> nN = new ArrayList<>();
        nN.add(din);
        ArrayList<Integer> iN = new ArrayList<>(in);

//        Consider the following code: (https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html)

//        List<EvenNumber> le = new ArrayList<>();
//        List<? extends NaturalNumber> ln = le;
//        ln.add(new NaturalNumber(35));  // compile-time error

//        ¿¿¿¿?????
//        Because List<EvenNumber> is a subtype of List<? extends NaturalNumber>, you can assign le to ln.
//        ¿¿¿But you cannot use ln to add a natural number to a list of even numbers. The following operations on the list are possible:????

          */


