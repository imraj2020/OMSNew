package com.cse.oms.Network;

public class Register {
    String networkId;
    String mobileNo;
    String password;
    boolean Success;
    String Message;

    public Register(String networkId, String mobileNo, String password) {
        this.networkId = networkId;
        this.mobileNo = mobileNo;
        this.password = password;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
