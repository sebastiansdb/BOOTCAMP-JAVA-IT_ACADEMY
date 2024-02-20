package n1exercici1.products;

public abstract class Product {

    private int idProduct;
    private static int idCounter;
    private final String name;
    private final double price;

    public Product (String name, double price){
        this.name = name;
        this.price = price;
        this.idProduct += idCounter++; //VER SI FUNCIONA O HAY QUE EXTRAER EL idCounter++;
    }

    public int getIdProduct(){
        return this.idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public abstract String toString();

}
