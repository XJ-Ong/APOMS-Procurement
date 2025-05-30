package com.group.apomsproject;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Admin extends User{
    private String AMID;
    private FileOperations fh = new FileOperations();

    public Admin(String AMID, String userName, String userPassword, String userAddress, String userContact) {
        super(userName, userPassword, userAddress, userContact);
        this.AMID = AMID;
    }

    public String getAMID() {
        return AMID;
    }

    public void setAMID(String AMID) {
        this.AMID = AMID;
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
    
    public void createUser(String id, String name, String pass, String address, String contact)
    {
        String role;
        if(id.startsWith("S"))
        {
            SalesManager sm = new SalesManager(id, name, pass, address, contact);
            fh.WriteFile(sm);
            role = "Sales Manager";
        }
        else if(id.startsWith("P"))
        {
            PurchaseManager pm = new PurchaseManager(id, name, pass, address, contact);
            fh.WriteFile(pm);
            role = "Purchase Manager";
        }
        else if(id.startsWith("F"))
        {
            FinanceManager fm = new FinanceManager(id, name, pass, address, contact);
            fh.WriteFile(fm);
            role = "Finance Manager";
        }
        else if(id.startsWith("I"))
        {
            InventoryManager im = new InventoryManager(id, name, pass, address, contact);
            fh.WriteFile(im);
            role = "Inventory Manager";
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error ID Entered");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "User " + name + " successfully added for " + role);
    }
    
    public DefaultTableModel viewUser(String className)
    {
        return fh.getTable(className);
    }
    
    public void updateUser(Object user, String ID)
    {
        fh.UpdateFile(user, ID, false);
    }
        
    public void deleteUser(Object user, String ID)
    {
        fh.UpdateFile(user, ID, true);
    }
}