package com.group.apomsproject;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PurchaseManager extends User{
    private String PMID;
    
    private FileOperations fh = new FileOperations();

    public PurchaseManager(String PMID, String userName, String userPassword, String userAddress, String userContact) {
        super(userName, userPassword, userAddress, userContact);
        this.PMID = PMID;
    }

    public String getPMID() {
        return PMID;
    }

    public void setPMID(String PMID) {
        this.PMID = PMID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
    
    public DefaultTableModel viewTable(String className)
    {
        return fh.getTable(className);
    }
    
    public List<PurchaseRequisition> recreatePRs()
    {
        return fh.recreateObj("PurchaseRequisition");
    }
}
