package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    public Tree getTreesSold(){
        for (Product product : this.getSaleProductsList()){
            if (product instanceof Tree){
                return (Tree) product;
            }
        }
        this.saleProductsList.getClass().getSimpleName();
        return null;
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

//        System.out.println("Ticket nº " + this.idSale + "\n" +
//                "Product    Description   Quantity     Unit Price    Total Price" + "\n");
//        for (Product product : saleProductsList){
//            System.out.println(product.toString());
//        }

//        return "Ticket nº " + this.idSale + "\n" +
//                "Product    Description   Quantity     Unit Price    Total Price" + "\n" +




//                            "Flower"          "\n" +        "1"+          "1"+           "10"
//                            "Decoration"          "\n" +        "1"+          "1"+           "10"            );

}

