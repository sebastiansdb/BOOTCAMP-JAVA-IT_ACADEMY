package n1exercici1.services;

import n1exercici1.connections.FakeBBDD;
import n1exercici1.connections.TxtBBDD;
import n1exercici1.products.Product;

import java.util.List;

public class DAOService {

    private List<Product> productList;
    private List<Sale> saleList;
    private List<String> flowerShopList;

    public boolean compareShopName (String shopName){
        flowerShopList = FakeBBDD.getFlowerShopList();
        return flowerShopList.stream().anyMatch(shop -> shop.equalsIgnoreCase(shopName));
    }

    public List<Product> getProductList (String flowerShopName){
        //this.productList = FakeBBDD.getProductList();
        this.productList = TxtBBDD.getProductListFromTxt(flowerShopName+"ProductList.txt");
        //this.productList = GetDataFromSQL.getProductList(
        // GET DATA FROM NOSQL
        return this.productList;
    }
    public List<Sale> getSaleList (String flowerShopName){
        //this.saleList = FakeBBDD.getSaleList();
        this.saleList = TxtBBDD.getSaleListFromTxt(flowerShopName+"SaleList.txt");
        // GET DATA FROM SQL
        // GET DATA FROM NOSQL
        return this.saleList;
    }

    public void exportProductList (List<Product> productList, String flowerShopName){
        //this.productList = productList;
        TxtBBDD.returnProductList(productList, flowerShopName+"ProductList.txt");
        // RETURN DATA TO SQL
        // RETURN DATA TO NOSQL
    }
    public void exportSaleList (List<Sale> saleList, String flowerShopName){
        //this.saleList = saleList;
        TxtBBDD.returnSaleList(saleList, flowerShopName+"SaleList.txt");
        // RETURN DATA TO SQL
        // RETURN DATA TO NOSQL
    }

}
