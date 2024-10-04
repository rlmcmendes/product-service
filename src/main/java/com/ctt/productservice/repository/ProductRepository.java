package com.ctt.productservice.repository;

import com.ctt.productservice.model.Product;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.UUID;

import static com.mongodb.client.MongoClients.create;

/**
 * Java class that makes the operations in the mongodb repository
 */
public class ProductRepository {
    private final MongoCollection<Document> collection;

    public ProductRepository(String dbName, String address) {
        MongoDatabase database;
        try (MongoClient mongoClient = create(address)) {
            database = mongoClient.getDatabase(dbName);
            collection = database.getCollection("products");
        }
    }

    public boolean save(Product product) {
        product.setId(UUID.randomUUID().toString());
        //Check if not in database already
        Document doc = new Document("id", product.getId())
                .append("stock", product.getStock())
                .append("description", product.getDescription())
                .append("categories", product.getCategories())
                .append("price", product.getPrice());
        collection.insertOne(doc);
        return true;
    }

    public void delete(String id) {
        collection.findOneAndDelete(new Document("id", id));
    }
}
