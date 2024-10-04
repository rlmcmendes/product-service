package com.ctt.productservice.repository;

import com.ctt.productservice.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ProductRepositoryTests {

    @Test
    public void testRepository() {
        ProductRepository repo = new ProductRepository("testDatabase", "mongodb://localhost:27017");
        Product product = new Product(39, "a description that fits", Arrays.asList("2","45"), 18.5f);
        Assertions.assertTrue(repo.save(product));
        repo.delete(product.getId());
    }
}
