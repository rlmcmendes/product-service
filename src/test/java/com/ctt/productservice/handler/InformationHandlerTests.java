package com.ctt.productservice.handler;

import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpExchange;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static com.ctt.productservice.handler.RegisterHandler.PRODUCT_REGISTERED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InformationHandlerTests {
    private ProductService productService;
    private InformationHandler informationHandler;

    @BeforeEach
    public void setUp() {
        productService = Mockito.mock(ProductService.class);
        informationHandler = new InformationHandler(productService);
    }

    /**
     * Tests if information handler is working, verifying if the service is being properly called and
     * the response of the exchange is 200
     * */
    @Test
    public void testHandle() {

        //Makes the Document return something
        when(productService.getProduct(any())).thenReturn(Document.parse("{\"stock\": 45}"));

        //JSON input
        String jsonInput = "{\"id\":\"iuagsrv67egiuw\"}";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(jsonInput.getBytes());

        // Mock HttpExchange
        HttpExchange exchange = Mockito.mock(HttpExchange.class);
        Mockito.when(exchange.getRequestBody()).thenReturn(inputStream);

        // Create an OutputStream to capture the response
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Mockito.when(exchange.getResponseBody()).thenReturn(outputStream);

        // Call the information handle method
        informationHandler.handle(exchange);

        // Verify that the productService.registerProduct was called
        verify(productService).getProduct(any());

        // Verify the response
        String response = outputStream.toString();
        Assertions.assertEquals("{\"stock\": 45}", response);
    }
}
