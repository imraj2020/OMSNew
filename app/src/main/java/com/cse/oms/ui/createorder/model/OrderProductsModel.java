
package com.cse.oms.ui.createorder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderProductsModel implements Serializable {
    private final static long serialVersionUID = -1365245772360690345L;
    String ProductCode;
    String Name;
    String Description;
    float TradePrice;
    int ProductStrength; //confusion it is integer or float
    float PackSize;
    int ProductFamilyId;
    String ProductFamilyName;
    String ProductCategory;
    String COGS; //Confused about its type
    float MRP;
    float Vat;
    String VatUnit; // Confused about its type
    float Discounted; // Confused about its type
    int TPUnit; // Confused about its type
    String ColdChangeProduct; // Confused about its type
    @SerializedName("ProductId")
    @Expose
    private Integer ProductId;
    private double amount;
    @SerializedName("Quantity")
    @Expose
    private double quantity;
    @SerializedName("UnitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("Status")
    @Expose
    private Integer status;

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

    public Integer getId() {
        return ProductId;
    }

    public void setId(Integer id) {
        this.ProductId = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public float getTradePrice() {
        return TradePrice;
    }

    public void setTradePrice(float tradePrice) {
        TradePrice = tradePrice;
    }


    public float getPackSize() {
        return PackSize;
    }

    public void setPackSize(float packSize) {
        PackSize = packSize;
    }

    public int getProductFamilyId() {
        return ProductFamilyId;
    }

    public void setProductFamilyId(int productFamilyId) {
        ProductFamilyId = productFamilyId;
    }

    public String getProductFamilyName() {
        return ProductFamilyName;
    }

    public void setProductFamilyName(String productFamilyName) {
        ProductFamilyName = productFamilyName;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getCOGS() {
        return COGS;
    }

    public void setCOGS(String COGS) {
        this.COGS = COGS;
    }

    public float getMRP() {
        return MRP;
    }

    public void setMRP(float MRP) {
        this.MRP = MRP;
    }

    public float getVat() {
        return Vat;
    }

    public void setVat(float vat) {
        Vat = vat;
    }

    public String getVatUnit() {
        return VatUnit;
    }

    public void setVatUnit(String vatUnit) {
        VatUnit = vatUnit;
    }

    public float getDiscounted() {
        return Discounted;
    }

    public void setDiscounted(float discounted) {
        Discounted = discounted;
    }

    public int getTPUnit() {
        return TPUnit;
    }

    public void setTPUnit(int TPUnit) {
        this.TPUnit = TPUnit;
    }

    public String getColdChangeProduct() {
        return ColdChangeProduct;
    }

    public void setColdChangeProduct(String coldChangeProduct) {
        ColdChangeProduct = coldChangeProduct;
    }

    @Override
    public String toString() {
        return "OrderProductsModel{" +
                "ProductCode='" + ProductCode + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", TradePrice=" + TradePrice +
                ", ProductStrength=" + ProductStrength +
                ", PackSize=" + PackSize +
                ", ProductFamilyId=" + ProductFamilyId +
                ", ProductFamilyName='" + ProductFamilyName + '\'' +
                ", ProductCategory='" + ProductCategory + '\'' +
                ", COGS='" + COGS + '\'' +
                ", MRP=" + MRP +
                ", Vat=" + Vat +
                ", VatUnit='" + VatUnit + '\'' +
                ", Discounted=" + Discounted +
                ", TPUnit=" + TPUnit +
                ", ColdChangeProduct='" + ColdChangeProduct + '\'' +
                ", ProductId=" + ProductId +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", status=" + status +
                '}';
    }
}
