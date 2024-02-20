package n2exercici1.services;

import n2exercici1.connections.SQLBBDD;
import n2exercici1.products.Product;
import n2exercici1.sales.Sale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DAOService {

    private static final String directory = "src/main/resources/";

    public boolean checkShopName (String shopName){
        boolean exists;
        try (Stream<Path> files = Files.walk(Paths.get(directory))){
            exists = files.map(Path::getFileName).map(Path::toString).anyMatch(fileName -> fileName.toLowerCase().contains(shopName.toLowerCase()));
            SQLBBDD.setSqlFileName(shopName+"flowershop");
        } catch (IOException e){
            exists = false;
        }
        return exists;
    }

    public List<Product> getProductList (){
        return SQLBBDD.getProductList();
    }
    public List<Sale> getSaleList (){
        return SQLBBDD.getSaleList();
    }

    public void exportProductList (List<Product> productList){
        SQLBBDD.returnProductList(productList);
    }
    public void exportSaleList (List<Sale> saleList){
        SQLBBDD.returnSaleList(saleList);
    }

}
