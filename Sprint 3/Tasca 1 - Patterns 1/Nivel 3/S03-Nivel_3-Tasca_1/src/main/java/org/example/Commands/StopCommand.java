package org.example.Commands;

import org.example.Operation_Classes.Vehicle;

public class StopCommand implements IOperation
{
    private final Vehicle vehicle;

    public StopCommand(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    public void execute()
    {
        vehicle.stop();
    }
}
