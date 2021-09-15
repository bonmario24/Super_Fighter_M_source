package com.facebook.appevents;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.appevents.internal.Constants;
import com.facebook.appevents.internal.RestrictiveDataManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AppEvent implements Serializable {
    private static final long serialVersionUID = 1;
    private static final HashSet<String> validatedIdentifiers = new HashSet<>();
    private final String checksum;
    private final boolean inBackground;
    private final boolean isImplicit;
    private final JSONObject jsonObject;
    private final String name;

    public AppEvent(String contextName, @NonNull String eventName, Double valueToSum, Bundle parameters, boolean isImplicitlyLogged, boolean isInBackground, @Nullable UUID currentSessionId) throws JSONException, FacebookException {
        this.isImplicit = isImplicitlyLogged;
        this.inBackground = isInBackground;
        this.name = eventName;
        this.jsonObject = getJSONObjectForAppEvent(contextName, eventName, valueToSum, parameters, currentSessionId);
        this.checksum = calculateChecksum();
    }

    public String getName() {
        return this.name;
    }

    private AppEvent(String jsonString, boolean isImplicit2, boolean inBackground2, String checksum2) throws JSONException {
        this.jsonObject = new JSONObject(jsonString);
        this.isImplicit = isImplicit2;
        this.name = this.jsonObject.optString(Constants.EVENT_NAME_EVENT_KEY);
        this.checksum = checksum2;
        this.inBackground = inBackground2;
    }

    public boolean getIsImplicit() {
        return this.isImplicit;
    }

    public JSONObject getJSONObject() {
        return this.jsonObject;
    }

    public boolean isChecksumValid() {
        if (this.checksum == null) {
            return true;
        }
        return calculateChecksum().equals(this.checksum);
    }

    private static void validateIdentifier(String identifier) throws FacebookException {
        boolean alreadyValidated;
        if (identifier == null || identifier.length() == 0 || identifier.length() > 40) {
            if (identifier == null) {
                identifier = "<None Provided>";
            }
            throw new FacebookException(String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", new Object[]{identifier, 40}));
        }
        synchronized (validatedIdentifiers) {
            alreadyValidated = validatedIdentifiers.contains(identifier);
        }
        if (alreadyValidated) {
            return;
        }
        if (identifier.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
            synchronized (validatedIdentifiers) {
                validatedIdentifiers.add(identifier);
            }
            return;
        }
        throw new FacebookException(String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", new Object[]{identifier}));
    }

    private JSONObject getJSONObjectForAppEvent(String contextName, @NonNull String eventName, Double valueToSum, Bundle parameters, @Nullable UUID currentSessionId) throws JSONException {
        validateIdentifier(eventName);
        JSONObject eventObject = new JSONObject();
        eventObject.put(Constants.EVENT_NAME_EVENT_KEY, eventName);
        eventObject.put(Constants.EVENT_NAME_MD5_EVENT_KEY, md5Checksum(eventName));
        eventObject.put(Constants.LOG_TIME_APP_EVENT_KEY, System.currentTimeMillis() / 1000);
        eventObject.put("_ui", contextName);
        if (currentSessionId != null) {
            eventObject.put("_session_id", currentSessionId);
        }
        if (parameters != null) {
            Map<String, String> processedParam = validateParameters(parameters);
            for (String key : processedParam.keySet()) {
                eventObject.put(key, processedParam.get(key));
            }
        }
        if (valueToSum != null) {
            eventObject.put(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM, valueToSum.doubleValue());
        }
        if (this.inBackground) {
            eventObject.put("_inBackground", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        if (this.isImplicit) {
            eventObject.put("_implicitlyLogged", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } else {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", eventObject.toString());
        }
        return eventObject;
    }

    private Map<String, String> validateParameters(Bundle parameters) throws FacebookException {
        Map<String, String> paramMap = new HashMap<>();
        for (String key : parameters.keySet()) {
            validateIdentifier(key);
            Object value = parameters.get(key);
            if ((value instanceof String) || (value instanceof Number)) {
                paramMap.put(key, value.toString());
            } else {
                throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", new Object[]{value, key}));
            }
        }
        RestrictiveDataManager.processParameters(paramMap, this.name);
        return paramMap;
    }

    static class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = -2488473066578201069L;
        private final boolean inBackground;
        private final boolean isImplicit;
        private final String jsonString;

        private SerializationProxyV1(String jsonString2, boolean isImplicit2, boolean inBackground2) {
            this.jsonString = jsonString2;
            this.isImplicit = isImplicit2;
            this.inBackground = inBackground2;
        }

        private Object readResolve() throws JSONException {
            return new AppEvent(this.jsonString, this.isImplicit, this.inBackground, (String) null);
        }
    }

    static class SerializationProxyV2 implements Serializable {
        private static final long serialVersionUID = 20160803001L;
        private final String checksum;
        private final boolean inBackground;
        private final boolean isImplicit;
        private final String jsonString;

        private SerializationProxyV2(String jsonString2, boolean isImplicit2, boolean inBackground2, String checksum2) {
            this.jsonString = jsonString2;
            this.isImplicit = isImplicit2;
            this.inBackground = inBackground2;
            this.checksum = checksum2;
        }

        private Object readResolve() throws JSONException {
            return new AppEvent(this.jsonString, this.isImplicit, this.inBackground, this.checksum);
        }
    }

    private Object writeReplace() {
        return new SerializationProxyV2(this.jsonObject.toString(), this.isImplicit, this.inBackground, this.checksum);
    }

    public String toString() {
        return String.format("\"%s\", implicit: %b, json: %s", new Object[]{this.jsonObject.optString(Constants.EVENT_NAME_EVENT_KEY), Boolean.valueOf(this.isImplicit), this.jsonObject.toString()});
    }

    private String calculateChecksum() {
        if (Build.VERSION.SDK_INT > 19) {
            return md5Checksum(this.jsonObject.toString());
        }
        ArrayList<String> keys = new ArrayList<>();
        Iterator<String> iterator = this.jsonObject.keys();
        while (iterator.hasNext()) {
            keys.add(iterator.next());
        }
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            sb.append(key).append(" = ").append(this.jsonObject.optString(key)).append(10);
        }
        return md5Checksum(sb.toString());
    }

    private static String md5Checksum(String toHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = toHash.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            return AppEventUtility.bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            Utility.logd("Failed to generate checksum: ", (Exception) e);
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        } catch (UnsupportedEncodingException e2) {
            Utility.logd("Failed to generate checksum: ", (Exception) e2);
            return AppEventsConstants.EVENT_PARAM_VALUE_YES;
        }
    }
}
