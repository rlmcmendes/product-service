package com.ctt.productservice.controller;

import com.ctt.productservice.handler.InformationHandler;
import com.ctt.productservice.handler.RegisterHandler;
import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Java class that creates the server and contexts
 */
public class ProductController {

    /**
     * Creates the server and contexts
     */
    public static void main(String[] args) throws IOException {
        // Create an HTTP server instance on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        ProductService productService = new ProductService();

        // Create a context for handling register requests
        server.createContext("/register", new RegisterHandler(productService));

        // Create a context for handling information request
        server.createContext("/information", new InformationHandler(productService));

        // Start the server
        server.start();
    }
}
