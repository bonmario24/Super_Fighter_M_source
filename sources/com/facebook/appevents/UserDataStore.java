package com.facebook.appevents;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Patterns;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDataStore {
    public static final String CITY = "ct";
    public static final String COUNTRY = "country";
    public static final String DATE_OF_BIRTH = "db";
    public static final String EMAIL = "em";
    public static final String FIRST_NAME = "fn";
    public static final String GENDER = "ge";
    public static final String LAST_NAME = "ln";
    public static final String PHONE = "ph";
    public static final String STATE = "st";
    /* access modifiers changed from: private */
    public static final String TAG = UserDataStore.class.getSimpleName();
    private static final String USER_DATA_KEY = "com.facebook.appevents.UserDataStore.userData";
    public static final String ZIP = "zp";
    /* access modifiers changed from: private */
    public static ConcurrentHashMap<String, String> hashedUserData;
    /* access modifiers changed from: private */
    public static AtomicBoolean initialized = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static SharedPreferences sharedPreferences;

    static void initStore() {
        if (!initialized.get()) {
            initAndWait();
        }
    }

    static void setUserDataAndHash(final Bundle ud) {
        InternalAppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                if (!UserDataStore.initialized.get()) {
                    Log.w(UserDataStore.TAG, "initStore should have been called before calling setUserData");
                    UserDataStore.initAndWait();
                }
                UserDataStore.updateHashUserData(ud);
                UserDataStore.sharedPreferences.edit().putString(UserDataStore.USER_DATA_KEY, UserDataStore.mapToJsonStr(UserDataStore.hashedUserData)).apply();
            }
        });
    }

    static void setUserDataAndHash(@Nullable String email, @Nullable String firstName, @Nullable String lastName, @Nullable String phone, @Nullable String dateOfBirth, @Nullable String gender, @Nullable String city, @Nullable String state, @Nullable String zip, @Nullable String country) {
        Bundle ud = new Bundle();
        if (email != null) {
            ud.putString(EMAIL, email);
        }
        if (firstName != null) {
            ud.putString(FIRST_NAME, firstName);
        }
        if (lastName != null) {
            ud.putString(LAST_NAME, lastName);
        }
        if (phone != null) {
            ud.putString(PHONE, phone);
        }
        if (dateOfBirth != null) {
            ud.putString(DATE_OF_BIRTH, dateOfBirth);
        }
        if (gender != null) {
            ud.putString(GENDER, gender);
        }
        if (city != null) {
            ud.putString(CITY, city);
        }
        if (state != null) {
            ud.putString(STATE, state);
        }
        if (zip != null) {
            ud.putString(ZIP, zip);
        }
        if (country != null) {
            ud.putString(COUNTRY, country);
        }
        setUserDataAndHash(ud);
    }

    static void clear() {
        InternalAppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                if (!UserDataStore.initialized.get()) {
                    Log.w(UserDataStore.TAG, "initStore should have been called before calling setUserData");
                    UserDataStore.initAndWait();
                }
                UserDataStore.hashedUserData.clear();
                UserDataStore.sharedPreferences.edit().putString(UserDataStore.USER_DATA_KEY, (String) null).apply();
            }
        });
    }

    static String getHashedUserData() {
        if (!initialized.get()) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            initAndWait();
        }
        return mapToJsonStr(hashedUserData);
    }

    /* access modifiers changed from: private */
    public static synchronized void initAndWait() {
        synchronized (UserDataStore.class) {
            if (!initialized.get()) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
                hashedUserData = new ConcurrentHashMap<>(JsonStrToMap(sharedPreferences.getString(USER_DATA_KEY, "")));
                initialized.set(true);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void updateHashUserData(Bundle ud) {
        if (ud != null) {
            for (String key : ud.keySet()) {
                Object rawVal = ud.get(key);
                if (rawVal != null) {
                    String value = rawVal.toString();
                    if (maybeSHA256Hashed(value)) {
                        hashedUserData.put(key, value.toLowerCase());
                    } else {
                        String encryptedValue = Utility.sha256hash(normalizeData(key, value));
                        if (encryptedValue != null) {
                            hashedUserData.put(key, encryptedValue);
                        }
                    }
                }
            }
        }
    }

    private static String normalizeData(String type, String data) {
        String data2 = data.trim().toLowerCase();
        if (EMAIL.equals(type)) {
            if (Patterns.EMAIL_ADDRESS.matcher(data2).matches()) {
                String str = data2;
                return data2;
            }
            Log.e(TAG, "Setting email failure: this is not a valid email address");
            String str2 = data2;
            return "";
        } else if (PHONE.equals(type)) {
            String str3 = data2;
            return data2.replaceAll("[^0-9]", "");
        } else if (GENDER.equals(type)) {
            String data3 = data2.length() > 0 ? data2.substring(0, 1) : "";
            if ("f".equals(data3) || "m".equals(data3)) {
                String str4 = data3;
                return data3;
            }
            Log.e(TAG, "Setting gender failure: the supported value for gender is f or m");
            String str5 = data3;
            return "";
        } else {
            return data2;
        }
    }

    private static boolean maybeSHA256Hashed(String data) {
        return data.matches("[A-Fa-f0-9]{64}");
    }

    /* access modifiers changed from: private */
    public static String mapToJsonStr(Map<String, String> map) {
        if (map.isEmpty()) {
            return "";
        }
        try {
            JSONObject jsonObject = new JSONObject();
            for (String key : map.keySet()) {
                jsonObject.put(key, map.get(key));
            }
            return jsonObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    private static Map<String, String> JsonStrToMap(String str) {
        if (str.isEmpty()) {
            return new HashMap();
        }
        try {
            Map<String, String> map = new HashMap<>();
            JSONObject jsonObject = new JSONObject(str);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                map.put(key, jsonObject.getString(key));
            }
            return map;
        } catch (JSONException e) {
            return new HashMap();
        }
    }
}
