package n3exercici1.services;

import n3exercici1.exceptions.ProductDoesNotExistsException;
import n3exercici1.products.Decoration;
import n3exercici1.products.Flower;
import n3exercici1.products.Product;
import n3exercici1.products.Tree;
import n3exercici1.services.mongoDBDAO.MongoDBManager;
import n3exercici1.services.productsDAO.*;

import java.util.List;

public class Stock {

    private static Stock stock;
    private boolean initStock;
    private FlowerDAO flowerDAO;
    private TreeDAO treeDAO;
    private DecorationDAO decorationDAO;
    private double stockValue;

    private Stock (MongoDBManager manager){
        try {
            this.flowerDAO = manager.getFlowerDAO();
            this.treeDAO = manager.getTreeDAO();
            this.decorationDAO = manager.getDecorationDAO();
            if (stockEmpty()) System.out.println("This store has zero stock in it");
            else calculateStockValue();
            this.initStock = true;
        } catch (NullPointerException e){
            this.initStock = false;
        }
    }
    public static Stock getStock (MongoDBManager manager){
        if (stock == null) stock = new Stock(manager);
        return stock;
    }
    private boolean stockEmpty(){
        return getFlowerStock().isEmpty() && getTreeStock().isEmpty() && getDecorationStock().isEmpty();
    }
    private void calculateStockValue(){
        List<Tree> treeStock = getTreeStock();
        List<Flower> flowerStock = getFlowerStock();
        List<Decoration> decorationStock = getDecorationStock();
        this.stockValue = treeStock.stream().mapToDouble(Product::getPrice).sum()
                + flowerStock.stream().mapToDouble(Product::getPrice).sum()
                + decorationStock.stream().mapToDouble(Product::getPrice).sum();
    }
    public boolean getInitStock(){
        return this.initStock;
    }

    public List<Tree> getTreeStock(){
        return this.treeDAO.getAll();
    }
    public List<Flower> getFlowerStock(){
        return this.flowerDAO.getAll();
    }
    public List<Decoration> getDecorationStock(){
        return this.decorationDAO.getAll();
    }
    public double getStockValue(){
        return stockValue;
    }

    public void addProduct (Product product){
        try {
            switch (product){
                case Tree tree -> treeDAO.insert(tree);
                case Flower flower -> flowerDAO.insert(flower);
                case Decoration decoration -> decorationDAO.insert(decoration);
                default -> throw new ProductDoesNotExistsException("This type of product does not exist.");
            }
            wrapStockChanges(product, "add");
        } catch (ProductDoesNotExistsException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeProduct (Product product){
        try {
            switch (product) {
                case Tree tree -> treeDAO.delete(tree);
                case Flower flower -> flowerDAO.delete(flower);
                case Decoration decoration -> decorationDAO.delete(decoration);
                default -> throw new ProductDoesNotExistsException("This product does not exist.");
            }
            wrapStockChanges(product, "remove");
        } catch (ProductDoesNotExistsException e){
            System.out.println(e.getMessage());
        }
    }
    public Product findProduct(String productName, String type){
        Product productFound = null;
        switch(type){
            case "TREE" -> productFound = treeDAO.getOne(productName);
            case "FLOWER" -> productFound = flowerDAO.getOne(productName);
            case "DECORATION" -> productFound = decorationDAO.getOne(productName);
        }
        return productFound;
    }
    private void wrapStockChanges(Product product, String action){
        System.out.println(action.equals("add") ? "Product stocked" : "Product removed");
        updateStockValue(product, action);
    }
    private void updateStockValue(Product product, String action){
        this.stockValue += (action.equals("add") ? product.getPrice() : -product.getPrice());
    }

}