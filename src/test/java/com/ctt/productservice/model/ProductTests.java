package com.ctt.productservice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Class to test if the product model is working properly
 * */
public class ProductTests {

    /**
     * Tests if the product model works in normal circumstances
     * */
    @Test
    public void testProductInNormalCircumstances() {
        Product product = new Product(94, "description", Arrays.asList("cat1","cat2"), 40.7f);
        product.setId("hcycusnciucy");
        Assertions.assertEquals("hcycusnciucy", product.getId());
        Assertions.assertEquals("description", product.getDescription());
        Assertions.assertEquals(40.7f, product.getPrice(), 0.001);
        Assertions.assertEquals(94, product.getStock());
        Assertions.assertEquals( Arrays.asList("cat1","cat2"), product.getCategories());
    }

    /**
     * Tests what happens when the product model faces null or negative values
     * */
    @Test
    public void testProductNullValues() {
        Product product = new Product(-1, null, null, -1);
        product.setId(null);
        Assertions.assertNull(product.getId());
        Assertions.assertNull(product.getDescription());
        Assertions.assertEquals(-1, product.getPrice(), 0.001);
        Assertions.assertEquals(-1, product.getStock());
        Assertions.assertNull(product.getCategories());
    }
}
