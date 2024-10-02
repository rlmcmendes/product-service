package com.ctt.productservice.controller;

import com.ctt.productservice.handler.RegisterHandler;
import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ProductController {

    public static void main(String[] args) throws IOException {
        // Create an HTTP server instance on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Create a context for handling requests to the root URL
        server.createContext("/register", new RegisterHandler(new ProductService()));

        // Start the server
        server.start();
    }
}
