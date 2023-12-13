package S1.Generics.Teoria;

public class TeoriaGenerics {

    /**
     * What makes a class generic is the fact that it has the same behavior for all of its possible type parameters;
     * the same class can be viewed as having many different types.
     *
     * In any nontrivial software project, bugs are simply a fact of life. Careful planning, programming, and testing can
     * help reduce their pervasiveness, but somehow, somewhere, they'll always find a way to creep into your code. This
     * becomes especially apparent as new features are introduced and your code base grows in size and complexity.
     *
     * Fortunately, some bugs are easier to detect than others. Compile-time bugs, for example, can be detected early on;
     * you can use the compiler's error messages to figure out what the problem is and fix it, right then and there.
     * Runtime bugs, however, can be much more problematic; they don't always surface immediately, and when they do, it
     * may be at a point in the program that is far removed from the actual cause of the problem.
     *
     * Generics add stability to your code by making more of your bugs detectable at compile time. After completing this
     * lesson, you may want to follow up with the Generics tutorial by Gilad Bracha.
     * (https://docs.oracle.com/javase/tutorial/extra/generics/index.html)
     *
     * A Java compiler applies strong type checking to generic code and issues errors if the code violates type safety.
     * Fixing compile-time errors is easier than fixing runtime errors, which can be difficult to find.
     *
     *  In some cases, the compiler infers the type of a wildcard. For example, a list may be defined as List<?> but,
     *  when evaluating an expression, the compiler infers a particular type from the code. This scenario is known
     *  as wildcard capture.
     *
     * For the most part, you don't need to worry about wildcard capture, except when you see an error message that
     * contains the phrase "capture of".
     *
     * The WildcardError example produces a capture error when compiled:
     *
     * import java.util.List;
     *
     * public class WildcardError {
     *
     *     void foo(List<?> i) {
     *         i.set(0, i.get(0));
     *     }
     * }
     *
     * In this example, the compiler processes the i input parameter as being of type Object. When the foo method invokes
     * List.set(int, E), the compiler is not able to confirm the type of object that is being inserted into the list,
     * and an error is produced. When this type of error occurs it typically means that the compiler believes that you are
     * assigning the wrong type to a variable. Generics were added to the Java language for this reason — to enforce type
     * safety at compile time.
     */
}
