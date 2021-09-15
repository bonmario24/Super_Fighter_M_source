package com.eagle.mixsdk.sdk.verify;

public class EagleCertificationInfo {
    private int certificationStat = -1;
    private String extInfo = "";
    private String identity = "";
    private int playerAge = -1;

    public EagleCertificationInfo() {
    }

    public EagleCertificationInfo(int certificationStat2, int playerAge2, String identity2, String extInfo2) {
        this.certificationStat = certificationStat2;
        this.playerAge = playerAge2;
        this.identity = identity2;
        this.extInfo = extInfo2;
    }

    public String getExtInfo() {
        return this.extInfo;
    }

    public void setExtInfo(String extInfo2) {
        this.extInfo = extInfo2;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String identity2) {
        this.identity = identity2;
    }

    public int getCertificationStat() {
        return this.certificationStat;
    }

    public void setCertificationStat(int certificationStat2) {
        this.certificationStat = certificationStat2;
    }

    public int getPlayerAge() {
        return this.playerAge;
    }

    public void setPlayerAge(int playerAge2) {
        this.playerAge = playerAge2;
    }
}
