package com.appsflyer.internal;

import com.appsflyer.AFLogger;

/* renamed from: com.appsflyer.internal.ag */
public final class C0923ag {

    /* renamed from: ı */
    private C0925a f511 = new C0925a() {
        /* renamed from: ǃ */
        public final Class<?> mo14688(String str) throws ClassNotFoundException {
            return Class.forName(str);
        }
    };

    /* renamed from: com.appsflyer.internal.ag$a */
    interface C0925a {
        /* renamed from: ǃ */
        Class<?> mo14688(String str) throws ClassNotFoundException;
    }

    /* renamed from: ı */
    public final String mo14687() {
        for (C0926c cVar : C0926c.values()) {
            if (m329(cVar.f521)) {
                return cVar.f522;
            }
        }
        return C0926c.DEFAULT.f522;
    }

    /* renamed from: ι */
    private boolean m329(String str) {
        try {
            this.f511.mo14688(str);
            AFLogger.afRDLog(new StringBuilder("Class: ").append(str).append(" is found.").toString());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return false;
        }
    }

    /* renamed from: com.appsflyer.internal.ag$c */
    enum C0926c {
        UNITY("android_unity", "com.unity3d.player.UnityPlayer"),
        REACT_NATIVE("android_reactNative", "com.facebook.react.ReactApplication"),
        CORDOVA("android_cordova", "org.apache.cordova.CordovaActivity"),
        SEGMENT("android_segment", "com.segment.analytics.integrations.Integration"),
        COCOS2DX("android_cocos2dx", "org.cocos2dx.lib.Cocos2dxActivity"),
        DEFAULT("android_native", "android_native"),
        ADOBE_EX("android_adobe_ex", "com.appsflyer.adobeextension"),
        FLUTTER("android_flutter", "com.appsflyer.appsflyersdk.AppsflyerSdkPlugin");
        
        /* access modifiers changed from: private */

        /* renamed from: і */
        public String f521;
        /* access modifiers changed from: private */

        /* renamed from: Ӏ */
        public String f522;

        private C0926c(String str, String str2) {
            this.f522 = str;
            this.f521 = str2;
        }
    }
}
