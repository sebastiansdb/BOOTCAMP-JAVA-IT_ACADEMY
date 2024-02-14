package org.example;

public class Tree extends Product{

    private int idTree;
    private static int idCounter;
    private double height;

    public Tree (String name, double price, double height){
        super(name, price);
        this.height = height;
        this.idTree += idCounter++; //VER SI FUNCIONA O HAY QUE EXTRAER EL idCounter++;
    }

    public int getIdTree(){
        return this.idTree;
    }
    public double getHeight(){
        return this.height;
    }
    public void setHeight(double height){
        this.height = height;
    }

    public String toString(){
        return "Tree       " + getName() + "  " + this.height + "             " + getPrice() + "€.";
    }

}
