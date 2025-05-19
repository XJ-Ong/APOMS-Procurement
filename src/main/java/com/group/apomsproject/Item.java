package com.group.apomsproject;

public class Item {
    private String itemCode;
    private String itemName;
    private String supplierID;
    private int stockLevel;
    private double unitPrice;
    private int reorderLevel;

    public Item(String itemCode, String itemName, String supplierId, int stockLevel, double unitPrice, int reorderLevel) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.supplierID = supplierID;
        this.stockLevel = stockLevel;
        this.unitPrice = unitPrice;
        this.reorderLevel = reorderLevel;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    
    
}
