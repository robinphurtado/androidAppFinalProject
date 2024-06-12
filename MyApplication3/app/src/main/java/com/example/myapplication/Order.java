package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "order_table")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int orderId;

    private int userId;

    private int productId;

    private int orderQuantity;

    Date orderDate;

    //constructor
    public Order(int userId, int productId, int orderQuantity, Date orderDate) {
        this.userId = userId;
        this.productId = productId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
    }

    //getters and setters


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
