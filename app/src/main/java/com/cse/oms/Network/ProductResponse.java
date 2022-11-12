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
    String COGS; //Confused about its type
    float MRP;
    float Vat;
    String VatUnit; // Confused about its type
    float Discounted; // Confused about its type
    int TPUnit; // Confused about its type
    String ColdChangeProduct; // Confused about its type

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
}
