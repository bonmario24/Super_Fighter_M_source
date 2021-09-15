package com.xhuyu.component.mvp.model;

import java.io.Serializable;

public class WalletOrderBean implements Serializable {
    private String amount;
    private String currency;
    private String productName;
    private String trade_code;
    private String trade_url;
    private String walletAmount;

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName2) {
        this.productName = productName2;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency2) {
        this.currency = currency2;
    }

    public String getWalletAmount() {
        return this.walletAmount;
    }

    public void setWalletAmount(String walletAmount2) {
        this.walletAmount = walletAmount2;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount2) {
        this.amount = amount2;
    }

    public String getTrade_code() {
        return this.trade_code;
    }

    public void setTrade_code(String trade_code2) {
        this.trade_code = trade_code2;
    }

    public String getTrade_url() {
        return this.trade_url;
    }

    public void setTrade_url(String trade_url2) {
        this.trade_url = trade_url2;
    }
}
