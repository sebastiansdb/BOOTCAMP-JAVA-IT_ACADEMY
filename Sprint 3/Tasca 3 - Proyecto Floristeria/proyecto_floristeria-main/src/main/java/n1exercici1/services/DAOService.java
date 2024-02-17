package n1exercici1.services;

import n1exercici1.FlowerShop;
import n1exercici1.products.Product;

import java.util.List;

public class DAOService {

    private List<Product> productList;
    private List<String> flowerShopList;

    public boolean compareShopName (String shopName){
        flowerShopList = FakeBBDD.getFlowerShopList();
        return flowerShopList.stream().anyMatch(shop -> shop.equalsIgnoreCase(shopName));
    }

    public List<Product> getProductList (String shopName){
        if(compareShopName(shopName)) productList = FakeBBDD.getProductList();
        else productList = null;
        return productList;
    }
    public void returnProductList (List<Product> productList){
        this.productList = productList;
    }

}
