package com.cse.oms.ui.createorder.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitOrderResponce {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("OrderVersion")
    @Expose
    private Integer orderVersion;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderVersion() {
        return orderVersion;
    }

    public void setOrderVersion(Integer orderVersion) {
        this.orderVersion = orderVersion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SubmitOrderResponce.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("success");
        sb.append('=');
        sb.append(((this.success == null) ? "<null>" : this.success));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null) ? "<null>" : this.message));
        sb.append(',');
        sb.append("orderNo");
        sb.append('=');
        sb.append(((this.orderNo == null) ? "<null>" : this.orderNo));
        sb.append(',');
        sb.append("orderVersion");
        sb.append('=');
        sb.append(((this.orderVersion == null) ? "<null>" : this.orderVersion));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
