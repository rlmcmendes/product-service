package com.ctt.productservice.service;

import com.ctt.productservice.model.Product;
import com.ctt.productservice.repository.ProductRepository;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Service that is called in controller and requests repository interference
 */
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * ProductService constructor
     * Gets a new instance of the repository
     */
    public ProductService() {
        this.productRepository = new ProductRepository("mongo-product-service", System.getenv("MONGO_URI"));
    }

    /**
     * Registers a product in our database calling the method in repository
     *
     * @param productMap - product map containing not generated attributes
     */
    //Categories can only be a list of string, no other way is possible when inserting
    @SuppressWarnings("unchecked")
    public void registerProduct(Document productMap) {
        List<String> categories = formatListOfDocumentsToListOfIds((List<Document>) productMap.get("categories"));
        Product product = new Product(0,
                productMap.getString("description"),
                categories,
                Float.parseFloat(productMap.getString("price")));
        product.setId(UUID.randomUUID().toString());
        productRepository.save(product);
    }

    /**
     * Requests a product information in our database calling the method in repository
     */
    public Document getProduct(Document requestIdMap) {
        return productRepository.findById(requestIdMap.getString("id"));
    }

    /**
     * Formats the list of document containing id and name from JSON categories to
     * a list of strings containing only the ids
     * */
    private List<String> formatListOfDocumentsToListOfIds(List<Document> categories) {
        List<String> categoriesList = new ArrayList<>();
        for(Document document:categories){
            categoriesList.add(document.getString("id"));
        }
        return categoriesList;
    }

}
