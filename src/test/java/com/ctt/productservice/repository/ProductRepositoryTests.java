package com.ctt.productservice.repository;

import com.ctt.productservice.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Class to test the repository
 */
public class ProductRepositoryTests {

    private ProductRepository repo;
    private Product product;

    /**
     * Sets up the repository and product to add
     */
    @BeforeEach
    public void setUp() {
        repo = new ProductRepository("testDatabase", "mongodb://localhost:27017");
        product = new Product(39, "a description that fits", Arrays.asList("2", "45"), 18.5f);
        product.setId("sohftcye6789");
    }

    /**
     * Deletes the entry in the end
     */
    @AfterEach
    public void deleteDatabseEntry() {
        repo.delete(product.getId());
    }

    /**
     * Tests a register operation using repository class directly
     * In the end the product is removed
     */
    @Test
    public void testRepositorySave() {
        Assertions.assertTrue(repo.save(product));
    }

    /**
     * Test information operation using repository class directly
     * In the end the product is removed
     */
    @Test
    public void testRepositoryInformation() {
        repo.save(product);
        Assertions.assertTrue(repo.findById(product.getId()).toJson().contains(
                "\"id\": \"sohftcye6789\", \"stock\": 39, \"description\": \"a description that fits\"" +
                        ", \"categories\": [\"2\", \"45\"], \"price\": 18.5"));
    }
}
