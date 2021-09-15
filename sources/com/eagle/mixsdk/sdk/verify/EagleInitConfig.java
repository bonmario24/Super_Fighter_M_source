package com.eagle.mixsdk.sdk.verify;

import android.text.TextUtils;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import org.json.JSONObject;

public class EagleInitConfig {
    public static final String MSG_MARK = "-->";
    private String banMsg;
    private int banStatus;
    private String errorMsg;
    @Deprecated
    public String idotUrl;
    private int isOfficial;
    @Deprecated
    public String statUrl;
    private int state;
    private int vmc;

    public EagleInitConfig() {
        this.banStatus = 0;
        this.vmc = 0;
        this.errorMsg = "";
        this.isOfficial = 1;
        this.state = -1;
    }

    public EagleInitConfig(int isOfficial2, String statUrl2, String idotUrl2, int banState, String banMsg2) {
        this.banStatus = 0;
        this.vmc = 0;
        this.errorMsg = "";
        this.isOfficial = isOfficial2;
        this.statUrl = statUrl2;
        this.idotUrl = idotUrl2;
        this.banStatus = banState;
        this.banMsg = banMsg2;
    }

    public boolean isDebugEnvironment() {
        return this.isOfficial == 0;
    }

    public String getStatUrl() {
        return this.statUrl;
    }

    public int getState() {
        return this.state;
    }

    public String getBanMsg() {
        if (TextUtils.isEmpty(this.banMsg)) {
            return ResPluginUtil.getStringByName("xeagle_notice_cotent");
        }
        return this.banMsg;
    }

    public boolean isForbidEnterGame() {
        return 1 == this.banStatus;
    }

    public boolean isVmc() {
        return 1 == this.vmc;
    }

    public String getVmcMsg() {
        return ResPluginUtil.getStringByName("xeagle_forbid_vmc");
    }

    public String getErrorMsg() {
        if (!TextUtils.isEmpty(this.errorMsg)) {
            return this.errorMsg;
        }
        if (isForbidEnterGame()) {
            return "status==1";
        }
        if (isVmc()) {
            return "vmc==1";
        }
        return ResPluginUtil.getStringByName("xeagle_init_fail");
    }

    public EagleInitConfig parseConfig(JSONObject json) {
        if (json != null) {
            this.state = json.optInt(ServerProtocol.DIALOG_PARAM_STATE);
            if (this.state != 1) {
                Log.m598d(Constants.TAG, "get init info  failed. the state is " + this.state);
                setErrorMsg("the state is " + this.state);
            } else {
                JSONObject jsonData = json.optJSONObject("data");
                if (jsonData == null) {
                    Log.m598d(Constants.TAG, "get init info  failed. the data is null");
                    setErrorMsg("the data is null");
                } else {
                    this.isOfficial = jsonData.optInt("isOfficial");
                    this.vmc = jsonData.optInt("isVmc");
                    JSONObject actJson = jsonData.optJSONObject(NativeProtocol.WEB_DIALOG_ACTION);
                    if (actJson != null) {
                        this.banStatus = actJson.optInt("status");
                        this.banMsg = actJson.optString("msg");
                    }
                }
            }
        }
        return this;
    }

    public EagleInitConfig setErrorMsg(String msg) {
        this.errorMsg = msg;
        return this;
    }
}
