package com.ctt.productservice.handler;

import com.ctt.productservice.service.ProductService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bson.Document;

import java.io.OutputStream;

import static com.ctt.productservice.util.HandlerUtils.convertInputStreamToString;

/**
 * Handler for information functionality
 */
public class InformationHandler implements HttpHandler {

    private final ProductService productService;

    /**
     * Constructor for INformationHandler
     *
     * @param service - ProductService to used
     */
    public InformationHandler(ProductService service) {
        this.productService = service;
    }

    /**
     * This is the entrypoint when an information request os made
     */
    @Override
    public void handle(HttpExchange exchange) {
        try {
            Document doc = productService.getProduct(convertInputStreamToString(exchange.getRequestBody()));
            String response = formatJsonWithoutObjectId(doc.toJson());
            exchange.sendResponseHeaders(200, response.length()); // Send 200 OK status
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Separates the ObjectId given by mongo from the rest of the JSON containing
     * the Product information
     *
     * @param json - json received in response
     * @return formated string
     */
    private String formatJsonWithoutObjectId(String json) {
        String[] splitInMongoObjectIdAndProductObject = json.split(",");
        if (splitInMongoObjectIdAndProductObject.length > 1) {
            String[] formatedStringArray = new String[splitInMongoObjectIdAndProductObject.length - 1];
            // Copy elements from index 1 to formatedStringArray
            System.arraycopy(splitInMongoObjectIdAndProductObject, 1, formatedStringArray, 0, formatedStringArray.length);
            return "{" + String.join(",", formatedStringArray);
        }
        return splitInMongoObjectIdAndProductObject[0];

    }

}

