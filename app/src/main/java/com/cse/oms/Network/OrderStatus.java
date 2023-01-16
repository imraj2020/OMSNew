package com.cse.oms.Network;

public class OrderStatus {

    String OrderNo;


    String Note;
    int TerritoryId;
    String SCId;
    String OrderStatus;
    String status;

    String EmpId;
    String OrderDate;
    String DeliveryDate;
    String CustomerId;


    public OrderStatus(String empId, String orderDate, String deliveryDate, String customerId) {
        EmpId = empId;
        OrderDate = orderDate;
        DeliveryDate = deliveryDate;
        CustomerId = customerId;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }


    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int getTerritoryId() {
        return TerritoryId;
    }

    public void setTerritoryId(int territoryId) {
        TerritoryId = territoryId;
    }

    public String getSCId() {
        return SCId;
    }

    public void setSCId(String SCId) {
        this.SCId = SCId;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
