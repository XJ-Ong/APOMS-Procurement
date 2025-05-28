package com.group.apomsproject;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Admin extends User{
    private String AMID;

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

    public DefaultTableModel viewUser(String user)
    {
        FileOperations fh = new FileOperations();
        return fh.getTable(user);
    }
    
    public void createUser(String id, String name, String pass, String address, String contact)
    {
        FileOperations fh = new FileOperations();
        if(id.startsWith("S"))
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
        
        }
        else if(id.startsWith("I"))
        {
        
        }
    }
}
