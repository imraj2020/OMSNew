
package com.cse.oms.ui.createorder.model.OrderInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderBaicInfoResponse {

    @SerializedName("OrderBaicInfo")
    @Expose
    private OrderBaicInfo orderBaicInfo;
    @SerializedName("OrderItemList")
    @Expose
    private List<OrderItem> orderItemList = null;

    public OrderBaicInfo getOrderBaicInfo() {
        return orderBaicInfo;
    }

    public void setOrderBaicInfo(OrderBaicInfo orderBaicInfo) {
        this.orderBaicInfo = orderBaicInfo;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrderBaicInfoResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("orderBaicInfo");
        sb.append('=');
        sb.append(((this.orderBaicInfo == null) ? "<null>" : this.orderBaicInfo));
        sb.append(',');
        sb.append("orderItemList");
        sb.append('=');
        sb.append(((this.orderItemList == null) ? "<null>" : this.orderItemList));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
