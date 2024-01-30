package org.example;

public class Init
{
    public static void init()
    {
        Undo obj = Undo.getInstance();
        // Agrego comandos
        System.out.println("Comandos agregados. \n");
        obj.addCommand("Borrar");
        obj.addCommand("Adelante");
        obj.addCommand("Seguir");
        obj.addCommand("Atras");
        // Muestro comandos agregados
        System.out.println("\nMuestro comandos: \n");
        obj.listCommands();
        // Borro el ultimo comando
        System.out.println("\nBorro el ultimo comando.\n");
        obj.removeLastCommand();
        // Vuelvo a mostrar
        System.out.println("Vuelvo a mostrar: \n");
        obj.listCommands();
    }
}
