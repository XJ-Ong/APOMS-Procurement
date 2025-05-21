package com.group.apomsproject;

public class Order {
    private String orderID;
    private String itemCode;
    private String quantity;
    private String orderDate;
    private String status;

    public Order(String orderID, String itemCode, String quantity, String orderDate, String status) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderID() { return orderID; }
    public String getItemCode() { return itemCode; }
    public String getQuantity() { return quantity; }
    public String getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
}
