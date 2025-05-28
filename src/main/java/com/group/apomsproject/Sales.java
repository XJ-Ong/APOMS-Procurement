package com.group.apomsproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sales {
    
    private String salesID;
    private String itemCode;
    private int quantitySold;
    private String SMID;

    public Sales(String salesID, String itemCode, int quantitySold, String SMID) {
        this.salesID = salesID;
        this.itemCode = itemCode;
        this.quantitySold = quantitySold;
        this.SMID = SMID;
    }

    public String getSalesID() {
        return salesID;
    }
    
    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getItemCode() {
        return itemCode;
    }
    
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantitySold() {
        return quantitySold;
    }
    
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
   
    public String getSMID(){
        return SMID;
    }
    
    public void setSMID(String SMID){
        this.SMID = SMID;
    }
    
    public String toCSV() {
        return salesID + "," + itemCode + "," + quantitySold + "," + SMID;
    }
    
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
}
