package com.cse.oms.ui.createorder.model;


import com.cse.oms.CreateOrderRoomDatabase.models.DraftOrderModel;

public class ShowDraftOrder {
    private DraftOrderModel draftOrderModel;
    private boolean delete;

    public DraftOrderModel getDraftOrderModel() {
        return draftOrderModel;
    }

    public void setDraftOrderModel(DraftOrderModel draftOrderModel) {
        this.draftOrderModel = draftOrderModel;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
