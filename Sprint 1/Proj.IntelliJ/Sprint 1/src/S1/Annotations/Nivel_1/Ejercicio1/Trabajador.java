package S1.Annotations.Nivel_1.Ejercicio1;

public class Trabajador {
    private String nombre;
    private String apellido;
    private float precioPorHora;

    public Trabajador(String nombre, String apellido, float precioPorHora) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.precioPorHora = precioPorHora;
    }
    public float calcularSueldo(float horasTrabajadas) {
        return this.precioPorHora * horasTrabajadas;
    }
    public String getName() {
        return this.nombre;
    }

}
