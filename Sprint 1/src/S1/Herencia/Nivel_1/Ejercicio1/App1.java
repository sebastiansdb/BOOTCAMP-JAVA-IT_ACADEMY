package S1.Herencia.Nivel_1.Ejercicio1;

public class App1 {

    public static void main (String[]args){

        Viento viento = new Viento(100,"Flauta");
        Instrumento cuerda = new Cuerda(150,"Guitarra");
        Instrumento percusion = new Percusion(200,"Bateria");

        viento.play();
        cuerda.play();
        percusion.play();

        Instrumento.MetodoEstatico();
    }
}
