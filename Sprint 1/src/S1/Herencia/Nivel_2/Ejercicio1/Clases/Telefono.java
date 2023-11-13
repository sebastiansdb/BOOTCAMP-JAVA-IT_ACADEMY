package S1.Herencia.Nivel_2.Ejercicio1.Clases;

public class Telefono {

    private String modelo;
    private String marca;

    // Constructor
    public Telefono(String modelo, String marca){
        this.modelo = modelo;
        this.marca = marca;
    }
    // Metodos Telefono
    public void llamar (String numTelefono) {
        System.out.println("Se está llamando al número: " + numTelefono);
    }
}
