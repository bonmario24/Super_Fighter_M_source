package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzct {
    final String zza;
    final Uri zzb;
    final String zzc;
    final String zzd;
    final boolean zze;
    final boolean zzf;
    final boolean zzg;
    final boolean zzh;
    @Nullable
    final zzcx<Context, Boolean> zzi;

    public zzct(Uri uri) {
        this((String) null, uri, "", "", false, false, false, false, (zzcx<Context, Boolean>) null);
    }

    private zzct(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzcx<Context, Boolean> zzcx) {
        this.zza = null;
        this.zzb = uri;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = false;
        this.zzf = false;
        this.zzg = false;
        this.zzh = false;
        this.zzi = null;
    }

    public final zzcn<Long> zza(String str, long j) {
        return zzcn.zzb(this, str, j);
    }

    public final zzcn<Boolean> zza(String str, boolean z) {
        return zzcn.zzb(this, str, z);
    }

    public final zzcn<Double> zza(String str, double d) {
        return zzcn.zzb(this, str, -3.0d);
    }

    public final zzcn<String> zza(String str, String str2) {
        return zzcn.zzb(this, str, str2);
    }
}
