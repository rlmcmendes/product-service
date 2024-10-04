package com.ctt.productservice.handler;

import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bson.Document;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterHandler implements HttpHandler {

    private final ProductService productService;

    public RegisterHandler(ProductService service) {
        this.productService = service;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            productService.registerProduct(convertInputStreamToString(exchange.getRequestBody()));
            String response = "Product registered";
            exchange.sendResponseHeaders(200, response.length()); // Send 200 OK status
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility method to convert InputStream to String
    private Map<String,Object> convertInputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return Document.parse(stringBuilder.toString());
    }

}
