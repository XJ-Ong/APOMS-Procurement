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
import java.util.Scanner;

public class SalesManagerApp {

    public static String generateSalesID(String salesFile) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(salesFile))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading sales file: " + e.getMessage());
        }

        // Format the ID with leading zeros, e.g., S001, S010
        return String.format("SA%03d", count);
    }

    public static String generateItemID(String itemFile) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(itemFile))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading sales file: " + e.getMessage());
        }

        // Format the ID with leading zeros, e.g., S001, S010
        return String.format("I%03d", count);
    }

    public static String generateSupplierID(String supplierFile) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(supplierFile))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading sales file: " + e.getMessage());
        }

        // Format the ID with leading zeros, e.g., S001, S010
        return String.format("SU%03d", count);
    }

    public static String generateRequisitionID(String requisitionFile) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(requisitionFile))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading requisition file: " + e.getMessage());
        }

        // Format the ID with leading zeros, e.g., R001, R010
        return String.format("RE%03d", count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItemManager itemManager = new ItemManager();
        SupplierManager supplierManager = new SupplierManager();
        RequisitionManager requisitionManager = new RequisitionManager();
        PurchaseOrderManager poManager = new PurchaseOrderManager();
        SalesManager salesManager = new SalesManager();

        System.out.println("Welcome to the Sales Manager App!");
        System.out.println("Please select an option:");
        System.out.println("1. Item Manager");
        System.out.println("2. Supplier Manager");
        System.out.println("3. Sales Manager");
        System.out.println("4. Purchase Requisition Manager");
        System.out.println("5. Purchase Order Manager");
        System.out.println("6. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.println("Item Manager selected.");
                System.out.println("Please select an option:");
                System.out.println("1. Add Item");
                System.out.println("2. Edit Item");
                System.out.println("3. Delete Item");
                System.out.println("4. Exit");
                int itemManagerChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (itemManagerChoice) {
                    case 1 -> {
                        System.out.println("Adding Item...");
                        String itemFile = "items.csv";
                        String itemID = generateItemID(itemFile);

                        System.out.print("Enter Item Name: ");
                        String itemName = scanner.nextLine();

                        System.out.print("Enter Supplier ID: ");
                        String supplierID = scanner.nextLine();

                        System.out.print("Enter Stock Level: ");
                        int stockLevel = scanner.nextInt();

                        System.out.print("Enter Unit Price: ");
                        double unitPrice = scanner.nextDouble();

                        System.out.print("Enter Reorder Level: ");
                        int reorderLevel = scanner.nextInt();

                        // Create an Item object
                        Item item = new Item(itemID, itemName, supplierID, stockLevel, unitPrice, reorderLevel);

                        // Add it using the OOP method
                        itemManager.addItem(item);
                        System.out.println("Item added successfully.");                        
                }
                    case 2 -> {
                        System.out.println("Editing Item...");
                        System.out.print("Enter Item ID to edit: ");
                        String itemIDToEdit = scanner.nextLine();
                        System.out.print("Enter new Item Name: ");
                        String newItemName = scanner.nextLine();
                        System.out.print("Enter new Supplier ID: ");
                        String newSupplierID = scanner.nextLine();
                        System.out.print("Enter new Stock Level: ");
                        int newStockLevel = scanner.nextInt();
                        System.out.print("Enter new Unit Price: ");
                        double newUnitPrice = scanner.nextDouble();
                        System.out.print("Enter new Reorder Level: ");
                        int newReorderLevel = scanner.nextInt();
                        // Edit item using correct method signature
                        itemManager.editItem(itemIDToEdit, newItemName, newSupplierID, newStockLevel, newUnitPrice, newReorderLevel);
                        System.out.println("Item edited successfully.");
                }
                    case 3 -> {
                        System.out.println("Deleting Item...");
                        System.out.print("Enter Item ID to delete: ");
                        String itemIDToDelete = scanner.nextLine();
                        itemManager.deleteItem(itemIDToDelete);
                        System.out.println("Item deleted successfully.");
                }
                    case 4 -> {
                        System.out.println("Exiting...");
                        return;
                }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
                break;

            case 2:
                System.out.println("Supplier Manager selected.");
                System.out.println("Please select an option:");
                System.out.println("1. Add Supplier");
                System.out.println("2. Edit Supplier");
                System.out.println("3. Delete Supplier");
                System.out.println("4. Exit");
                int supplierChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (supplierChoice) {
                    case 1 -> {
                        System.out.println("Adding Supplier...");
                        String supplierFile = "suppliers.csv";
                        String suID = generateSupplierID(supplierFile);

                        System.out.print("Enter Supplier Name: ");
                        String supplierName = scanner.nextLine();

                        System.out.print("Enter Supplier Contact: ");
                        String supplierContact = scanner.nextLine();

                        // Create a Supplier object
                        Supplier supplier = new Supplier(suID, supplierName, supplierContact);
                        // Add it using the OOP method
                        supplierManager.addSupplier(supplier);
                        System.out.println("Supplier added successfully.");
                }
                    case 2 -> {
                        System.out.println("Editing Supplier...");
                        System.out.print("Enter Supplier ID to edit: ");
                        String supplierIDToEdit = scanner.nextLine();
                        System.out.print("Enter new Supplier Name: ");
                        String newSupplierName = scanner.nextLine();
                        System.out.print("Enter new Supplier Contact: ");
                        String newSupplierContact = scanner.nextLine();
                        // Edit supplier using correct method signature
                        supplierManager.editSupplier(supplierIDToEdit, newSupplierName, newSupplierContact);
                        System.out.println("Supplier edited successfully.");
                }
                    case 3 -> {
                        System.out.println("Deleting Supplier...");
                        System.out.print("Enter Supplier ID to delete: ");
                        String supplierIDToDelete = scanner.nextLine();
                        supplierManager.deleteSupplier(supplierIDToDelete);
                        System.out.println("Supplier deleted successfully.");
                }
                    case 4 -> {
                        System.out.println("Exiting...");
                        return;
                }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
                break;

            case 3:
                System.out.println("Sales Manager selected.");
                System.out.println("Please select an option:");
                System.out.println("1. Add Daily Sales Entry");
                System.out.println("2. Edit Daily Sales Entry");
                System.out.println("3. Delete Daily Sales Entry");
                System.out.println("4. Exit");
                int salesChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (salesChoice) {
                    case 1 -> {
                        System.out.println("Adding Daily Sales Entry...");
                        String salesFile = "daily_sales.csv";
                        String salesID = generateSalesID(salesFile);

                        System.out.print("Enter Item Code: ");
                        String itemCode = scanner.nextLine();

                        System.out.print("Enter Quantity Sold: ");
                        int quantitySold = scanner.nextInt();

                        System.out.print("Enter Sales Manager ID: ");
                        String smID = scanner.nextLine();

                        // Create a DailySales object
                        Sales sale = new Sales(salesID, itemCode, quantitySold, smID);

                        // Add it using the OOP method
                        salesManager.addDailySalesEntry(sale);
                        System.out.println("Daily sales entry added successfully.");
                }
                    case 2 -> {
                        System.out.println("Editing Daily Sales Entry...");
                        System.out.print("Enter Sales ID to edit: ");
                        String salesIDToEdit = scanner.nextLine();
                        System.out.print("Enter new Item Code: ");
                        String newItemCode = scanner.nextLine();
                        System.out.print("Enter new Quantity Sold: ");
                        int newQuantitySold = scanner.nextInt();
                        System.out.print("Enter new Sales Manager ID: ");
                        String newSMID = scanner.nextLine();
                        // Edit daily sales entry using correct method signature
                        salesManager.editDailySalesEntry(salesIDToEdit, newItemCode, newQuantitySold, newSMID);
                        System.out.println("Daily sales entry edited successfully.");
                }
                    case 3 -> {
                        System.out.println("Deleting Daily Sales Entry...");
                        System.out.print("Enter Sales ID to delete: ");
                        String salesIDToDelete = scanner.nextLine();
                        salesManager.deleteDailySalesEntry(salesIDToDelete);
                        System.out.println("Daily sales entry deleted successfully.");
                }
                    case 4 -> {
                        System.out.println("Exiting...");
                        return;
                }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
                break;

            case 4:
                System.out.println("Purchase Requisition Manager selected.");
                System.out.println("Please select an option:");
                System.out.println("1. Add Purchase Requisition");
                System.out.println("2. Edit Purchase Requisition");
                System.out.println("3. Delete Purchase Requisition");
                System.out.println("4. Exit");
                int requisitionChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (requisitionChoice) {
                    case 1 -> {
                        System.out.println("Adding Purchase Requisition...");
                        String requisitionFile = "purchase_requisitions.csv";
                        String requisitionID = generateRequisitionID(requisitionFile);

                        System.out.print("Enter Quantity: ");
                        int quantity = scanner.nextInt();

                        System.out.print("Enter Delivery Date (YYYY-MM-DD): ");
                        String deliveryDate = scanner.nextLine();

                        String status = "Pending";
                        System.out.print("Enter Sales Manager ID: ");
                        String smID = scanner.nextLine();

                        // Create a PurchaseRequisition object
                        PurchaseRequisition requisition = new PurchaseRequisition(requisitionID, quantity, deliveryDate,
                                status, smID);

                        // Add it using the OOP method
                        requisitionManager.addRequisition(requisition);
                        System.out.println("Purchase requisition added successfully.");
                }
                    case 2 -> {
                        System.out.println("Editing Purchase Requisition...");
                        System.out.print("Enter Requisition ID to edit: ");
                        String requisitionIDToEdit = scanner.nextLine();
                        System.out.print("Enter new Quantity: ");
                        int newQuantity = scanner.nextInt();
                        System.out.print("Enter new Delivery Date (YYYY-MM-DD): ");
                        String newDeliveryDate = scanner.nextLine();
                        String stat = "Pending";
                        System.out.print("Enter new Manager ID: ");
                        String newSMID = scanner.nextLine();
                        // Edit requisition using correct method signature
                        requisitionManager.editRequisition(requisitionIDToEdit, newQuantity, newDeliveryDate, stat,
                                newSMID);
                        System.out.println("Purchase requisition edited successfully.");
                }
                    case 3 -> {
                        System.out.println("Deleting Purchase Requisition...");
                        System.out.print("Enter Requisition ID to delete: ");
                        String requisitionIDToDelete = scanner.nextLine();
                        requisitionManager.deleteRequisition(requisitionIDToDelete);
                        System.out.println("Purchase requisition deleted successfully.");
                }
                    case 4 -> {
                        System.out.println("Exiting...");
                        return;
                }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
                break;

            case 5:
                System.out.println("Purchase Order Manager selected.");
                System.out.println("Please select an option:");
                System.out.println("1. View Purchase Orders");
                System.out.println("2. Exit");
                int poChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (poChoice) {
                    case 1 -> {
                        System.out.println("Listing Purchase Orders...");
                        poManager.listPurchaseOrders();
                }
                    case 2 -> {
                        System.out.println("Exiting...");
                        return;
                }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
                break;

            case 6:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
