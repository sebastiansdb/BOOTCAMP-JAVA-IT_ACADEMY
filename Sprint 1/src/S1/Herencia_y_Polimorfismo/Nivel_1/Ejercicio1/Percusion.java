package S1.Herencia_y_Polimorfismo.Nivel_1.Ejercicio1;

public class Percusion extends Instrumento {
    public Percusion(float price, String name) {
        super(price, name);
    }
    @Override
    public void play(){
        System.out.println("Esta sonando un instrumento de percusion");
    }
}
