package S1.Herencia.Nivel_1.Ejercicio2;
import java.util.Scanner;
public class Coche {
    private static final String marca;
    private static String modelo;
    private final int potencia;

    static {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa la marca");
        marca = sc.nextLine();
        System.out.println("Ingresa el modelo");
        modelo = sc.nextLine();
    }
    // Constructor
    public Coche (int potencia) {
        this.potencia = potencia;
    }
    public void acelerar(){
        System.out.println("El vehicle està accelerant");
    }
    // Metodos de clase
    static void frenar(){
        System.out.println("El vehicle està frenant");
    }

    @Override
    public String toString() {
        return "Coche: " +
                "modelo = '" + modelo + '\'' +
                ", potencia = '" + potencia + '\''+
                ", marca = '" + marca + '\'';
    }
}
