package n1exercici1.connections;

import n1exercici1.products.Decoration;
import n1exercici1.products.Flower;
import n1exercici1.products.Product;
import n1exercici1.products.Tree;
import n1exercici1.products.enums.MadeOf;
import n1exercici1.services.Sale;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtBBDD {

    public static List<Product> getProductListFromTxt(String txtFileName){
        List<Product> productListFromTxt = new ArrayList<>();
        for (String[] s : TxtBBDD.getTxtLines(txtFileName)) {
            if (s[1].equalsIgnoreCase("FLOWER")) {
                productListFromTxt.add(new Flower(s[2],Double.parseDouble(s[3]),s[4]));
            } else if (s[1].equalsIgnoreCase("TREE")){
                productListFromTxt.add(new Tree(s[2],Double.parseDouble(s[3]),Double.parseDouble(s[4])));
            } else {
                if (s[4].equalsIgnoreCase("WOOD")){
                    productListFromTxt.add(new Decoration(s[2],Double.parseDouble(s[3]), MadeOf.WOOD));
                } else {
                    productListFromTxt.add(new Decoration (s[2],Double.parseDouble(s[3]), MadeOf.PLASTIC));
                }
            }
        }
        return productListFromTxt;
    }
    public static List<Sale> getSaleListFromTxt(String txtFileName){
        List<Sale> saleListFromTxt = new ArrayList<>();
        List<String> productListFromTxt = new ArrayList<>();
        for (String[] s : TxtBBDD.getTxtLines(txtFileName)){
            productListFromTxt.addAll(Arrays.asList(s));
            productListFromTxt.removeFirst();
            productListFromTxt.removeFirst();
            saleListFromTxt.add(new Sale(Integer.parseInt(s[1]),productListFromTxt));
        }
        return saleListFromTxt;
    }
    private static List<String[]> getTxtLines(String fileToRead) {
        String wordsChain;
        List<String[]> txtLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            while ((wordsChain = br.readLine()) != null) {
                txtLines.add(wordsChain.split(";"));
            }
        } catch (IOException e) {
            System.out.println("Database txt doesn't exists or wrong path provided");
        }
        return txtLines;
    }

    public static void printSaleToTXT(Sale sale){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Ticket"+sale.getIdSale()+".txt").getAbsoluteFile()))){
            bw.write(sale.toString());
        } catch (IOException e){
            System.out.println("There was an error printing the ticket at the specified path.");
        }
    }

    public static void returnProductList(List<Product> productList, String fileToSave){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileToSave).getAbsoluteFile(), true))){
            String productAtribute;
            for (Product product : productList){
                switch (product){
                    case Tree tree -> productAtribute = String.valueOf(tree.getHeight());
                    case Flower flower -> productAtribute = flower.getColor();
                    case Decoration decoration -> productAtribute = decoration.getMaterial();
                    default -> productAtribute="";
                }
                bw.write(product.getIdProduct() + ";" + product.getClass() + ";" + product.getPrice() + ";" + productAtribute);
                bw.newLine();
            }
        } catch (IOException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }
    public static void returnSaleList(List<Sale> saleList, String fileToSave){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileToSave).getAbsoluteFile(), true))){
            for (Sale sale : saleList){
                bw.write(sale.getIdSale()+ ";" + sale.getSalePrice());
                for (String product : sale.getProductList()){
                    bw.write(";" + product);
                }
                bw.newLine();
            }
        } catch (IOException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }

}
