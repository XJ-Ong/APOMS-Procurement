/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.apomsproject;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 *
 * @author sitesh
 */
public class Searching {
    public DefaultTableModel searchItems(String keyword, String csvFilePath) {
        DefaultTableModel model = new DefaultTableModel();
        keyword = keyword.trim().toLowerCase();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] values = line.split(",");

                if (isFirstLine) {
                    for (String column : values) {
                        model.addColumn(column.trim());
                    }
                    isFirstLine = false;
                    continue;
                }

                if (values.length >= 3) {
                    String itemId = values[0].toLowerCase();
                    String itemName = values[1].toLowerCase();

                    if (itemId.contains(keyword) || itemName.contains(keyword) || keyword.isEmpty()) {
                        model.addRow(values);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }

        return model;
    }
    
}
