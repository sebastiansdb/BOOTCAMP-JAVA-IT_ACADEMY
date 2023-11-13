package S1.Generics.Nivel_3.Classes;

import java.util.ArrayList;

public class App3 {

    public static void main (String[] args) {
        Smartphone sP1 = new Smartphone();
        Smartwatch sW1 = new Smartwatch();
        System.out.println("Calling Phone Interface Method:\n");
        Generic.callInterfaceMethod(sP1);
        System.out.println("\nCalling Smartphone Class Methods:\n");
        Generic.callPhoneClassMethods(sP1);
        System.out.println("\nCalling Smartwatch Class Methods:\n");
        Generic.callInterfaceMethod(sW1);
        //Generic.callPhoneClassMethods(sW1); ¿¿¿???

        ////////////////////////////////////////////////////////////////

        Integer in = 10;
        Number din = 10;
//         ¿¿ Por qué no se puede hacer esto???
//        ArrayList<Number> nN = new ArrayList<>(din);
        ArrayList<Integer> iN = new ArrayList<>(in);

//        Consider the following code: (https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html)

//        List<EvenNumber> le = new ArrayList<>();
//        List<? extends NaturalNumber> ln = le;
//        ln.add(new NaturalNumber(35));  // compile-time error

//        ¿¿¿¿?????
//        Because List<EvenNumber> is a subtype of List<? extends NaturalNumber>, you can assign le to ln.
//        ¿¿¿But you cannot use ln to add a natural number to a list of even numbers. The following operations on the list are possible:????



    }
}
