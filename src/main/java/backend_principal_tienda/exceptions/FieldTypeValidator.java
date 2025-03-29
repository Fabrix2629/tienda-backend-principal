package backend_principal_tienda.exceptions;

import com.fasterxml.jackson.databind.JsonNode;

public class FieldTypeValidator {

    public static void validateString(JsonNode node, String fieldName) {
        if (node.has(fieldName)){
            if (!node.get(fieldName).isTextual()) {
                throw new InvalidTypeException(fieldName, "String");
            }
        }
    }

    public static void validateInteger(JsonNode node, String fieldName) {
        if (node.has(fieldName)) {
            if (!node.get(fieldName).isInt()) {
                throw new InvalidTypeException(fieldName, "Integer");
            }
        }
    }

    public static void validateDouble(JsonNode node, String fieldName) {
        if (node.has(fieldName)) {
            if (!node.get(fieldName).isDouble() && !node.get(fieldName).isInt()) {
                throw new InvalidTypeException(fieldName, "Double");
            }
        }
    }
}