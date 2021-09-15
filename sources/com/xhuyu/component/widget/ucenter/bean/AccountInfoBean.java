package com.xhuyu.component.widget.ucenter.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AccountInfoBean {
    private int itemType;
    private String otherInfo = "";
    private String title;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemType {
        public static final int EMAIL = 2;
        public static final int LANGUAGE = 4;
        public static final int PASSWORD = 1;
        public static final int PHONE = 3;
    }

    public AccountInfoBean(String title2, int itemType2) {
        this.title = title2;
        this.itemType = itemType2;
    }

    public AccountInfoBean(String title2, String otherInfo2, int itemType2) {
        this.title = title2;
        this.otherInfo = otherInfo2;
        this.itemType = itemType2;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int itemType2) {
        this.itemType = itemType2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public String getOtherInfo() {
        return this.otherInfo;
    }

    public void setOtherInfo(String otherInfo2) {
        this.otherInfo = otherInfo2;
    }
}
