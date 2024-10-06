package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class that contains the request testing util methods
 */
public final class RequestUtils {

    /**
     * Makes a request returning the httpresponse
     *
     * @param url             - url to make the request to
     * @param jsonInputString - requestBody
     * @param -               requestType, ideally POST or GET
     */
    public static HttpResponse<String> openRequest(String url, String jsonInputString, String requestType) {

        HttpResponse<String> response = null;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json");

            if (requestType.equals("POST")) {
                builder.POST(HttpRequest.BodyPublishers.ofString(jsonInputString));
            } else if (requestType.equals("GET")) {
                builder.method("GET", HttpRequest.BodyPublishers.ofString(jsonInputString));
            }

            // Creates the HTTP request
            HttpRequest request = builder.build();

            // Sends the request and gets the response
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}


