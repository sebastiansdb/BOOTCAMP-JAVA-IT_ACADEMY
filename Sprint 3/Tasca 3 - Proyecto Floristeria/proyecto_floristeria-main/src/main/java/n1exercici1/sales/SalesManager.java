package n1exercici1.sales;

import n1exercici1.services.DAOService;

import java.util.List;

public class SalesManager {
    private List<Sale> saleList;
    private DAOService service;
    private static SalesManager instance;

    private SalesManager() { // Esto no hace falta ponerlo, java lo poner por defecto.

    }

    public static SalesManager getInstance() {
        if (instance == null) {
            instance = new SalesManager();
        }
        return instance;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }
    public void addSale(Sale sale) {
        saleList.add(sale);
    }
    public void printSales(List<Sale> saleList) {
        saleList.forEach(System.out::println);
    }
    public void totalSalesValue(List<Sale> saleList){
        double salePrice = 0;
        if (!saleList.isEmpty()){
            for (Sale sale : saleList){
                salePrice += sale.getSaleAmount(); // aqui habia un metodo llamado "getSalePrice", yo lo tenía creado
                // como "getSaleAmount", lo cambié
            }
//            saleList.forEach(sale -> {
//                salePrice += sale.getSalePrice();
//            });
            System.out.println("Precio de venta: " + salePrice);
        }

    }
    public void printTcket(List<Sale> saleList){

    }
}
