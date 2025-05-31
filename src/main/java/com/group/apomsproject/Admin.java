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
        if(id.startsWith("A"))
        {
            Admin am = new Admin(id, name, pass, address, contact);
            fh.WriteFile(am);
        }
        else if(id.startsWith("S"))
        {
            SalesManager sm = new SalesManager(id, name, pass, address, contact);
            fh.WriteFile(sm);
        }
        else if(id.startsWith("P"))
        {
            PurchaseManager pm = new PurchaseManager(id, name, pass, address, contact);
            fh.WriteFile(pm);
        }
        else if(id.startsWith("F"))
        {
            FinanceManager fm = new FinanceManager(id, name, pass, address, contact);
            fh.WriteFile(fm);
        }
        else if(id.startsWith("I"))
        {
            InventoryManager im = new InventoryManager(id, name, pass, address, contact);
            fh.WriteFile(im);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error ID Entered");
        }
    }
    
    public DefaultTableModel viewUserTable(String className)
    {
        return fh.getTable(className);
    }
    
    public void updateUser(Object user, String ID)
    {
        fh.UpdateFile(user, ID);
    }
        
    public void deleteUser(Object user, String ID)
    {
        Class<?> userObj = user.getClass();
        String className = userObj.getSimpleName();
        fh.DeleteRecord(className, ID);
    }
    
    public Object getUserIDFromList(List<Object> users, String enteredID, String idGetter)
    {
        Object user = fh.getIDFromList(users, enteredID, idGetter);
        return user;
    }
}