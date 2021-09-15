package com.eagle.mixsdk.sdk;

public class ProductQueryResult {
    private String currency;
    private String price;
    private String productID;
    private String productName;

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName2) {
        this.productName = productName2;
    }

    public String getProductID() {
        return this.productID;
    }

    public void setProductID(String productID2) {
        this.productID = productID2;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price2) {
        this.price = price2;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency2) {
        this.currency = currency2;
    }
}
