package com.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for formatting JSON strings to a more readable format.
 */
@Slf4j
public class JSONFormatter {
    public static void prettyJSON(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            log.info(formattedJson);
        } catch (Exception e) {
            log.error("Error while formatting JSON -> " + e.getMessage());
        }
    }
}
