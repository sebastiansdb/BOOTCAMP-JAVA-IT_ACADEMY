package n1exercici1.products;

public class Tree extends Product{

    private double height;

    public Tree (String name, double price, double height){
        super(name, price);
        this.height = height;
    }

    public double getHeight(){
        return this.height;
    }
    public void setHeight(double height){
        this.height = height;
    }

    public String toString(){
        return "Tree: " + getName() + ", Height: " + this.height + ", Price: " + getPrice() + "€.";
    }

}
