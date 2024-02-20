package n2exercici1.connections;

import n2exercici1.products.Decoration;
import n2exercici1.products.Flower;
import n2exercici1.products.Product;
import n2exercici1.products.Tree;
import n2exercici1.products.enums.MadeOf;
import n2exercici1.sales.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLBBDD {

    private static String sqlFileName;

    public static void setSqlFileName(String fileName){
        sqlFileName = fileName;
    }

    public static List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        Product newProduct;
        try (ResultSet reader = ((SQLBBDDConnection.getConnection(sqlFileName)).createStatement()).executeQuery("SELECT * FROM products")) {
            while (reader.next()) {
                newProduct = createProductFromBBDD(reader);
                if (newProduct!=null) productList.add(newProduct);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            productList = null;
        }
        return productList;
    }
    private static Product createProductFromBBDD(ResultSet reader) throws SQLException {
        Product newProduct;
        switch (reader.getString(2).toUpperCase()){
            case "FLOWER" -> newProduct = new Flower(reader.getString(3),reader.getDouble(4),reader.getString(5));
            case "TREE" -> newProduct = new Tree(reader.getString(3), reader.getDouble(4), reader.getDouble(5));
            case "DECORATION" -> {
                if (reader.getString(5).equalsIgnoreCase("WOOD")){
                    newProduct = new Decoration(reader.getString(3), reader.getDouble(4), MadeOf.WOOD);
                } else {
                    newProduct = new Decoration(reader.getString(3), reader.getDouble(4), MadeOf.PLASTIC);
                }
            }
            default -> newProduct = null;
        }
        return newProduct;
    }
    public static List<Sale> getSaleList (){
        List<Sale> saleList = new ArrayList<>();
        List<String> productList;
        try (ResultSet reader = ((SQLBBDDConnection.getConnection(sqlFileName)).createStatement()).executeQuery("SELECT * FROM sales")) {
            while (reader.next()){
                productList = Arrays.asList((reader.getString(4)).split(";"));
                saleList.add(new Sale(reader.getDouble(2),reader.getDate(3),productList));
            }
        } catch (SQLException e) {
            saleList = null;
        }
        return saleList;
    }

    public static void returnProductList(List<Product> productList){
        try (Connection connection = SQLBBDDConnection.getConnection(sqlFileName);
             PreparedStatement delete = connection.prepareStatement("TRUNCATE TABLE products");
             PreparedStatement insert = connection.prepareStatement("INSERT INTO products (type, name, price, attribute) VALUES (?, ?, ?, ?)")){
            delete.executeUpdate();
            for (Product product : productList) {
                insert.setString(1, product.getType());
                insert.setString(2, product.getName());
                insert.setDouble(3, product.getPrice());
                insert.setString(4, product.getAttribute());
                insert.addBatch();
            }
            insert.executeBatch();
        } catch (SQLException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }
    public static void returnSaleList(List<Sale> saleList){
        try (Connection connection = SQLBBDDConnection.getConnection(sqlFileName);
             PreparedStatement delete = connection.prepareStatement("TRUNCATE TABLE sales");
             PreparedStatement insert = connection.prepareStatement("INSERT INTO sales (totalPrice, date, productList) VALUES (?, ?, ?)")){
            delete.executeUpdate();
            for (Sale sale : saleList){
                insert.setDouble(1, sale.getSaleAmount());
                insert.setDate(2, (Date) sale.getSaleDate());
                insert.setString(3, String.join(";", sale.getProductList()));
                insert.addBatch();
            }
            insert.executeBatch();
        } catch (SQLException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }

}
