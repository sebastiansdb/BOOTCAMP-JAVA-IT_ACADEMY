package n1exercici1.services;

import n1exercici1.connections.TxtBBDD;
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

    public boolean checkShopName (String shopName){
        boolean exists;
        try (Stream<Path> files = Files.walk(Paths.get(directory))){
            exists = files.map(Path::getFileName).map(Path::toString).anyMatch(fileName -> fileName.toLowerCase().
                    contains(shopName.toLowerCase()));
        } catch (IOException e){
            exists = false;
        }
        return exists;
    }

    public List<Product> getProductList (String flowerShopName){
        return TxtBBDD.getProductList(directory + flowerShopName + "ProductList.txt");
    }
    public List<Sale> getSaleList (String flowerShopName){
        return TxtBBDD.getSaleList(directory + flowerShopName + "SaleList.txt");
    }

    public void exportProductList (List<Product> productList){
        TxtBBDD.returnProductList(productList);
    }
    public void exportSaleList (List<Sale> saleList){
        TxtBBDD.returnSaleList(saleList);
    }

}
