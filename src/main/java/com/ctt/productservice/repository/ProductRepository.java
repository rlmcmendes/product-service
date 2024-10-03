package com.ctt.productservice.repository;

import com.ctt.productservice.model.Product;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.UUID;

/**
 * Java class that makes the operations in the mongodb repository
 * */
public class ProductRepository {
    private final MongoCollection<Document> collection;

    public ProductRepository() {
        MongoClient mongoClient = MongoClients.create("mongodb://172.17.0.2:27017");
        MongoDatabase database = mongoClient.getDatabase("mongo-product-service");
        collection = database.getCollection("products");
    }

    public boolean save(Product product) {
        try {
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
        catch(Exception e) {
            return false;
        }
    }

    public boolean delete(String id) {
        try {
            collection.findOneAndDelete(new Document("id", id));
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
