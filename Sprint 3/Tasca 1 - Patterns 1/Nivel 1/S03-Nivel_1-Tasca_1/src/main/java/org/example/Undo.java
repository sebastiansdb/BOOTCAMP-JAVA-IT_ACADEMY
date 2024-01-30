package org.example;

import java.util.ArrayList;
import java.util.List;

public class Undo
{
    /*
    En esta clase se usa el patron SINGLETONE.
    Una de sus ventajas es que permite tener una única instancia de una clase para usar en toda la app,
    así optimizamos recursos de memoria.
    La desventaja es que a veces no será suficiente y necesitaremos usar instancias de otra clase dentro de la app.
     */
    private static Undo instance;
    static List<String> commandHistory;

    /*
    Si el constructor es de ambito PRIVADO, cuando querramos instanciar un objeto, no dispondremos del operador "new".
    Es importante hacerlo privado para que se pueda instanciar un objeto sólo mediante el método getInstance()
     */
    private Undo() {
        commandHistory = new ArrayList<>();
    }

    /*
    - Sólo se podra crear una instnacia objeto de la clase SingleTone a través de este metodo.
    - La palabra reservada static hace posible acceder a este método mediante de Clase.método.
     */
    public static Undo getInstance(){
        if (instance == null)
        {
            instance = new Undo();
        }
        return instance;
    }

    public static void addCommand(String command)
    {
        commandHistory.add(command);
    }

    public static void removeLastCommand ()
    {
        if (commandHistory.isEmpty())
        {
            commandHistory.remove(commandHistory.size() -1 );
        }
    }

    public static void listCommands()
    {
        for (int i = 0; i<commandHistory.size(); i++)
        System.out.println("Command " + (i+1) + ": " + commandHistory.get(i));
    }
}
