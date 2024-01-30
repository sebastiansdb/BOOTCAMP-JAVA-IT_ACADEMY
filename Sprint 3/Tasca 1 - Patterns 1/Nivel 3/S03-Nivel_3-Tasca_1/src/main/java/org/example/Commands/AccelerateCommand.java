package org.example.Commands;

import org.example.Operation_Classes.Vehicle;

public class AccelerateCommand implements IOperation
{
    private final Vehicle vehicle;
    public AccelerateCommand(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    public void execute()
    {
        vehicle.accelerate();
    }
}
