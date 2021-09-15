package com.eagle.mixsdk.sdk;

public class ShareParams {
    public static final int TYPE_SHARE_WITH_DEFAULT = 0;
    public static final int TYPE_SHARE_WITH_IMAGE = 1;
    public static final int TYPE_SHARE_WITH_SDK_DATA = 2;
    private String comment;
    private String content;
    private boolean dialogMode;
    private String imgPath;
    private String imgUrl;
    private int notifyIcon;
    private String notifyIconText;
    public int shareType;
    private String sourceName;
    private String sourceUrl;
    private String title;
    private String titleUrl;
    private String url;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public String getTitleUrl() {
        return this.titleUrl;
    }

    public void setTitleUrl(String titleUrl2) {
        this.titleUrl = titleUrl2;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String sourceName2) {
        this.sourceName = sourceName2;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String sourceUrl2) {
        this.sourceUrl = sourceUrl2;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content2) {
        this.content = content2;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl2) {
        this.imgUrl = imgUrl2;
    }

    public boolean isDialogMode() {
        return this.dialogMode;
    }

    public void setDialogMode(boolean dialogMode2) {
        this.dialogMode = dialogMode2;
    }

    public int getNotifyIcon() {
        return this.notifyIcon;
    }

    public void setNotifyIcon(int notifyIcon2) {
        this.notifyIcon = notifyIcon2;
    }

    public String getNotifyIconText() {
        return this.notifyIconText;
    }

    public void setNotifyIconText(String notifyIconText2) {
        this.notifyIconText = notifyIconText2;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment2) {
        this.comment = comment2;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url2) {
        this.url = url2;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath2) {
        this.imgPath = imgPath2;
    }

    public void setShareType(int shareType2) {
        this.shareType = shareType2;
    }
}
