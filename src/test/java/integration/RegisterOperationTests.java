package integration;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RegisterOperationTests {

    @Test
    public void testRegisterOperation() {
        String url = "http://localhost:8080/register";
        String jsonInputString = "{\"stock\":300,\n" +
                "\"description\":\"Just to make sure version 4\",\n" +
                "\"categories\":[\"cat1 f\",\"cat2\"],\n" +
                "\"price\":20.25}";

        try {
            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Create the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response code and body
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
