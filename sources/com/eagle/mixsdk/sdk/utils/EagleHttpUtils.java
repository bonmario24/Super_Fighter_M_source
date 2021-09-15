package com.eagle.mixsdk.sdk.utils;

import android.annotation.SuppressLint;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.manger.EagleLoopDomainManager;
import com.eagle.mixsdk.sdk.okhttp.imp.OKHttpCallbackListener;
import com.eagle.mixsdk.sdk.okhttp.model.ExtensionInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONObject;

public class EagleHttpUtils extends HttpUtil {
    public static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @SuppressLint({"BadHostnameVerifier"})
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";

    public interface EagleHttpResultListener {
        void onMainThreadFail(String str, ExtensionInfo extensionInfo);

        void onMainThreadSuccess(JSONObject jSONObject, ExtensionInfo extensionInfo);
    }

    public static void doGetAsyncRequestWithLoop(String notInHostnamePath, EagleHttpResultListener listener) {
        doGetAsyncRequestWithLoop(notInHostnamePath, (Object) null, listener);
    }

    public static void doGetAsyncRequestWithLoop(String notInHostnamePath, Object args, final EagleHttpResultListener listener) {
        EagleLoopDomainManager.getInstance().doGetAsyncRequest(notInHostnamePath, args, new OKHttpCallbackListener() {
            public void onSuccess(final JSONObject jsonObject, final ExtensionInfo extensionInfo) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadSuccess(jsonObject, extensionInfo);
                        }
                    }
                });
            }

            public void onFailure(final String s, final ExtensionInfo extensionInfo) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadFail(s, extensionInfo);
                        }
                    }
                });
            }
        });
    }

    public static void doPostAsyncRequestWithLoop(String notInHostnamePath, String params, EagleHttpResultListener listener) {
        doPostAsyncRequestWithLoop(notInHostnamePath, params, (Object) null, listener);
    }

    public static void doPostAsyncRequestWithLoop(String notInHostnamePath, String params, Object args, final EagleHttpResultListener listener) {
        EagleLoopDomainManager.getInstance().doPostAsyncRequest(notInHostnamePath, params, args, new OKHttpCallbackListener() {
            public void onSuccess(final JSONObject jsonObject, final ExtensionInfo extensionInfo) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadSuccess(jsonObject, extensionInfo);
                        }
                    }
                });
            }

            public void onFailure(final String s, final ExtensionInfo extensionInfo) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadFail(s, extensionInfo);
                        }
                    }
                });
            }
        });
    }

    public static void doPostAsyncByFormRequestWithLoop(String notInHostnamePath, String params, EagleHttpResultListener listener) {
        doPostAsyncByFormRequestWithLoop(notInHostnamePath, params, (Object) null, listener);
    }

    public static void doPostAsyncByFormRequestWithLoop(String notInHostnamePath, String params, Object args, final EagleHttpResultListener listener) {
        EagleLoopDomainManager.getInstance().doPostAsyncByFormRequest(notInHostnamePath, params, args, new OKHttpCallbackListener() {
            public void onSuccess(final JSONObject jsonObject, final ExtensionInfo extensionInfo) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadSuccess(jsonObject, extensionInfo);
                        }
                    }
                });
            }

            public void onFailure(final String s, final ExtensionInfo extensionInfo) {
                EagleSDK.getInstance().runOnMainThread(new Runnable() {
                    public void run() {
                        if (listener != null) {
                            listener.onMainThreadFail(s, extensionInfo);
                        }
                    }
                });
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX WARNING: type inference failed for: r14v11, types: [java.net.URLConnection] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0098 A[SYNTHETIC, Splitter:B:25:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f3 A[SYNTHETIC, Splitter:B:52:0x00f3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String httpGet(java.lang.String r17, java.util.Map<java.lang.String, java.lang.String> r18) {
        /*
            r10 = 0
            r12 = 0
            r2 = 0
            r6 = 0
            java.lang.String r9 = ""
            if (r18 == 0) goto L_0x0010
            java.lang.String r14 = "UTF-8"
            r0 = r18
            java.lang.String r9 = urlParamsFormat(r0, r14)     // Catch:{ Exception -> 0x0103 }
        L_0x0010:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0103 }
            r14.<init>()     // Catch:{ Exception -> 0x0103 }
            r0 = r17
            java.lang.StringBuilder r14 = r14.append(r0)     // Catch:{ Exception -> 0x0103 }
            java.lang.String r15 = "?"
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0103 }
            java.lang.StringBuilder r14 = r14.append(r9)     // Catch:{ Exception -> 0x0103 }
            java.lang.String r4 = r14.toString()     // Catch:{ Exception -> 0x0103 }
            java.lang.String r14 = "EagleSDK"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0103 }
            r15.<init>()     // Catch:{ Exception -> 0x0103 }
            java.lang.String r16 = "the fullUrl is "
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x0103 }
            java.lang.StringBuilder r15 = r15.append(r4)     // Catch:{ Exception -> 0x0103 }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x0103 }
            android.util.Log.d(r14, r15)     // Catch:{ Exception -> 0x0103 }
            java.net.URL r13 = new java.net.URL     // Catch:{ Exception -> 0x0103 }
            r13.<init>(r4)     // Catch:{ Exception -> 0x0103 }
            java.lang.String r14 = r13.getProtocol()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r14 = r14.toUpperCase()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r15 = "HTTPS"
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            if (r14 == 0) goto L_0x009c
            trustAllHosts()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.net.URLConnection r5 = r13.openConnection()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            javax.net.ssl.HttpsURLConnection r5 = (javax.net.ssl.HttpsURLConnection) r5     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            javax.net.ssl.HostnameVerifier r14 = DO_NOT_VERIFY     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r5.setHostnameVerifier(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r2 = r5
        L_0x0065:
            int r14 = r2.getResponseCode()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r15 = 200(0xc8, float:2.8E-43)
            if (r14 != r15) goto L_0x00b6
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.io.InputStream r14 = r2.getInputStream()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r7.<init>(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x008b, all -> 0x00ff }
            r1.<init>(r7)     // Catch:{ Exception -> 0x008b, all -> 0x00ff }
            java.lang.StringBuffer r11 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x008b, all -> 0x00ff }
            r11.<init>()     // Catch:{ Exception -> 0x008b, all -> 0x00ff }
            r8 = 0
        L_0x0081:
            java.lang.String r8 = r1.readLine()     // Catch:{ Exception -> 0x008b, all -> 0x00ff }
            if (r8 == 0) goto L_0x00a5
            r11.append(r8)     // Catch:{ Exception -> 0x008b, all -> 0x00ff }
            goto L_0x0081
        L_0x008b:
            r3 = move-exception
            r6 = r7
            r12 = r13
        L_0x008e:
            r3.printStackTrace()     // Catch:{ all -> 0x00eb }
            if (r2 == 0) goto L_0x0096
            r2.disconnect()
        L_0x0096:
            if (r6 == 0) goto L_0x009b
            r6.close()     // Catch:{ IOException -> 0x00e6 }
        L_0x009b:
            return r10
        L_0x009c:
            java.net.URLConnection r14 = r13.openConnection()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r0 = r14
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r2 = r0
            goto L_0x0065
        L_0x00a5:
            java.lang.String r10 = r11.toString()     // Catch:{ Exception -> 0x008b, all -> 0x00ff }
            r6 = r7
        L_0x00aa:
            if (r2 == 0) goto L_0x00af
            r2.disconnect()
        L_0x00af:
            if (r6 == 0) goto L_0x0105
            r6.close()     // Catch:{ IOException -> 0x00e0 }
            r12 = r13
            goto L_0x009b
        L_0x00b6:
            java.lang.String r14 = "EagleSDK"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r15.<init>()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r16 = "get connection failed. code:"
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            int r16 = r2.getResponseCode()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r16 = ";url:"
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.StringBuilder r15 = r15.append(r4)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            android.util.Log.e(r14, r15)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            goto L_0x00aa
        L_0x00dd:
            r3 = move-exception
            r12 = r13
            goto L_0x008e
        L_0x00e0:
            r3 = move-exception
            r3.printStackTrace()
            r12 = r13
            goto L_0x009b
        L_0x00e6:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x009b
        L_0x00eb:
            r14 = move-exception
        L_0x00ec:
            if (r2 == 0) goto L_0x00f1
            r2.disconnect()
        L_0x00f1:
            if (r6 == 0) goto L_0x00f6
            r6.close()     // Catch:{ IOException -> 0x00f7 }
        L_0x00f6:
            throw r14
        L_0x00f7:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x00f6
        L_0x00fc:
            r14 = move-exception
            r12 = r13
            goto L_0x00ec
        L_0x00ff:
            r14 = move-exception
            r6 = r7
            r12 = r13
            goto L_0x00ec
        L_0x0103:
            r3 = move-exception
            goto L_0x008e
        L_0x0105:
            r12 = r13
            goto L_0x009b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eagle.mixsdk.sdk.utils.EagleHttpUtils.httpGet(java.lang.String, java.util.Map):java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX WARNING: type inference failed for: r14v6, types: [java.net.URLConnection] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0096 A[SYNTHETIC, Splitter:B:25:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f3 A[SYNTHETIC, Splitter:B:52:0x00f3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String httpPost(java.lang.String r17, java.util.Map<java.lang.String, java.lang.String> r18) {
        /*
            r10 = 0
            r12 = 0
            r2 = 0
            r6 = 0
            java.lang.String r9 = ""
            if (r18 == 0) goto L_0x0010
            java.lang.String r14 = "UTF-8"
            r0 = r18
            java.lang.String r9 = urlParamsFormat(r0, r14)     // Catch:{ Exception -> 0x0103 }
        L_0x0010:
            java.net.URL r13 = new java.net.URL     // Catch:{ Exception -> 0x0103 }
            r0 = r17
            r13.<init>(r0)     // Catch:{ Exception -> 0x0103 }
            java.lang.String r14 = r13.getProtocol()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r14 = r14.toUpperCase()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r15 = "HTTPS"
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            if (r14 == 0) goto L_0x009a
            trustAllHosts()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.net.URLConnection r5 = r13.openConnection()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            javax.net.ssl.HttpsURLConnection r5 = (javax.net.ssl.HttpsURLConnection) r5     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            javax.net.ssl.HostnameVerifier r14 = DO_NOT_VERIFY     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r5.setHostnameVerifier(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r2 = r5
        L_0x0036:
            r14 = 1
            r2.setDoInput(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r14 = 1
            r2.setDoOutput(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r14 = "POST"
            r2.setRequestMethod(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r14 = "Content-Type"
            java.lang.String r15 = "application/x-www-form-urlencoded"
            r2.setRequestProperty(r14, r15)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r14 = "Charset"
            java.lang.String r15 = "utf-8"
            r2.setRequestProperty(r14, r15)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.io.OutputStream r14 = r2.getOutputStream()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r3.<init>(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r3.writeBytes(r9)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r3.flush()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r3.close()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            int r14 = r2.getResponseCode()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r15 = 200(0xc8, float:2.8E-43)
            if (r14 != r15) goto L_0x00b4
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.io.InputStream r14 = r2.getInputStream()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r7.<init>(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0089, all -> 0x00ff }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0089, all -> 0x00ff }
            java.lang.StringBuffer r11 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0089, all -> 0x00ff }
            r11.<init>()     // Catch:{ Exception -> 0x0089, all -> 0x00ff }
            r8 = 0
        L_0x007f:
            java.lang.String r8 = r1.readLine()     // Catch:{ Exception -> 0x0089, all -> 0x00ff }
            if (r8 == 0) goto L_0x00a3
            r11.append(r8)     // Catch:{ Exception -> 0x0089, all -> 0x00ff }
            goto L_0x007f
        L_0x0089:
            r4 = move-exception
            r6 = r7
            r12 = r13
        L_0x008c:
            r4.printStackTrace()     // Catch:{ all -> 0x00eb }
            if (r2 == 0) goto L_0x0094
            r2.disconnect()
        L_0x0094:
            if (r6 == 0) goto L_0x0099
            r6.close()     // Catch:{ IOException -> 0x00e6 }
        L_0x0099:
            return r10
        L_0x009a:
            java.net.URLConnection r14 = r13.openConnection()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r0 = r14
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r2 = r0
            goto L_0x0036
        L_0x00a3:
            java.lang.String r10 = r11.toString()     // Catch:{ Exception -> 0x0089, all -> 0x00ff }
            r6 = r7
        L_0x00a8:
            if (r2 == 0) goto L_0x00ad
            r2.disconnect()
        L_0x00ad:
            if (r6 == 0) goto L_0x0105
            r6.close()     // Catch:{ IOException -> 0x00e0 }
            r12 = r13
            goto L_0x0099
        L_0x00b4:
            java.lang.String r14 = "EagleSDK"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r15.<init>()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r16 = "post connection failed. code:"
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            int r16 = r2.getResponseCode()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r16 = ";url:"
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            r0 = r17
            java.lang.StringBuilder r15 = r15.append(r0)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            android.util.Log.e(r14, r15)     // Catch:{ Exception -> 0x00dd, all -> 0x00fc }
            goto L_0x00a8
        L_0x00dd:
            r4 = move-exception
            r12 = r13
            goto L_0x008c
        L_0x00e0:
            r4 = move-exception
            r4.printStackTrace()
            r12 = r13
            goto L_0x0099
        L_0x00e6:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0099
        L_0x00eb:
            r14 = move-exception
        L_0x00ec:
            if (r2 == 0) goto L_0x00f1
            r2.disconnect()
        L_0x00f1:
            if (r6 == 0) goto L_0x00f6
            r6.close()     // Catch:{ IOException -> 0x00f7 }
        L_0x00f6:
            throw r14
        L_0x00f7:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x00f6
        L_0x00fc:
            r14 = move-exception
            r12 = r13
            goto L_0x00ec
        L_0x00ff:
            r14 = move-exception
            r6 = r7
            r12 = r13
            goto L_0x00ec
        L_0x0103:
            r4 = move-exception
            goto L_0x008c
        L_0x0105:
            r12 = r13
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eagle.mixsdk.sdk.utils.EagleHttpUtils.httpPost(java.lang.String, java.util.Map):java.lang.String");
    }

    public static String urlParamsFormat(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String val = params.get(key);
            if (val == null) {
                val = "";
            }
            String encodedName = URLEncoder.encode(key, charset);
            String encodedValue = URLEncoder.encode(val, charset);
            if (sb.length() > 0) {
                sb.append(PARAMETER_SEPARATOR);
            }
            sb.append(encodedName).append(NAME_VALUE_SEPARATOR).append(encodedValue);
        }
        return sb.toString();
    }

    public static void trustAllHosts() {
        TrustManager[] trustAllCerts = {new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @SuppressLint({"TrustAllX509TrustManager"})
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @SuppressLint({"TrustAllX509TrustManager"})
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init((KeyManager[]) null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
