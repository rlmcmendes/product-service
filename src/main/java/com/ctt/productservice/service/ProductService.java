package com.ctt.productservice.service;

import com.ctt.productservice.model.Product;
import com.ctt.productservice.repository.ProductRepository;
import org.bson.Document;

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
        this.productRepository = new ProductRepository("mongo-product-service", "mongodb://180.18.0.2:27017");
    }

    /**
     * Registers a product in our database calling the method in repository
     *
     * @param productMap - product map containing not generated attributes
     */
    //Categories can only be a list of string, no other way is possible when inserting
    @SuppressWarnings("unchecked")
    public void registerProduct(Map<String, Object> productMap) {
        List<String> categories = (List<String>) productMap.get("categories");
        Product product = new Product((int) productMap.get("stock"),
                (String) productMap.get("description"),
                categories,
                (float) ((double) productMap.get("price")));
        product.setId(UUID.randomUUID().toString());
        productRepository.save(product);
    }

    /**
     * Requests a product information in our database calling the method in repository
     */
    public Document getProduct(Map<String, Object> requestIdMap) {
        return productRepository.findById((String) requestIdMap.get("id"));
    }
}
