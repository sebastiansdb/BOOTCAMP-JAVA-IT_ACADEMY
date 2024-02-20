package n1exercici1.services;

import n1exercici1.exceptions.ProductDoesNotExistsException;
import n1exercici1.products.*;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private static Stock stock;
    private final boolean initStock; // IMPIDE QUE SE LANZE EL MENSAJE PRODUCTO AÑADIDO HASTA QUE SE HAYA CARGADO LA BBDD
    private final List<Tree> treeStock;
    private final List<Flower> flowerStock;
    private final List<Decoration> decorationStock;
    private List<Product> productStock;
    private double stockValue;

    private Stock (DAOService service, String flowerShopName){
        this.treeStock = new ArrayList<>();
        this.flowerStock = new ArrayList<>();
        this.decorationStock = new ArrayList<>();
        List<Product> productStock = service.getProductList(flowerShopName);
        // Si el txt no existe, el metodo "getProductList" devuelve una instancia de ArrayList<Product>.
        if (this.productStock!=null){
            // Instancio el atributo "productStock" aquí. Si no lo hago, al querer cargar el productStock por
            // primera vez, se intentará hacr un productStock.add() y arrojará un NULLPOINTEREXCEPTION.
            this.productStock = new ArrayList<>();
            productStock.forEach(this::addProduct);
        }
        this.productStock = productStock;
        this.initStock = true;
    }
    public static Stock getStock (DAOService service, String flowerShopName){
        if (stock == null) stock = new Stock(service, flowerShopName);
        return stock;
    }
    public List<Product> getProductStock (){
        return this.productStock;
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
    public Product findProduct(String productName){
        return this.productStock.stream().filter(product -> product.getName().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
    }
    private void wrapStockChanges(Product product, String action){
        String message = action.equals("remove") ? "Product removed" : this.initStock ? "Product stocked" : null;
        if (message!=null) System.out.println(message);
        updateStockValue(product, action);
        updateProductStock(product, action);
    }
    private void updateStockValue(Product product, String action){
        stockValue += (action.equals("add") ? product.getPrice() : -product.getPrice());
    }
    private void updateProductStock(Product product, String action){
        if (action.equals("add")) {
            productStock.add(product);
        } else {
            productStock.remove(product);
        }
    }

}
