package org.example.Operation_Classes;

public abstract class Vehicle
{
    String name;

    public Vehicle(String name)
    {
        this.name = name;
    }
    public void accelerate()
    {
        System.out.println("The " + this.name + " is Accelerating");
    }

    public void stop()
    {
        System.out.println("The " + this.name + " is Stopping");
    }

    public void start()
    {
        System.out.println("The " + this.name + " is Starting");
    }
}
