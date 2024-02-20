package n3exercici1.products;

public class Tree extends Product {

    private final double height;

    public Tree (String name, double price, double height){
        super(name, price);
        this.height = height;
    }
    public Tree (int id, String name, double price, double height){
        super(id, name, price);
        this.height = height;
    }

    public String getType(){
        return "TREE";
    }
    public String getAttribute(){
        return String.valueOf(this.height);
    }
    public String toString(){
        return "Tree: " + getName() + ", Height: " + this.height + "m, Price: " + getPrice() + "€.";
    }
    public String toTable(){
        return "Tree       " + getName() + "  " + this.height + "             " + getPrice() + "€.";
    }

}
