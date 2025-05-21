package com.group.apomsproject;

import javax.swing.table.DefaultTableModel;

public class InventoryManager extends User
{
    private String IMID;

    public InventoryManager(String IMID, String userName, String userPassword, String userAddress, String userContact)
    {
        super(userName, userPassword, userAddress, userContact);
        this.IMID = IMID;
    }
    public String getIMID() {
        return IMID;
    }

    public void setIMID(String IMID) {
        this.IMID = IMID;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserAddress() {
        return userAddress;
    }

    @Override
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String getUserContact() {
        return userContact;
    }

    @Override
    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    @Override
    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public DefaultTableModel LoadInventory()
    {
        FileOperations fh = new FileOperations();
        DefaultTableModel model = fh.getTable("Item");
        return model;
    }
    
    public void ClearTable(DefaultTableModel model)
    {
        model.setRowCount(0);
    }
}
