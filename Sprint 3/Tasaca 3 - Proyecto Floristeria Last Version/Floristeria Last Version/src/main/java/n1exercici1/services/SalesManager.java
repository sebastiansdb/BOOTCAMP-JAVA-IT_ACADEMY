package n1exercici1.services;

import n1exercici1.connections.TxtBBDD;
import n1exercici1.products.Product;

import java.util.Comparator;
import java.util.List;

public class SalesManager {

    private static SalesManager salesManager;
    private final List<Sale> salesHistoryList;
    private double earnedMoney;

    private SalesManager(DAOService service, String flowerShopName) {
        this.salesHistoryList = service.getSaleList(flowerShopName);
        this.earnedMoney = calculateTotalSalesValue();
    }
    public static SalesManager getSalesManager(DAOService service, String flowerShopName) {
        if (salesManager == null) salesManager = new SalesManager(service, flowerShopName);
        return salesManager;
    }
    private double calculateTotalSalesValue(){
        return this.salesHistoryList.stream().mapToDouble(Sale::getSalePrice).sum();
    }

    public List<Sale> getSalesHistoryList() {
        return this.salesHistoryList;
    }
    public double getEarnedMoney(){
        return this.earnedMoney;
    }

    public void manageTheCart(List<Product> cart, double salePrice){
        cart.sort(Comparator.comparingInt(Product::getIdProduct));
        addSale(new Sale(salePrice, getProductsFromCart(cart)));
    }
    private List<String> getProductsFromCart(List<Product> cart){
        return cart.stream().map(Product::toString).toList();
    }
    private void addSale(Sale sale) {
        this.salesHistoryList.add(sale);
        System.out.println("Sale registered.");
        updateEarnedMoney(sale);
    }
    private void updateEarnedMoney(Sale sale){
        this.earnedMoney += sale.getSalePrice();
    }
    public void printSalesHistory() {
        this.salesHistoryList.forEach(System.out::println);
    }
    public void printTcket(int idSale){
        Sale ticketSale = this.salesHistoryList.get(idSale);
        System.out.println(ticketSale);
        TxtBBDD.printSaleToTXT(ticketSale);
        System.out.println("\nThe ticket has been printed.");
    }
}
