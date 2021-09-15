package com.thinkfly.cloudlader.interceptor;

import com.eagle.mixsdk.sdk.did.ThinkFlyUtils;
import com.thinkfly.cloudlader.Interceptor;
import com.thinkfly.cloudlader.data.DBData;
import com.thinkfly.plugins.coludladder.log.Log;
import com.thinkfly.plugins.resolution.Resolution;
import com.thinkfly.star.CloudLadderApplication;
import java.util.List;

public class DidInterceptor implements Interceptor {
    public List<DBData> intercept(List<DBData> list) {
        if (list != null && list.size() > 0) {
            for (DBData dbData : list) {
                Resolution.fixDeviceData(dbData.getJson());
            }
        }
        return list;
    }

    public boolean isIntercept() {
        if (ThinkFlyUtils.checkDIDValid(CloudLadderApplication.getInstance().getApplication())) {
            return false;
        }
        Log.m662d(Log.TAG, "the did is invalid ");
        return true;
    }
}
