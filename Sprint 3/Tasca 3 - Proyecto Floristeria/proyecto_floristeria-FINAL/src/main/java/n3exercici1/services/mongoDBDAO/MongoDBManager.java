package n3exercici1.services.mongoDBDAO;

import com.mongodb.client.MongoCollection;
import n3exercici1.services.productsDAO.*;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MongoDBManager implements DAOManager {

    private static final String directory = "src/main/resources/";
    private String shopName;
    private MongoCollection<Document> productsCollecion;
    private MongoCollection<Document> salesCollection;
    private DecorationDAO decorationDAO;
    private FlowerDAO flowerDAO;
    private TreeDAO treeDAO;
    private SaleDAO saleDAO;

    public MongoDBManager(String shopName) {
        try {
            this.shopName = shopName;
            this.productsCollecion = MongoDBConnection.getConnection(this.shopName+"flowershop", "products");
            this.salesCollection = MongoDBConnection.getConnection(this.shopName+"flowershop", "sales");
        } catch (Exception e) {
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
        if (flowerDAO == null) flowerDAO = new MongoDBFlowerDAO(this.productsCollecion);
        return flowerDAO;
    }
    @Override
    public TreeDAO getTreeDAO() {
        if (treeDAO == null) treeDAO = new MongoDBTreeDAO(this.productsCollecion);
        return treeDAO;
    }
    @Override
    public DecorationDAO getDecorationDAO() {
        if (decorationDAO == null) decorationDAO = new MongoDBDecorationDAO(this.productsCollecion);
        return decorationDAO;
    }
    @Override
    public SaleDAO getSaleDAO() {
        if (saleDAO == null) saleDAO = new MongoDBSaleDAO(this.salesCollection);
        return saleDAO;
    }
}
