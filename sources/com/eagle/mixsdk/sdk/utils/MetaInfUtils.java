package com.eagle.mixsdk.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.doraemon.util.ShellAdbUtil;
import com.facebook.appevents.AppEventsConstants;

public class MetaInfUtils {
    private static MetaInfUtils _MetaInfUtils;
    private static String extChannel_tag = "extChannel";
    private static String extChannel_txt = "ext_channel.txt";
    private static String subChannelID_tag = "subChannelID";
    private static String subChannelID_txt = "sub_channelid.txt";
    private String extChannel = "";
    private int subChannelID = 0;

    private MetaInfUtils() {
    }

    public static MetaInfUtils getInstance() {
        if (_MetaInfUtils != null) {
            return _MetaInfUtils;
        }
        MetaInfUtils metaInfUtils = new MetaInfUtils();
        _MetaInfUtils = metaInfUtils;
        return metaInfUtils;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a8, code lost:
        if (r8.getSize() <= 0) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00aa, code lost:
        r5 = new java.io.BufferedReader(new java.io.InputStreamReader(r0.getInputStream(r8)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00bc, code lost:
        r10 = r5.readLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c0, code lost:
        if (r10 == null) goto L_0x0139;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ca, code lost:
        if (r10.contains(subChannelID_tag) == false) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00cc, code lost:
        r20.subChannelID = java.lang.Integer.parseInt(editString(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e4, code lost:
        if (r10.contains(extChannel_tag) == false) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e6, code lost:
        r11 = editString(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00f4, code lost:
        if (com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(r11) != false) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f6, code lost:
        r20.extChannel = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0143 A[SYNTHETIC, Splitter:B:44:0x0143] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initChannelContent(android.content.Context r21) {
        /*
            r20 = this;
            android.content.pm.ApplicationInfo r4 = r21.getApplicationInfo()
            java.lang.String r14 = r4.sourceDir
            r15 = 0
            java.util.zip.ZipFile r16 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x018a }
            r0 = r16
            r0.<init>(r14)     // Catch:{ IOException -> 0x018a }
            java.util.Enumeration r7 = r16.entries()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
        L_0x0012:
            boolean r17 = r7.hasMoreElements()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            if (r17 == 0) goto L_0x002c
            java.lang.Object r8 = r7.nextElement()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            java.util.zip.ZipEntry r8 = (java.util.zip.ZipEntry) r8     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            java.lang.String r9 = r8.getName()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            java.lang.String r17 = "../"
            r0 = r17
            boolean r17 = r9.contains(r0)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            if (r17 == 0) goto L_0x0096
        L_0x002c:
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r17.<init>()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r0 = r20
            int r0 = r0.subChannelID     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r18 = r0
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            java.lang.String r18 = ""
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            java.lang.String r17 = r17.toString()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r0 = r20
            r1 = r21
            r2 = r17
            r0.saveSubChannelID(r1, r2)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r0 = r20
            java.lang.String r0 = r0.extChannel     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r17 = r0
            r0 = r20
            r1 = r21
            r2 = r17
            r0.saveExtChannelID(r1, r2)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            if (r16 == 0) goto L_0x0062
            r16.close()     // Catch:{ IOException -> 0x0178 }
        L_0x0062:
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r17.<init>()
            r0 = r20
            int r0 = r0.subChannelID
            r18 = r0
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r18 = ""
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            r0 = r20
            r1 = r21
            r2 = r17
            r0.saveSubChannelID(r1, r2)
            r0 = r20
            java.lang.String r0 = r0.extChannel
            r17 = r0
            r0 = r20
            r1 = r21
            r2 = r17
            r0.saveExtChannelID(r1, r2)
            r15 = r16
        L_0x0095:
            return
        L_0x0096:
            java.lang.String r17 = "META-INF/eagle_common_config.txt"
            r0 = r17
            boolean r17 = r9.startsWith(r0)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            if (r17 == 0) goto L_0x0012
            long r12 = r8.getSize()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r18 = 0
            int r17 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r17 <= 0) goto L_0x002c
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            java.io.InputStreamReader r17 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r0 = r16
            java.io.InputStream r18 = r0.getInputStream(r8)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r17.<init>(r18)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r0 = r17
            r5.<init>(r0)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
        L_0x00bc:
            java.lang.String r10 = r5.readLine()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            if (r10 == 0) goto L_0x0139
            java.lang.String r17 = subChannelID_tag     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r0 = r17
            boolean r17 = r10.contains(r0)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            if (r17 == 0) goto L_0x00dc
            r0 = r20
            java.lang.String r17 = r0.editString(r10)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            int r17 = java.lang.Integer.parseInt(r17)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r0 = r17
            r1 = r20
            r1.subChannelID = r0     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
        L_0x00dc:
            java.lang.String r17 = extChannel_tag     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            r0 = r17
            boolean r17 = r10.contains(r0)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            if (r17 == 0) goto L_0x00bc
            r0 = r20
            java.lang.String r11 = r0.editString(r10)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            java.lang.String r17 = "0"
            r0 = r17
            boolean r17 = r0.equals(r11)     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            if (r17 != 0) goto L_0x00bc
            r0 = r20
            r0.extChannel = r11     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            goto L_0x00bc
        L_0x00fb:
            r6 = move-exception
            r15 = r16
        L_0x00fe:
            r6.printStackTrace()     // Catch:{ all -> 0x0188 }
            if (r15 == 0) goto L_0x0106
            r15.close()     // Catch:{ IOException -> 0x017e }
        L_0x0106:
            java.lang.StringBuilder r17 = new java.lang.StringBuilder
            r17.<init>()
            r0 = r20
            int r0 = r0.subChannelID
            r18 = r0
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r18 = ""
            java.lang.StringBuilder r17 = r17.append(r18)
            java.lang.String r17 = r17.toString()
            r0 = r20
            r1 = r21
            r2 = r17
            r0.saveSubChannelID(r1, r2)
            r0 = r20
            java.lang.String r0 = r0.extChannel
            r17 = r0
            r0 = r20
            r1 = r21
            r2 = r17
            r0.saveExtChannelID(r1, r2)
            goto L_0x0095
        L_0x0139:
            r5.close()     // Catch:{ IOException -> 0x00fb, all -> 0x013e }
            goto L_0x002c
        L_0x013e:
            r17 = move-exception
            r15 = r16
        L_0x0141:
            if (r15 == 0) goto L_0x0146
            r15.close()     // Catch:{ IOException -> 0x0183 }
        L_0x0146:
            java.lang.StringBuilder r18 = new java.lang.StringBuilder
            r18.<init>()
            r0 = r20
            int r0 = r0.subChannelID
            r19 = r0
            java.lang.StringBuilder r18 = r18.append(r19)
            java.lang.String r19 = ""
            java.lang.StringBuilder r18 = r18.append(r19)
            java.lang.String r18 = r18.toString()
            r0 = r20
            r1 = r21
            r2 = r18
            r0.saveSubChannelID(r1, r2)
            r0 = r20
            java.lang.String r0 = r0.extChannel
            r18 = r0
            r0 = r20
            r1 = r21
            r2 = r18
            r0.saveExtChannelID(r1, r2)
            throw r17
        L_0x0178:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0062
        L_0x017e:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0106
        L_0x0183:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0146
        L_0x0188:
            r17 = move-exception
            goto L_0x0141
        L_0x018a:
            r6 = move-exception
            goto L_0x00fe
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eagle.mixsdk.sdk.utils.MetaInfUtils.initChannelContent(android.content.Context):void");
    }

    private String editString(String editStr) {
        if (editStr == null || TextUtils.isEmpty(editStr) || !editStr.contains("=")) {
            return null;
        }
        String result = editStr.split("=")[1].trim();
        if (result.contains(ShellAdbUtil.COMMAND_LINE_END)) {
            result = result.replace(ShellAdbUtil.COMMAND_LINE_END, "");
        }
        if (result.contains("\r")) {
            return result.replace("\r", "");
        }
        return result;
    }

    private void saveSubChannelID(Context context, String content) {
        StoreUtils.putString(subChannelID_tag, content);
    }

    private void saveExtChannelID(Context context, String content) {
        StoreUtils.putString(extChannel_tag, content);
    }

    @Deprecated
    public int readSubChannelID(Activity context) {
        return getSubChannelID();
    }

    @Deprecated
    public String readExtChannelID(Activity context) {
        return getExtChannel();
    }

    public int getSubChannelID() {
        if (this.subChannelID == 0) {
            String subChannelIDStr = StoreUtils.getString(subChannelID_tag);
            if (!TextUtils.isEmpty(subChannelIDStr)) {
                try {
                    this.subChannelID = Integer.parseInt(subChannelIDStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.subChannelID;
    }

    public String getExtChannel() {
        if (TextUtils.isEmpty(this.extChannel)) {
            String tempExtC = StoreUtils.getString(extChannel_tag);
            if (!AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(tempExtC)) {
                this.extChannel = tempExtC;
            }
        }
        return this.extChannel;
    }

    public void setSubChannelID(int subChannelID2) {
        this.subChannelID = subChannelID2;
    }

    public void setExtChannel(String extChannel2) {
        this.extChannel = extChannel2;
    }
}
