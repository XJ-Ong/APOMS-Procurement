package com.group.apomsproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalesManager {
    private final String salesFile = "daily_sales.csv";

    public void addDailySalesEntry(Sales sale) {
        if (sale == null
                || sale.getSalesID() == null
                || sale.getItemCode() == null
                || sale.getSalesDate() == null
                || sale.getQuantitySold() <= 0
                || sale.getSMID() == null) {

            System.out.println("Invalid sales entry. Please provide all details correctly.");
            return;
        }
        ;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(salesFile, true))) {
            writer.write(sale.toCSV());
            writer.newLine();
            System.out.println("Sales recorded for item ID: " + sale.getItemCode());
        } catch (IOException e) {
            System.out.println("Error saving sales entry: " + e.getMessage());
        }
    }

    // Add delete or edit methods if needed
    public void deleteDailySalesEntry(String salesID, String itemCode) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(salesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(salesID + ",")) {
                    lines.add(line);
                } else {
                    String[] parts = line.split(",");
                    if (parts.length >= 5 && parts[0].equals(salesID) && parts[1].equals(itemCode)) {
                        found = true;
                        continue; // skip this line
                    }
                    lines.add(line);
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(salesFile))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Sales entry deleted.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void editDailySalesEntry(String salesID, String itemCode, int newQty, String newDate, String newSMID) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(salesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(salesID + ",")) {
                    lines.add(line);
                } else {
                    String[] parts = line.split(",");
                    if (parts.length >= 5 && parts[0].equals(salesID) && parts[1].equals(itemCode)) {
                        String updatedLine = salesID + "," + itemCode + "," + newQty + "," + newDate + "," + newSMID;
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(salesFile))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Sales entry updated.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

