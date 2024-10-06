package com.ctt.productservice.model;

import java.util.List;

/**
 * Java class representing a mongodb data entry as a POJO
 */
public class Product {
    private String id; // UUID
    private final int stock;
    private final String description;
    private final List<String> categories;
    private final float price;

    /**
     * Constructor of the Product object
     *
     * @param stock       - stock of the product
     * @param description - description of the product
     * @param categories  - category ids to which the product belongs to
     * @param price       - product price
     */
    public Product(int stock, String description, List<String> categories, float price) {
        this.stock = stock;
        this.description = description;
        this.categories = categories;
        this.price = price;
    }

    /**
     * Gets the product ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the product stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Gets the product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the product categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Gets the product price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets product id
     */
    public void setId(String id) {
        this.id = id;
    }
}
