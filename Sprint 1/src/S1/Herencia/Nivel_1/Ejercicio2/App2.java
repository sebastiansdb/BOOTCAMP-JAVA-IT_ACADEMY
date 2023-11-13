package S1.Herencia.Nivel_1.Ejercicio2;
import java.util.Scanner;

public class App2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa la potencia");
        int potencia = sc.nextInt();
        Coche a = new Coche(potencia);
        Coche b = new Coche(100);
        System.out.println(a);
        System.out.println(b);
        System.out.println("Todos las instancias de la clase Coche tendrán los mismos atributos 'Marca' y 'Modelo'," +
                " ya que éstos son estaticos");
        a.acelerar();
        a.frenar();
        System.out.println("LLamada a metodo estatico sin instancia:");
        Coche.frenar();
    }
}