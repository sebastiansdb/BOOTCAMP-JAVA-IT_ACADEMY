package org.example;

import org.example.Commands.AccelerateCommand;
import org.example.Commands.StartCommand;
import org.example.Commands.StopCommand;
import org.example.Invoker.Invoker;
import org.example.Operation_Classes.Bicycle;
import org.example.Operation_Classes.Car;
import org.example.Operation_Classes.Plane;
import org.example.Operation_Classes.Ship;

public class InitCommandPattern
{
    public static void init()
    {
        // Instanciando el invoker
        Invoker ivk = new Invoker();
        // Agregando los comandos
        ivk.takeOrder(new AccelerateCommand(new Ship("SHIP")));
        ivk.takeOrder(new StartCommand(new Ship ("SHIP")));
        ivk.takeOrder(new StopCommand(new Ship ("SHIP")));
        ivk.takeOrder(new AccelerateCommand(new Car("CAR")));
        ivk.takeOrder(new StartCommand(new Car ("CAR")));
        ivk.takeOrder(new StopCommand(new Car ("CAR")));
        ivk.takeOrder(new AccelerateCommand(new Bicycle("BIKE")));
        ivk.takeOrder(new StartCommand(new Bicycle ("BIKE")));
        ivk.takeOrder(new StopCommand(new Bicycle ("BIKE")));
        ivk.takeOrder(new AccelerateCommand(new Plane("PLANE")));
        ivk.takeOrder(new StartCommand(new Plane ("PLANE")));
        ivk.takeOrder(new StopCommand(new Plane ("PLANE")));
        // Ejecutando los comandos
        ivk.executeManeuvers();
    }
}
