package com.cse.oms.Network;

public class CustomerResponse {

    int TerritoryId;
    String TerritoryName;
    String SCId;
    String DepotName;
    String CustomerId;
    String Name;
    String Address;

    public int getTerritoryId() {
        return TerritoryId;
    }

    public void setTerritoryId(int territoryId) {
        TerritoryId = territoryId;
    }

    public String getTerritoryName() {
        return TerritoryName;
    }

    public void setTerritoryName(String territoryName) {
        TerritoryName = territoryName;
    }

    public String getSCId() {
        return SCId;
    }

    public void setSCId(String SCId) {
        this.SCId = SCId;
    }

    public String getDepotName() {
        return DepotName;
    }

    public void setDepotName(String depotName) {
        DepotName = depotName;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
