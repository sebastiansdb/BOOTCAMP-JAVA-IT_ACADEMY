package org.example;

public class Flower extends Product{

    private int idTree;
    private int idCounter;
    private String color;

    public Flower(String name, double price, String color){
        super(name, price);
        this.color = color;
        this.idTree += idCounter++;
    }

    public int getIdTree(){
        return this.idTree;
    }
    public String getColor(){
        return this.color;
    }
    public void setColor(String color){
        this.color = color;
    }

    public String toString(){
        return "Flower     " + getName() + "    " + this.color + "      " + getPrice() + "€.";
    }

}
