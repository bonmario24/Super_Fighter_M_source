package com.appsflyer;

import android.text.TextUtils;
import com.appsflyer.internal.C0968z;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;

public abstract class OneLinkHttpTask implements Runnable {
    static final String BASE_URL = "https://%sonelink.%s/shortlink-sdk/v1";
    static final String NO_CONNECTION_ERROR_MSG = "Can't get one link data";
    private static final int WAIT_TIMEOUT = 3000;
    private AppsFlyerLibCore afLib;
    private HttpsUrlConnectionProvider connectionProvider;
    public String oneLinkId;

    /* access modifiers changed from: protected */
    public abstract String getOneLinkUrl();

    /* access modifiers changed from: protected */
    public abstract void handleResponse(String str);

    /* access modifiers changed from: protected */
    public abstract void initRequest(HttpsURLConnection httpsURLConnection) throws JSONException, IOException;

    /* access modifiers changed from: protected */
    public abstract void onErrorResponse();

    public OneLinkHttpTask(AppsFlyerLibCore appsFlyerLibCore) {
        this.afLib = appsFlyerLibCore;
    }

    public void setConnProvider(HttpsUrlConnectionProvider httpsUrlConnectionProvider) {
        this.connectionProvider = httpsUrlConnectionProvider;
    }

    public void run() {
        doRequest();
    }

    private void doRequest() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String str = "";
        String str2 = "";
        String oneLinkUrl = getOneLinkUrl();
        AFLogger.afRDLog("oneLinkUrl: " + oneLinkUrl);
        try {
            HttpsURLConnection r5 = this.connectionProvider.mo14672(oneLinkUrl);
            r5.addRequestProperty("content-type", "application/json");
            r5.addRequestProperty("authorization", C0968z.m414(currentTimeMillis));
            r5.addRequestProperty("af-timestamp", String.valueOf(currentTimeMillis));
            r5.setReadTimeout(3000);
            r5.setConnectTimeout(3000);
            initRequest(r5);
            int responseCode = r5.getResponseCode();
            str = this.afLib.mo14600((HttpURLConnection) r5);
            if (responseCode == 200) {
                AFLogger.afInfoLog("Status 200 ok");
            } else {
                str2 = "Response code = " + responseCode + " content = " + str;
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog("Error while calling " + oneLinkUrl, th);
            str2 = "Error while calling " + oneLinkUrl + " stacktrace: " + th.toString();
        }
        if (TextUtils.isEmpty(str2)) {
            AFLogger.afInfoLog("Connection call succeeded: " + str);
            handleResponse(str);
            return;
        }
        AFLogger.afWarnLog("Connection error: " + str2);
        onErrorResponse();
    }

    public static class HttpsUrlConnectionProvider {
        /* access modifiers changed from: package-private */
        /* renamed from: ǃ */
        public final HttpsURLConnection mo14672(String str) throws IOException {
            return (HttpsURLConnection) new URL(str).openConnection();
        }
    }
}
