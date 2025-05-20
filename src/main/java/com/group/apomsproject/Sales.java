package com.group.apomsproject;

public class Sales {
    
    private String salesID;
    private String itemCode;
    private int quantitySold;
    private String salesDate;
    private String SMID;

    public Sales(String salesID, String itemCode, int quantitySold, String salesDate, String SMID) {
        this.salesID = salesID;
        this.itemCode = itemCode;
        this.quantitySold = quantitySold;
        this.salesDate = salesDate;
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

    public String getSalesDate() {
        return salesDate;
    }
    
    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }
    
    public String getSMID(){
        return SMID;
    }
    
    public void setSMID(String SMID){
        this.SMID = SMID;
    }
    
    public String toCSV() {
        return salesID + "," + itemCode + "," + quantitySold + "," + salesDate + "," + SMID;
    }
}
