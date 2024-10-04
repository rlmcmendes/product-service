package integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.ctt.productservice.handler.RegisterHandler.PRODUCT_REGISTERED;

/**
 * Test class for register operation
 * */
public class RegisterOperationTests {

    private static final int HTTP_OK = 200;

    /**
     * Tests if a normal register operation works
     * */
    @Test
    public void testRegisterOperation() {
        String url = "http://localhost:8080/register";
        String jsonInputString = "{\"stock\":300,\n" +
                "\"description\":\"Just to make sure version 4\",\n" +
                "\"categories\":[\"cat1 f\",\"cat2\"],\n" +
                "\"price\":20.25}";
        HttpResponse<String> response = null;

        try {
            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Create the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                    .build();

            // Sends the request and gets the response
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Asserts that response in not null
        Assertions.assertNotNull(response);

        //Asserts that response is 200
        Assertions.assertEquals(HTTP_OK, response.statusCode());

        //Asserts that response body is correct
        Assertions.assertEquals(PRODUCT_REGISTERED, response.body());
    }
}
