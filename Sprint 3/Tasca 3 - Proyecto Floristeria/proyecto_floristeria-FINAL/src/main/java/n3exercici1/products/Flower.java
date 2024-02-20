package n3exercici1.products;

public class Flower extends Product {

    private final String color;

    public Flower(String name, double price, String color){
        super(name, price);
        this.color = color;
    }
    public Flower (int id, String name, double price, String color){
        super(id, name, price);
        this.color = color;
    }

    public String getType(){
        return "FLOWER";
    }
    public String getAttribute(){
        return this.color;
    }
    public String toString(){
        return "Flower: " + getName() + ", Color: " + this.color + ", Price: " + getPrice() + "€.";
    }
    public String toTable(){
        return "Flower     " + getName() + "    " + this.color + "      " + getPrice() + "€.";
    }

}
