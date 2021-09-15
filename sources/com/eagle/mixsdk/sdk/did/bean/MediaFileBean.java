package com.eagle.mixsdk.sdk.did.bean;

public class MediaFileBean {
    private String disPlayName;
    private int fileID;
    private String filePath;

    public MediaFileBean(int fileID2, String disPlayName2, String filePath2) {
        this.fileID = fileID2;
        this.disPlayName = disPlayName2;
        this.filePath = filePath2;
    }

    public int getFileID() {
        return this.fileID;
    }

    public String getDisPlayName() {
        return this.disPlayName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String toString() {
        return "MediaFileBean{fileID=" + this.fileID + ", disPlayName='" + this.disPlayName + '\'' + ", filePath='" + this.filePath + '\'' + '}';
    }
}
