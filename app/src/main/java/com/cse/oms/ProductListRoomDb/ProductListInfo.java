package com.cse.oms.ProductListRoomDb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductListInfo {

    @PrimaryKey @NonNull
    int productid;
    String productcode;
    String name;
    String description;
    float tradeprice;
    int productstrength; //confusion it is integer or float
    float packsize;
    int productfamilyid;
    String productfamilyname;
    String productcategory;
    float cogs; //Confused about its type
    float mrp;
    float vat;
    float vatunit; // Confused about its type
    float discounted; // Confused about its type
    float tpunit; // Confused about its type
    int coldchangeproduct; // Confused about its type

    private double amount;
    private int quantity;

    public ProductListInfo(int productid, String productcode, String name, String description, float tradeprice, int productstrength, float packsize, int productfamilyid, String productfamilyname, String productcategory, float cogs, float mrp, float vat, float vatunit, float discounted, float tpunit, int coldchangeproduct) {
        this.productid = productid;
        this.productcode = productcode;
        this.name = name;
        this.description = description;
        this.tradeprice = tradeprice;
        this.productstrength = productstrength;
        this.packsize = packsize;
        this.productfamilyid = productfamilyid;
        this.productfamilyname = productfamilyname;
        this.productcategory = productcategory;
        this.cogs = cogs;
        this.mrp = mrp;
        this.vat = vat;
        this.vatunit = vatunit;
        this.discounted = discounted;
        this.tpunit = tpunit;
        this.coldchangeproduct = coldchangeproduct;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTradeprice() {
        return tradeprice;
    }

    public void setTradeprice(float tradeprice) {
        this.tradeprice = tradeprice;
    }

    public int getProductstrength() {
        return productstrength;
    }

    public void setProductstrength(int productstrength) {
        this.productstrength = productstrength;
    }

    public float getPacksize() {
        return packsize;
    }

    public void setPacksize(float packsize) {
        this.packsize = packsize;
    }

    public int getProductfamilyid() {
        return productfamilyid;
    }

    public void setProductfamilyid(int productfamilyid) {
        this.productfamilyid = productfamilyid;
    }

    public String getProductfamilyname() {
        return productfamilyname;
    }

    public void setProductfamilyname(String productfamilyname) {
        this.productfamilyname = productfamilyname;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public float getCogs() {
        return cogs;
    }

    public void setCogs(float cogs) {
        this.cogs = cogs;
    }

    public float getMrp() {
        return mrp;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public float getVatunit() {
        return vatunit;
    }

    public void setVatunit(float vatunit) {
        this.vatunit = vatunit;
    }

    public float getDiscounted() {
        return discounted;
    }

    public void setDiscounted(float discounted) {
        this.discounted = discounted;
    }

    public float getTpunit() {
        return tpunit;
    }

    public void setTpunit(float tpunit) {
        this.tpunit = tpunit;
    }

    public int getColdchangeproduct() {
        return coldchangeproduct;
    }

    public void setColdchangeproduct(int coldchangeproduct) {
        this.coldchangeproduct = coldchangeproduct;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
