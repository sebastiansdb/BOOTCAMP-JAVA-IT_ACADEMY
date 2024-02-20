package n1exercici1.products;

public class Tree extends Product{

    private final double height;

    public Tree (String name, double price, double height){
        super(name, price);
        this.height = height;
    }

    public double getHeight(){
        return this.height;
    }
    public String toString(){
        return "Tree: " + getName() + ", Height: " + this.height + ", Price: " + getPrice() + "€.";
    }
    public String treeTable(){
        return "Tree       " + getName() + "  " + this.height + "             " + getPrice() + "€.";
    }

}
