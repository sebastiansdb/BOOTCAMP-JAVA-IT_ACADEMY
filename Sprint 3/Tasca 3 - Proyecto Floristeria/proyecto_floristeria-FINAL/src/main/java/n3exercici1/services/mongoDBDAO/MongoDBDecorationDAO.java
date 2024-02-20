package n3exercici1.services.mongoDBDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import n3exercici1.products.Decoration;
import n3exercici1.products.enums.MadeOf;
import n3exercici1.services.productsDAO.DecorationDAO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBDecorationDAO implements DecorationDAO {

    private final MongoCollection<Document> collection;

    public MongoDBDecorationDAO(MongoCollection<Document> collection) {
        this.collection = collection;
    }
    @Override
    public void insert(Decoration decoration) {
        try {
            collection.insertOne(decorationToDocument(decoration));
        } catch (Exception e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    private static Document decorationToDocument(Decoration decoration){
        return new Document()
                .append("id", decoration.getIdProduct())
                .append("type", "DECORATION")
                .append("name", decoration.getName())
                .append("price", decoration.getPrice())
                .append("attribute", decoration.getAttribute());
    }
    @Override
    public void delete(Decoration decoration) {
        try{
            collection.deleteOne(new Document("name", decoration.getName()));
        } catch (Exception e){
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public List<Decoration> getAll() {
        List<Decoration> decorationList = new ArrayList<>();
        try {
            for (Document doc : collection.find(Filters.eq("type","DECORATION"))){
                decorationList.add(createDecoration(doc));
            }
        } catch (Exception e) {
            System.out.println("The decoration list could not be recovered.");
            decorationList = null;
        }
        return decorationList;
    }
    @Override
    public Decoration getOne(String name) {
        Decoration decoration = null;
        try {
            Document doc = collection.find(Filters.and(Filters.eq("type", "DECORATION"), Filters.eq("name", name))).first();
            if (doc != null) decoration = createDecoration(doc);
        } catch (Exception e) {
            System.out.println("An error ocurred at obtaining the decoration: " + e.getMessage());
        }
        return decoration;
    }
    private Decoration createDecoration(Document doc) {
        return new Decoration(doc.getString("name"),
                doc.getDouble("price"),
                doc.getString("attribute").equalsIgnoreCase("wood") ? MadeOf.WOOD : MadeOf.PLASTIC);
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
    public void update(Decoration decoration) {}

}