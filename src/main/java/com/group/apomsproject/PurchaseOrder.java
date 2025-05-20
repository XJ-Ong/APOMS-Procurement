package com.group.apomsproject;

public class PurchaseOrder {
    protected String POID;
    private String PRID;
    private String itemCode;
    private int quantity;
    private String supplierID;
    private String PMID;
    private String status;
    private boolean isApproved;

    public String getPOID() {
        return POID;
    }

    public void setPOID(String POID) {
        this.POID = POID;
    }

    public String getPRID() {
        return PRID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getPMID() {
        return PMID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
    
    

    public PurchaseOrder(String POID, String PRID, String itemCode, int quantity, String supplierID, String status, String PMID) {
        //if (!isPOIDUnique(POID)) {
        //    throw new IllegalArgumentException("POID must be unique.");
        //}
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        this.POID = POID;
        this.PRID = PRID;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.supplierID = supplierID;
        this.PMID = PMID;
        this.status = "Pending";
        this.isApproved = false;
    }

    //private boolean isPOIDUnique(String POID) {
    //    throw new UnsupportedOperationException("Not supported yet."); 
    //}
}
