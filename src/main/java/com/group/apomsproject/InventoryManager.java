package com.group.apomsproject;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InventoryManager extends User
{
    private String IMID;
    
    private FileOperations fh = new FileOperations();

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
    
    public DefaultTableModel LoadInventory()
    {
        FileOperations fh = new FileOperations();
        DefaultTableModel model = fh.getTable("Item");
        return model;
    }
    
    public DefaultTableModel viewTable(String className)
    {
        return fh.getTable(className);
    }
    
    public List<ImportList> recreateILs()
    {
        return fh.recreateObj("ImportList");
    }
    
    public void updateObject(Object obj, String ID)
    {
        fh.UpdateFile(obj, ID);
    }
    
    public Item recreateItem(String id)
    {
        List<Item> items = fh.recreateObj("Item");
        for(Item itm : items)
        {
            if(id.equals(itm.getItemID()))
            {
                return itm;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Item not found for " + id);
        return null;
    }
}
