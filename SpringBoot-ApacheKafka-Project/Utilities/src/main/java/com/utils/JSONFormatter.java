package com.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for formatting JSON strings to a more readable format.
 */
public class JSONFormatter {
    public static void prettyJSON(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            System.out.println(formattedJson);
        } catch (Exception e) {
            System.out.println("Error while formatting JSON -> " + e.getMessage());
        }
    }
}
