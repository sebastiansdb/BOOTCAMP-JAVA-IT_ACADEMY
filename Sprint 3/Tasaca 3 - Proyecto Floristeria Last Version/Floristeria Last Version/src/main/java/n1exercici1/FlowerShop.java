package n1exercici1;

import n1exercici1.exceptions.ProductDoesNotExistsException;
import n1exercici1.products.*;
import n1exercici1.products.enums.MadeOf;
import n1exercici1.services.DAOService;
import n1exercici1.services.Sale;
import n1exercici1.services.SalesManager;
import n1exercici1.services.Stock;

import java.util.Comparator;
import java.util.List;

public class FlowerShop {

    private final DAOService service;
    private static FlowerShop flowerShop;
    private final String flowerShopName;
    private Stock stock;
    private SalesManager salesManager;
    private boolean shopExists;

    private FlowerShop (String flowerShopName){
        this.shopExists = true;
        this.service = new DAOService();
        this.flowerShopName = flowerShopName;
        if (service.compareShopName(flowerShopName)){
            this.stock = Stock.getStock(service, flowerShopName);
            this.salesManager = SalesManager.getSalesManager(service, flowerShopName);
        } else {
            this.shopExists = false;
        }
    }
    public static FlowerShop openFlowerShop(String flowerShopName){
        if (flowerShop == null) flowerShop = new FlowerShop(flowerShopName);
        return flowerShop;
    }
    public boolean seeIfFlowerShopExists(){
        return this.shopExists;
    }

    // TREE'S METHOD
    public void addTree(String treeName, double treePrice, double treeHeigth){
        if (findProduct(treeName)==null) stock.addProduct(new Tree(treeName, treePrice, treeHeigth));
        else System.out.println("This product is already stocked");
    }
    public void printTreeStock(){
        stock.getTreeStock().forEach(System.out::println);
    }
    public void removeTree(String productName){
        try{
            stock.removeProduct(findProduct(productName));
        } catch (NullPointerException e){
            System.out.println("This tree is not stocked.");
        }
    }

    // FLOWER'S METHOD
    public void addFlower(String flowerName, double flowerPrice, String flowerColor){
        if (findProduct(flowerName)==null) stock.addProduct(new Flower(flowerName, flowerPrice, flowerColor));
        else System.out.println("This product is already stocked");
    }
    public void printFlowerStock(){
        stock.getFlowerStock().forEach(System.out::println);
    }
    public void removeFlower(String productName){
        try {
            stock.removeProduct(findProduct(productName));
        } catch (NullPointerException e){
            System.out.println("This flower is not stocked");
        }
    }

    // DECORATION'S METHOD
    public void addDecoration(String decorationName, double decorationPrice, MadeOf madeof) {
        if (findProduct(decorationName)==null) stock.addProduct(new Decoration(decorationName, decorationPrice, madeof));
        else System.out.println("This product is already stocked");
    }
    public void printDecorationStock(){
        stock.getDecorationStock().forEach(System.out::println);
    }
    public void removeDecoration(String productName){
        try {
            stock.removeProduct(findProduct(productName));
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

    // SALE'S METHOD
    public Product getProduct (String productName) throws ProductDoesNotExistsException {
        Product product = findProduct(productName);
        if (product==null) throw new ProductDoesNotExistsException("This product doesn't exist or is out of stock.");
        else stock.removeProduct(product);
        return product;
    }
    public Product findProduct(String productName){
        return stock.findProduct(productName);
    }
    public void returnProduct (List<Product> cart){
        cart.forEach(stock::addProduct);
    }
    public void processSale(List<Product> cart){
        double salePrice = cart.stream().mapToDouble(Product::getPrice).sum();
        salesManager.manageTheCart(cart, salePrice);
    }
    public void printSalesHistory(){
        salesManager.printSalesHistory();
    }
    public void printEarnedMoney(){
        System.out.println("The store's earned money is " + salesManager.getEarnedMoney() + "€.");
    }
    public void printSale(int idSale){
        System.out.println(salesManager.getSalesHistoryList().get(idSale));
    }
    public void printSaleTicket(int idSale){
        salesManager.printTcket(idSale);
    }

    // WRAP UP METHOD
    public void saveChanges(){
        List<Product> productList = stock.getProductStock();
        productList.sort(Comparator.comparingInt(Product::getIdProduct));
        List<Sale> saleList = salesManager.getSalesHistoryList();

        service.exportProductList(productList, this.flowerShopName);
        service.exportSaleList(saleList, this.flowerShopName);
    }

}
