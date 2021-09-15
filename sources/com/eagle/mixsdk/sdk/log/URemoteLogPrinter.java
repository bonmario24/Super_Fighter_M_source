package com.eagle.mixsdk.sdk.log;

import com.eagle.mixsdk.sdk.utils.EagleHttpUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class URemoteLogPrinter {
    private int interval;
    private List<ULog> logs;
    private boolean running;
    private Timer timer;
    /* access modifiers changed from: private */
    public String url;

    class LogPrintTask extends TimerTask {
        LogPrintTask() {
        }

        public void run() {
            try {
                List<ULog> andClear = URemoteLogPrinter.this.getAndClear();
                if (andClear.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for (ULog json : andClear) {
                        sb.append(json.toJSON()).append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1).append("]");
                    HashMap hashMap = new HashMap();
                    hashMap.put("log", sb.toString());
                    EagleHttpUtils.httpPost(URemoteLogPrinter.this.url, hashMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
                URemoteLogPrinter.this.stop();
            }
        }
    }

    public URemoteLogPrinter() {
        this.interval = 1000;
    }

    public URemoteLogPrinter(String str, int i) {
        this.interval = 1000;
        this.logs = Collections.synchronizedList(new ArrayList());
        this.url = str;
        this.interval = i;
    }

    public List<ULog> getAndClear() {
        ArrayList arrayList;
        synchronized (this.logs) {
            arrayList = new ArrayList(this.logs);
            this.logs.clear();
        }
        return arrayList;
    }

    public void print(ULog uLog) {
        start();
        synchronized (this.logs) {
            this.logs.add(uLog);
        }
    }

    public void printImmediate(String str, ULog uLog) {
        HashMap hashMap = new HashMap();
        hashMap.put("log", uLog.toJSON());
        EagleHttpUtils.httpPost(str, hashMap);
    }

    public void start() {
        if (!this.running) {
            this.running = true;
            LogPrintTask logPrintTask = new LogPrintTask();
            this.timer = new Timer(true);
            this.timer.scheduleAtFixedRate(logPrintTask, 100, (long) this.interval);
        }
    }

    public void stop() {
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.running = false;
    }
}
