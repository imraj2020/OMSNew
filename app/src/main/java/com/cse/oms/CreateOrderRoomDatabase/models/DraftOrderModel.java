package com.cse.oms.CreateOrderRoomDatabase.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DraftOrderModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int orderId;
    private int orderStatus;

    private String dateTime;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
