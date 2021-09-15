package com.zero.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.okgo.cache.CacheEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SimpleService extends Service {
    static Map<String, List<PushData>> pushInfos = new HashMap();
    static List<SpeciallyPushData> speciallypush = new ArrayList();

    /* renamed from: id */
    int f326id;
    NotificationManager manager;
    private MessageThread messageThread = null;
    PendingIntent pendingIntent;
    SimpleService service;

    class MessageThread extends Thread {
        MessageThread() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x0084 A[Catch:{ InterruptedException -> 0x0099 }] */
        /* JADX WARNING: Removed duplicated region for block: B:77:0x0000 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r31 = this;
            L_0x0000:
                java.util.Calendar r6 = java.util.Calendar.getInstance()
                r26 = 7
                r0 = r26
                int r26 = r6.get(r0)
                int r23 = r26 + -1
                r26 = 11
                r0 = r26
                int r12 = r6.get(r0)
                r26 = 12
                r0 = r26
                int r17 = r6.get(r0)
                r26 = 13
                r0 = r26
                int r18 = r6.get(r0)
                long r26 = java.lang.System.currentTimeMillis()
                r28 = 1000(0x3e8, double:4.94E-321)
                long r21 = r26 / r28
                r0 = r23
                long r0 = (long) r0
                r24 = r0
                int r26 = r12 * 60
                int r26 = r26 * 60
                int r27 = r17 * 60
                int r26 = r26 + r27
                int r26 = r26 + r18
                r0 = r26
                long r7 = (long) r0
                r19 = 0
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                int r26 = r26.size()
                if (r26 > 0) goto L_0x0052
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                int r26 = r26.size()
                if (r26 <= 0) goto L_0x0256
            L_0x0052:
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                int r26 = r26.size()
                if (r26 <= 0) goto L_0x006a
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                java.util.Set r26 = r26.keySet()
                java.util.Iterator r27 = r26.iterator()
            L_0x0064:
                boolean r26 = r27.hasNext()
                if (r26 != 0) goto L_0x00a6
            L_0x006a:
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                int r26 = r26.size()
                if (r26 <= 0) goto L_0x007d
                r13 = 0
            L_0x0073:
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                int r26 = r26.size()
                r0 = r26
                if (r13 < r0) goto L_0x01aa
            L_0x007d:
                r26 = 1000(0x3e8, double:4.94E-321)
                java.lang.Thread.sleep(r26)     // Catch:{ InterruptedException -> 0x0099 }
                if (r19 == 0) goto L_0x0000
                java.lang.String r26 = "unity"
                java.lang.String r27 = "绾跨▼鐫′簲绉掑墠锛侊紒锛�"
                android.util.Log.e(r26, r27)     // Catch:{ InterruptedException -> 0x0099 }
                r26 = 5000(0x1388, double:2.4703E-320)
                java.lang.Thread.sleep(r26)     // Catch:{ InterruptedException -> 0x0099 }
                java.lang.String r26 = "unity"
                java.lang.String r27 = "绾跨▼鐫′簲绉掑悗锛侊紒锛�"
                android.util.Log.e(r26, r27)     // Catch:{ InterruptedException -> 0x0099 }
                goto L_0x0000
            L_0x0099:
                r9 = move-exception
                java.lang.String r26 = "unity"
                java.lang.String r27 = "绾跨▼鏆傚仠涓�绉掑紓甯革紒锛侊�??"
                android.util.Log.e(r26, r27)
                r9.printStackTrace()
                goto L_0x0000
            L_0x00a6:
                java.lang.Object r15 = r27.next()
                java.lang.String r15 = (java.lang.String) r15
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                r0 = r26
                java.lang.Object r26 = r0.get(r15)
                java.util.List r26 = (java.util.List) r26
                int r16 = r26.size()
                r13 = 0
            L_0x00bb:
                r0 = r16
                if (r13 >= r0) goto L_0x0064
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                r0 = r26
                java.lang.Object r26 = r0.get(r15)
                java.util.List r26 = (java.util.List) r26
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$PushData r26 = (com.zero.service.SimpleService.PushData) r26
                r0 = r26
                int r0 = r0.daystamp
                r26 = r0
                r0 = r26
                long r0 = (long) r0
                r28 = r0
                int r26 = (r28 > r7 ? 1 : (r28 == r7 ? 0 : -1))
                if (r26 != 0) goto L_0x01a6
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                r0 = r26
                java.lang.Object r26 = r0.get(r15)
                java.util.List r26 = (java.util.List) r26
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$PushData r26 = (com.zero.service.SimpleService.PushData) r26
                r0 = r26
                int r0 = r0.week
                r26 = r0
                r0 = r26
                long r0 = (long) r0
                r28 = r0
                int r26 = (r28 > r24 ? 1 : (r28 == r24 ? 0 : -1))
                if (r26 != 0) goto L_0x01a6
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                r0 = r26
                java.lang.Object r26 = r0.get(r15)
                java.util.List r26 = (java.util.List) r26
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$PushData r26 = (com.zero.service.SimpleService.PushData) r26
                r0 = r26
                boolean r0 = r0.open
                r26 = r0
                if (r26 == 0) goto L_0x01a6
                r0 = r31
                com.zero.service.SimpleService r0 = com.zero.service.SimpleService.this
                r28 = r0
                java.lang.String r29 = com.teamtop3.Defenders.Localization.m_shouhuzhe
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                r0 = r26
                java.lang.Object r26 = r0.get(r15)
                java.util.List r26 = (java.util.List) r26
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$PushData r26 = (com.zero.service.SimpleService.PushData) r26
                r0 = r26
                java.lang.String r0 = r0.name
                r30 = r0
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                r0 = r26
                java.lang.Object r26 = r0.get(r15)
                java.util.List r26 = (java.util.List) r26
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$PushData r26 = (com.zero.service.SimpleService.PushData) r26
                r0 = r26
                java.lang.String r0 = r0.content
                r26 = r0
                r0 = r28
                r1 = r29
                r2 = r30
                r3 = r26
                r0.SendMessage(r1, r2, r3)
                java.lang.String r28 = "unity"
                java.lang.StringBuilder r26 = new java.lang.StringBuilder
                java.lang.String r29 = "鍙戦�佹帹閫佺殑娑堟伅涔嬪悗  锛屼俊鎭负 "
                r0 = r26
                r1 = r29
                r0.<init>(r1)
                r0 = r26
                r1 = r24
                java.lang.StringBuilder r26 = r0.append(r1)
                r0 = r26
                java.lang.StringBuilder r29 = r0.append(r7)
                java.util.Map<java.lang.String, java.util.List<com.zero.service.SimpleService$PushData>> r26 = com.zero.service.SimpleService.pushInfos
                r0 = r26
                java.lang.Object r26 = r0.get(r15)
                java.util.List r26 = (java.util.List) r26
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$PushData r26 = (com.zero.service.SimpleService.PushData) r26
                r0 = r26
                java.lang.String r0 = r0.name
                r26 = r0
                r0 = r29
                r1 = r26
                java.lang.StringBuilder r26 = r0.append(r1)
                java.lang.String r26 = r26.toString()
                r0 = r28
                r1 = r26
                android.util.Log.e(r0, r1)
                r19 = 1
            L_0x01a6:
                int r13 = r13 + 1
                goto L_0x00bb
            L_0x01aa:
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$SpeciallyPushData r26 = (com.zero.service.SimpleService.SpeciallyPushData) r26
                r0 = r26
                int r0 = r0.stamp
                r26 = r0
                r0 = r26
                long r0 = (long) r0
                r26 = r0
                int r26 = (r21 > r26 ? 1 : (r21 == r26 ? 0 : -1))
                if (r26 != 0) goto L_0x0252
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$SpeciallyPushData r26 = (com.zero.service.SimpleService.SpeciallyPushData) r26
                r0 = r26
                boolean r0 = r0.open
                r26 = r0
                if (r26 == 0) goto L_0x0252
                r0 = r31
                com.zero.service.SimpleService r0 = com.zero.service.SimpleService.this
                r27 = r0
                java.lang.String r28 = com.teamtop3.Defenders.Localization.m_shouhuzhe
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$SpeciallyPushData r26 = (com.zero.service.SimpleService.SpeciallyPushData) r26
                r0 = r26
                java.lang.String r0 = r0.name
                r29 = r0
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$SpeciallyPushData r26 = (com.zero.service.SimpleService.SpeciallyPushData) r26
                r0 = r26
                java.lang.String r0 = r0.content
                r26 = r0
                r0 = r27
                r1 = r28
                r2 = r29
                r3 = r26
                r0.SendMessage(r1, r2, r3)
                java.lang.String r27 = "unity"
                java.lang.StringBuilder r28 = new java.lang.StringBuilder
                java.lang.String r26 = "鍙戦�佹帹閫佺殑娑堟伅涔嬪悗  锛屼俊鎭负 "
                r0 = r28
                r1 = r26
                r0.<init>(r1)
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$SpeciallyPushData r26 = (com.zero.service.SimpleService.SpeciallyPushData) r26
                r0 = r26
                int r0 = r0.stamp
                r26 = r0
                r0 = r28
                r1 = r26
                java.lang.StringBuilder r28 = r0.append(r1)
                java.util.List<com.zero.service.SimpleService$SpeciallyPushData> r26 = com.zero.service.SimpleService.speciallypush
                r0 = r26
                java.lang.Object r26 = r0.get(r13)
                com.zero.service.SimpleService$SpeciallyPushData r26 = (com.zero.service.SimpleService.SpeciallyPushData) r26
                r0 = r26
                java.lang.String r0 = r0.name
                r26 = r0
                r0 = r28
                r1 = r26
                java.lang.StringBuilder r26 = r0.append(r1)
                java.lang.String r26 = r26.toString()
                r0 = r27
                r1 = r26
                android.util.Log.e(r0, r1)
                r19 = 1
            L_0x0252:
                int r13 = r13 + 1
                goto L_0x0073
            L_0x0256:
                java.io.File r11 = new java.io.File
                java.lang.StringBuilder r26 = new java.lang.StringBuilder
                java.lang.String r27 = "/data/data/"
                r26.<init>(r27)
                java.lang.String r27 = com.zero.service.ShowNotificationReceiver.packgename
                java.lang.StringBuilder r26 = r26.append(r27)
                java.lang.String r27 = "/files/push.txt"
                java.lang.StringBuilder r26 = r26.append(r27)
                java.lang.String r26 = r26.toString()
                r0 = r26
                r11.<init>(r0)
                boolean r26 = r11.exists()
                if (r26 == 0) goto L_0x007d
                r4 = 0
                java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ JSONException -> 0x0363, IOException -> 0x035a }
                java.io.FileReader r26 = new java.io.FileReader     // Catch:{ JSONException -> 0x0363, IOException -> 0x035a }
                r0 = r26
                r0.<init>(r11)     // Catch:{ JSONException -> 0x0363, IOException -> 0x035a }
                r0 = r26
                r5.<init>(r0)     // Catch:{ JSONException -> 0x0363, IOException -> 0x035a }
                r20 = 0
                java.lang.String r20 = r5.readLine()     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
            L_0x028f:
                if (r20 != 0) goto L_0x029d
                r5.close()     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                goto L_0x007d
            L_0x0296:
                r10 = move-exception
                r4 = r5
            L_0x0298:
                r10.printStackTrace()
                goto L_0x007d
            L_0x029d:
                r14 = 0
                org.json.JSONObject r14 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r0 = r20
                r14.<init>(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.String r26 = "open"
                r0 = r26
                boolean r26 = r14.getBoolean(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                if (r26 == 0) goto L_0x02f5
                java.lang.String r26 = "timesatmp"
                r0 = r26
                boolean r26 = r14.has(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                if (r26 == 0) goto L_0x02fa
                java.lang.String r26 = "timesatmp"
                r0 = r26
                int r26 = r14.getInt(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r0 = r21
                int r0 = (int) r0     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r27 = r0
                r0 = r26
                r1 = r27
                if (r0 != r1) goto L_0x02f5
                r0 = r31
                com.zero.service.SimpleService r0 = com.zero.service.SimpleService.this     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r28 = r0
                java.lang.String r29 = com.teamtop3.Defenders.Localization.m_shouhuzhe     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.String r26 = "name"
                r0 = r26
                java.lang.Object r26 = r14.get(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.CharSequence r26 = (java.lang.CharSequence) r26     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.String r27 = "content"
                r0 = r27
                java.lang.Object r27 = r14.get(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.CharSequence r27 = (java.lang.CharSequence) r27     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r0 = r28
                r1 = r29
                r2 = r26
                r3 = r27
                r0.SendMessage(r1, r2, r3)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r19 = 1
            L_0x02f5:
                java.lang.String r20 = r5.readLine()     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                goto L_0x028f
            L_0x02fa:
                java.lang.String r26 = "week"
                r0 = r26
                boolean r26 = r14.has(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                if (r26 == 0) goto L_0x02f5
                java.lang.String r26 = "daystamp"
                r0 = r26
                boolean r26 = r14.has(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                if (r26 == 0) goto L_0x02f5
                java.lang.String r26 = "week"
                r0 = r26
                int r26 = r14.getInt(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r0 = r26
                long r0 = (long) r0     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r26 = r0
                int r26 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1))
                if (r26 != 0) goto L_0x02f5
                java.lang.String r26 = "daystamp"
                r0 = r26
                int r26 = r14.getInt(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r0 = r26
                long r0 = (long) r0     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r26 = r0
                int r26 = (r26 > r7 ? 1 : (r26 == r7 ? 0 : -1))
                if (r26 != 0) goto L_0x02f5
                r0 = r31
                com.zero.service.SimpleService r0 = com.zero.service.SimpleService.this     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r28 = r0
                java.lang.String r29 = com.teamtop3.Defenders.Localization.m_shouhuzhe     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.String r26 = "name"
                r0 = r26
                java.lang.Object r26 = r14.get(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.CharSequence r26 = (java.lang.CharSequence) r26     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.String r27 = "content"
                r0 = r27
                java.lang.Object r27 = r14.get(r0)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                java.lang.CharSequence r27 = (java.lang.CharSequence) r27     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r0 = r28
                r1 = r29
                r2 = r26
                r3 = r27
                r0.SendMessage(r1, r2, r3)     // Catch:{ JSONException -> 0x0296, IOException -> 0x0360 }
                r19 = 1
                goto L_0x02f5
            L_0x035a:
                r9 = move-exception
            L_0x035b:
                r9.printStackTrace()
                goto L_0x007d
            L_0x0360:
                r9 = move-exception
                r4 = r5
                goto L_0x035b
            L_0x0363:
                r10 = move-exception
                goto L_0x0298
            */
            throw new UnsupportedOperationException("Method not decompiled: com.zero.service.SimpleService.MessageThread.run():void");
        }
    }

    static class PushData {
        public String content;
        public int daystamp;
        public String name;
        public boolean open;
        public int week;

        public PushData(int i, int i2, String str, String str2, boolean z) {
            this.content = str2;
            this.open = z;
            this.week = i;
            this.daystamp = i2;
            this.name = str;
        }
    }

    static class SpeciallyPushData {
        public String content;
        public String key;
        public String name;
        public boolean open;
        public int stamp;

        public SpeciallyPushData(String str, int i, String str2, String str3, boolean z) {
            this.key = str;
            this.stamp = i;
            this.name = str2;
            this.content = str3;
            this.open = z;
        }
    }

    public static void ActivityDestroy() {
        if (pushInfos.size() > 0 || speciallypush.size() > 0) {
            String str = "/data/data/" + ShowNotificationReceiver.packgename + "/files/push.txt";
            Log.e("unity", "activity destroy check push length is " + pushInfos.size());
            Log.e("unity", "鎷兼帴鍑烘潵鐨勭洰褰曚负         " + str);
            File file = new File(str);
            Log.e("unity", "111111111");
            try {
                if (!file.exists()) {
                    Log.e("unity", "22222222222");
                    file.createNewFile();
                    Log.e("unity", "鎺ㄩ�佹枃浠朵繚瀛樼殑鍦板潃 涓� 锛�" + file.getAbsolutePath() + "   " + str);
                } else {
                    Log.e("unity", "3333333333");
                    file.delete();
                    file.createNewFile();
                    Log.e("unity", "鎺ㄩ�佹枃浠朵繚瀛樼殑鍦板潃 涓� 锛�" + file.getAbsolutePath() + "   " + str);
                }
            } catch (IOException e) {
                Log.e("unity", "444444444");
                e.printStackTrace();
            }
            FileOutputStream fileOutputStream = null;
            try {
                Log.e("unity", "5555555555");
                fileOutputStream = new FileOutputStream(str);
            } catch (FileNotFoundException e2) {
                Log.e("unity", "66666666666");
                e2.printStackTrace();
            }
            if (fileOutputStream == null) {
                Log.e("unity", "SimpleService::ActivityDestroy error!!!!config file is not create!!!!path=" + str);
                return;
            }
            PrintStream printStream = new PrintStream(fileOutputStream);
            if (pushInfos.size() > 0) {
                for (String next : pushInfos.keySet()) {
                    int size = pushInfos.get(next).size();
                    for (int i = 0; i < size; i++) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put(CacheEntity.KEY, next);
                            jSONObject.put("week", ((PushData) pushInfos.get(next).get(i)).week);
                            jSONObject.put("daystamp", ((PushData) pushInfos.get(next).get(i)).daystamp);
                            jSONObject.put("open", ((PushData) pushInfos.get(next).get(i)).open);
                            jSONObject.put("name", ((PushData) pushInfos.get(next).get(i)).name);
                            jSONObject.put(FirebaseAnalytics.Param.CONTENT, ((PushData) pushInfos.get(next).get(i)).content);
                        } catch (JSONException e3) {
                            Log.e("unity", "7777777777777");
                            e3.printStackTrace();
                        }
                        Log.e("unity", "8888888888888");
                        printStream.println(jSONObject.toString());
                    }
                }
            }
            if (speciallypush.size() > 0) {
                for (int i2 = 0; i2 < speciallypush.size(); i2++) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(CacheEntity.KEY, speciallypush.get(i2).key);
                        jSONObject2.put("timesatmp", speciallypush.get(i2).stamp);
                        jSONObject2.put("open", speciallypush.get(i2).open);
                        jSONObject2.put("name", speciallypush.get(i2).name);
                        jSONObject2.put(FirebaseAnalytics.Param.CONTENT, speciallypush.get(i2).content);
                    } catch (JSONException e4) {
                        Log.e("unity", "99999999999");
                        e4.printStackTrace();
                    }
                    Log.e("unity", "aaaaaaaaaaa");
                    printStream.println(jSONObject2.toString());
                }
            }
            printStream.close();
            try {
                fileOutputStream.close();
            } catch (IOException e5) {
                Log.e("unity", "bbbbbbbbbbb");
                e5.printStackTrace();
            }
        }
    }

    public static void ClearNotifications() {
        if (pushInfos.size() > 0) {
            pushInfos.clear();
        }
        if (speciallypush.size() > 0) {
            speciallypush.clear();
        }
        File file = new File("/data/data/" + ShowNotificationReceiver.packgename + "/files/push.txt");
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    public static void RemovePushMessage(String str) {
        if (pushInfos.size() > 0) {
            for (String next : pushInfos.keySet()) {
                if (next.equals(str)) {
                    pushInfos.get(next).clear();
                }
            }
        }
        Iterator<SpeciallyPushData> it = speciallypush.iterator();
        while (it.hasNext()) {
            if (it.next().key.equals(str)) {
                it.remove();
            }
        }
    }

    public static void closePush(String str) {
        if (pushInfos.size() > 0) {
            for (String next : pushInfos.keySet()) {
                if (next.equals(str)) {
                    int size = pushInfos.get(next).size();
                    for (int i = 0; i < size; i++) {
                        ((PushData) pushInfos.get(next).get(i)).open = false;
                    }
                }
            }
        }
        if (speciallypush.size() > 0) {
            for (int i2 = 0; i2 < speciallypush.size(); i2++) {
                if (speciallypush.get(i2).key.equals(str)) {
                    speciallypush.get(i2).open = false;
                }
            }
        }
    }

    public static void openPush(String str) {
        if (pushInfos.size() > 0) {
            for (String next : pushInfos.keySet()) {
                if (next.equals(str)) {
                    int size = pushInfos.get(next).size();
                    for (int i = 0; i < size; i++) {
                        ((PushData) pushInfos.get(next).get(i)).open = true;
                    }
                }
            }
        }
        if (speciallypush.size() > 0) {
            for (int i2 = 0; i2 < speciallypush.size(); i2++) {
                if (speciallypush.get(i2).key.equals(str)) {
                    speciallypush.get(i2).open = true;
                }
            }
        }
    }

    public static void setDisposablePush(String str, int i, String str2, String str3, boolean z) {
        boolean z2 = false;
        if (speciallypush.size() > 0) {
            for (int i2 = 0; i2 < speciallypush.size(); i2++) {
                if (speciallypush.get(i2).key.equals(str) && speciallypush.get(i2).name.equals(str2) && speciallypush.get(i2).stamp == i) {
                    z2 = true;
                }
            }
            if (!z2) {
                speciallypush.add(new SpeciallyPushData(str, i, str2, str3, z));
                return;
            }
            return;
        }
        speciallypush.add(new SpeciallyPushData(str, i, str2, str3, z));
    }

    public static void setOnePush(String str, int i, int i2, String str2, String str3, boolean z) {
        Log.e("unity", "褰撳墠鏄熸湡涓�  锛�" + Calendar.getInstance().get(7));
        Log.e("unity", "1111111111");
        PushData pushData = new PushData(i, i2, str2, str3, z);
        Log.e("unity", "");
        boolean z2 = false;
        if (pushInfos.containsKey(str)) {
            Log.e("unity", "222222222");
            int size = pushInfos.get(str).size();
            for (int i3 = 0; i3 < size; i3++) {
                if (((PushData) pushInfos.get(str).get(i3)).daystamp == i2 && ((PushData) pushInfos.get(str).get(i3)).week == i && ((PushData) pushInfos.get(str).get(i3)).name.equals(str2)) {
                    z2 = true;
                }
            }
            Log.e("unity", "3333333333");
            if (!z2) {
                Log.e("unity", "666666666");
                pushInfos.get(str).add(pushData);
                Log.e("unity", "5555555");
            }
            Log.e("unity", "44444444");
        } else {
            Log.e("unity", "77777777");
            ArrayList arrayList = new ArrayList();
            arrayList.add(pushData);
            pushInfos.put(str, arrayList);
        }
        Log.e("unity", "淇濆瓨鍚庣紦瀛樻暟鎹殑�??垮害涓� 锛�" + pushInfos.get(str).size());
    }

    public void SendMessage(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        Log.e("unity", "杩涘叆鍙戦�氱煡銆傘�傘�傘�傘��");
        int identifier = getApplicationContext().getResources().getIdentifier("app_icon", "drawable", getApplicationContext().getPackageName());
        long currentTimeMillis = System.currentTimeMillis();
        Notification.Builder builder = new Notification.Builder(this.service);
        builder.setContentIntent(this.pendingIntent).setSmallIcon(identifier).setWhen(currentTimeMillis).setTicker(charSequence).setContentTitle(charSequence2).setContentText(charSequence3).setAutoCancel(true);
        Notification notification = builder.getNotification();
        notification.defaults = 1;
        this.manager.notify(this.f326id, notification);
        this.f326id++;
        Log.e("unity", "鍙戝畬閫氱煡銆傘�傘�傛湁娌℃湁锛燂�??");
    }

    /* access modifiers changed from: package-private */
    public boolean isRunningApp() {
        for (ActivityManager.RunningTaskInfo runningTaskInfo : ((ActivityManager) this.service.getSystemService("activity")).getRunningTasks(1000)) {
            if (runningTaskInfo.topActivity.getPackageName().equals(ShowNotificationReceiver.packgename)) {
                return true;
            }
        }
        return false;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        Intent intent = new Intent(this, ShowNotificationReceiver.class);
        intent.addFlags(268435456);
        this.pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 134217728);
        this.manager = (NotificationManager) getSystemService("notification");
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.messageThread = new MessageThread();
        this.messageThread.start();
        this.service = this;
        return super.onStartCommand(intent, 1, i2);
    }
}
