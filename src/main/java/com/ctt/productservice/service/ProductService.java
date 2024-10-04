package com.ctt.productservice.service;

import com.ctt.productservice.model.Product;
import com.ctt.productservice.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Service that is called in controller and requests repository interference
 * */
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * ProductService constructor
     *
     * Gets a new instance of the repository
     * */
    public ProductService() {
        this.productRepository = new ProductRepository("mongo-product-service", "mongodb://180.18.0.2:27017");
    }

    /**
     * Registers a product in our database calling the method in repository
     *
     * @param productMap - product map containing not generated attributes
     * */
    public void registerProduct(Map<String,Object> productMap) {
        List<String> categories = (List<String>) productMap.get("categories");
        Product product = new Product((int) productMap.get("stock"),
                (String) productMap.get("description"),
                categories,
                (float) ((double) productMap.get("price")));
        productRepository.save(product);
    }

    private List<String> parseArray(String categories) {
        List<String> categoriesArray = new ArrayList<>();
        String[] splittedCategories = categories.split("\"");
        for(int i = 0 ; i<splittedCategories.length; i++) {
            if(i%2 == 1) {
                categoriesArray.add(splittedCategories[i]);
            }
        }
        return categoriesArray;
    }
}
