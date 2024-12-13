package org.example;

import java.sql.*;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/PROJECT";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "i0000";

    public static boolean customerExists(int customerId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM customer WHERE id = ?")) {
            statement.setInt(1, customerId);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void insertOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO `order` (id, date, amount, customer_id) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, order.getId());
            statement.setString(2, order.getDate());
            statement.setDouble(3, order.getAmount());
            statement.setInt(4, order.getCustomerId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Commande insérée avec succès pour l'ID: " + order.getId());
            } else {
                System.out.println("Aucune commande insérée pour l'ID: " + order.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
