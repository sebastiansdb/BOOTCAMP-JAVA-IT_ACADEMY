package S1.Java_Utils.Practicas_Basicas;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
public class Main {

    public static void main(String[] args) throws IOException {

                                            // Path Operations  //

        // Creating a Path

        //Path path1 = Paths.get(args[0]);
        Path path1 = Paths.get("/Users/sebastian/Coding/Java/IT_Academy/Java con Spring Framework");
        System.out.println(path1);
        //Path p3 = Paths.get(URI.create("/Users/sebastian/Coding/Java/IT_Academy/Java con Spring Framework"));
        //System.out.println(p3);

        //  Retrieving Information about a Path

        // ¿Para qué es " %s%n " ?
        System.out.format("toString: %s%n", path1.toString());
        System.out.format("getFileName: %s%n", path1.getFileName());
        System.out.format("getName(0): %s%n", path1.getName(0));
        System.out.format("getNameCount: %d%n", path1.getNameCount());
        System.out.format("subpath(0,2): %s%n", path1.subpath(0,2));
        System.out.format("getParent: %s%n", path1.getParent());
        System.out.format("getRoot: %s%n", path1.getRoot());


        // ¿Para qué se usa URI?

        // If you need to convert the path to a string that can be opened from a browser, you can use toUri. For example:
        System.out.format("%s%n", path1.toUri());

        //  String a = path1.toUri();  ??¿¿¿¿??

        ///////////////////////////////////    Ejemplos Metodos clase "FILE"   //////////////////////////////////

        String route = "/Users/sebastian/Documents/z_aramba/Easo/Reparaciones";
        Path p1 = Paths.get(route);
        Path root = p1.getRoot();
        String rootStr = p1.toString();
        File aux;
        String a;
        File[] files = null;

        File actualDirectory = new File(route);

        if (actualDirectory.exists()) {
            a = actualDirectory.getParent();
            System.out.println();
            System.out.println("getParent\n" + a + "\n");


            aux = actualDirectory.getParentFile();
            System.out.println("getParentFile\n" + aux + "\n");

            a = actualDirectory.getCanonicalPath();
            System.out.println(a);

            aux = actualDirectory.getCanonicalFile();
            System.out.println(aux);

            a = actualDirectory.getAbsolutePath();
            System.out.println(a);

            a = actualDirectory.getName();
            System.out.println(a);

            aux = actualDirectory.getAbsoluteFile();
            System.out.println(aux);
        }
        ///////////////////////////////////    Ejemplos Metodos clase "FILE"   //////////////////////////////////
    }
}
