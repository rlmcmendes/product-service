package com.ctt.productservice.util;

import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Java class containing Handler Util methods
 */
public final class HandlerUtils {

    /**
     * Utility method to convert InputStream to String
     *
     * @param inputStream to read the information request from
     * @return map of the id of the field to its value
     */
    public static Document convertInputStreamToString(InputStream inputStream) throws IOException {
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
