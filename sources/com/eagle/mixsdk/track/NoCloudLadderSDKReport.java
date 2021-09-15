package com.eagle.mixsdk.track;

import android.content.Context;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerProperties;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.did.DIDV3Proxy;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;
import com.eagle.mixsdk.sdk.log.Log;
import com.thinkfly.cloudlader.CombDataFactory;
import com.thinkfly.cloudlader.Interceptor;
import com.thinkfly.cloudlader.data.DBData;
import com.thinkfly.plugins.coludladder.CloudLadder;
import com.thinkfly.star.GlobalParams;
import com.thinkfly.star.builder.EventBuilder;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class NoCloudLadderSDKReport extends BaseReport {
    private static final String appKey = "eaglesdk";
    private static final String appSec = "450rsjsxa2nvOMxsw";
    static boolean isNeedReportDid;
    static boolean isNeedReportGAID;
    static boolean isNeedReportOAID = true;
    private static int taskNum;
    private final CombDataFactory mCombDataFactory;
    private CloudLadder mLadder;

    private static class SingletonHolder {
        public static final NoCloudLadderSDKReport instance = new NoCloudLadderSDKReport();

        private SingletonHolder() {
        }
    }

    private NoCloudLadderSDKReport() {
        this.mLadder = new CloudLadder.Builder().setAppSec(appSec).setAppKey(appKey).setHost(getHost()).setDebug(true).setDbName("eaglesdkdid").setInterceptor(new Interceptor() {
            public List<DBData> intercept(List<DBData> list) {
                if (list != null && list.size() > 0) {
                    for (DBData data : list) {
                        try {
                            JSONObject jsonObject = data.getJson();
                            if (jsonObject.has("context")) {
                                JSONObject contextJson = jsonObject.optJSONObject("context");
                                if (contextJson.has("data")) {
                                    JSONObject dataJson = contextJson.optJSONObject("data");
                                    String didv3 = dataJson.optString("didv3", "");
                                    if (TextUtils.isEmpty(didv3) || "00000000000000000000000000000000".equals(didv3)) {
                                        String deviceID = DIDV3Proxy.getInstance().obtainUUID(EagleSDK.getInstance().getApplication());
                                        Log.m598d(Constants.TAG, "fix didv3 " + deviceID);
                                        dataJson.put("didv3", deviceID);
                                        dataJson.put("didv3_from", "" + DIDV3Proxy.getDidFeatures());
                                    }
                                    String didv4 = dataJson.optString("didv4", "");
                                    if (TextUtils.isEmpty(didv4) || "00000000000000000000000000000000".equals(didv4)) {
                                        String deviceID2 = DIDV4Proxy.getInstance().obtainUUID(EagleSDK.getInstance().getApplication());
                                        Log.m598d(Constants.TAG, "fix didv4 " + deviceID2);
                                        dataJson.put("didv4", deviceID2);
                                        dataJson.put("didv4_from", "" + DIDV4Proxy.getFeatures());
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return list;
            }

            public boolean isIntercept() {
                return "00000000000000000000000000000000".equals(DIDV4Proxy.getInstance().obtainUUID(EagleSDK.getInstance().getApplication()));
            }
        }).builder(EagleSDK.getInstance().getApplication());
        GlobalParams globalParams = new GlobalParams();
        globalParams.put(AppsFlyerProperties.APP_ID, EagleSDK.getInstance().getAppID() + "");
        globalParams.put("chnuid", "");
        this.mCombDataFactory = new CombDataFactory.Configure().setAppKey(appKey).setC(String.valueOf(EagleSDK.getInstance().getCurrChannel())).setAppv(Constants.VERSIONNAME).setSc("" + EagleSDK.getInstance().getSubChannel()).setExtc(EagleSDK.getInstance().getExtChannel()).setGlobalParams(globalParams).config();
    }

    public static NoCloudLadderSDKReport get() {
        return SingletonHolder.instance;
    }

    /* access modifiers changed from: package-private */
    public void checkDataAndDestroy() {
        if (this.mLadder != null) {
            if (this.mLadder.isExistData()) {
                this.mLadder.tryReport();
            } else if (!isNeedReportDid && !isNeedReportOAID && !isNeedReportGAID) {
                this.mLadder.onDestroy(EagleSDK.getInstance().getApplication());
                Log.m602w(Constants.TAG, "NoCloudLadderSDKReport onDestroy because of no data or task");
                this.mLadder = null;
            }
        }
    }

    private void reportEvent(Context context, EventBuilder builder, boolean refresh) {
        if (this.mLadder != null) {
            try {
                this.mLadder.report(context, this.mCombDataFactory.createEventJson(context, builder).toString(), refresh ? 50 : 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void reportDidEvent() {
        reportEvent(EagleSDK.getInstance().getApplication(), new EventBuilder.Configure().setAction("apk_did").setUid(EagleSDK.getInstance().getEagleUid()).putString("chnuid", EagleSDK.getInstance().getChannelUid()).put("didv3", DIDV3Proxy.getInstance().obtainUUID(EagleSDK.getInstance().getApplication())).put("didv3_from", "" + DIDV3Proxy.getDidFeatures()).put("didv4", DIDV4Proxy.getInstance().obtainUUID(EagleSDK.getInstance().getApplication())).put("didv4_from", "" + DIDV4Proxy.getFeatures()).build(), true);
    }
}
