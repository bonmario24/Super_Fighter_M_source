package com.eagle.mixsdk.sdk.log;

public class URemoteLog implements ILog {
    private URemoteLogPrinter printer;

    public URemoteLog(String str, int i) {
        this.printer = new URemoteLogPrinter(str, i);
    }

    /* renamed from: d */
    public void mo15758d(String str, String str2) {
        this.printer.print(new ULog("DEBUG", str, str2));
    }

    public void destory() {
        this.printer.stop();
    }

    /* renamed from: e */
    public void mo15760e(String str, String str2) {
        this.printer.print(new ULog("ERROR", str, str2));
    }

    /* renamed from: e */
    public void mo15761e(String str, String str2, Throwable th) {
        this.printer.print(new ULog("ERROR", str, str2, th));
    }

    /* renamed from: i */
    public void mo15762i(String str, String str2) {
        this.printer.print(new ULog("INFO", str, str2));
    }

    /* renamed from: w */
    public void mo15763w(String str, String str2) {
        this.printer.print(new ULog("WARNING", str, str2));
    }

    /* renamed from: w */
    public void mo15764w(String str, String str2, Throwable th) {
        this.printer.print(new ULog("WARNING", str, str2, th));
    }
}
