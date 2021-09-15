package com.appsflyer.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.appsflyer.AFDeepLinkManager;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.OneLinkHttpTask;
import com.appsflyer.ServerConfigHandler;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.appsflyer.internal.ab */
public final class C0917ab extends OneLinkHttpTask {

    /* renamed from: Ι */
    private static List<String> f505 = Arrays.asList(new String[]{"onelink.me", "onelnk.com", "app.aflink.com"});

    /* renamed from: ı */
    private String f506;

    /* renamed from: ǃ */
    private String f507;

    /* renamed from: ι */
    public C0918b f508;

    /* renamed from: com.appsflyer.internal.ab$b */
    public interface C0918b {
        /* renamed from: ı */
        void mo14612(String str);

        /* renamed from: ɩ */
        void mo14613(Map<String, String> map);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0917ab(Uri uri, AppsFlyerLibCore appsFlyerLibCore) {
        super(appsFlyerLibCore);
        boolean z;
        boolean z2;
        if (!TextUtils.isEmpty(uri.getHost()) && !TextUtils.isEmpty(uri.getPath())) {
            boolean z3 = false;
            for (String contains : f505) {
                if (uri.getHost().contains(contains)) {
                    z2 = true;
                } else {
                    z2 = z3;
                }
                z3 = z2;
            }
            if (AFDeepLinkManager.f342 != null) {
                AFLogger.afRDLog(new StringBuilder("Validate custom domain URLs: ").append(Arrays.asList(AFDeepLinkManager.f342)).toString());
                z = z3;
                for (String str : AFDeepLinkManager.f342) {
                    if (uri.getHost().contains(str) && str != "") {
                        AFLogger.afDebugLog(new StringBuilder("DeepLink matches customDomain: ").append(uri.toString()).toString());
                        z = true;
                    }
                }
            } else {
                z = z3;
            }
            String[] split = uri.getPath().split("/");
            if (z && split.length == 3) {
                this.oneLinkId = split[1];
                this.f507 = split[2];
                this.f506 = uri.toString();
            }
        }
    }

    /* renamed from: ι */
    public final boolean mo14683() {
        return !TextUtils.isEmpty(this.oneLinkId) && !TextUtils.isEmpty(this.f507) && !this.oneLinkId.equals("app");
    }

    public final void initRequest(HttpsURLConnection httpsURLConnection) throws JSONException, IOException {
        httpsURLConnection.setRequestMethod("GET");
    }

    public final String getOneLinkUrl() {
        return new StringBuilder().append(ServerConfigHandler.getUrl("https://%sonelink.%s/shortlink-sdk/v1")).append("/").append(this.oneLinkId).append("?id=").append(this.f507).toString();
    }

    public final void handleResponse(String str) {
        try {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.optString(next));
            }
            this.f508.mo14613(hashMap);
        } catch (JSONException e) {
            this.f508.mo14612("Can't parse one link data");
            AFLogger.afErrorLog("Error while parsing to json ".concat(String.valueOf(str)), e);
        }
    }

    public final void onErrorResponse() {
        this.f508.mo14612(this.f506 != null ? this.f506 : "Can't get one link data");
    }
}
