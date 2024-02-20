package n3exercici1.services;

import n3exercici1.sales.TicketPrinter;
import n3exercici1.services.productsDAO.SaleDAO;
import n3exercici1.products.Product;
import n3exercici1.sales.Sale;
import n3exercici1.services.mongoDBDAO.MongoDBManager;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SalesManager {

    private static SalesManager salesManager;
    private boolean initSalesManager = false;
    private SaleDAO saleDAO;
    private double earnedMoney;

    private SalesManager(MongoDBManager manager) {
        try {
            this.saleDAO = manager.getSaleDAO();
            if (this.saleDAO.getAll().isEmpty()) System.out.println("This store doesn't contain a history of sales yet");
            else calculateTotalSalesValue();
            this.initSalesManager = true;
        } catch (NullPointerException e){
            this.initSalesManager = false;
        }
    }
    public static SalesManager getSalesManager(MongoDBManager manager) {
        if (salesManager == null) salesManager = new SalesManager(manager);
        return salesManager;
    }
    private void calculateTotalSalesValue(){
        this.earnedMoney = this.saleDAO.getAll().stream().mapToDouble(Sale::getSaleAmount).sum();
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
        addSale(new Sale(saleDAO.getLastID(), salePrice, date , getProductsFromCart(cart)));
    }
    private List<String> getProductsFromCart(List<Product> cart){
        return cart.stream().map(Product::toString).toList();
    }
    private void addSale(Sale sale) {
        int listSize = saleDAO.getAll().size();
        this.saleDAO.insert(sale);
        if (saleDAO.getAll().size()>listSize) {
            System.out.println("Sale registered.");
            updateEarnedMoney(sale);
        }
    }
    private void updateEarnedMoney(Sale sale){
        this.earnedMoney += sale.getSaleAmount();
    }
    public void printSalesHistory() {
        List<Sale> salesHistory = saleDAO.getAll();
        if (salesHistory.isEmpty()) System.out.println("The sales history is empty.");
        else salesHistory.forEach(System.out::println);
    }
    public void printTcket(int idSale){
        String ticketSale = saleDAO.getOne(idSale).generateTicket();
        if (ticketSale!=null) {
            System.out.println(ticketSale);
            TicketPrinter.printTicketToTXT(ticketSale, idSale);
            System.out.println("\nThe ticket has been printed.");
        } else {
            System.out.println("This sale's ID is not registered.");
        }
    }

}