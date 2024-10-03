package com.ctt.productservice.handler;

import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

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
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Product registered";
        exchange.sendResponseHeaders(200, response.length()); // Send 200 OK status
        OutputStream os = exchange.getResponseBody();
        productService.registerProduct(convertInputStreamToString(exchange.getRequestBody()));
        os.write(response.getBytes());
        os.close();
    }

    // Utility method to convert InputStream to String
    private Map<String,String> convertInputStreamToString(InputStream inputStream) throws IOException {
        Map<String,String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                map.putAll(convertJsonToMap(line));
            }
        }
        return map;
    }

    public Map<String, String> convertJsonToMap(String jsonString) {
        Map<String, String> map = new HashMap<>();
        // Regular expression to match key-value pairs
        Pattern pattern = Pattern.compile("\"(.*?)\":");
        Matcher matcher = pattern.matcher(jsonString);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = jsonString.substring(matcher.end());
            if(value.charAt(value.length()-1)==','){
                value = value.substring(0, value.length()-1);
            }
            map.put(key, value);
        }
        return map;
    }
}
