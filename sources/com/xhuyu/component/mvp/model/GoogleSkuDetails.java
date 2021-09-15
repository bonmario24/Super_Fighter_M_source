package com.xhuyu.component.mvp.model;

public class GoogleSkuDetails {
    private String currency;
    private double price;

    public GoogleSkuDetails(double price2, String currency2) {
        this.price = price2;
        this.currency = currency2;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price2) {
        this.price = price2;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency2) {
        this.currency = currency2;
    }
}
