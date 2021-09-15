package com.eagle.mixsdk.sdk.verify;

import android.support.annotation.NonNull;
import com.doraemon.p027eg.CheckUtils;
import com.eagle.mixsdk.sdk.did.utils.DIDMD5Util;
import com.facebook.share.internal.ShareConstants;
import org.json.JSONObject;

public class EagleToken {
    private int ageLimit;
    EagleAntiAddictionInfo antiAddictionInfo;
    private int antiAddictionSwitch;
    private EagleCertificationInfo certificationInfo;
    private String extension;
    private String gameNoticeContent;
    private int gameNoticeSwitch;
    private boolean hasBindMobile;
    private int isActivated;
    private int isOld;
    private boolean isOpenBindMobile;
    private int isOpenServer;
    private String msg;
    private long registerTime;
    private String sdkUserID;
    private String sdkUsername;
    private boolean suc;
    private String token;
    private long userID;
    private String username;

    public EagleToken() {
        this.suc = false;
        this.isOld = 0;
        this.userID = 0;
        this.sdkUserID = "";
        this.username = "";
        this.sdkUsername = "";
        this.token = "";
        this.msg = "";
        this.extension = "";
        this.isActivated = 0;
        this.antiAddictionSwitch = 0;
        this.ageLimit = 0;
        this.isOpenBindMobile = false;
        this.hasBindMobile = false;
        this.registerTime = 0;
        this.suc = false;
    }

    public EagleToken(long userID2, String sdkUserID2, String username2, String sdkUsername2, String token2, String extension2, String msg2, int isOld2, int isActivated2) {
        this.suc = false;
        this.isOld = 0;
        this.userID = 0;
        this.sdkUserID = "";
        this.username = "";
        this.sdkUsername = "";
        this.token = "";
        this.msg = "";
        this.extension = "";
        this.isActivated = 0;
        this.antiAddictionSwitch = 0;
        this.ageLimit = 0;
        this.isOpenBindMobile = false;
        this.hasBindMobile = false;
        this.registerTime = 0;
        this.userID = userID2;
        this.sdkUserID = sdkUserID2;
        this.username = username2;
        this.sdkUsername = sdkUsername2;
        this.token = token2;
        this.extension = extension2;
        this.msg = msg2;
        this.suc = true;
        this.isOld = isOld2;
        this.isActivated = isActivated2;
    }

    @NonNull
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("suc", this.suc);
            json.put("userID", this.userID);
            json.put("sdkUserID", this.sdkUserID);
            json.put("username", this.username);
            json.put("sdkUsername", this.sdkUsername);
            json.put("token", this.token);
            json.put(ShareConstants.MEDIA_EXTENSION, this.extension);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public int getAntiAddictionSwitch() {
        return this.antiAddictionSwitch;
    }

    public void setAntiAddictionSwitch(int antiAddictionSwitch2) {
        this.antiAddictionSwitch = antiAddictionSwitch2;
    }

    public EagleAntiAddictionInfo getAntiAddictionInfo() {
        return this.antiAddictionInfo;
    }

    public void setAntiAddictionInfo(EagleAntiAddictionInfo antiAddictionInfo2) {
        if (this.antiAddictionSwitch == 1 && antiAddictionInfo2.getAntiAddictionState() == 1 && CheckUtils.isNullOrEmpty(antiAddictionInfo2.getIdentityPlate())) {
            antiAddictionInfo2.setIdentityPlate(DIDMD5Util.MD5(getUserID() + "").toLowerCase());
        }
        this.antiAddictionInfo = antiAddictionInfo2;
    }

    public EagleCertificationInfo getCertificationInfo() {
        return this.certificationInfo;
    }

    public void setCertificationInfo(EagleCertificationInfo certificationInfo2) {
        this.certificationInfo = certificationInfo2;
    }

    public long getUserID() {
        return this.userID;
    }

    public void setUserID(long userID2) {
        this.userID = userID2;
    }

    public String getSdkUserID() {
        return this.sdkUserID;
    }

    public void setSdkUserID(String sdkUserID2) {
        this.sdkUserID = sdkUserID2;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token2) {
        this.token = token2;
    }

    public boolean isSuc() {
        return this.suc;
    }

    public void setSuc(boolean suc2) {
        this.suc = suc2;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension2) {
        this.extension = extension2;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username2) {
        this.username = username2;
    }

    public String getSdkUsername() {
        return this.sdkUsername;
    }

    public void setSdkUsername(String sdkUsername2) {
        this.sdkUsername = sdkUsername2;
    }

    public String getMsg() {
        return this.msg;
    }

    public EagleToken setMsg(String msg2) {
        this.msg = msg2;
        return this;
    }

    public int getIsOld() {
        return this.isOld;
    }

    public void setIsOld(int isOld2) {
        this.isOld = isOld2;
    }

    public int getIsActivated() {
        return this.isActivated;
    }

    public void setIsActivated(int isActivated2) {
        this.isActivated = isActivated2;
    }

    public boolean isOpenServer() {
        return this.isOpenServer == 1;
    }

    public String getServerPrompt() {
        return getGameNoticeContent();
    }

    public EagleToken setIsOpenServer(int isOpenServer2) {
        this.isOpenServer = isOpenServer2;
        return this;
    }

    public String getGameNoticeContent() {
        return this.gameNoticeContent;
    }

    public EagleToken setGameNoticeContent(String gameNoticeContent2) {
        this.gameNoticeContent = gameNoticeContent2;
        return this;
    }

    public boolean isShowGameNotice() {
        return this.gameNoticeSwitch == 1;
    }

    public EagleToken setGameNoticeSwitch(int gameNoticeSwitch2) {
        this.gameNoticeSwitch = gameNoticeSwitch2;
        return this;
    }

    public boolean isOpenBindMobile() {
        return this.isOpenBindMobile;
    }

    public void setOpenBindMobile(boolean openBindMobile) {
        this.isOpenBindMobile = openBindMobile;
    }

    public boolean isBindMobile() {
        return this.hasBindMobile;
    }

    /* access modifiers changed from: package-private */
    public void setHasBindMobile(boolean hasBindMobile2) {
        this.hasBindMobile = hasBindMobile2;
    }

    /* access modifiers changed from: package-private */
    public void setRegisterTime(long registerTime2) {
        this.registerTime = registerTime2;
    }

    public long getRegisterTime() {
        return this.registerTime;
    }

    public void setAgeLimit(int ageLimit2) {
        this.ageLimit = ageLimit2;
    }

    public int getAgeLimit() {
        return this.ageLimit;
    }
}
