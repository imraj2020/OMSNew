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
    String cogs; //Confused about its type
    float mrp;
    float vat;
    String vatunit; // Confused about its type
    float discounted; // Confused about its type
    int tpunit; // Confused about its type
    String coldchangeproduct; // Confused about its type


    public ProductListInfo(int productid, String productcode, String name, String description, float tradeprice, int productstrength, float packsize, int productfamilyid, String productfamilyname, String productcategory, String cogs, float mrp, float vat, String vatunit, float discounted, int tpunit, String coldchangeproduct) {
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

    public String getCogs() {
        return cogs;
    }

    public void setCogs(String cogs) {
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

    public String getVatunit() {
        return vatunit;
    }

    public void setVatunit(String vatunit) {
        this.vatunit = vatunit;
    }

    public float getDiscounted() {
        return discounted;
    }

    public void setDiscounted(float discounted) {
        this.discounted = discounted;
    }

    public int getTpunit() {
        return tpunit;
    }

    public void setTpunit(int tpunit) {
        this.tpunit = tpunit;
    }

    public String getColdchangeproduct() {
        return coldchangeproduct;
    }

    public void setColdchangeproduct(String coldchangeproduct) {
        this.coldchangeproduct = coldchangeproduct;
    }
}
