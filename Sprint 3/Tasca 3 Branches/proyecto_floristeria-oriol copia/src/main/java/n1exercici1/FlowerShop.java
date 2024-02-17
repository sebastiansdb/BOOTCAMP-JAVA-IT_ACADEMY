package n1exercici1;

import n1exercici1.products.*;
import n1exercici1.products.enums.MadeOf;
import n1exercici1.services.DAOService;
import n1exercici1.services.Stock;

import java.util.Comparator;
import java.util.List;

public class FlowerShop {

    private final DAOService service;
    private static FlowerShop flowerShop;
    private final String flowerShopName;
    private static Stock stock;

    private FlowerShop (String flowerShopName){
        this.service = new DAOService();
        this.flowerShopName = flowerShopName;
        stock = Stock.getStock(flowerShopName, service);
        //FALTAN VENTAS
    }
    public static FlowerShop openFlowerShop(String flowerShopName){
        if (flowerShop == null) flowerShop = new FlowerShop(flowerShopName);
        return flowerShop;
    }
    public boolean seeIfFlowerShopExists(){
        return stock.getProductStock()!=null;
    }

    // TREE'S METHOD
    public void addTree(String treeName, double treePrice, double treeHeigth){
        stock.addProduct(new Tree(treeName, treePrice, treeHeigth));
    }
    public void printTreeStock(){
        stock.getTreeStock().forEach(System.out::println);
    }
    public Tree findTree(String productName){
        return stock.getTreeStock().stream().filter(tree -> tree.getName().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }
    public void removeTree(String productName){
        try{
            stock.removeProduct(findTree(productName));
        } catch (NullPointerException e){
            System.out.println("This tree is not stocked.");
        }
    }

    // FLOWER'S METHOD
    public void addFlower(String flowerName, double flowerPrice, String flowerColor){
        stock.addProduct(new Flower(flowerName, flowerPrice, flowerColor));
    }
    public void printFlowerStock(){
        stock.getFlowerStock().forEach(System.out::println);
    }
    public Flower findFlower(String productName){
        return stock.getFlowerStock().stream().filter(flower -> flower.getName().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }
    public void removeFlower(String productName){
        try {
            stock.removeProduct(findFlower(productName));
        } catch (NullPointerException e){
            System.out.println("This flower is not stocked");
        }
    }

    // DECORATION'S METHOD
    public void addDecoration(String decorationName, double decorationPrice, MadeOf madeof) {
        stock.addProduct(new Decoration(decorationName, decorationPrice, madeof));
    }
    public void printDecorationStock(){
        stock.getDecorationStock().forEach(System.out::println);
    }
    public Decoration findDecoration(String productName){
        return stock.getDecorationStock().stream().filter(decoration -> decoration.getName().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }
    public void removeDecoration(String productName){
        try {
            stock.removeProduct(findDecoration(productName));
        } catch (NullPointerException e){
            System.out.println("This decoration is not stocked");
        }
    }

    public void printStock(){
        System.out.println("Tree's quantity: " + stock.getTreeStock().size() + ".\n" +
                "Flower's quantity: " + stock.getFlowerStock().size() + ".\n" +
                "Decoration's quantity: " + stock.getDecorationStock().size() + ".");
    }
    public void printShopValue(){
        System.out.println("The actual value of the " + flowerShopName + " shop is " + stock.getStockValue() + "€.");
    }

    public void saveProductList(){
        List<Product> productList = stock.getProductStock();
        productList.sort(Comparator.comparingInt(Product::getIdProduct));
        service.returnProductList(productList);
    }

}
