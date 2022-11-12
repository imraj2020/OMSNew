package com.cse.oms.CustomerListRoomDb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CustomerListInfo {


    int territoryid;
    String territoryname;
    String scid;
    String depotname;
    @PrimaryKey @NonNull
    String customerid;
    String name;
    String address;

    public CustomerListInfo(int territoryid, String territoryname, String scid, String depotname, @NonNull String customerid, String name, String address) {
        this.territoryid = territoryid;
        this.territoryname = territoryname;
        this.scid = scid;
        this.depotname = depotname;
        this.customerid = customerid;
        this.name = name;
        this.address = address;
    }

    public int getTerritoryid() {
        return territoryid;
    }

    public void setTerritoryid(int territoryid) {
        this.territoryid = territoryid;
    }

    public String getTerritoryname() {
        return territoryname;
    }

    public void setTerritoryname(String territoryname) {
        this.territoryname = territoryname;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }

    public String getDepotname() {
        return depotname;
    }

    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }

    @NonNull
    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(@NonNull String customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
