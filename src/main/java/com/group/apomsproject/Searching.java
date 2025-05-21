/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.apomsproject;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

/**
 * @author sitesh
 */
public class Searching {
    public DefaultTableModel searchItems(String keyword, String className, String csvFilePath) {
        DefaultTableModel model = new DefaultTableModel();
        keyword = keyword.trim().toLowerCase();

        try {
            // Initialize FileOperations
            FileOperations fileOps = new FileOperations();

            // Get class dynamically
            Class<?> targetClass = Class.forName("com.group.apomsproject." + className);

            // Get headers from HeaderRegistry
            List<String> headers = HeaderRegistry.getHeaders(targetClass);
            if (headers.isEmpty()) {
                throw new RuntimeException("No headers defined for class: " + className);
            }
            if (headers.size() < 2) {
                throw new RuntimeException("Class " + className + " must have at least two headers for search");
            }

            // Add columns to model
            for (String header : headers) {
                model.addColumn(header);
            }

            // Read CSV data using FileOperations
            List<Map<String, String>> data = fileOps.ReadFile(csvFilePath);

            // Filter rows based on keyword
            for (Map<String, String> row : data) {
                // Ensure row has at least the first two columns
                if (row.containsKey(headers.get(0)) && row.containsKey(headers.get(1))) {
                    String firstColumn = row.getOrDefault(headers.get(0), "").toLowerCase();
                    String secondColumn = row.getOrDefault(headers.get(1), "").toLowerCase();

                    if (firstColumn.contains(keyword) || secondColumn.contains(keyword) || keyword.isEmpty()) {
                        String[] rowData = new String[headers.size()];
                        for (int i = 0; i < headers.size(); i++) {
                            rowData[i] = row.getOrDefault(headers.get(i), "");
                        }
                        model.addRow(rowData);
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found: " + className, e);
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }

        return model;
    }
}