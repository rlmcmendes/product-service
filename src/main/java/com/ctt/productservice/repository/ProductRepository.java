package com.ctt.productservice.repository;

import com.ctt.productservice.model.Product;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.MongoClients.create;

/**
 * Java class that makes the operations in the mongodb repository
 */
public class ProductRepository {
    private final MongoCollection<Document> collection;

    public ProductRepository(String dbName, String address) {
        MongoClient mongoClient = create(address);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        collection = database.getCollection("products");

    }

    public boolean save(Product product) {
        //Check if not in database already
        Document doc = new Document("id", product.getId())
                .append("stock", product.getStock())
                .append("description", product.getDescription())
                .append("categories", product.getCategories())
                .append("price", product.getPrice());
        collection.insertOne(doc);
        return true;
    }

    /**
     * Gets document from databse through uuid
     *
     * @param uuid unique id to get
     * @return complete document form database
     */
    public Document findById(String uuid) {
        return collection.find(new Document("id", uuid)).first();
    }

    public void delete(String id) {
        collection.findOneAndDelete(new Document("id", id));
    }
}
