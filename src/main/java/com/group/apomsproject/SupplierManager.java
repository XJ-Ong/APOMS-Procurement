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

    public boolean deleteSupplier(String supplierId) {
        List<String> lines = new ArrayList<>();
        boolean supplierDeleted = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(supplierId + ",")) {
                    supplierDeleted = true;
                    continue;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            System.out.println("Supplier deleted.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }
        
        return supplierDeleted;
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
    
    public Supplier findSupplierByID(String supplierID){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length !=3) continue;
                
                String id = parts[0].trim();
                String name = parts[1].trim();
                String contact = parts[2].trim();
                
                if (id.equalsIgnoreCase(supplierID)){
                    return new Supplier(id, name, contact);
                }
            }
        } catch (IOException | NumberFormatException e){
            System.out.println("Error reading supplier file: " + e.getMessage());
        }
        
        return null;
    }
}

