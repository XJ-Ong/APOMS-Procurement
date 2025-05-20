/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group.apomsproject;

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PurchaseOrderManager {
    private final String file = "purchase_orders.csv";

    public void listPurchaseOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Purchase Orders:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading purchase orders: " + e.getMessage());
        }
    }
}
