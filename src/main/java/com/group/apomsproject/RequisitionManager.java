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

    public void deleteRequisition(String requisitionId) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(requisitionId + ",")) {
                    lines.add(line);
                } else {
                    found = true;
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
            System.out.println("Requisition deleted.");
        } catch (IOException e) {
            System.out.println("Error writing requisitions file: " + e.getMessage());
        }
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
}

