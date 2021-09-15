package com.eagle.mixsdk.sdk.active;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.doraemon.p027eg.CommonUtil;
import com.doraemon.util.language.Language;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.log.Log;
import com.eagle.mixsdk.sdk.plugin.EagleUser;
import com.eagle.mixsdk.sdk.utils.HttpUtil;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.eagle.mixsdk.track.EagleTrackReport;
import org.json.JSONObject;

public class AndroidJSObject {
    public static String getActiveWebViewParams() {
        return "appID=" + EagleSDK.getInstance().getAppID() + "&channelID=" + EagleSDK.getInstance().getCurrChannel() + "&subChannelID=" + EagleSDK.getInstance().getSubChannel() + "&extChannelID=" + EagleSDK.getInstance().getExtChannel() + "&pt=" + "android" + "&v=" + Constants.VERSIONNAME + "&language=" + HttpUtil.getLanguage();
    }

    @JavascriptInterface
    public String _getActiveParams() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appID", EagleSDK.getInstance().getAppID());
            jsonObject.put("channelID", EagleSDK.getInstance().getCurrChannel());
            jsonObject.put("subChannelID", EagleSDK.getInstance().getSubChannel());
            jsonObject.put("extChannelID", EagleSDK.getInstance().getExtChannel());
            jsonObject.put(Language.f817pt, "android");
            jsonObject.put("v", Constants.VERSIONNAME);
            jsonObject.put("userID", EagleSDK.getInstance().getUToken().getUserID());
            jsonObject.put("token", EagleSDK.getInstance().getUToken().getToken());
            jsonObject.put("lang", CommonUtil.getLanguage());
            jsonObject.put("language", HttpUtil.getLanguage());
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @JavascriptInterface
    public void _reportActiveParams(String code, String link, String event, String guid) {
        Log.m602w(Constants.TAG, "_reportActiveParams code " + code + " link " + link + " event " + event + " guid " + guid);
        EagleTrackReport.getInstance().reportActive(code, link, event, guid);
    }

    @JavascriptInterface
    public void _responseActiveState(int state) {
        if (state == 1) {
            CustomAlertDialog.getInstance().closeDialog();
            EagleToken uToken = EagleSDK.getInstance().getUToken();
            if (!uToken.isOpenServer()) {
                EagleUser.getInstance().loginFail("关服中，游戏暂不开放");
                return;
            }
            EagleSDK.getInstance().runOnMainThread(new Runnable() {
                public void run() {
                    Toast.makeText(EagleSDK.getInstance().getContext(), "激活成功,进入游戏...", 0).show();
                }
            });
            EagleSDK.getInstance().onLoginSuccess(uToken);
        }
    }

    @JavascriptInterface
    public void _openExternalLinks(String url) {
        if (url != null && !TextUtils.isEmpty(url)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            EagleSDK.getInstance().getContext().startActivity(intent);
        }
    }

    @JavascriptInterface
    public void _gotoBack() {
        CustomAlertDialog.getInstance().gotoBack();
    }
}
