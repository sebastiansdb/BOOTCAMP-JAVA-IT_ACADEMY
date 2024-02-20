package n3exercici1.services.mongoDBDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import n3exercici1.sales.Sale;
import n3exercici1.services.productsDAO.SaleDAO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoDBSaleDAO implements SaleDAO {

    private final MongoCollection<Document> collection;

    public MongoDBSaleDAO(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public void insert(Sale sale) {
        try {
            collection.insertOne(saleToDocument(sale));
        } catch (Exception e) {
            System.out.println("The sales changes could not be made: " + e.getMessage());
        }
    }
    private static Document saleToDocument(Sale sale){
        return new Document()
                .append("id", sale.getIdSale())
                .append("totalPrice", sale.getSaleAmount())
                .append("date", sale.getSaleDate())
                .append("productList", String.join(";", sale.getProductList()));
    }
    @Override
    public List<Sale> getAll() {
        List<Sale> saleList = new ArrayList<>();
        try {
            for (Document doc : collection.find()){
                saleList.add(createSale(doc));
            }
        } catch (Exception e) {
            System.out.println("The sales list could not be recovered.");
            saleList = null;
        }
        return saleList;
    }
    @Override
    public Sale getOne (Integer id) {
        Sale sale = null;
        try{
            Document doc = collection.find(Filters.eq("id",id)).first();
            if (doc != null) sale = createSale(doc);
        } catch (Exception e) {
            System.out.println("An error ocurred at obtaining the sale: " + e.getMessage());
        }
        return sale;
    }
    private Sale createSale(Document doc) {
        return new Sale(doc.getInteger("id"),
                doc.getDouble("totalPrice"),
                doc.getDate("date"),
                Arrays.asList((doc.getString("productList")).split(";")));
    }
    @Override
    public int getLastID(){
        int lastID = 0;
        try {
            Document doc = collection.find().sort(Sorts.descending("id")).limit(1).first();
            if (doc != null) lastID = doc.getInteger("id", 0);
        } catch (Exception e){
            System.out.println("Error obtaining the next ID number: " + e.getMessage());
        }
        return lastID;
    }

    @Override
    public void delete(Sale sale) {}
    @Override
    public void update(Sale sale) {}






}
