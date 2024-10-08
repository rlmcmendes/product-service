package integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.RequestUtils;

import java.net.http.HttpResponse;

import static com.ctt.productservice.handler.RegisterHandler.PRODUCT_REGISTERED;

/**
 * Test class for register operation
 */
public class RegisterOperationTests {

    private static final int HTTP_OK = 200;

    /**
     * Tests if a normal register operation works
     */
    @Test
    public void testRegisterOperation() {
        String url = "http://localhost:8080/register";
        String jsonInputString = "{ " +
                "\"description\":\"Just to make sure version 4\",\n" +
                "\"categories\":[\n" +
                "        {\n" +
                "            \"id\": \"123e4567-e89b-12d3-a456-426614174000\",\n" +
                "            \"name\": \"Category A\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"123e4567-e89b-12d3-a456-426614174001\",\n" +
                "            \"name\": \"Category B\"\n" +
                "        }\n" +
                "    ],\n" +
                "\"price\":\"20.25\"}";

        HttpResponse<String> response = RequestUtils.openRequest(url, jsonInputString, "POST");

        //Asserts that response in not null
        Assertions.assertNotNull(response);

        //Asserts that response is 200
        Assertions.assertEquals(HTTP_OK, response.statusCode());

        //Asserts that response body is correct
        Assertions.assertEquals(PRODUCT_REGISTERED, response.body());
    }
}
