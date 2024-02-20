package n3exercici1.services.mongoDBDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import n3exercici1.products.Flower;
import n3exercici1.services.productsDAO.FlowerDAO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBFlowerDAO implements FlowerDAO {
    private final MongoCollection<Document> collection;
    public MongoDBFlowerDAO(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public void insert(Flower flower) {
        try {
            collection.insertOne(flowerToDocument(flower));
        } catch (Exception e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    private static Document flowerToDocument(Flower flower){
        return new Document()
                .append("id", flower.getIdProduct())
                .append("type", "FLOWER")
                .append("name", flower.getName())
                .append("price", flower.getPrice())
                .append("attribute", flower.getAttribute());
    }
    @Override
    public void delete(Flower flower) {
        try{
            collection.deleteOne(new Document("name", flower.getName()));
        } catch (Exception e){
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public List<Flower> getAll() {
        List<Flower> flowerList = new ArrayList<>();
        try {
            for (Document doc : collection.find(Filters.eq("type","FLOWER"))){
                flowerList.add(createFlower(doc));
            }
        } catch (Exception e) {
            System.out.println("The flower list could not be recovered.");
            flowerList = null;
        }
        return flowerList;
    }
    @Override
    public Flower getOne(String name) {
        Flower flower = null;
        try {
            Document doc = collection.find(Filters.and(Filters.eq("type", "FLOWER"), Filters.eq("name", name))).first();
            if (doc != null) flower = createFlower(doc);
        } catch (Exception e) {
            System.out.println("An error ocurred at obtaining the flower: " + e.getMessage());
        }
        return flower;
    }
    private Flower createFlower(Document doc) {
        return new Flower(doc.getString("name"),
                doc.getDouble("price"),
                doc.getString("attribute"));
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
    public void update(Flower flower) {}

}