package n1exercici1.services;

import n1exercici1.services.connections.TxtBBDD;
import n1exercici1.products.Product;
import n1exercici1.sales.Sale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DAOService {

    private static final String directory = "src/main/resources/";
    private static String productFileName;
    private static String saleFileName;

    public boolean checkShopName (String shopName){
        boolean shopExists;
        try (Stream<Path> files = Files.walk(Paths.get(directory))){
            shopExists = files.map(Path::getFileName).map(Path::toString).anyMatch(fileName -> fileName.toLowerCase().contains(shopName.toLowerCase()));
            productFileName = shopName+"ProductList.txt";
            saleFileName = shopName+"SaleList.txt";
        } catch (IOException e){
            shopExists = false;
        }
        return shopExists;
    }

    public List<Product> getProductList (){
        return TxtBBDD.getProductList(directory + productFileName);
    }
    public List<Sale> getSaleList (){
        return TxtBBDD.getSaleList(directory + saleFileName);
    }

    public void exportProductList (List<Product> productList){
        TxtBBDD.returnProductList(productList);
    }
    public void exportSaleList (List<Sale> saleList){
        TxtBBDD.returnSaleList(saleList);
    }

}
