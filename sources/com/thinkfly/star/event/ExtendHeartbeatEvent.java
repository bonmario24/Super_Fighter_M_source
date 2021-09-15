package com.thinkfly.star.event;

import android.content.Context;
import com.thinkfly.cloudlader.CombDataFactory;
import com.thinkfly.star.builder.Builder;
import com.thinkfly.star.builder.EventBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class ExtendHeartbeatEvent implements IHeartbeatEvent {
    private Builder mBuilder;

    public ExtendHeartbeatEvent(Builder builder) {
        this.mBuilder = builder;
    }

    public JSONObject createHeartbeatJson(Context context, CombDataFactory data) {
        try {
            return data.createEventJson(context, (EventBuilder) this.mBuilder);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public void setUid(String uid) {
    }

    public void updateEvent(Builder builder) {
    }
}
