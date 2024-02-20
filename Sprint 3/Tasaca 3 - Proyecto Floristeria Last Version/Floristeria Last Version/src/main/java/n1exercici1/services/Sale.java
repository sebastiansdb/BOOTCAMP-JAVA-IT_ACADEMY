package n1exercici1.services;

import java.util.List;

public class Sale {

    private int idSale;
    private static int idCounter;
    private final double salePrice;
    private final List<String> productList;

    public Sale (double salePrice, List<String> productList){
        this.salePrice = salePrice;
        this.productList = productList;
        this.idSale += idCounter++;
    }

    public int getIdSale(){
        return this.idSale;
    }
    public double getSalePrice(){
        return this.salePrice;
    }
    public List<String> getProductList(){
        return this.productList;
    }
    public String toString(){
        StringBuilder productsSold = new StringBuilder();
        for (String product : this.productList){
            productsSold.append(product).append("\n");
        }
        return "Sale: " + this.idSale + ", Products sold:\n" +
                productsSold + "\nTotal sell price: " + this.salePrice + "€";
    }
}
