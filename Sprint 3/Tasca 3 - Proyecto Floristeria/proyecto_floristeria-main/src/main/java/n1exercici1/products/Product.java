package n1exercici1.products;

public abstract class Product {

    private int idProduct;
    private static int idCounter;
    private String name;
    private double price;

    public Product (String name, double price){
        this.name = name;
        this.price = price;
        this.idProduct += idCounter++; //VER SI FUNCIONA O HAY QUE EXTRAER EL idCounter++;
    }

    public int getIdProduct(){
        return this.idProduct;
    }
    public String getName(){
        return this.name;
    }
    public void setName (String name){
        this.name = name;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public abstract String toString();

}
