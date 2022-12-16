
package com.cse.oms.ui.createorder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubmitOrder {

    @SerializedName("OrderDetails")
    @Expose
    private List<OrderProductsModel> orderDetails = null;
    @SerializedName("CustomerId")
    @Expose
    private String customerId;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("EntryDateTime")
    @Expose
    private String entryDateTime;
    @SerializedName("EntryBy")
    @Expose
    private Integer entryBy;
    @SerializedName("Note")
    @Expose
    private String note;
    @SerializedName("TerritoryId")
    @Expose
    private Integer territoryId;
    @SerializedName("SCId")
    @Expose
    private String sCId;
    @SerializedName("OnBehalfOfEmpId")
    @Expose
    private Integer onBehalfOfEmpId;

    public List<OrderProductsModel> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderProductsModel> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(String entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public Integer getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(Integer entryBy) {
        this.entryBy = entryBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(Integer territoryId) {
        this.territoryId = territoryId;
    }

    public String getSCId() {
        return sCId;
    }

    public void setSCId(String sCId) {
        this.sCId = sCId;
    }

    public Integer getOnBehalfOfEmpId() {
        return onBehalfOfEmpId;
    }

    public void setOnBehalfOfEmpId(Integer onBehalfOfEmpId) {
        this.onBehalfOfEmpId = onBehalfOfEmpId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SubmitOrder.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderDetails");
        sb.append('=');
        sb.append(((this.orderDetails == null) ? "<null>" : this.orderDetails.toString()));
        sb.append(',');
        sb.append("customerId");
        sb.append('=');
        sb.append(((this.customerId == null) ? "<null>" : this.customerId));
        sb.append(',');
        sb.append("orderDate");
        sb.append('=');
        sb.append(((this.orderDate == null) ? "<null>" : this.orderDate));
        sb.append(',');
        sb.append("deliveryDate");
        sb.append('=');
        sb.append(((this.deliveryDate == null) ? "<null>" : this.deliveryDate));
        sb.append(',');
        sb.append("entryDateTime");
        sb.append('=');
        sb.append(((this.entryDateTime == null) ? "<null>" : this.entryDateTime));
        sb.append(',');
        sb.append("entryBy");
        sb.append('=');
        sb.append(((this.entryBy == null) ? "<null>" : this.entryBy));
        sb.append(',');
        sb.append("note");
        sb.append('=');
        sb.append(((this.note == null) ? "<null>" : this.note));
        sb.append(',');
        sb.append("territoryId");
        sb.append('=');
        sb.append(((this.territoryId == null) ? "<null>" : this.territoryId));
        sb.append(',');
        sb.append("sCId");
        sb.append('=');
        sb.append(((this.sCId == null) ? "<null>" : this.sCId));
        sb.append(',');
        sb.append("onBehalfOfEmpId");
        sb.append('=');
        sb.append(((this.onBehalfOfEmpId == null) ? "<null>" : this.onBehalfOfEmpId));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
