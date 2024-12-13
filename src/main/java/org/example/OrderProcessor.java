package org.example;

import java.util.List;

public class OrderProcessor {
    public static void main(String[] args) {
        // Chemins des fichiers
        String inputFile = "Data/input.json";
        String outputFile = "Data/output.json";
        String errorFile = "Data/error.json";

        // Lire les orders depuis le fichier input
        List<Order> orders = FileHelper.readOrdersFromJson(inputFile);

        for (Order order : orders) {
            // Vérifie si le client existe dans la base
            boolean customerExists = DatabaseHelper.customerExists(order.getCustomerId());

            if (customerExists) {
                // Ajoute l'order dans la table "order"
                DatabaseHelper.insertOrder(order);

                // Ajoute l'order dans le fichier output
                FileHelper.writeOrderToJson(outputFile, order);
            } else {
                // Ajoute l'order dans le fichier error
                FileHelper.writeOrderToJson(errorFile, order);
            }
        }

        System.out.println("Traitement terminé !");
    }
}
