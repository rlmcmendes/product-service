package com.ctt.productservice.controller;

import com.ctt.productservice.handler.RegisterHandler;
import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ProductControllerTests {

    private HttpServer server;
    private ProductService productService;
    private RegisterHandler registerHandler;

    @BeforeEach
    public void setUp() throws IOException {
        // Mock the ProductService
        productService = Mockito.mock(ProductService.class);
        registerHandler = new RegisterHandler(productService);

        // Create and start the HTTP server
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/register", registerHandler);
        server.start();
    }

    @AfterEach
    public void tearDown() {
        if (server != null) {
            server.stop(0); // Stop the server immediately
        }
    }

    @Test
    public void testRegisterEndpoint() throws Exception {
        // Create a URL object for the register endpoint
        URL url = new URL("http://localhost:8080/register");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        // Prepare the JSON input
        String jsonInputString = "{\"id\":\"123e4567-e89b-12d3-a456-426614174000\",\"name\":\"Sample Product\"}";

        // Write the JSON input to the connection output stream
        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonInputString.getBytes());
            os.flush();
        }

        // Get the response code and verify it
        int responseCode = connection.getResponseCode();
        Assertions.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
    }
}
