package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;

final class zzal extends zzae<T> {
    private final Object lock = new Object();
    private String zzec;
    private T zzed;
    private final /* synthetic */ zzan zzee;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzal(zzao zzao, String str, Object obj, zzan zzan) {
        super(zzao, str, obj, (zzai) null);
        this.zzee = zzan;
    }

    /* access modifiers changed from: protected */
    public final T zza(SharedPreferences sharedPreferences) {
        try {
            return zzb(sharedPreferences.getString(this.zzds, ""));
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            String valueOf = String.valueOf(this.zzds);
            Log.e("PhenotypeFlag", valueOf.length() != 0 ? "Invalid byte[] value in SharedPreferences for ".concat(valueOf) : new String("Invalid byte[] value in SharedPreferences for "), classCastException);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final T zzb(String str) {
        T t;
        try {
            synchronized (this.lock) {
                if (!str.equals(this.zzec)) {
                    T zzb = this.zzee.zzb(Base64.decode(str, 3));
                    this.zzec = str;
                    this.zzed = zzb;
                }
                t = this.zzed;
            }
            return t;
        } catch (IOException | IllegalArgumentException e) {
            String str2 = this.zzds;
            Log.e("PhenotypeFlag", new StringBuilder(String.valueOf(str2).length() + 27 + String.valueOf(str).length()).append("Invalid byte[] value for ").append(str2).append(": ").append(str).toString());
            return null;
        }
    }
}
