package com.ctt.productservice.handler;

import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;

import static com.ctt.productservice.util.HandlerUtils.convertInputStreamToString;

/**
 * Java class to handle register request
 */
public class RegisterHandler implements HttpHandler {

    public static final String PRODUCT_REGISTERED = "Product registered";
    ;
    private final ProductService productService;

    /**
     * Constructor of Register Handler
     *
     * @param service - product service
     */
    public RegisterHandler(ProductService service) {
        this.productService = service;
    }

    /**
     * Method to be called when /register is requested to the server
     */
    @Override
    public void handle(HttpExchange exchange) {
        try {
            productService.registerProduct(convertInputStreamToString(exchange.getRequestBody()));
            exchange.sendResponseHeaders(200, PRODUCT_REGISTERED.length()); // Send 200 OK status
            OutputStream os = exchange.getResponseBody();
            os.write(PRODUCT_REGISTERED.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
