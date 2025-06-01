package com.group.apomsproject;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class SalesManager extends User{
    private String SMID;
    
    private FileOperations fh = new FileOperations();

    public SalesManager(String SMID, String userName, String userPassword, String userAddress, String userContact) {
        super(userName, userPassword, userAddress, userContact);
        this.SMID = SMID;
    }

    public String getSMID() {
        return SMID;
    }

    public void setSMID(String SMID) {
        this.SMID = SMID;
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
    
    public void addSupplier(String id, String name, String address, String contact)
    {
        Supplier sp = new Supplier(id, name, address, contact);
        fh.WriteFile(sp);
    }
    
    public DefaultTableModel viewTable(String className)
    {
        return fh.getTable(className);
    }
    
    public List<Supplier> recreateSuppliers()
    {
        return fh.recreateObj("Supplier");
    }
    
    public void updateObject(Object obj, String ID)
    {
        // Change quantity of item when updating sales record
        if(obj.getClass().getSimpleName().equals("Sales"))
        {
            try
            {
                List<Sales> salesList = fh.recreateObj("Sales");
                Sales originalSales = null;
                for(Sales sales : salesList)
                {
                    Method getSalesIDMethod = sales.getClass().getMethod("getSalesID");
                    String currentSalesID = (String) getSalesIDMethod.invoke(sales);
                    
                    if(currentSalesID.equals(ID))
                    {
                        originalSales = sales;
                        break;
                    }
                }
                
                if(originalSales == null)
                {
                    JOptionPane.showMessageDialog(null, "Sales not found: " + ID);
                    return;
                }
                
                Method getSalesQuantityOG = originalSales.getClass().getMethod("getQuantitySold");
                int originalQuantity = (Integer) getSalesQuantityOG.invoke(originalSales);
                
                fh.UpdateFile(obj, ID);
                
                Method getSalesQuantityNew = obj.getClass().getMethod("getQuantitySold");
                int newQuantity = (Integer) getSalesQuantityNew.invoke(obj);
                
                int quantityChange = originalQuantity - newQuantity;
                updateItemQuantityForSales(ID, quantityChange);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error adjusting item quantity: " + e.getMessage());
            }
        }
        else
        {
            // for other record updates
            fh.UpdateFile(obj, ID);
        }
    }
    
    public boolean deleteObject(String className, String ID)
    {
        if(className.equals("Sales"))
        {
            try
            {
                List<Sales> salesList = fh.recreateObj("Sales");
                Sales targetSales = null;
                for(Sales sales : salesList)
                {
                    Method getSalesIDMethod = sales.getClass().getMethod("getSalesID");
                    String currentSalesID = (String) getSalesIDMethod.invoke(sales);
                    
                    if(currentSalesID.equals(ID))
                    {
                        targetSales = sales;
                        break;
                    }
                }
                
                if(targetSales == null)
                {
                    JOptionPane.showMessageDialog(null, "Sales not found: " + ID);
                    return false;
                }
                
                Method getItemID = targetSales.getClass().getMethod("getItemID");
                Method getSalesQuantity = targetSales.getClass().getMethod("getQuantitySold");
                String itemID = (String) getItemID.invoke(targetSales);
                int quantityChange = 0 - (Integer) getSalesQuantity.invoke(targetSales);
                
                updateItemQuantityForSales(itemID, quantityChange);
                
                return fh.DeleteRecord(className, ID);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error adjusting item quantity: " + e.getMessage());
                return false;
            }
        }
        else
        {
            return fh.DeleteRecord(className, ID);
        }
    }
    
    public void addItem(String id, String name, double price, int quantity, int rol)
    {
        Item itm = new Item(id, name, price, quantity, rol);
        fh.WriteFile(itm);
    }
    
    public List<Item> recreateItems()
    {
        return fh.recreateObj("Item");
    }
    
    public String generateISMID()
    {
        return fh.generateISMID();
    }
    
    public void addISM(String id, String itemid, String spid, double importprice)
    {
        ItemSupplierMap ism = new ItemSupplierMap(id, itemid, spid, importprice);
        fh.WriteFile(ism);
    }
    
    public List<ItemSupplierMap> recreateISMs()
    {
        return fh.recreateObj("ItemSupplierMap");
    }
    
    public void addSales(String id, String itemid, int quantity, String date, String smid)
    {
        Sales sl = new Sales(id, itemid, quantity, date, smid);
        fh.WriteFile(sl);
        updateItemQuantityForSales(itemid, quantity);
    }
    
    private void updateItemQuantityForSales(String id, int quantity)
    {
        try
        {
            List<Item> items = fh.recreateObj("Item");
            Item targetItem = null;
            for(Item item : items)
            {
                Method getItemIDMethod = item.getClass().getMethod("getItemID");
                String currentItemID = (String) getItemIDMethod.invoke(item);
                if(currentItemID.equals(id))
                {
                    targetItem = item;
                    break;
                }
            }
            
            if(targetItem == null)
            {
                JOptionPane.showMessageDialog(null, "ItemID not found: " + id);
                return;
            }
            
            int currentQuantity = targetItem.getQuantity();
            int newQuantity = currentQuantity - quantity;
            
            if(newQuantity < 0)
            {
                JOptionPane.showMessageDialog(null, "Error: Not enough items for " +id);
                return;
            }
            
            targetItem.setQuantity(newQuantity);
            
            fh.UpdateFile(targetItem, id);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error updating item quantity: " + e.getMessage());
        }
    }
    
    public List<Sales> recreateSales()
    {
        return fh.recreateObj("Sales");
    }
    
    public void addPR(String id, String itemid, int quantity, String date, String smid)
    {
        PurchaseRequisition pr = new PurchaseRequisition(id, itemid, quantity, "pending", date, smid);
        fh.WriteFile(pr);
    }
    
    public List<PurchaseRequisition> recreatePRs()
    {
        return fh.recreateObj("PurchaseRequisition");
    }
}


