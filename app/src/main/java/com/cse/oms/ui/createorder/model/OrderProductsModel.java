
package com.cse.oms.ui.createorder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderProductsModel implements Serializable {
    @SerializedName("ProductId")
    @Expose
    private Integer ProductId;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("TradePrice")
    @Expose
    private double TradePrice;
    @SerializedName("PackSize")
    @Expose
    private double PackSize;
    @SerializedName("MRP")
    @Expose
    private double MRP;
    @SerializedName("Vat")
    @Expose
    private double Vat;
    @SerializedName("Quantity")
    @Expose
    private double quantity;
    @SerializedName("UnitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("Status")
    @Expose
    private Integer status;
    private double Amount;
    private boolean isChecked;

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getTradePrice() {
        return TradePrice;
    }

    public void setTradePrice(double tradePrice) {
        TradePrice = tradePrice;
    }

    public double getPackSize() {
        return PackSize;
    }

    public void setPackSize(double packSize) {
        PackSize = packSize;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public double getVat() {
        return Vat;
    }

    public void setVat(double vat) {
        Vat = vat;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "OrderProductsModel{" +
                "ProductId=" + ProductId +
                ", Name='" + Name + '\'' +
                ", TradePrice=" + TradePrice +
                ", PackSize=" + PackSize +
                ", MRP=" + MRP +
                ", Vat=" + Vat +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", status=" + status +
                ", Amount=" + Amount +
                '}';
    }
}