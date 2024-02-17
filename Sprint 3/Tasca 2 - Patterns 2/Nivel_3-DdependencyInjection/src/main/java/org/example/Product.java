package org.example;

public class Product {
    private String name;

    private double price; // dollar price

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void showProductPrice(MoneyConverter currency){
        System.out.println("Poducto: " + this.name + ". Precio: " +
                this.getPrice()*currency.getCurrencyRate() + currency.getCurrencySymbol());
    }
}
