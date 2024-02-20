package n1exercici1.services.connections;

import n1exercici1.products.Decoration;
import n1exercici1.products.Flower;
import n1exercici1.products.Product;
import n1exercici1.products.Tree;
import n1exercici1.products.enums.MadeOf;
import n1exercici1.sales.Sale;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtBBDD {

    private static String stockDirectory;
    private static String salesDirectory;

    private static List<String[]> getTxtLines(String fileToRead) {
        List<String[]> txtLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) txtLines.add(line.split(";"));
            }
        } catch (IOException e) {
            txtLines = null;
        }
        return txtLines;
    }
    public static List<Product> getProductList(String txtFileName){
        stockDirectory = txtFileName;
        List<Product> productList = new ArrayList<>();
        Product newProduct;
        for (String[] s : getTxtLines(txtFileName)) {
            newProduct = createProduct(s);
            if (newProduct!=null) productList.add(newProduct);
        }
        return productList;
    }
    private static Product createProduct(String[] s){
        Product newProduct = null;
        switch (s[1].toUpperCase()){
            case "FLOWER" -> newProduct = new Flower(s[2],Double.parseDouble(s[3]),s[4]);
            case "TREE" -> newProduct = new Tree(s[2],Double.parseDouble(s[3]),Double.parseDouble(s[4]));
            case "DECORATION" -> {
                if (s[4].equalsIgnoreCase("WOOD")){
                    newProduct = new Decoration(s[2],Double.parseDouble(s[3]), MadeOf.WOOD);
                } else {
                    newProduct = new Decoration (s[2],Double.parseDouble(s[3]), MadeOf.PLASTIC);
                }
            }
        }
        return newProduct;
    }
    public static List<Sale> getSaleList (String txtFileName){
        salesDirectory = txtFileName;
        List<Sale> saleListFromTxt = new ArrayList<>();
        for (String[] s : getTxtLines(txtFileName)){
            List<String> productListFromTxt = new ArrayList<>(Arrays.asList(s).subList(3, s.length));
            saleListFromTxt.add(new Sale(Double.parseDouble(s[1]), Date.valueOf(s[2]), productListFromTxt));
        }
        return saleListFromTxt;
    }


    public static void returnProductList(List<Product> productList){
        try (PrintWriter writer = new PrintWriter(new FileWriter(stockDirectory))){
            for (Product product : productList) {
                writer.println(product.getIdProduct() + ";" + product.getType() + ";" + product.getName() + ";" + product.getPrice() + ";" + product.getAttribute());
            }
        } catch (IOException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }
    public static void returnSaleList(List<Sale> saleList){
        try (PrintWriter writer = new PrintWriter(new FileWriter(salesDirectory))){
            for (Sale sale : saleList){
                writer.print(sale.getIdSale()+ ";" + sale.getSaleAmount() + ";" + sale.getSaleDate());
                for (String product : sale.getProductList()){
                    writer.print(";" + product);
                }
                writer.println();
            }
        } catch (IOException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }

}
