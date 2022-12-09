package com.cse.oms.Network;

public class ProductResponse {

    int ProductId;
    String ProductCode;
    String Name;
    String Description;
    float TradePrice;
    int ProductStrength; //confusion it is integer or float
    float PackSize;
    int ProductFamilyId;
    String ProductFamilyName;
    String ProductCategory;

    float COGS; //Confused about its type
    float MRP;
    float Vat;

    float VatUnit; // Confused about its type

    int Discounted; // Confused about its type
    float TPUnit; // Confused about its type
    int ColdChangeProduct; // Confused about its type

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getTradePrice() {
        return TradePrice;
    }

    public void setTradePrice(float tradePrice) {
        TradePrice = tradePrice;
    }

    public int getProductStrength() {
        return ProductStrength;
    }

    public void setProductStrength(int productStrength) {
        ProductStrength = productStrength;
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

    public Float getCOGS() {
        return COGS;
    }

    public void setCOGS(Float COGS) {
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

    public Float getVatUnit() {
        return VatUnit;
    }

    public void setVatUnit(Float vatUnit) {
        VatUnit = vatUnit;
    }

    public int getDiscounted() {
        return Discounted;
    }

    public void setDiscounted(int discounted) {
        Discounted = discounted;
    }

    public Float getTPUnit() {
        return TPUnit;
    }

    public void setTPUnit(Float TPUnit) {
        this.TPUnit = TPUnit;
    }

    public int getColdChangeProduct() {
        return ColdChangeProduct;
    }

    public void setColdChangeProduct(int coldChangeProduct) {
        ColdChangeProduct = coldChangeProduct;
    }
}
