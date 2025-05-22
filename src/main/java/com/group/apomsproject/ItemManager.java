/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.apomsproject;

/**
 *
 * @author User
 */
import java.io.*;
import java.util.*;

public class ItemManager {
    private final String file = "items.csv";

    public void addItem(Item item) {
        if (item == null
                || item.getItemCode() == null
                || item.getItemName() == null
                || item.getSupplierID() == null
                || item.getStockLevel() < 0
                || item.getUnitPrice() < 0
                || item.getReorderLevel() < 0) {

            System.out.println("Invalid item entry. Please provide all details correctly.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(item.toCSV());
            writer.newLine();
            System.out.println("Item saved: " + item.getItemCode());
        } catch (IOException e) {
            System.out.println("Error saving item: " + e.getMessage());
        }
    }

    public boolean deleteItem(String itemID) {
        List<String> lines = new ArrayList<>();
        boolean itemDeleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(itemID + ",")) {
                    itemDeleted = true; // Mark item for deletion
                    continue; // Skip this line
                }
                lines.add(line); // Keep other lines
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }

    // Rewrite the file without the deleted item
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Item deleted.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }

        return itemDeleted;
    }


    public void editItem(String itemCode, String newName, String newSupplierID, int newStockLevel, double newUnitPrice,
            int newReorderLevel) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(itemCode + ",")) {
                    // Replace the line with the new data
                    String updatedLine = itemCode + "," + newName + "," + newSupplierID + "," + newStockLevel + "," + newUnitPrice + ","
                            + newReorderLevel;
                    lines.add(updatedLine);
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Item ID not found. No update made.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Item updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
    
    public Item findItemByID(String itemID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6) continue; // Skip invalid lines

            // Extract values from CSV
                String id = parts[0].trim();
                String name = parts[1].trim();
                String supplierID = parts[2].trim();
                int stockLevel = Integer.parseInt(parts[3].trim());
                double unitPrice = Double.parseDouble(parts[4].trim());
                int reorderLevel = Integer.parseInt(parts[5].trim());

            // Compare with user input
                if (id.equalsIgnoreCase(itemID)) {
                    return new Item(id, name, supplierID, stockLevel, unitPrice, reorderLevel);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading item file: " + e.getMessage());
        }

        return null; // Not found
    }
}

