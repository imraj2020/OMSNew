package com.cse.oms.ui.createorder;

public class UiModificationEvent {
    private boolean modifyUi;

    public UiModificationEvent(boolean modifyUi) {
        this.modifyUi = modifyUi;
    }

    public boolean isModifyUi() {
        return modifyUi;
    }

    public void setModifyUi(boolean modifyUi) {
        this.modifyUi = modifyUi;
    }
}
