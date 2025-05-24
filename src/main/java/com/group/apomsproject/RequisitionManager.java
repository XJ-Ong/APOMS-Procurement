/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.apomsproject;

/**
 *
 * @author User
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class RequisitionManager {
    private final String file = "requisitions.csv";

    public void addRequisition(PurchaseRequisition req) {
        if (req == null
                || req.getPRID() == null
                || req.getDeliveryDate() == null
                || req.getStatus() == null
                || req.getSMID() == null
                || req.getQuantity() <= 0) {

            System.out.println("Invalid requisition entry. Please provide all required details.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(req.toCSV());
            writer.newLine();
            System.out.println("Requisition saved: " + req.getPRID());
        } catch (IOException e) {
            System.out.println("Error writing requisition: " + e.getMessage());
        }
    }

    public boolean deleteRequisition(String prID) {
        List<String> lines = new ArrayList<>();
        boolean prDeleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(prID + ",")) {
                    prDeleted = true; // Mark item for deletion
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
            System.out.println("Purchase requisition deleted.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }

        return prDeleted;
    }

    public void editRequisition(String requisitionId, int newQuantity, String newDeliveryDate, String newStatus,
            String newSMID) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(requisitionId + ",")) {
                    String updatedLine = requisitionId + "," + newQuantity + "," + newDeliveryDate + "," + newStatus
                            + "," + newSMID;
                    lines.add(updatedLine);
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading requisitions file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("Requisition ID not found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Requisition updated.");
        } catch (IOException e) {
            System.out.println("Error writing requisitions file: " + e.getMessage());
        }
    }

    public void displayRequisitions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Requisitions:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading requisitions: " + e.getMessage());
        }
    }
    
    public PurchaseRequisition findRequisitionByID(String prID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 5) continue; // Skip invalid lines

            // Extract values from CSV
                String id = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());
                String date = parts[2].trim();
                String status = parts[3].trim();
                String smID = parts[4].trim();


            // Compare with user input
                if (id.equalsIgnoreCase(prID)) {
                    return new PurchaseRequisition(id, quantity, date, status, smID);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading item file: " + e.getMessage());
        }

        return null; // Not found
    }   
    
    public List<PurchaseRequisition> getAllRequisition() {
        List<PurchaseRequisition> purchaseRequisition = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 5) continue; // Skip invalid lines

                String PRID = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());
                String deliveryDate = parts[2].trim();
                String status = parts[3].trim();
                String smID = parts[4].trim();

                PurchaseRequisition pRequisition = new PurchaseRequisition(PRID, quantity, deliveryDate, status, smID);
                purchaseRequisition.add(pRequisition);
            }

        } catch (IOException | NumberFormatException e) {
        System.out.println("Error loading items from CSV: " + e.getMessage());
        }

        return purchaseRequisition;
    }
}

