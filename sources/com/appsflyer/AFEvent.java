package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class AFEvent {

    /* renamed from: ı */
    WeakReference<Context> f349;

    /* renamed from: Ɩ */
    public String f350;
    @Nullable

    /* renamed from: ǃ */
    public Intent f351;

    /* renamed from: ȷ */
    boolean f352;

    /* renamed from: ɨ */
    public int f353;

    /* renamed from: ɩ */
    AppsFlyerTrackingRequestListener f354;

    /* renamed from: ɪ */
    private Map<String, Object> f355;

    /* renamed from: ɹ */
    String f356;

    /* renamed from: ɾ */
    private final boolean f357;

    /* renamed from: ɿ */
    private byte[] f358;
    @Nullable

    /* renamed from: Ι */
    Map<String, Object> f359;

    /* renamed from: ι */
    Context f360;
    @Nullable

    /* renamed from: І */
    String f361;

    /* renamed from: і */
    String f362;

    /* renamed from: Ӏ */
    String f363;

    /* renamed from: ӏ */
    private String f364;

    public AFEvent() {
        this((String) null, (Boolean) null, (Context) null);
    }

    public AFEvent(@Nullable String str, @Nullable Boolean bool, @Nullable Context context) {
        this.f363 = str;
        this.f357 = bool != null ? bool.booleanValue() : true;
        this.f360 = context;
    }

    /* access modifiers changed from: protected */
    public AFEvent context(Context context) {
        this.f360 = context;
        return this;
    }

    public Context context() {
        if (this.f360 != null) {
            return this.f360;
        }
        if (this.f349 != null) {
            return this.f349.get();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ǃ */
    public final AFEvent mo14501() {
        if (context() != null) {
            this.f360 = context().getApplicationContext();
        }
        return this;
    }

    public AFEvent weakContext() {
        this.f349 = new WeakReference<>(this.f360);
        this.f360 = null;
        return this;
    }

    public AFEvent urlString(String str) {
        this.f362 = str;
        return this;
    }

    public String urlString() {
        return this.f362;
    }

    @Nullable
    public Intent intent() {
        return this.f351;
    }

    public AFEvent key(String str) {
        this.f364 = str;
        return this;
    }

    public String key() {
        return this.f364;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ι */
    public final boolean mo14502() {
        return this.f352;
    }

    public AFEvent params(Map<String, ?> map) {
        this.f355 = map;
        return this;
    }

    public Map<String, Object> params() {
        return this.f355;
    }

    public AFEvent post(byte[] bArr) {
        this.f358 = bArr;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ι */
    public final byte[] mo14503() {
        return this.f358;
    }

    public boolean isEncrypt() {
        return this.f357;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public String addChannel(String str) {
        String configuredChannel = AppsFlyerLibCore.getInstance().getConfiguredChannel(context());
        return configuredChannel != null ? Uri.parse(str).buildUpon().appendQueryParameter(AppsFlyerProperties.CHANNEL, configuredChannel).build().toString() : str;
    }
}
