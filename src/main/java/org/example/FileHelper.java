package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Order> readOrdersFromJson(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Order>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void writeOrderToJson(String filePath, Order order) {
        try {
            List<Order> orders = readOrdersFromJson(filePath);
            orders.add(order);
            objectMapper.writeValue(new File(filePath), orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
