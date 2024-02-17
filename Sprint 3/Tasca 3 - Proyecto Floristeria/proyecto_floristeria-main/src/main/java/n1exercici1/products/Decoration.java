package n1exercici1.products;

import n1exercici1.products.enums.MadeOf;

public class Decoration extends Product{

    private String material;

    public Decoration(String name, double price, MadeOf madeOf){
        super(name, price);
        this.material = madeOf.name().toLowerCase();
    }

    public String getMaterial(){
        return this.material;
    }
    public void setMaterial(MadeOf madeOf){
        this.material = madeOf.name().toLowerCase();
    }
    public String toString(){
        return "Decoration  " + getName() + "       " + getMaterial() + "       " + getPrice() + "€.";
    }

}
