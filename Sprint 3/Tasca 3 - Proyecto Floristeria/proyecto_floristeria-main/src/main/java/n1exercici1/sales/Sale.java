package n1exercici1.sales;

import n1exercici1.products.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Sale {
    private int idSale;
    private static int idSaleCounter; // Supongo que se inicializa en cero por default.
    private double saleAmount; // final??

    private final Date saleDate;
    private static HashMap<String, String> ticketPrinter;
    private static List<Product> saleProductsList;

    static {
        ticketPrinter = new HashMap<>();
    }
    public Sale(double saleAmount, Date saleDate, List<Product> saleProductsList) {
        this.idSale += idSaleCounter++;
        this.saleAmount = saleAmount;
        this.saleDate = saleDate;
        this.saleProductsList = saleProductsList;
    }

    public double getSaleAmount() {
        return this.saleAmount;
    }

    public List<Product> getSaleProductsList() {
        return this.saleProductsList;
    }

    public String generateTicket() {

        List<String> ticketLines = new ArrayList<>();
        String ticketHeadline = "Ticket nº " + this.idSale + "\n" + "Date: " + this.saleDate + "\n" +
                "Product    Name    Description     Price" + "\n";
        for (Product product : saleProductsList) { // int i = 0; i < saleProductsList.size(); i++
            ticketLines.add(product.toString());
        }
        return ticketHeadline + String.join("\n", ticketLines);
    }
}

