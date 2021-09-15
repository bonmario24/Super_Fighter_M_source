package com.thinkfly.plugins.coludladder.utils;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.thinkfly.plugins.coludladder.config.Config;
import com.thinkfly.plugins.coludladder.config.Version;
import com.thinkfly.plugins.coludladder.okhttp.OKHttpCenter;
import com.thinkfly.plugins.coludladder.okhttp.OKHttpUtil;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

public class FixTimeUtils {
    private long elapsedRealtime;
    public String intervalTime = Config.UNKNOWN;
    private volatile boolean isNeedFix = true;
    /* access modifiers changed from: private */
    public volatile boolean isNeedReObtainTime = true;
    private String mHost;
    private final List<IObtainServerTimeCallback> mObtainServerTimeCallbackList = new CopyOnWriteArrayList();
    private volatile long serverTime;
    /* access modifiers changed from: private */
    public volatile String versionTips = "";

    public interface IObtainServerTimeCallback {
        void onObtainServerTime(long j);
    }

    private static class SingletonHolder {
        public static FixTimeUtils instance = new FixTimeUtils();

        private SingletonHolder() {
        }
    }

    public static FixTimeUtils getInstance() {
        return SingletonHolder.instance;
    }

    public String getIntervalTime() {
        return this.intervalTime;
    }

    public void obtainServerTime(String host) {
        obtainServerTime(host, (IObtainServerTimeCallback) null);
    }

    public void obtainServerTime(String host, IObtainServerTimeCallback serverTimeCallback) {
        if (!this.isNeedReObtainTime) {
            logTips(this.versionTips);
            if (this.serverTime != 0) {
                serverTimeCallback.onObtainServerTime(getCurrentTimeMillisWithFix());
            } else {
                addObtainServerTimeCallback(serverTimeCallback);
            }
        } else {
            this.isNeedReObtainTime = false;
            this.mHost = host;
            addObtainServerTimeCallback(serverTimeCallback);
            Log.d(Config.TAG, "start obtian server time  : ");
            OKHttpUtil.getAsync(HostUtils.getRoot(host) + "/update/android/" + Version.VERSION_NAME, new OKHttpCenter.OKHttpCallbackListener() {
                public void onSuccess(JSONObject resultJson) {
                    if (resultJson == null || !resultJson.has("timestamp")) {
                        boolean unused = FixTimeUtils.this.isNeedReObtainTime = true;
                        return;
                    }
                    if (201 == resultJson.optInt("status")) {
                        String unused2 = FixTimeUtils.this.versionTips = resultJson.optString("data");
                        FixTimeUtils.this.logTips(FixTimeUtils.this.versionTips);
                    }
                    Log.d(Config.TAG, "obtian server time is ok \n intervalTime: " + FixTimeUtils.this.getServiceTimeAndLocalTimeInterval(resultJson.optLong("timestamp")));
                    FixTimeUtils.this.sendObtainServerTimeResult();
                }

                public void onFailure(String errorThrows) {
                    Log.d(Config.TAG, "obtian server time errorThrows : " + errorThrows);
                    boolean unused = FixTimeUtils.this.isNeedReObtainTime = true;
                }
            });
        }
    }

    public boolean isNeedFixTime() {
        return !this.intervalTime.equals(Config.UNKNOWN) && Long.valueOf(this.intervalTime).longValue() != 0;
    }

    public boolean isValidCurrentTime() {
        return !this.intervalTime.equals(Config.UNKNOWN);
    }

    public boolean isNeedReObtainTime() {
        return this.isNeedReObtainTime;
    }

    public long fixTimeStamp(long time) {
        try {
            if (isNeedFixTime()) {
                return getCurrentTimeMillisWithFix();
            }
            return time;
        } catch (NumberFormatException e) {
            Log.d(Config.TAG, "fixTimeStamp exception  : " + e.getLocalizedMessage());
            return time;
        }
    }

    public long getCurrentTimeMillisWithFix() {
        if (this.elapsedRealtime == 0) {
            this.elapsedRealtime = geServerTimeMillis() - SystemClock.elapsedRealtime();
        }
        return SystemClock.elapsedRealtime() + this.elapsedRealtime;
    }

    private void updateElapsedRealTime(long serviceTime) {
        this.elapsedRealtime = serviceTime - SystemClock.elapsedRealtime();
    }

    public void updateIntervalTime(String intervalTime2) {
        this.intervalTime = intervalTime2;
    }

    public long getServiceTimeAndLocalTimeInterval(long serviceTime) {
        this.serverTime = serviceTime;
        updateElapsedRealTime(serviceTime);
        long intervalTime2 = serviceTime - System.currentTimeMillis();
        if (Math.abs(intervalTime2) <= 30000) {
            intervalTime2 = 0;
        }
        updateIntervalTime("" + intervalTime2);
        return intervalTime2;
    }

    public long geServerTimeMillis() {
        if (this.serverTime > 0) {
            Log.d(Config.TAG, "server time from request");
        } else {
            Log.d(Config.TAG, "server time from mobile phone");
            this.serverTime = System.currentTimeMillis();
        }
        return this.serverTime;
    }

    /* access modifiers changed from: private */
    public void logTips(String tips) {
        if (!TextUtils.isEmpty(tips)) {
            Log.e(Config.TAG, "┏==============================================================================================┓");
            Log.e(Config.TAG, "  ==============================================================================================");
            Log.e(Config.TAG, "  ====================================来自遥远的温馨提示START===================================");
            Log.e(Config.TAG, " *\n *  .--,       .--, \n * ( (  \\.---./  ) ) \n *  '.__/o   o\\__.' \n *     {=  ^  =} \n *      >  -  < \n *     /       \\ \n *    //        \\\\ \n *   //|   .    |\\\\ \n *     \\       /_.-~^`'-. \n *      \\  _  /--'         ` \n *    ___)( )(___ \n *   (((__) (__)))    高山仰止,景行行止.虽不能至,心向往之。 \n *");
            Log.e(Config.TAG, "  云梯版本检测:" + tips);
            Log.e(Config.TAG, "  云梯版本检测:" + tips);
            Log.e(Config.TAG, "  云梯版本检测:" + tips);
            Log.e(Config.TAG, "  ====================================来自遥远的温馨提示END=====================================");
            Log.e(Config.TAG, "  ==============================================================================================");
            Log.e(Config.TAG, "┗==============================================================================================┛");
        }
    }

    private void addObtainServerTimeCallback(IObtainServerTimeCallback callback) {
        if (callback != null && !this.mObtainServerTimeCallbackList.contains(callback)) {
            this.mObtainServerTimeCallbackList.add(callback);
        }
    }

    /* access modifiers changed from: private */
    public void sendObtainServerTimeResult() {
        long time = getCurrentTimeMillisWithFix();
        for (IObtainServerTimeCallback callback : this.mObtainServerTimeCallbackList) {
            callback.onObtainServerTime(time);
        }
        this.mObtainServerTimeCallbackList.clear();
    }
}
