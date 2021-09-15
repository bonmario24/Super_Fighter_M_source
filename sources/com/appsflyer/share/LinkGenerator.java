package com.appsflyer.share;

import android.content.Context;
import com.appsflyer.AFExecutor;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.CreateOneLinkHttpTask;
import com.appsflyer.OneLinkHttpTask;
import com.appsflyer.ServerConfigHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LinkGenerator {

    /* renamed from: ı */
    private String f669;

    /* renamed from: Ɩ */
    private String f670;

    /* renamed from: ǃ */
    private String f671;

    /* renamed from: ȷ */
    private String f672;

    /* renamed from: ɨ */
    private Map<String, String> f673 = new HashMap();

    /* renamed from: ɩ */
    String f674;

    /* renamed from: ɪ */
    private Map<String, String> f675 = new HashMap();

    /* renamed from: ɹ */
    private String f676;

    /* renamed from: ɾ */
    private String f677;

    /* renamed from: Ι */
    private String f678;

    /* renamed from: ι */
    String f679;

    /* renamed from: І */
    private String f680;

    /* renamed from: і */
    private String f681;

    /* renamed from: Ӏ */
    private String f682;

    public LinkGenerator(String str) {
        this.f678 = str;
    }

    public LinkGenerator setBrandDomain(String str) {
        this.f677 = str;
        return this;
    }

    public String getBrandDomain() {
        return this.f677;
    }

    public LinkGenerator setDeeplinkPath(String str) {
        this.f682 = str;
        return this;
    }

    public LinkGenerator setBaseDeeplink(String str) {
        this.f672 = str;
        return this;
    }

    public String getChannel() {
        return this.f671;
    }

    public LinkGenerator setChannel(String str) {
        this.f671 = str;
        return this;
    }

    public LinkGenerator setReferrerCustomerId(String str) {
        this.f680 = str;
        return this;
    }

    public String getMediaSource() {
        return this.f678;
    }

    public Map<String, String> getParameters() {
        return this.f673;
    }

    public String getCampaign() {
        return this.f669;
    }

    public LinkGenerator setCampaign(String str) {
        this.f669 = str;
        return this;
    }

    public LinkGenerator addParameter(String str, String str2) {
        this.f673.put(str, str2);
        return this;
    }

    public LinkGenerator addParameters(Map<String, String> map) {
        if (map != null) {
            this.f673.putAll(map);
        }
        return this;
    }

    public LinkGenerator setReferrerUID(String str) {
        this.f676 = str;
        return this;
    }

    public LinkGenerator setReferrerName(String str) {
        this.f681 = str;
        return this;
    }

    public LinkGenerator setReferrerImageURL(String str) {
        this.f670 = str;
        return this;
    }

    public LinkGenerator setBaseURL(String str, String str2, String str3) {
        if (str == null || str.length() <= 0) {
            this.f674 = String.format("https://%s/%s", new Object[]{ServerConfigHandler.getUrl("%sapp.%s"), str3});
        } else {
            if (str2 == null || str2.length() < 5) {
                str2 = "go.onelink.me";
            }
            this.f674 = String.format("https://%s/%s", new Object[]{str2, str});
        }
        return this;
    }

    /* renamed from: ǃ */
    private StringBuilder m416() {
        StringBuilder sb = new StringBuilder();
        if (this.f674 == null || !this.f674.startsWith("http")) {
            sb.append(ServerConfigHandler.getUrl(Constants.f663));
        } else {
            sb.append(this.f674);
        }
        if (this.f679 != null) {
            sb.append('/').append(this.f679);
        }
        this.f675.put(Constants.URL_MEDIA_SOURCE, this.f678);
        sb.append('?').append("pid=").append(m417(this.f678, "media source"));
        if (this.f676 != null) {
            this.f675.put("af_referrer_uid", this.f676);
            sb.append('&').append("af_referrer_uid=").append(m417(this.f676, "referrerUID"));
        }
        if (this.f671 != null) {
            this.f675.put("af_channel", this.f671);
            sb.append('&').append("af_channel=").append(m417(this.f671, AppsFlyerProperties.CHANNEL));
        }
        if (this.f680 != null) {
            this.f675.put("af_referrer_customer_id", this.f680);
            sb.append('&').append("af_referrer_customer_id=").append(m417(this.f680, "referrerCustomerId"));
        }
        if (this.f669 != null) {
            this.f675.put(Constants.URL_CAMPAIGN, this.f669);
            sb.append('&').append("c=").append(m417(this.f669, FirebaseAnalytics.Param.CAMPAIGN));
        }
        if (this.f681 != null) {
            this.f675.put("af_referrer_name", this.f681);
            sb.append('&').append("af_referrer_name=").append(m417(this.f681, "referrerName"));
        }
        if (this.f670 != null) {
            this.f675.put("af_referrer_image_url", this.f670);
            sb.append('&').append("af_referrer_image_url=").append(m417(this.f670, "referrerImageURL"));
        }
        if (this.f672 != null) {
            StringBuilder append = new StringBuilder().append(this.f672);
            append.append(this.f672.endsWith("/") ? "" : "/");
            if (this.f682 != null) {
                append.append(this.f682);
            }
            this.f675.put("af_dp", append.toString());
            sb.append('&').append("af_dp=").append(m417(this.f672, "baseDeeplink"));
            if (this.f682 != null) {
                sb.append(this.f672.endsWith("/") ? "" : "%2F").append(m417(this.f682, "deeplinkPath"));
            }
        }
        for (String next : this.f673.keySet()) {
            if (!sb.toString().contains(new StringBuilder().append(next).append("=").append(m417(this.f673.get(next), next)).toString())) {
                sb.append('&').append(next).append('=').append(m417(this.f673.get(next), next));
            }
        }
        return sb;
    }

    /* renamed from: ɩ */
    private static String m417(String str, String str2) {
        try {
            return URLEncoder.encode(str, "utf8");
        } catch (UnsupportedEncodingException e) {
            AFLogger.afInfoLog(new StringBuilder("Illegal ").append(str2).append(": ").append(str).toString());
            return "";
        } catch (Throwable th) {
            return "";
        }
    }

    public String generateLink() {
        return m416().toString();
    }

    public void generateLink(Context context, CreateOneLinkHttpTask.ResponseListener responseListener) {
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID);
        if (!this.f673.isEmpty()) {
            for (Map.Entry next : this.f673.entrySet()) {
                this.f675.put(next.getKey(), next.getValue());
            }
        }
        m416();
        String str = this.f677;
        Map<String, String> map = this.f675;
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, generate User Invite Link is disabled", true);
            return;
        }
        CreateOneLinkHttpTask createOneLinkHttpTask = new CreateOneLinkHttpTask(string, map, AppsFlyerLibCore.getInstance(), context, AppsFlyerLib.getInstance().isTrackingStopped());
        createOneLinkHttpTask.setConnProvider(new OneLinkHttpTask.HttpsUrlConnectionProvider());
        createOneLinkHttpTask.setListener(responseListener);
        createOneLinkHttpTask.setBrandDomain(str);
        AFExecutor.getInstance().getThreadPoolExecutor().execute(createOneLinkHttpTask);
    }
}
