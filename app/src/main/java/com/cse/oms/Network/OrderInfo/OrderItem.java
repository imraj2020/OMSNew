
package com.cse.oms.Network.OrderInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItem {

    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductCode")
    @Expose
    private String productCode;
    @SerializedName("Quantity")
    @Expose
    private Double quantity;
    @SerializedName("UnitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("TotalPrice")
    @Expose
    private Double totalPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrderItem.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("productName");
        sb.append('=');
        sb.append(((this.productName == null) ? "<null>" : this.productName));
        sb.append(',');
        sb.append("productCode");
        sb.append('=');
        sb.append(((this.productCode == null) ? "<null>" : this.productCode));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null) ? "<null>" : this.quantity));
        sb.append(',');
        sb.append("unitPrice");
        sb.append('=');
        sb.append(((this.unitPrice == null) ? "<null>" : this.unitPrice));
        sb.append(',');
        sb.append("totalPrice");
        sb.append('=');
        sb.append(((this.totalPrice == null) ? "<null>" : this.totalPrice));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
