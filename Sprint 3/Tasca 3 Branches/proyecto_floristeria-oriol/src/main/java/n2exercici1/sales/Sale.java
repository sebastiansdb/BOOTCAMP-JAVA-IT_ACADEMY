package n2exercici1.sales;

import java.util.Date;
import java.util.List;

public class Sale {

    private int idSale;
    private static int idCounter = 1;
    private final double saleAmount;
    private final Date saleDate;
    private final List<String> productList;

    public Sale (double salePrice, Date saleDate, List<String> productList){
        this.saleAmount = salePrice;
        this.saleDate = saleDate;
        this.productList = productList;
        this.idSale += idCounter++;
    }

    public int getIdSale(){
        return this.idSale;
    }
    public double getSaleAmount(){
        return this.saleAmount;
    }
    public Date getSaleDate(){
        return saleDate;
    }
    public List<String> getProductList(){
        return this.productList;
    }

    public String generateTicket() {
        String ticketHeadline = "Ticket nº " + this.idSale + "\n" + "Date: " + this.saleDate + "\n" +
                "Product    Name    Description     Price" + "\n";
        return ticketHeadline + String.join("\n", this.productList);
    }
    public String toString(){
        StringBuilder productsSold = new StringBuilder();
        for (String product : this.productList){
            productsSold.append(product).append("\n");
        }
        return "Sale: " + this.idSale + ", Date: " + this.saleDate + ", Products sold:\n" +
                productsSold + "Total sell price: " + this.saleAmount + "€.\n";
    }
}
