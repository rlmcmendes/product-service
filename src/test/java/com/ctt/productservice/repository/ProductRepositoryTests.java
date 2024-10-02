package com.ctt.productservice.repository;

import com.ctt.productservice.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ProductRepositoryTests {

    @Test
    public void testRepository() {
        ProductRepository repo = new ProductRepository();
        Product product = new Product(39, "a descirption that fits", Arrays.asList("2","45"), 18.5f);
        product.setId("hdycycbecyc6e783hdbcy");
        Assertions.assertTrue(repo.save(product));
        repo.delete("hdycycbecyc6e783hdbcy");
    }
}
