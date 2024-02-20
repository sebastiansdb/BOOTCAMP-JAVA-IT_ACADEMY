package n2exercici1.services;

import n2exercici1.products.Product;
import n2exercici1.sales.Sale;
import n2exercici1.sales.TicketPrinter;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SalesManager {

    private static SalesManager salesManager;
    private boolean initSalesManager = false;
    private List<Sale> salesHistoryList;
    private double earnedMoney;

    private SalesManager(DAOService service) {
        try {
            this.salesHistoryList = service.getSaleList();
            if (salesHistoryList.isEmpty()) System.out.println("This store doesn't contain a history of sales yet");
            calculateTotalSalesValue();
            this.initSalesManager = true;
        } catch (NullPointerException e){
            this.initSalesManager = false;
        }
    }
    public static SalesManager getSalesManager(DAOService service) {
        if (salesManager == null) salesManager = new SalesManager(service);
        return salesManager;
    }
    private void calculateTotalSalesValue(){
        this.earnedMoney = this.salesHistoryList.stream().mapToDouble(Sale::getSaleAmount).sum();
    }

    public List<Sale> getSalesHistoryList() {
        return this.salesHistoryList;
    }
    public double getEarnedMoney(){
        return this.earnedMoney;
    }
    public boolean getInitSalesManager(){
        return initSalesManager;
    }

    public void manageTheCart(List<Product> cart, double salePrice){
        cart.sort(Comparator.comparingInt(Product::getIdProduct));
        Date date = (Calendar.getInstance().getTime());
        addSale(new Sale(salePrice, date , getProductsFromCart(cart)));
    }
    private List<String> getProductsFromCart(List<Product> cart){
        return cart.stream().map(Product::toTable).toList();
    }
    private void addSale(Sale sale) {
        this.salesHistoryList.add(sale);
        System.out.println("Sale registered.");
        updateEarnedMoney(sale);
    }
    private void updateEarnedMoney(Sale sale){
        this.earnedMoney += sale.getSaleAmount();
    }
    public void printSalesHistory() {
        this.salesHistoryList.forEach(System.out::println);
    }
    public void printTcket(int idSale){
        String ticketSale = this.salesHistoryList.get(idSale-1).generateTicket();
        System.out.println(ticketSale);
        TicketPrinter.printTicketToTXT(ticketSale, idSale);
        System.out.println("\nThe ticket has been printed.");
    }
}
