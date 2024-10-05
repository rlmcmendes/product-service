package com.ctt.productservice.handler;

import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.ctt.productservice.handler.RegisterHandler.PRODUCT_REGISTERED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Class to test register handler
 * */
public class RegisterHandlerTests {
    private ProductService productService;
    private RegisterHandler registerHandler;

    @BeforeEach
    public void setUp() {
        productService = Mockito.mock(ProductService.class);
        registerHandler = new RegisterHandler(productService);
    }

    /**
     * Tests if handle is working, verifying if the service is being properly called and
     * the response of the exchange is 200
     * */
    @Test
    public void testHandle() {
        //JSON input
        String jsonInput = "{\n" +
                "    \"description\":\"Sample Product\",\n" +
                "    \"categories\":[\"cat1\",\"cat2\"],\n" +
                "    \"price\":19.99\n" +
                "}";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(jsonInput.getBytes());

        // Mock HttpExchange
        HttpExchange exchange = Mockito.mock(HttpExchange.class);
        Mockito.when(exchange.getRequestBody()).thenReturn(inputStream);

        // Create an OutputStream to capture the response
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Mockito.when(exchange.getResponseBody()).thenReturn(outputStream);

        // Call the handle method
        registerHandler.handle(exchange);

        // Verify that the productService.registerProduct was called
        verify(productService).registerProduct(any());

        // Verify the response
        String response = outputStream.toString();
        Assertions.assertEquals(PRODUCT_REGISTERED, response);
    }

}
