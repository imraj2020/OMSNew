
package com.cse.oms.ui.createorder.model.OrderInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderBaicInfo {

    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("OrderVersion")
    @Expose
    private Integer orderVersion;
    @SerializedName("Note")
    @Expose
    private String note;
    @SerializedName("SalesCenterName")
    @Expose
    private String salesCenterName;
    @SerializedName("TotalOrderPrice")
    @Expose
    private Double totalOrderPrice;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("EntryByName")
    @Expose
    private String entryByName;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("CustomerMobile")
    @Expose
    private String customerMobile;
    @SerializedName("CustomerAddress")
    @Expose
    private String customerAddress;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderVersion() {
        return orderVersion;
    }

    public void setOrderVersion(Integer orderVersion) {
        this.orderVersion = orderVersion;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSalesCenterName() {
        return salesCenterName;
    }

    public void setSalesCenterName(String salesCenterName) {
        this.salesCenterName = salesCenterName;
    }

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getEntryByName() {
        return entryByName;
    }

    public void setEntryByName(String entryByName) {
        this.entryByName = entryByName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrderBaicInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderNo");
        sb.append('=');
        sb.append(((this.orderNo == null) ? "<null>" : this.orderNo));
        sb.append(',');
        sb.append("orderVersion");
        sb.append('=');
        sb.append(((this.orderVersion == null) ? "<null>" : this.orderVersion));
        sb.append(',');
        sb.append("note");
        sb.append('=');
        sb.append(((this.note == null) ? "<null>" : this.note));
        sb.append(',');
        sb.append("salesCenterName");
        sb.append('=');
        sb.append(((this.salesCenterName == null) ? "<null>" : this.salesCenterName));
        sb.append(',');
        sb.append("totalOrderPrice");
        sb.append('=');
        sb.append(((this.totalOrderPrice == null) ? "<null>" : this.totalOrderPrice));
        sb.append(',');
        sb.append("orderDate");
        sb.append('=');
        sb.append(((this.orderDate == null) ? "<null>" : this.orderDate));
        sb.append(',');
        sb.append("deliveryDate");
        sb.append('=');
        sb.append(((this.deliveryDate == null) ? "<null>" : this.deliveryDate));
        sb.append(',');
        sb.append("entryByName");
        sb.append('=');
        sb.append(((this.entryByName == null) ? "<null>" : this.entryByName));
        sb.append(',');
        sb.append("customerName");
        sb.append('=');
        sb.append(((this.customerName == null) ? "<null>" : this.customerName));
        sb.append(',');
        sb.append("customerEmail");
        sb.append('=');
        sb.append(((this.customerEmail == null) ? "<null>" : this.customerEmail));
        sb.append(',');
        sb.append("customerMobile");
        sb.append('=');
        sb.append(((this.customerMobile == null) ? "<null>" : this.customerMobile));
        sb.append(',');
        sb.append("customerAddress");
        sb.append('=');
        sb.append(((this.customerAddress == null) ? "<null>" : this.customerAddress));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
