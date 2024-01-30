package org.example.Commands;

import org.example.Operation_Classes.Vehicle;

public class StartCommand implements IOperation
{
    private final Vehicle vehicle;

    public StartCommand(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    public void execute()
    {
        vehicle.start();
    }
}
