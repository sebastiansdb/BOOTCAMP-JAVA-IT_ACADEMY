package S1.Excepciones.Nivel_1.Ejercicio1;

public class Producto {

    private String nombre;
    private double precio;

    public Producto (String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
     }
     // Getters
    public double getPrecio(){
        return precio;
    }
}
