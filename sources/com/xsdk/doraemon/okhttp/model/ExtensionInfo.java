package com.xsdk.doraemon.okhttp.model;

public class ExtensionInfo {
    private Object extData;
    private String trackTag = "";
    private String urlTag = "";

    public Object getExtData() {
        return this.extData;
    }

    public void setExtData(Object extData2) {
        this.extData = extData2;
    }

    public String getTrackTag() {
        return this.trackTag;
    }

    public void setTrackTag(String trackTag2) {
        this.trackTag = trackTag2;
    }

    public String getUrlTag() {
        return this.urlTag;
    }

    public void setUrlTag(String urlTag2) {
        this.urlTag = urlTag2;
    }

    public String toString() {
        return "ExtensionInfo{trackTag='" + this.trackTag + '\'' + ", urlTag='" + this.urlTag + '\'' + ", extData='" + this.extData + '\'' + '}';
    }
}
