package com.eagle.mixsdk.sdk.okhttp.model;

public class DomainBean {
    private String domainUrl;
    private boolean isUse;

    public DomainBean(boolean isUse2, String domainUrl2) {
        this.isUse = isUse2;
        this.domainUrl = domainUrl2;
    }

    public String getDomainUrl() {
        return this.domainUrl;
    }

    public void setDomainUrl(String domainUrl2) {
        this.domainUrl = domainUrl2;
    }

    public boolean isUse() {
        return this.isUse;
    }

    public void setUse(boolean isUse2) {
        this.isUse = isUse2;
    }
}
