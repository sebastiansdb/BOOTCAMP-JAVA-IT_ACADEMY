package S1.Herencia_y_Polimorfismo.Nivel_1.Ejercicio1;

public class Cuerda extends Instrumento {

    public Cuerda(float price, String name) {
        super(price, name);
    }
    @Override
    public void play() {
        System.out.println("Està sonant un instrument de corda");
    }

}
