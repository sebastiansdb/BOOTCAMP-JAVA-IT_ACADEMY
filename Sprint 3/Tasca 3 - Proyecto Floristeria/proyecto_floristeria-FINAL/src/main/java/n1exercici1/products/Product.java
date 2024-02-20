package n1exercici1.products;

public abstract class Product {

    private int idProduct;
    private static int idCounter=1;
    private final String name;
    private final double price;

    public Product (String name, double price){
        this.name = name;
        this.price = price;
        this.idProduct += idCounter++;
    }

    public int getIdProduct(){
        return this.idProduct;
    }

    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public abstract String getAttribute();
    public abstract String getType();
    public abstract String toString();
    public abstract String toTable();

}
