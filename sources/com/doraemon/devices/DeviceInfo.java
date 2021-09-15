package com.doraemon.devices;

import android.content.Context;
import android.os.Build;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.util.DeviceEmuCheckUtil;
import com.doraemon.util.LocationUtil;
import com.doraemon.util.MemoryUtil;
import com.doraemon.util.MobileBehaviorUtil;
import com.doraemon.util.XposedCheckUtil;

public class DeviceInfo {
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

    public String getBoard() {
        return this.board;
    }

    public void setBoard(String board2) {
        this.board = board2;
    }

    public String getBuildID() {
        return this.buildID;
    }

    public void setBuildID(String buildID2) {
        this.buildID = buildID2;
    }

    public String getDeviceFingerprint() {
        return this.deviceFingerprint;
    }

    public void setDeviceFingerprint(String deviceFingerprint2) {
        this.deviceFingerprint = deviceFingerprint2;
    }

    public String getDeviceDisplay() {
        return this.deviceDisplay;
    }

    public void setDeviceDisplay(String deviceDisplay2) {
        this.deviceDisplay = deviceDisplay2;
    }

    public String getRadioVersion() {
        return this.radioVersion;
    }

    public void setRadioVersion(String radioVersion2) {
        this.radioVersion = radioVersion2;
    }

    public String getAndroidID() {
        return this.androidID;
    }

    public void setAndroidID(String androidID2) {
        this.androidID = androidID2;
    }

    public boolean isSimulator() {
        return this.isSimulator;
    }

    public void setSimulator(boolean simulator) {
        this.isSimulator = simulator;
    }

    public long getTotalRamMemory() {
        return this.totalRamMemory;
    }

    public void setTotalRamMemory(long totalRamMemory2) {
        this.totalRamMemory = totalRamMemory2;
    }

    public long getAvailRamMemory() {
        return this.availRamMemory;
    }

    public void setAvailRamMemory(long availRamMemory2) {
        this.availRamMemory = availRamMemory2;
    }

    public String getDefaultInput() {
        return this.defaultInput;
    }

    public void setDefaultInput(String defaultInput2) {
        this.defaultInput = defaultInput2;
    }

    public String getDefaultBrowser() {
        return this.defaultBrowser;
    }

    public void setDefaultBrowser(String defaultBrowser2) {
        this.defaultBrowser = defaultBrowser2;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location2) {
        this.location = location2;
    }

    public boolean isSupportMagnetic() {
        return this.isSupportMagnetic;
    }

    public void setSupportMagnetic(boolean supportMagnetic) {
        this.isSupportMagnetic = supportMagnetic;
    }

    public boolean isSupportGyroscope() {
        return this.isSupportGyroscope;
    }

    public void setSupportGyroscope(boolean supportGyroscope) {
        this.isSupportGyroscope = supportGyroscope;
    }

    public boolean isSupportMicrophone() {
        return this.isSupportMicrophone;
    }

    public void setSupportMicrophone(boolean supportMicrophone) {
        this.isSupportMicrophone = supportMicrophone;
    }

    public boolean isSupportXposed() {
        return this.isSupportXposed;
    }

    public void setSupportXposed(boolean supportXposed) {
        this.isSupportXposed = supportXposed;
    }

    public String getMiitmOAID() {
        return this.miitmOAID;
    }

    public void setMiitmOAID(String miitmOAID2) {
        this.miitmOAID = miitmOAID2;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product2) {
        this.product = product2;
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String device2) {
        this.device = device2;
    }

    public long getTotalDisk() {
        return this.totalDisk;
    }

    public void setTotalDisk(long totalDisk2) {
        this.totalDisk = totalDisk2;
    }

    public long getFreeDisk() {
        return this.freeDisk;
    }

    public void setFreeDisk(long freeDisk2) {
        this.freeDisk = freeDisk2;
    }

    public static class Builder {
        public DeviceInfo build(Context context) {
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setBoard(Build.BOARD);
            deviceInfo.setBuildID(Build.ID);
            deviceInfo.setDeviceFingerprint(Build.FINGERPRINT);
            deviceInfo.setDeviceDisplay(Build.DISPLAY);
            deviceInfo.setRadioVersion(Build.getRadioVersion());
            deviceInfo.setAndroidID(CommonUtil.getAndroidId());
            deviceInfo.setSimulator(DeviceEmuCheckUtil.mayOnEmulator(context));
            deviceInfo.setTotalRamMemory(MemoryUtil.getTotalMemory());
            deviceInfo.setAvailRamMemory(MemoryUtil.getAvailMemory(context));
            deviceInfo.setDefaultInput(MobileBehaviorUtil.getInputMethod(context));
            deviceInfo.setDefaultBrowser(MobileBehaviorUtil.getBrowserApp(context));
            deviceInfo.setLocation(LocationUtil.getLocationInfo(context));
            deviceInfo.setSupportMagnetic(MobileBehaviorUtil.isSupportMagnetic(context));
            deviceInfo.setSupportGyroscope(MobileBehaviorUtil.isSupportGyroscope(context));
            deviceInfo.setSupportMicrophone(MobileBehaviorUtil.isSupportMicrophone(context));
            deviceInfo.setSupportXposed(XposedCheckUtil.checkXposed(context));
            deviceInfo.setProduct(Build.PRODUCT);
            deviceInfo.setDevice(Build.DEVICE);
            deviceInfo.setTotalDisk(MemoryUtil.getTotalInternalMemorySize());
            deviceInfo.setFreeDisk(MemoryUtil.getAvailableInternalMemorySize());
            deviceInfo.setMiitmOAID(MsaSdkHelper.getInstance().getOAID());
            return deviceInfo;
        }
    }
}
