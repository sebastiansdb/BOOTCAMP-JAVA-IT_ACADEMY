package n2exercici1.services;

import n2exercici1.exceptions.ProductDoesNotExistsException;
import n2exercici1.products.Decoration;
import n2exercici1.products.Flower;
import n2exercici1.products.Product;
import n2exercici1.products.Tree;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private static Stock stock;
    private boolean initStock; // IMPIDE QUE SE LANZE EL MENSAJE PRODUCTO AÑADIDO HASTA QUE SE HAYA CARGADO LA BBDD
    private final List<Tree> treeStock = new ArrayList<>();
    private final List<Flower> flowerStock = new ArrayList<>();
    private final List<Decoration> decorationStock = new ArrayList<>();
    private final List<Product> productStock = new ArrayList<>();
    private double stockValue;

    private Stock (DAOService service){
        try {
            new ArrayList<>(service.getProductList()).forEach(this::addProduct);
            if (productStock.isEmpty()) System.out.println("This store has zero stock in it");
            this.initStock = true;
        } catch (NullPointerException e){
            this.initStock = false;
        }
    }
    public static Stock getStock (DAOService service){
        if (stock == null) stock = new Stock(service);
        return stock;
    }
    public List<Product> getProductStock (){
        return this.productStock;
    }
    public boolean getInitStock(){
        return initStock;
    }

    public List<Tree> getTreeStock(){
        return treeStock;
    }
    public List<Flower> getFlowerStock(){
        return flowerStock;
    }
    public List<Decoration> getDecorationStock(){
        return decorationStock;
    }
    public double getStockValue(){
        return stockValue;
    }

    public void addProduct (Product product){
        try {
            switch (product){
                case Tree tree -> treeStock.add(tree);
                case Flower flower -> flowerStock.add(flower);
                case Decoration decoration -> decorationStock.add(decoration);
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
                case Tree tree -> treeStock.remove(tree);
                case Flower flower -> flowerStock.remove(flower);
                case Decoration decoration -> decorationStock.remove(decoration);
                default -> throw new ProductDoesNotExistsException("This product does not exist.");
            }
            wrapStockChanges(product, "remove");
        } catch (ProductDoesNotExistsException e){
            System.out.println(e.getMessage());
        }
    }
    public Product findProduct(String productName, String type){
        return this.productStock.stream().filter(product -> product.getName().equalsIgnoreCase(productName) && product.getType().equalsIgnoreCase(type))
                .findFirst().orElse(null);
    }
    private void wrapStockChanges(Product product, String action){
        String message = action.equals("remove") ? "Product removed" : this.initStock ? "Product stocked" : null;
        if (message!=null) System.out.println(message);
        updateStockValue(product, action);
        updateProductStock(product, action);
    }
    private void updateStockValue(Product product, String action){
        this.stockValue += (action.equals("add") ? product.getPrice() : -product.getPrice());
    }
    private void updateProductStock(Product product, String action){
        if (action.equals("add")) {
            this.productStock.add(product);
        } else {
            this.productStock.remove(product);
        }
    }

}
