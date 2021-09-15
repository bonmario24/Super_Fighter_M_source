package com.xhuyu.component.mvp.model;

import java.io.Serializable;
import java.util.List;

public class PayWayBean implements Serializable {
    private List<PayWayBean> child;
    private String currency = "";
    private int is_expanded;
    private String name = "";
    private String tips;
    private int type;
    private String wallet_amount = "";

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency2) {
        this.currency = currency2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public boolean isExpanded() {
        if (this.is_expanded == 1) {
            return true;
        }
        return false;
    }

    public String getWallet_amount() {
        return this.wallet_amount;
    }

    public void setWallet_amount(String wallet_amount2) {
        this.wallet_amount = wallet_amount2;
    }

    public int getIs_expanded() {
        return this.is_expanded;
    }

    public void setIs_expanded(int is_expanded2) {
        this.is_expanded = is_expanded2;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String tips2) {
        this.tips = tips2;
    }

    public PayWayBean() {
    }

    public PayWayBean(int type2, String name2) {
        this.type = type2;
        this.name = name2;
    }

    public List<PayWayBean> getChild() {
        return this.child;
    }

    public void setChild(List<PayWayBean> child2) {
        this.child = child2;
    }

    public PayWayBean(int type2, String name2, List<PayWayBean> child2) {
        this.type = type2;
        this.name = name2;
        this.child = child2;
    }
}
