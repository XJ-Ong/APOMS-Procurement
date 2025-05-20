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

    public void deleteItem(String itemID) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(itemID + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Item deleted.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void editItem(String itemCode, String newName, String newSupplierID, double newUnitPrice,
            int newReorderLevel) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(itemCode + ",")) {
                    // Replace the line with the new data
                    String updatedLine = itemCode + "," + newName + "," + newSupplierID + "," + newUnitPrice + ","
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
}

