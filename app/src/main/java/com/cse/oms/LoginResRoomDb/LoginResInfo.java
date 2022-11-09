package com.cse.oms.LoginResRoomDb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoginResInfo {

    @PrimaryKey @NonNull
    int empId;
    String fullName;
    String empNetworkId;
    String empCode;
    String email;
    String mobileNo;
    String departmentName;
    String designationName;
    int bUId;
    String bUName;
    int salesLineId;
    String salesLineName;
    int regionId;
    String regionName;
    int teamId;
    String teamName;
    int territoryId;
    String territoryName;

    public LoginResInfo(int empId, String fullName, String empNetworkId, String empCode, String email, String mobileNo, String departmentName, String designationName, int bUId, String bUName, int salesLineId, String salesLineName, int regionId, String regionName, int teamId, String teamName, int territoryId, String territoryName) {
        this.empId = empId;
        this.fullName = fullName;
        this.empNetworkId = empNetworkId;
        this.empCode = empCode;
        this.email = email;
        this.mobileNo = mobileNo;
        this.departmentName = departmentName;
        this.designationName = designationName;
        this.bUId = bUId;
        this.bUName = bUName;
        this.salesLineId = salesLineId;
        this.salesLineName = salesLineName;
        this.regionId = regionId;
        this.regionName = regionName;
        this.teamId = teamId;
        this.teamName = teamName;
        this.territoryId = territoryId;
        this.territoryName = territoryName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmpNetworkId() {
        return empNetworkId;
    }

    public void setEmpNetworkId(String empNetworkId) {
        this.empNetworkId = empNetworkId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public int getbUId() {
        return bUId;
    }

    public void setbUId(int bUId) {
        this.bUId = bUId;
    }

    public String getbUName() {
        return bUName;
    }

    public void setbUName(String bUName) {
        this.bUName = bUName;
    }

    public int getSalesLineId() {
        return salesLineId;
    }

    public void setSalesLineId(int salesLineId) {
        this.salesLineId = salesLineId;
    }

    public String getSalesLineName() {
        return salesLineName;
    }

    public void setSalesLineName(String salesLineName) {
        this.salesLineName = salesLineName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(int territoryId) {
        this.territoryId = territoryId;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }
}
