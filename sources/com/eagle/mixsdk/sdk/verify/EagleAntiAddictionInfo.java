package com.eagle.mixsdk.sdk.verify;

public class EagleAntiAddictionInfo {
    private int antiAddictionState = 0;
    private String extInfo = "";
    private String identityPlate = "";
    private int playerAge = 0;

    public EagleAntiAddictionInfo(int antiAddictionState2, int playerAge2, String identityPlate2, String extInfo2) {
        this.antiAddictionState = antiAddictionState2;
        this.playerAge = playerAge2;
        this.identityPlate = identityPlate2;
        this.extInfo = extInfo2;
    }

    public EagleAntiAddictionInfo() {
    }

    public int getAntiAddictionState() {
        return this.antiAddictionState;
    }

    public void setAntiAddictionState(int antiAddictionState2) {
        this.antiAddictionState = antiAddictionState2;
    }

    public int getPlayerAge() {
        return this.playerAge;
    }

    public void setPlayerAge(int playerAge2) {
        this.playerAge = playerAge2;
    }

    public String getIdentityPlate() {
        return this.identityPlate;
    }

    public void setIdentityPlate(String identityPlate2) {
        this.identityPlate = identityPlate2;
    }

    public String getExtInfo() {
        return this.extInfo;
    }

    public void setExtInfo(String extInfo2) {
        this.extInfo = extInfo2;
    }
}
