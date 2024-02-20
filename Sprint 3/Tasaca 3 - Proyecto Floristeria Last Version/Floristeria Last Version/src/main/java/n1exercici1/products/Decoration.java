package n1exercici1.products;

import n1exercici1.products.enums.MadeOf;

public class Decoration extends Product{

    private final String material;

    public Decoration(String name, double price, MadeOf madeOf){
        super(name, price);
        this.material = madeOf.name().toLowerCase();
    }

    public String getMaterial(){
        return this.material;
    }
    public String toString(){
        return "Decoration: " + getName() + ", Made of: " + this.material + ", Price: " + getPrice() + "€.";
    }
    public String decorationTable(){
        return "Decoration  " + getName() + "       " + this.material + "       " + getPrice() + "€.";
    }

}
