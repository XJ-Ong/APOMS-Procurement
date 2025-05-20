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

public class SupplierManager {
    private final String file = "suppliers.csv";

    public void addSupplier(Supplier supplier) {
        if (supplier == null
                || supplier.getSupplierID() == null
                || supplier.getSupplierName() == null
                || supplier.getSupplierContact() == null) {

            System.out.println("Invalid supplier entry. Please provide all required details.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(supplier.toCSV());
            writer.newLine();
            System.out.println("Supplier saved: " + supplier.getSupplierID());
        } catch (IOException e) {
            System.out.println("Error saving supplier: " + e.getMessage());
        }
    }

    public void deleteSupplier(String supplierId) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(supplierId + ",")) {
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
            System.out.println("Supplier deleted.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public void editSupplier(String supplierID, String newName, String newContactInfo) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(supplierID + ",")) {
                    // Replace the line with the updated supplier info
                    String updatedLine = supplierID + "," + newName + "," + newContactInfo;
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
            System.out.println("Supplier ID not found. No changes made.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Supplier updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

