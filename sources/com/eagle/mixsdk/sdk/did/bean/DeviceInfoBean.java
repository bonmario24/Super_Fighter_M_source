package com.eagle.mixsdk.sdk.did.bean;

public class DeviceInfoBean {
    private String androidID = "";
    private long availRamMemory;
    private String board;
    private String buildID;
    private String defaultBrowser = "";
    private String defaultInput = "";
    private String device;
    private String deviceDisplay;
    private String deviceFingerprint;
    private long freeDisk;
    private boolean isSimulator;
    private boolean isSupportGyroscope;
    private boolean isSupportMagnetic;
    private boolean isSupportMicrophone;
    private boolean isSupportXposed;
    private String location = "";
    private String miitmOAID = "";
    private String product;
    private String radioVersion;
    private long totalDisk;
    private long totalRamMemory;

    public String getAndroidID() {
        return this.androidID;
    }

    public long getAvailRamMemory() {
        return this.availRamMemory;
    }

    public String getBoard() {
        return this.board;
    }

    public String getBuildID() {
        return this.buildID;
    }

    public String getDefaultBrowser() {
        return this.defaultBrowser;
    }

    public String getDefaultInput() {
        return this.defaultInput;
    }

    public String getDevice() {
        return this.device;
    }

    public String getDeviceDisplay() {
        return this.deviceDisplay;
    }

    public String getDeviceFingerprint() {
        return this.deviceFingerprint;
    }

    public long getFreeDisk() {
        return this.freeDisk;
    }

    public String getLocation() {
        return this.location;
    }

    public String getMiitmOAID() {
        return this.miitmOAID;
    }

    public String getProduct() {
        return this.product;
    }

    public String getRadioVersion() {
        return this.radioVersion;
    }

    public long getTotalDisk() {
        return this.totalDisk;
    }

    public long getTotalRamMemory() {
        return this.totalRamMemory;
    }

    public boolean isSimulator() {
        return this.isSimulator;
    }

    public boolean isSupportGyroscope() {
        return this.isSupportGyroscope;
    }

    public boolean isSupportMagnetic() {
        return this.isSupportMagnetic;
    }

    public boolean isSupportMicrophone() {
        return this.isSupportMicrophone;
    }

    public boolean isSupportXposed() {
        return this.isSupportXposed;
    }

    public void setAndroidID(String str) {
        this.androidID = str;
    }

    public void setAvailRamMemory(long j) {
        this.availRamMemory = j;
    }

    public void setBoard(String str) {
        this.board = str;
    }

    public void setBuildID(String str) {
        this.buildID = str;
    }

    public void setDefaultBrowser(String str) {
        this.defaultBrowser = str;
    }

    public void setDefaultInput(String str) {
        this.defaultInput = str;
    }

    public void setDevice(String str) {
        this.device = str;
    }

    public void setDeviceDisplay(String str) {
        this.deviceDisplay = str;
    }

    public void setDeviceFingerprint(String str) {
        this.deviceFingerprint = str;
    }

    public void setFreeDisk(long j) {
        this.freeDisk = j;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setMiitmOAID(String str) {
        this.miitmOAID = str;
    }

    public void setProduct(String str) {
        this.product = str;
    }

    public void setRadioVersion(String str) {
        this.radioVersion = str;
    }

    public void setSimulator(boolean z) {
        this.isSimulator = z;
    }

    public void setSupportGyroscope(boolean z) {
        this.isSupportGyroscope = z;
    }

    public void setSupportMagnetic(boolean z) {
        this.isSupportMagnetic = z;
    }

    public void setSupportMicrophone(boolean z) {
        this.isSupportMicrophone = z;
    }

    public void setSupportXposed(boolean z) {
        this.isSupportXposed = z;
    }

    public void setTotalDisk(long j) {
        this.totalDisk = j;
    }

    public void setTotalRamMemory(long j) {
        this.totalRamMemory = j;
    }
}
