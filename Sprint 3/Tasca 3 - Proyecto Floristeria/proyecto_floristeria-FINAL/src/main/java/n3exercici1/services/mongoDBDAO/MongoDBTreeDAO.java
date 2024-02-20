package n3exercici1.services.mongoDBDAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import n3exercici1.products.Tree;
import n3exercici1.services.productsDAO.TreeDAO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBTreeDAO implements TreeDAO {

    private final MongoCollection<Document> collection;

    public MongoDBTreeDAO(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public void insert(Tree tree) {
        try {
            collection.insertOne(treeToDocument(tree));
        } catch (Exception e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    private static Document treeToDocument(Tree tree){
        return new Document()
                .append("id", tree.getIdProduct())
                .append("type", "TREE")
                .append("name", tree.getName())
                .append("price", tree.getPrice())
                .append("attribute", tree.getAttribute());
    }
    @Override
    public void delete(Tree tree) {
        try{
            collection.deleteOne(new Document("name", tree.getName()));
        } catch (Exception e){
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public List<Tree> getAll() {
        List<Tree> treeList = new ArrayList<>();
        try {
            for (Document doc : collection.find(Filters.eq("type","TREE"))){
                treeList.add(createTree(doc));
            }
        } catch (Exception e) {
            System.out.println("The tree list could not be recovered.");
            treeList = null;
        }
        return treeList;
    }
    @Override
    public Tree getOne(String name) {
        Tree tree = null;
        try {
            Document doc = collection.find(Filters.and(Filters.eq("type", "TREE"), Filters.eq("name", name))).first();
            if (doc != null) tree = createTree(doc);
        } catch (Exception e) {
            System.out.println("An error ocurred at obtaining the tree: " + e.getMessage());
        }
        return tree;
    }
    private Tree createTree(Document doc) {
        return new Tree(doc.getString("name"),
                doc.getDouble("price"),
                doc.getDouble("attribute"));
    }
    @Override
    public int getLastID() {
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
    public void update(Tree tree) {}
}
