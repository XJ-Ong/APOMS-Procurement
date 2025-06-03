package com.group.apomsproject;

public class ItemSupplierMap {
    private String ISMID;
    private String itemID;
    private String supplierID;
    private double importPrice;

    public ItemSupplierMap(String ISMID, String itemID, String supplierID, double importPrice) {
        this.ISMID = ISMID;
        this.itemID = itemID;
        this.supplierID = supplierID;
        this.importPrice = importPrice;
    }

    public String getISMID() {
        return ISMID;
    }

    public void setISMID(String ISMID) {
        this.ISMID = ISMID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }
}
