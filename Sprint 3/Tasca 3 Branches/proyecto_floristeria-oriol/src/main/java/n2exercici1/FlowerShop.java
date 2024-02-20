package n2exercici1;

import n2exercici1.exceptions.ProductDoesNotExistsException;
import n2exercici1.products.Decoration;
import n2exercici1.products.Flower;
import n2exercici1.products.Product;
import n2exercici1.products.Tree;
import n2exercici1.products.enums.MadeOf;
import n2exercici1.sales.Sale;
import n2exercici1.services.DAOService;
import n2exercici1.services.SalesManager;
import n2exercici1.services.Stock;

import java.util.Comparator;
import java.util.List;

public class FlowerShop {

    private final DAOService service;
    private static FlowerShop flowerShop;
    private final String flowerShopName;
    private Stock stock;
    private SalesManager salesManager;
    private boolean shopExists = true;

    private FlowerShop (String flowerShopName){
        this.service = new DAOService();
        this.flowerShopName = flowerShopName;
        if (service.checkShopName(flowerShopName)){
            this.stock = Stock.getStock(service);
            this.salesManager = SalesManager.getSalesManager(service);
            if (!(stock.getInitStock() && salesManager.getInitSalesManager())){
                this.shopExists = false;
            }
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
        stock.addProduct(new Tree(treeName, treePrice, treeHeigth));
    }
    public void printTreeStock(){
        if (stock.getTreeStock().isEmpty()){
            System.out.println("There is not trees stocked");
        } else {
            stock.getTreeStock().forEach(System.out::println);
        }
    }
    public void removeTree(String productName){
        try{
            stock.removeProduct(findProduct(productName, "TREE"));
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
    public void removeFlower(String productName){
        try {
            stock.removeProduct(findProduct(productName, "FLOWER"));
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
    public void removeDecoration(String productName){
        try {
            stock.removeProduct(findProduct(productName, "DECORATION"));
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
        System.out.println("The actual value of the " + flowerShopName + " shop is " + Math.round(stock.getStockValue()*100.0)/100.0 + "€.");
    }

    // SALE'S METHOD
    public Product getProduct (String productName, String productType) throws ProductDoesNotExistsException {
        Product product = findProduct(productName, productType);
        if (product==null) throw new ProductDoesNotExistsException("This product doesn't exist or is out of stock.");
        else stock.removeProduct(product);
        return product;
    }
    public Product findProduct(String productName, String productType){
        return stock.findProduct(productName, productType);
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
    public void printSaleTicket(int idSale){
        salesManager.printTcket(idSale);
    }

    // WRAP UP METHOD
    public void saveChanges(){
        List<Product> productList = stock.getProductStock();
        productList.sort(Comparator.comparingInt(Product::getIdProduct));
        List<Sale> saleList = salesManager.getSalesHistoryList();
        service.exportProductList(productList);
        service.exportSaleList(saleList);
    }

}
