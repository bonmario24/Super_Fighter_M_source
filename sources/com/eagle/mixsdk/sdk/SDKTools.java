package com.eagle.mixsdk.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.ClipboardManager;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.log.Log;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SDKTools {
    public static boolean isNetworkAvailable(Context activity) {
        NetworkInfo localNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService("connectivity");
            if (connectivityManager == null || (localNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !localNetworkInfo.isAvailable()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002c A[SYNTHETIC, Splitter:B:15:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032 A[SYNTHETIC, Splitter:B:19:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0044 A[SYNTHETIC, Splitter:B:30:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0062 A[SYNTHETIC, Splitter:B:45:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0068 A[SYNTHETIC, Splitter:B:49:0x0068] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getAssetConfigs(android.content.Context r8, java.lang.String r9) {
        /*
            r4 = 0
            r0 = 0
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x007e }
            android.content.res.AssetManager r7 = r8.getAssets()     // Catch:{ Exception -> 0x007e }
            java.io.InputStream r7 = r7.open(r9)     // Catch:{ Exception -> 0x007e }
            r5.<init>(r7)     // Catch:{ Exception -> 0x007e }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0080, all -> 0x0077 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0080, all -> 0x0077 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0024, all -> 0x007a }
            r6.<init>()     // Catch:{ Exception -> 0x0024, all -> 0x007a }
            r3 = 0
        L_0x001a:
            java.lang.String r3 = r1.readLine()     // Catch:{ Exception -> 0x0024, all -> 0x007a }
            if (r3 == 0) goto L_0x0038
            r6.append(r3)     // Catch:{ Exception -> 0x0024, all -> 0x007a }
            goto L_0x001a
        L_0x0024:
            r2 = move-exception
            r0 = r1
            r4 = r5
        L_0x0027:
            r2.printStackTrace()     // Catch:{ all -> 0x005f }
            if (r0 == 0) goto L_0x0030
            r0.close()     // Catch:{ IOException -> 0x0055 }
            r0 = 0
        L_0x0030:
            if (r4 == 0) goto L_0x0036
            r4.close()     // Catch:{ IOException -> 0x005a }
            r4 = 0
        L_0x0036:
            r7 = 0
        L_0x0037:
            return r7
        L_0x0038:
            java.lang.String r7 = r6.toString()     // Catch:{ Exception -> 0x0024, all -> 0x007a }
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch:{ IOException -> 0x0049 }
            r0 = 0
        L_0x0042:
            if (r5 == 0) goto L_0x0053
            r5.close()     // Catch:{ IOException -> 0x004f }
            r4 = 0
            goto L_0x0037
        L_0x0049:
            r2 = move-exception
            r2.printStackTrace()
        L_0x004d:
            r0 = r1
            goto L_0x0042
        L_0x004f:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0053:
            r4 = r5
            goto L_0x0037
        L_0x0055:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0030
        L_0x005a:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0036
        L_0x005f:
            r7 = move-exception
        L_0x0060:
            if (r0 == 0) goto L_0x0066
            r0.close()     // Catch:{ IOException -> 0x006d }
            r0 = 0
        L_0x0066:
            if (r4 == 0) goto L_0x006c
            r4.close()     // Catch:{ IOException -> 0x0072 }
            r4 = 0
        L_0x006c:
            throw r7
        L_0x006d:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0066
        L_0x0072:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x006c
        L_0x0077:
            r7 = move-exception
            r4 = r5
            goto L_0x0060
        L_0x007a:
            r7 = move-exception
            r0 = r1
            r4 = r5
            goto L_0x0060
        L_0x007e:
            r2 = move-exception
            goto L_0x0027
        L_0x0080:
            r2 = move-exception
            r4 = r5
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eagle.mixsdk.sdk.SDKTools.getAssetConfigs(android.content.Context, java.lang.String):java.lang.String");
    }

    @SuppressLint({"NewApi"})
    public static Map<String, String> getAssetPropConfig(Context context, String assetsPropertiesFile) {
        try {
            Properties pro = new Properties();
            pro.load(new InputStreamReader(context.getAssets().open(assetsPropertiesFile), "UTF-8"));
            Map<String, String> result = new HashMap<>();
            for (Map.Entry<Object, Object> entry : pro.entrySet()) {
                String keyStr = entry.getKey().toString().trim();
                String keyVal = entry.getValue().toString().trim();
                if (!result.containsKey(keyStr)) {
                    result.put(keyStr, keyVal);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMetaData(Context ctx, String key) {
        try {
            ApplicationInfo appInfo = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128);
            if (appInfo != null && appInfo.metaData != null && appInfo.metaData.containsKey(key)) {
                return "" + appInfo.metaData.get(key);
            }
            Log.m599e(Constants.TAG, "The meta-data key is not exists." + key);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyToClipBoard(final Activity activity, final String s) {
        activity.runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                ((ClipboardManager) activity.getSystemService("clipboard")).setText(s);
            }
        });
    }

    public static Map<String, String> collectDeviceInfo(Context ctx) {
        Map<String, String> info = new HashMap<>();
        try {
            PackageInfo p = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 1);
            if (p != null) {
                info.put("versionName", p.versionName == null ? "null" : p.versionName);
                info.put("versionCode", p.versionCode + "");
            }
            for (Field f : Build.class.getDeclaredFields()) {
                try {
                    f.setAccessible(true);
                    Object obj = f.get((Object) null);
                    info.put(f.getName(), obj == null ? "null" : obj.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            Log.m599e(Constants.TAG, "an error occured when collect package info...");
            e2.printStackTrace();
        }
        return info;
    }

    public static String getSystemKeyboard(final Activity activity) {
        FutureTask<String> futureResult = new FutureTask<>(new Callable<String>() {
            @SuppressLint({"NewApi"})
            public String call() throws Exception {
                ClipboardManager cmb = (ClipboardManager) activity.getSystemService("clipboard");
                if (cmb.hasText()) {
                    return cmb.getText().toString();
                }
                return "";
            }
        });
        activity.runOnUiThread(futureResult);
        try {
            return futureResult.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x007a A[SYNTHETIC, Splitter:B:34:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0086 A[SYNTHETIC, Splitter:B:40:0x0086] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getLogicChannel(android.content.Context r13, java.lang.String r14) {
        /*
            android.content.pm.ApplicationInfo r0 = r13.getApplicationInfo()
            java.lang.String r7 = r0.sourceDir
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "META-INF/"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r14)
            java.lang.String r5 = r11.toString()
            r9 = 0
            java.util.zip.ZipFile r10 = new java.util.zip.ZipFile     // Catch:{ Exception -> 0x0074 }
            r10.<init>(r7)     // Catch:{ Exception -> 0x0074 }
            java.util.Enumeration r2 = r10.entries()     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            r6 = 0
        L_0x0024:
            boolean r11 = r2.hasMoreElements()     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            if (r11 == 0) goto L_0x003b
            java.lang.Object r3 = r2.nextElement()     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            java.util.zip.ZipEntry r3 = (java.util.zip.ZipEntry) r3     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            java.lang.String r4 = r3.getName()     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            boolean r11 = r4.startsWith(r5)     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            if (r11 == 0) goto L_0x0024
            r6 = r4
        L_0x003b:
            boolean r11 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            if (r11 != 0) goto L_0x0066
            java.lang.String r11 = "_"
            java.lang.String[] r8 = r6.split(r11)     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            if (r8 == 0) goto L_0x0066
            int r11 = r8.length     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            r12 = 2
            if (r11 < r12) goto L_0x0066
            r11 = 0
            r11 = r8[r11]     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            int r11 = r11.length()     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            int r11 = r11 + 1
            java.lang.String r11 = r6.substring(r11)     // Catch:{ Exception -> 0x0092, all -> 0x008f }
            if (r10 == 0) goto L_0x005f
            r10.close()     // Catch:{ Exception -> 0x0061 }
        L_0x005f:
            r9 = r10
        L_0x0060:
            return r11
        L_0x0061:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x005f
        L_0x0066:
            if (r10 == 0) goto L_0x0095
            r10.close()     // Catch:{ Exception -> 0x006e }
            r9 = r10
        L_0x006c:
            r11 = 0
            goto L_0x0060
        L_0x006e:
            r1 = move-exception
            r1.printStackTrace()
            r9 = r10
            goto L_0x006c
        L_0x0074:
            r1 = move-exception
        L_0x0075:
            r1.printStackTrace()     // Catch:{ all -> 0x0083 }
            if (r9 == 0) goto L_0x006c
            r9.close()     // Catch:{ Exception -> 0x007e }
            goto L_0x006c
        L_0x007e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x006c
        L_0x0083:
            r11 = move-exception
        L_0x0084:
            if (r9 == 0) goto L_0x0089
            r9.close()     // Catch:{ Exception -> 0x008a }
        L_0x0089:
            throw r11
        L_0x008a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0089
        L_0x008f:
            r11 = move-exception
            r9 = r10
            goto L_0x0084
        L_0x0092:
            r1 = move-exception
            r9 = r10
            goto L_0x0075
        L_0x0095:
            r9 = r10
            goto L_0x006c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eagle.mixsdk.sdk.SDKTools.getLogicChannel(android.content.Context, java.lang.String):java.lang.String");
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    public static ProgressDialog showProgressTip(Activity context, String title) {
        ProgressDialog loadingActivity = new ProgressDialog(context);
        loadingActivity.setIndeterminate(true);
        loadingActivity.setCancelable(true);
        loadingActivity.setMessage(title);
        loadingActivity.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
            }
        });
        if (!context.isFinishing()) {
            loadingActivity.show();
        }
        return loadingActivity;
    }

    public static void hideProgressTip(ProgressDialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
