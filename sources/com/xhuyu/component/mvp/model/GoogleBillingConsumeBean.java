package com.xhuyu.component.mvp.model;

import com.android.billingclient.api.Purchase;

public class GoogleBillingConsumeBean {
    private boolean needPullPay;
    private Purchase purchase;

    public GoogleBillingConsumeBean(Purchase purchase2, boolean needPullPay2) {
        this.purchase = purchase2;
        this.needPullPay = needPullPay2;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchase purchase2) {
        this.purchase = purchase2;
    }

    public boolean isNeedPullPay() {
        return this.needPullPay;
    }

    public void setNeedPullPay(boolean needPullPay2) {
        this.needPullPay = needPullPay2;
    }
}
