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

    /**
     * Constructor of ProductRepository
     * Gets the mongoClient connection and its collection
     *
     * @param address to connect to
     * @param dbName  database name
     */
    public ProductRepository(String dbName, String address) {
        MongoClient mongoClient = create(address);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        collection = database.getCollection("products");

    }

    /**
     * Saves the product in the database
     *
     * @param product - product to be saved in database
     * @return true if added successfully, false otherwise
     */
    public boolean save(Product product) {
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

    /**
     * Method to delete a database entry, essential for testing purposes
     * It is not accessible through any endpoint
     */
    public void delete(String id) {
        collection.findOneAndDelete(new Document("id", id));
    }
}
