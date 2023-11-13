package S1.Generics.Dudas;

public class Dudas {

    /**
     *
     * https://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html
     *
     * public void addRectangle(List<? extends Shape> shapes) {
//         // Compile-time error!                               ¿ Por qué no se puede hacer esto? (ver link)
     *     shapes.add(0, new Rectangle());
     * }
     *
     ****************************************************************
     * ¿Cual es la diferencia entre los dos casos de aqui abajo?
     * (https://docs.oracle.com/javase/tutorial/extra/generics/methods.html)
     ****************************************************************
     *
//          ¿Por que no se puede hacer esto??? --> Por que "?" podria ser distinto de "Object"?
     *
     * Consider writing a method that takes an array of objects and a collection and puts all objects in the array into
     * the collection. Here's a first attempt:
     *
     * static void fromArrayToCollection(Object[] a, Collection<?> c) {
     *     for (Object o : a) {
     *         c.add(o); // compile-time error
     *     }
     * }
     *
     * By now, you will have learned to avoid the beginner's mistake of trying to use Collection<Object> as the type of
     * the collection parameter. You may or may not have recognized that using Collection<?> isn't going to work either.
     * Recall that you cannot just shove objects into a collection of unknown type.
     *
     * The way to do deal with these problems is to use generic methods. Just like type declarations, method declarations
     * can be generic—that is, parameterized by one or more type parameters.
     *
     * static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
     *     for (T o : a) {
     *         c.add(o); // Correct
     *     }
     *
//          ¿Para que se usa el "<T>"????????
     * }
     *
//          ¿ No entiendo esto ¿¿¿??? (Esta en el mismo link, mas abajo):
//     Tal vez es por esto:
     Notice that we don't have to pass an actual type argument to a generic method. The compiler
     infers the type argument for us, based on the types of the actual arguments. It will generally infer the most specific
     type argument that will make the call type-correct.
     *
     * // T inferred to be Object
     * fromArrayToCollection(sa, co);
     *
//          Duda en : https://docs.oracle.com/javase/tutorial/extra/generics/legacy.html
     * En la parte de "Using Generic Code in Legacy Code" (Ultimo titulo de la pagina).
     *
     *      No entiendo los ejemplos de esta pagina:
     * https://docs.oracle.com/javase/tutorial/extra/generics/fineprint.html
     *
     *
     *      Mismo link que arriba, al final de la pagina:
     * Similarly, attempting to create an array object whose element type is a type variable causes a compile-time error:
     *
     * <T> T[] makeArray(T t) {
     *     return new T[100]; // Error.
     * }
     *
     *      ¿Para qué se usa el "<T>" aqui?
     *      ¿Por que da error?
     *
     *      Varias dudas aqui ... :
     *      https://docs.oracle.com/javase/tutorial/extra/generics/fineprint.html
     */
}
