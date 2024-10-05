package integration;

import com.ctt.productservice.model.Product;
import com.ctt.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.RequestUtils;

import java.net.http.HttpResponse;
import java.util.Arrays;

/**
 * Class to test InformationOperation
 * */
public class InformationOperationTests {
    private static final int HTTP_OK = 200;

    /**
     * Tests if a normal register operation works, adding a product to the database, retrieving it,
     * testing for correctness and then deleting it
     */
    @Test
    public void testInformationOperation() throws InterruptedException {
        ProductRepository repo = new ProductRepository("mongo-product-service", "mongodb://localhost:27017");
        Product product = new Product(39, "a description that fits", Arrays.asList("2", "45"), 18.5f);
        product.setId("7346465rhgdgde6737");
        repo.save(product);
        String url = "http://localhost:8080/information";
        String jsonInputString = "{\"id\":\"7346465rhgdgde6737\"}";
        HttpResponse<String> response = RequestUtils.openRequest(url, jsonInputString, "GET");
        Assertions.assertEquals(HTTP_OK, response.statusCode());
        Assertions.assertEquals("{ \"id\": \"7346465rhgdgde6737\", \"stock\": 39, \"description\": \"a description that fits\", \"categories\": [\"2\", \"45\"], \"price\": 18.5}", response.body());
        repo.delete(product.getId());
    }
}
