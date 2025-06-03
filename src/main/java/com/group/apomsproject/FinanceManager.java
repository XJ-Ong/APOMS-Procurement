package com.group.apomsproject;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class FinanceManager extends User{
    
    private String FMID;

    private FileOperations fh = new FileOperations();
    
    public FinanceManager(String FMID, String userName, String userPassword, String userAddress, String userContact)
    {
        super(userName, userPassword, userAddress, userContact);
        this.FMID = FMID;
    }

    public String getFMID() {
        return FMID;
    }

    public void setFMID(String FMID) {
        this.FMID = FMID;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public DefaultTableModel viewTable(String className)
    {
        return fh.getTable(className);
    }
    
    public List<PurchaseOrder> recreatePOs()
    {
        return fh.recreateObj("PurchaseOrder");
    }
    
    public void updateObject(Object obj, String ID)
    {
        fh.UpdateFile(obj, ID);
    }
    
    public void addIL(String ilid, String poid, String itemid, String spid, int quantity, String date, String fmid)
    {
        ImportList il = new ImportList(ilid, poid, itemid, spid, quantity, "pending", date, fmid);
        fh.WriteFile(il);
    }
    
    public List<ImportList> recreateILs()
    {
        return fh.recreateObj("ImportList");
    }
    
    public List<ItemSupplierMap> recreateISMs()
    {
        return fh.recreateObj("ItemSupplierMap");
    }
    
    public void addFR(String frid, String ilid, String itemid, String spid, double pay, String date, String fmid)
    {
        FinancialReport fr = new FinancialReport(frid, ilid, itemid, spid, pay, date, fmid);
        fh.WriteFile(fr);
    }
}
