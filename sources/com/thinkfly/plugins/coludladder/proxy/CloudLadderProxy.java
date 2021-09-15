package com.thinkfly.plugins.coludladder.proxy;

import android.content.ContentValues;
import com.eagle.mixsdk.sdk.verify.EagleInitConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.okgo.model.Progress;
import com.thinkfly.cloudlader.Interceptor;
import com.thinkfly.cloudlader.data.DBData;
import com.thinkfly.plugins.coludladder.CloudLadder;
import com.thinkfly.plugins.coludladder.config.Config;
import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.plugins.coludladder.okhttp.OKHttpUtil;
import com.thinkfly.plugins.coludladder.p034dk.DatabaseHelper;
import com.thinkfly.plugins.coludladder.utils.DataUtils;
import com.thinkfly.plugins.coludladder.utils.FixTimeUtils;
import com.thinkfly.plugins.coludladder.utils.device.DeviceUtils;
import com.thinkfly.plugins.resolution.Resolution;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

public class CloudLadderProxy implements Runnable {
    private AtomicInteger dataCount;
    private volatile boolean isPushing;
    private boolean isStop;
    private CloudLadder mCloudLadder = null;
    private DatabaseHelper mDatabaseHelper;
    private Interceptor mInterceptor;

    public CloudLadderProxy(CloudLadder cloudLadder, String dbName) {
        this.mCloudLadder = cloudLadder;
        this.mDatabaseHelper = new DatabaseHelper(cloudLadder.getContext(), dbName + ".db", dbName + "_table");
        this.mDatabaseHelper.open();
        this.dataCount = new AtomicInteger(this.mDatabaseHelper.getCount());
        this.isStop = false;
    }

    public CloudLadderProxy setInterceptor(Interceptor interceptor) {
        this.mInterceptor = interceptor;
        return this;
    }

    public DatabaseHelper getDatabaseHelper() {
        return this.mDatabaseHelper;
    }

    public long addRecordToDatabase(String record, int priority) {
        long rid = -1;
        try {
            byte[] byteDataArr = DataUtils.jsonToByteArray(record);
            ContentValues values = new ContentValues();
            values.put(FirebaseAnalytics.Param.CONTENT, byteDataArr);
            values.put(Progress.PRIORITY, Integer.valueOf(priority));
            values.put("timestamp", FixTimeUtils.getInstance().getIntervalTime());
            rid = getDatabaseHelper().insertToTrack(values);
            if (rid != -1) {
                this.dataCount.getAndIncrement();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rid;
    }

    public void run() {
        if (this.isStop) {
            Log.m662d(Log.TAG, "isStop ");
            return;
        }
        Log.m662d(Config.TAG, "Thread Name:" + Thread.currentThread().getName());
        if (isNullData()) {
            Log.m662d(Log.TAG, "data count is 0");
        } else if (!DeviceUtils.isNetworkAvailable()) {
            Log.m662d(Log.TAG, "isNetworkAvailable false ");
        } else if (!FixTimeUtils.getInstance().isValidCurrentTime()) {
            if (FixTimeUtils.getInstance().isNeedReObtainTime()) {
                FixTimeUtils.getInstance().obtainServerTime(this.mCloudLadder.getHost());
            }
            Log.m662d(Log.TAG, "the server time is invalid ");
        } else if (this.mInterceptor == null || !this.mInterceptor.isIntercept()) {
            pushData();
        } else {
            Log.m662d(Log.TAG, "interceptor is intercept");
        }
    }

    private synchronized void pushData() {
        this.isPushing = true;
        int macCount = Config.RepeatConfig.MAXNUMBER;
        if (this.dataCount.get() >= 200) {
            macCount = this.dataCount.get();
        }
        pushData(macCount);
        this.isPushing = false;
    }

    private void pushData(int maxCount) {
        List<DBData> mQueryDataLst = getDatabaseHelper().queryDataFromTrackWithLimit(maxCount);
        if (mQueryDataLst == null || mQueryDataLst.size() < 1) {
            Log.m662d(Log.TAG, "no more data to send...");
            return;
        }
        if (this.mInterceptor != null) {
            mQueryDataLst = this.mInterceptor.intercept(mQueryDataLst);
        }
        if (mQueryDataLst == null) {
            Log.m662d(Log.TAG, "list is null after intercept");
            return;
        }
        int size = mQueryDataLst.size();
        Log.m662d(Log.TAG, "mQueryDataLst size : " + size);
        JSONArray jsonArray = new JSONArray();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < size; i++) {
            DBData qData = mQueryDataLst.get(i);
            stringBuffer.append(qData.getId()).append(",");
            Resolution.resolution(qData.getJson(), qData.getTimestamp());
            jsonArray.put(qData.getJson());
        }
        Log.m662d(Log.TAG, "id : --->" + stringBuffer.toString());
        String sign = createSign(jsonArray);
        Log.m662d(Log.TAG, "sign : " + sign);
        if (sign != null) {
            JSONObject resultJson = OKHttpUtil.postRequest(this.mCloudLadder.getHost(), toCompress(jsonArray, 1), sign, 1);
            Log.m662d(Log.TAG, "sendData resultJson : " + resultJson);
            if (resultJson != null && resultJson.has("status") && resultJson.optInt("status") == 200) {
                verificationTime(resultJson);
                long start_rid = (long) mQueryDataLst.get(0).getId();
                if (size == 1) {
                    Log.m662d(Log.TAG, "delete rid : " + start_rid);
                    getDatabaseHelper().deleteFromTrackById(start_rid);
                } else {
                    long end_rid = (long) mQueryDataLst.get(size - 1).getId();
                    Log.m662d(Log.TAG, "delete rid : " + start_rid + EagleInitConfig.MSG_MARK + end_rid);
                    getDatabaseHelper().deleteMuchDataFromTrackById(start_rid, end_rid);
                }
                this.dataCount.getAndAdd(0 - size);
            }
        }
    }

    private String createSign(JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() <= 0) {
            return null;
        }
        return DataUtils.encrypt(this.mCloudLadder.getAppSec(), jsonArray.toString());
    }

    private String toCompress(JSONArray jsonArray, int isCompress) {
        try {
            Log.m662d(Log.TAG, "sendData jsonArray length: " + jsonArray.length());
            Log.m662d(Log.TAG, "sendData jsonArray : " + jsonArray.toString());
            if (jsonArray.length() <= 0) {
                return "";
            }
            String sendData = DataUtils.compress(jsonArray);
            Log.m662d(Log.TAG, "sendData compress : " + sendData);
            return sendData;
        } catch (Exception e) {
            e.printStackTrace();
            String sendData2 = jsonArray.toString();
            Log.m662d(Log.TAG, "sendData Exception : " + sendData2);
            return sendData2;
        }
    }

    private void verificationTime(JSONObject jsonObject) {
        if (jsonObject != null && jsonObject.has("timestamp")) {
            FixTimeUtils.getInstance().getServiceTimeAndLocalTimeInterval(jsonObject.optLong("timestamp"));
        }
    }

    public boolean isReadSend() {
        return !this.isPushing && this.dataCount.get() >= Config.RepeatConfig.MAXNUMBER / 2;
    }

    public boolean isNullData() {
        return this.dataCount.get() == 0;
    }

    public void onDestroy() {
        this.isStop = true;
        this.mDatabaseHelper.close();
    }
}
