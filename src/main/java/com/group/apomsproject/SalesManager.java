package com.group.apomsproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalesManager {
    private final String file = "daily_sales.csv";

    public void addDailySalesEntry(Sales sale) {
        if (sale == null
                || sale.getSalesID() == null
                || sale.getItemCode() == null
                || sale.getQuantitySold() <= 0
                || sale.getSMID() == null) {

            System.out.println("Invalid sales entry. Please provide all details correctly.");
            return;
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(sale.toCSV());
            writer.newLine();
            System.out.println("Sales recorded for item ID: " + sale.getItemCode());
        } catch (IOException e) {
            System.out.println("Error saving sales entry: " + e.getMessage());
        }
    }

    // Add delete or edit methods if needed
    public boolean deleteDailySalesEntry(String salesID) {
        List<String> lines = new ArrayList<>();
        boolean salesDeleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(salesID + ",")) {
                    salesDeleted = true; // Mark item for deletion
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
            System.out.println("Sales deleted.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }

        return salesDeleted;
    }

    public void editDailySalesEntry(String salesID, String itemCode, int newQty, String newSMID) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(salesID + ",")) {
                    lines.add(line);
                } else {
                    String[] parts = line.split(",");
                    if (parts.length >= 4 && parts[0].equals(salesID) && parts[1].equals(itemCode)) {
                        String updatedLine = salesID + "," + itemCode + "," + newQty + "," + newSMID;
                        lines.add(updatedLine);
                        found = true;
                    } else {
                        lines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Sales entry not found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Sales entry updated.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
    
    public Sales findSalesByID(String salesID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue; // Skip invalid lines

            // Extract values from CSV
                String id = parts[0].trim();
                String code = parts[1].trim();
                int quantity = Integer.parseInt(parts[2].trim());
                String smID = parts[3].trim();
                
            // Compare with user input
                if (id.equalsIgnoreCase(salesID)) {
                    return new Sales(id, code, quantity, smID);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading item file: " + e.getMessage());
        }

        return null; // Not found
    }

}

