package n1exercici1.products;

public class Flower extends Product{

    private String color;

    public Flower(String name, double price, String color){
        super(name, price);
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
    public void setColor(String color){
        this.color = color;
    }

    public String toString(){
        return "Flower: " + getName() + ", Color: " + this.color + ", Price: " + getPrice() + "€.";
    }

}
