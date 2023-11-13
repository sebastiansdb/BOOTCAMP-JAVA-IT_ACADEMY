package S1.Herencia.Nivel_2.Ejercicio1.Clases;

import S1.Herencia.Nivel_2.Ejercicio1.Clases.Interfaces.Camara;
import S1.Herencia.Nivel_2.Ejercicio1.Clases.Interfaces.Reloj;

public class Smartphone extends Telefono implements Camara, Reloj {

    public Smartphone(String modelo, String marca){
        super(modelo, marca);
    }
    public void fotografiar(){
        System.out.println("Se esta haciendo una foto");
    }
    public void alarma(){
        System.out.println("Esta sonando la alarma");
    }
}
