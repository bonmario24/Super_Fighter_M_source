package com.thinkfly.star.event;

import android.content.Context;
import android.text.TextUtils;
import com.thinkfly.cloudlader.CombDataFactory;
import com.thinkfly.star.builder.Builder;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudLadderHeartbeatEvent implements IHeartbeatEvent {
    private Builder mBuilder;

    public CloudLadderHeartbeatEvent(Builder builder) {
        this.mBuilder = builder;
    }

    public JSONObject createHeartbeatJson(Context context, CombDataFactory data) {
        try {
            return data.createHeartbeatJson(context, this.mBuilder);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public void setUid(String uid) {
        if (!TextUtils.isEmpty(uid) && this.mBuilder != null) {
            this.mBuilder.setUid(uid);
        }
    }

    public void updateEvent(Builder builder) {
        if (builder != null) {
            this.mBuilder = builder;
        }
    }
}
