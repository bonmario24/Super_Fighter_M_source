package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import com.appsflyer.share.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.okgo.cookie.SerializableCookie;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class AndroidUtils {
    /* renamed from: ɩ */
    public static boolean m233(Context context, Intent intent) {
        return context.getPackageManager().queryIntentServices(intent, 0).size() > 0;
    }

    public static boolean isPermissionAvailable(Context context, String str) {
        if (str == null) {
            throw new IllegalArgumentException("permission is null");
        }
        int checkPermission = context.checkPermission(str, Process.myPid(), Process.myUid());
        AFLogger.afRDLog(new StringBuilder("is Permission Available: ").append(str).append("; res: ").append(checkPermission).toString());
        return checkPermission == 0;
    }

    /* renamed from: ɩ */
    static boolean m232() {
        return Build.BRAND.equals("OPPO");
    }

    /* renamed from: ι */
    static Map<String, String> m234(Context context, Map<String, String> map, Uri uri) {
        boolean z;
        String str;
        if (uri.getQuery() != null) {
            z = false;
            for (String str2 : uri.getQuery().split("&")) {
                int indexOf = str2.indexOf("=");
                if (indexOf > 0) {
                    str = str2.substring(0, indexOf);
                } else {
                    str = str2;
                }
                if (!map.containsKey(str)) {
                    if (str.equals(Constants.URL_CAMPAIGN)) {
                        str = FirebaseAnalytics.Param.CAMPAIGN;
                    } else if (str.equals(Constants.URL_MEDIA_SOURCE)) {
                        str = "media_source";
                    } else if (str.equals("af_prt")) {
                        z = true;
                        str = "agency";
                    }
                    map.put(str, "");
                }
                map.put(str, (indexOf <= 0 || str2.length() <= indexOf + 1) ? null : str2.substring(indexOf + 1));
            }
        } else {
            z = false;
        }
        try {
            if (!map.containsKey("install_time")) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                long j = packageInfo.firstInstallTime;
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                map.put("install_time", simpleDateFormat.format(new Date(j)));
            }
        } catch (Exception e) {
            AFLogger.afErrorLog("Could not fetch install time. ", e);
        }
        if (uri.getQueryParameter("af_deeplink") != null && !map.containsKey("af_status")) {
            map.put("af_status", "Non-organic");
        }
        if (z) {
            map.remove("media_source");
        }
        String path = uri.getPath();
        if (path != null) {
            map.put("path", path);
        }
        String scheme = uri.getScheme();
        if (scheme != null) {
            map.put("scheme", scheme);
        }
        String host = uri.getHost();
        if (host != null) {
            map.put(SerializableCookie.HOST, host);
        }
        return map;
    }

    public static String signature(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException, CertificateException, NoSuchAlgorithmException {
        Signature[] signatureArr = packageManager.getPackageInfo(str, 64).signatures;
        MessageDigest instance = MessageDigest.getInstance("SHA256");
        instance.update(((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getEncoded());
        return String.format("%032X", new Object[]{new BigInteger(1, instance.digest())});
    }
}
