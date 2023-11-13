package S1.Herencia.Nivel_1.Ejercicio1;

public abstract class Instrumento {

    private float price;
    private String name;

    // Constructor
    public Instrumento( float price, String name){
            this.price = price;
            this.name = name;
        }

    // Getters and Setters
    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Bloque de inicializacion estatica
    static {
        System.out.println("Los atributos de la clase Instrumento han sido cargados");
    }

    // Miembro de inicializacion estatico
    public static void MetodoEstatico(){
        System.out.println("Mensaje del metodo estatico de la clase Instrumento");
    }
    public abstract void play();

}
