package com.bun.miitmdid.p013b;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

@Keep
/* renamed from: com.bun.miitmdid.b.b */
public class C0983b {
    @Keep

    /* renamed from: h */
    private static boolean f700h;
    @Keep

    /* renamed from: a */
    private String f701a = null;
    @Keep

    /* renamed from: b */
    private C0985b f702b = null;
    @Keep

    /* renamed from: c */
    private Object f703c = null;
    @Keep

    /* renamed from: d */
    private Map<String, String> f704d = new HashMap();
    @Keep

    /* renamed from: e */
    private Map<String, String> f705e = new HashMap();
    @Keep

    /* renamed from: f */
    private Map<String, String> f706f = new HashMap();
    @Keep

    /* renamed from: g */
    private String f707g = "GET";

    @Keep
    /* renamed from: com.bun.miitmdid.b.b$a */
    class C0984a extends AsyncTask<Void, Void, C0986c> {
        @Keep

        /* renamed from: a */
        C0983b f708a = C0983b.this;

        @Keep
        C0984a() {
        }

        /* access modifiers changed from: protected */
        @Keep
        /* renamed from: a */
        public native C0986c mo14795a(Void... voidArr);

        /* access modifiers changed from: protected */
        @Keep
        /* renamed from: a */
        public native void mo14796a(C0986c cVar);

        /* access modifiers changed from: protected */
        @Keep
        public native /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr);

        /* access modifiers changed from: protected */
        @Keep
        public native /* bridge */ /* synthetic */ void onPostExecute(Object obj);
    }

    @Keep
    /* renamed from: com.bun.miitmdid.b.b$b */
    public interface C0985b {
        @Keep
        /* renamed from: a */
        void mo14799a(Exception exc, int i, String str);
    }

    @Keep
    /* renamed from: com.bun.miitmdid.b.b$c */
    private class C0986c {
        @Keep

        /* renamed from: a */
        private String f710a;
        @Keep

        /* renamed from: b */
        private int f711b;
        @Keep

        /* renamed from: c */
        private Exception f712c;

        @Keep
        public C0986c(C0983b bVar, String str, Exception exc, int i) {
            this.f710a = str;
            this.f712c = exc;
            this.f711b = i;
        }

        @Keep
        /* renamed from: a */
        static native /* synthetic */ Exception m475a(C0986c cVar);

        @Keep
        /* renamed from: b */
        static native /* synthetic */ int m476b(C0986c cVar);

        @Keep
        /* renamed from: c */
        static native /* synthetic */ String m477c(C0986c cVar);
    }

    @Keep
    private C0983b(Context context) {
    }

    @Keep
    /* renamed from: a */
    public static native C0983b m456a(@NonNull Context context);

    @Keep
    /* renamed from: a */
    static native /* synthetic */ String m457a(C0983b bVar);

    @Keep
    /* renamed from: a */
    private native void m458a(HttpURLConnection httpURLConnection);

    @Keep
    /* renamed from: b */
    private native C0986c m459b();

    @Keep
    /* renamed from: b */
    static native /* synthetic */ C0986c m460b(C0983b bVar);

    @Keep
    /* renamed from: b */
    private static native void m461b(@NonNull String str);

    @Keep
    /* renamed from: c */
    private native C0986c m462c();

    @Keep
    /* renamed from: c */
    static native /* synthetic */ C0986c m463c(C0983b bVar);

    @Keep
    /* renamed from: d */
    static native /* synthetic */ C0985b m464d(C0983b bVar);

    @Keep
    /* renamed from: d */
    private native String m465d();

    @Keep
    /* renamed from: a */
    public native C0983b mo14789a();

    @Keep
    /* renamed from: a */
    public native C0983b mo14790a(C0985b bVar);

    @Keep
    /* renamed from: a */
    public native C0983b mo14791a(Object obj);

    @Keep
    /* renamed from: a */
    public native C0983b mo14792a(@NonNull String str);

    @Keep
    /* renamed from: a */
    public native C0983b mo14793a(@NonNull String str, @NonNull String str2);

    @Keep
    /* renamed from: a */
    public native C0983b mo14794a(Map<String, String> map);
}
