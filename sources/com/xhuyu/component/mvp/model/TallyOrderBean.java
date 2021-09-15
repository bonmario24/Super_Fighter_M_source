package com.xhuyu.component.mvp.model;

import com.doraemon.p027eg.CheckUtils;

public class TallyOrderBean {
    private String leftInfo;
    private String rightDetail;
    private String textColorString;

    public TallyOrderBean(String leftInfo2, String rightDetail2) {
        this.leftInfo = leftInfo2;
        this.rightDetail = rightDetail2;
    }

    public TallyOrderBean(String leftInfo2, String rightDetail2, String textColorString2) {
        this.leftInfo = leftInfo2;
        this.rightDetail = rightDetail2;
        this.textColorString = textColorString2;
    }

    public String getTextColorString() {
        return CheckUtils.isNullOrEmpty(this.textColorString) ? "#3E3E48" : this.textColorString;
    }

    public void setTextColorString(String textColorString2) {
        this.textColorString = textColorString2;
    }

    public String getLeftInfo() {
        return this.leftInfo;
    }

    public void setLeftInfo(String leftInfo2) {
        this.leftInfo = leftInfo2;
    }

    public String getRightDetail() {
        return this.rightDetail;
    }

    public void setRightDetail(String rightDetail2) {
        this.rightDetail = rightDetail2;
    }
}
