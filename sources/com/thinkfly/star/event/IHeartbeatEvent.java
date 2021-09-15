package com.thinkfly.star.event;

import android.content.Context;
import com.thinkfly.cloudlader.CombDataFactory;
import com.thinkfly.star.builder.Builder;
import org.json.JSONObject;

public interface IHeartbeatEvent {
    JSONObject createHeartbeatJson(Context context, CombDataFactory combDataFactory);

    void setUid(String str);

    void updateEvent(Builder builder);
}
