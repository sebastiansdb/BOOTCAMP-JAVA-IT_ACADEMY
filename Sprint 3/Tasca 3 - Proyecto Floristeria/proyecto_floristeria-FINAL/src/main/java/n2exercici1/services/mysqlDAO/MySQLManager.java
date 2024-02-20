package n2exercici1.services.mysqlDAO;

import n2exercici1.services.productsDAO.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

public class MySQLManager implements DAOManager {

    private static final String directory = "src/main/resources/";
    private String shopName;
    private Connection connection;
    private DecorationDAO decorationDAO;
    private FlowerDAO flowerDAO;
    private TreeDAO treeDAO;
    private SaleDAO saleDAO;

    public MySQLManager(String shopName) {
        try {
            this.shopName = shopName;
            this.connection = new MySQLConnection(this.shopName+"flowershop").getConnection();
        } catch (SQLException e) {
            System.out.println("Connection failed." + e.getMessage());
        }
    }
    public boolean checkShopName(){
        boolean exists;
        try (Stream<Path> files = Files.walk(Paths.get(directory))){
            exists = files.map(Path::getFileName).map(Path::toString).anyMatch(fileName -> fileName.toLowerCase().contains(this.shopName.toLowerCase()));
        } catch (IOException e){
            exists = false;
        }
        return exists;
    }

    @Override
    public FlowerDAO getFlowerDAO() {
        if (flowerDAO == null) flowerDAO = new MySQLFlowerDAO(this.connection);
        return flowerDAO;
    }
    @Override
    public TreeDAO getTreeDAO() {
        if (treeDAO == null) treeDAO = new MySQLTreeDAO(this.connection);
        return treeDAO;
    }
    @Override
    public DecorationDAO getDecorationDAO() {
        if (decorationDAO == null) decorationDAO = new MySQLDecorationDAO(this.connection);
        return decorationDAO;
    }
    @Override
    public SaleDAO getSaleDAO() {
        if (saleDAO == null) saleDAO = new MySQLSaleDAO(this.connection);
        return saleDAO;
    }
}
