package com.eagle.mixsdk.sdk;

import android.text.TextUtils;

public class PayParams {
    private int buyNum;
    private int coinNum;
    private String currency = "CNY";
    private String extension;
    private String gameExtension;
    private String gameName;
    private String gameOrderID = "";
    private String money;
    private String orderID;
    private String payNotifyUrl;
    private int price;
    private String productDesc;
    private String productId;
    private String productName;
    private int ratio;
    private String roleId;
    private int roleLevel;
    private String roleName;
    private String serverId;
    private String serverName;
    private String vip;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId2) {
        this.productId = productId2;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName2) {
        this.productName = productName2;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public void setProductDesc(String productDesc2) {
        this.productDesc = productDesc2;
    }

    public int getPrice() {
        if (this.price == 0) {
            return changePriceToYuan();
        }
        return this.price;
    }

    private int changePriceToYuan() {
        if ("CNY".equals(this.currency) && !TextUtils.isEmpty(this.money)) {
            try {
                return Integer.parseInt(this.money) / 100;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void setPrice(int price2) {
        this.price = price2;
    }

    public String getMoney() {
        if (!TextUtils.isEmpty(this.money) || this.price <= 0 || !"CNY".equals(this.currency)) {
            return this.money;
        }
        return (this.price * 100) + "";
    }

    public void setMoney(String money2) {
        this.money = money2;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency2) {
        this.currency = currency2;
    }

    public int getBuyNum() {
        return this.buyNum;
    }

    public void setBuyNum(int buyNum2) {
        this.buyNum = buyNum2;
    }

    public int getCoinNum() {
        return this.coinNum;
    }

    public void setCoinNum(int coinNum2) {
        this.coinNum = coinNum2;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String serverId2) {
        this.serverId = serverId2;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId2) {
        this.roleId = roleId2;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName2) {
        this.roleName = roleName2;
    }

    public int getRoleLevel() {
        return this.roleLevel;
    }

    public void setRoleLevel(int roleLevel2) {
        this.roleLevel = roleLevel2;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension2) {
        this.extension = extension2;
        this.gameExtension = extension2;
    }

    public void setEagleExtension(String extension2) {
        this.extension = extension2;
    }

    public String getGameExtension() {
        return this.gameExtension;
    }

    public int getRatio() {
        return this.ratio;
    }

    public void setRatio(int ratio2) {
        this.ratio = ratio2;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName2) {
        this.serverName = serverName2;
    }

    public String getVip() {
        return this.vip;
    }

    public void setVip(String vip2) {
        this.vip = vip2;
    }

    public String getPayNotifyUrl() {
        return this.payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl2) {
        this.payNotifyUrl = payNotifyUrl2;
    }

    public String getOrderID() {
        return this.orderID;
    }

    public void setOrderID(String orderID2) {
        this.orderID = orderID2;
    }

    public String getGameOrderID() {
        return this.gameOrderID;
    }

    public void setGameOrderID(String gameOrderID2) {
        this.gameOrderID = gameOrderID2;
    }

    public String getGameName() {
        return this.gameName;
    }

    public void setGameName(String gameName2) {
        this.gameName = gameName2;
    }

    @Deprecated
    public void setCpOrderID(String paramString) {
        this.gameOrderID = paramString;
    }

    @Deprecated
    public String getCpOrderID() {
        return this.gameOrderID;
    }

    public String toString() {
        return "{\"productId\":\"" + this.productId + "\", \"productName\":\"" + this.productName + "\", \"productDesc\":\"" + this.productDesc + "\", \"price\":" + this.price + ", \"money\":\"" + this.money + "\", \"currency\":\"" + this.currency + "\", \"ratio\":" + this.ratio + ", \"buyNum\":" + this.buyNum + ", \"coinNum\":" + this.coinNum + ", \"serverId\":\"" + this.serverId + "\", \"serverName\":\"" + this.serverName + "\", \"gameName\":\"" + this.gameName + "\", \"roleId\":\"" + this.roleId + "\", \"roleName\":\"" + this.roleName + "\", \"roleLevel\":" + this.roleLevel + ", \"payNotifyUrl\":\"" + this.payNotifyUrl + "\", \"vip\":\"" + this.vip + "\", \"gameOrderID\":\"" + this.gameOrderID + "\", \"orderID\":\"" + this.orderID + "\", \"extension\":\"" + this.extension + "\", \"gameExtension\":\"" + this.gameExtension + "\"" + '}';
    }
}
