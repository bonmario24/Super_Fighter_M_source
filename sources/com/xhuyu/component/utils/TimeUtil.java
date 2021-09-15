package com.xhuyu.component.utils;

import com.facebook.appevents.AppEventsConstants;
import java.util.TimeZone;

public class TimeUtil {
    public static String getTimeZone() {
        String timeZone = TimeZone.getDefault().getDisplayName(false, 0);
        if (timeZone.contains("GMT")) {
            timeZone = timeZone.replace("GMT", "");
        }
        if (timeZone.contains(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            timeZone = timeZone.replace(AppEventsConstants.EVENT_PARAM_VALUE_NO, "");
        }
        if (timeZone.contains(":")) {
            return timeZone.replace(":", "");
        }
        return timeZone;
    }
}
