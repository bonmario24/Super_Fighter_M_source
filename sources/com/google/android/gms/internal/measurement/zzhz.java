package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzhz extends zzhx<zzhw, zzhw> {
    zzhz() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzhc zzhc) {
        return false;
    }

    private static void zza(Object obj, zzhw zzhw) {
        ((zzfe) obj).zzb = zzhw;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(Object obj) {
        ((zzfe) obj).zzb.zzc();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzf(Object obj) {
        return ((zzhw) obj).zze();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zze(Object obj) {
        return ((zzhw) obj).zzd();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(Object obj, Object obj2) {
        zzhw zzhw = (zzhw) obj;
        zzhw zzhw2 = (zzhw) obj2;
        if (zzhw2.equals(zzhw.zza())) {
            return zzhw;
        }
        return zzhw.zza(zzhw, zzhw2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, zziq zziq) throws IOException {
        ((zzhw) obj).zza(zziq);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, zziq zziq) throws IOException {
        ((zzhw) obj).zzb(zziq);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, Object obj2) {
        zza(obj, (zzhw) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(Object obj) {
        zzhw zzhw = ((zzfe) obj).zzb;
        if (zzhw != zzhw.zza()) {
            return zzhw;
        }
        zzhw zzb = zzhw.zzb();
        zza(obj, zzb);
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzb(Object obj) {
        return ((zzfe) obj).zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ void zza(Object obj, Object obj2) {
        zza(obj, (zzhw) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj) {
        zzhw zzhw = (zzhw) obj;
        zzhw.zzc();
        return zzhw;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza() {
        return zzhw.zzb();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzhw) obj).zza((i << 3) | 3, (Object) (zzhw) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, zzdw zzdw) {
        ((zzhw) obj).zza((i << 3) | 2, (Object) zzdw);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzhw) obj).zza((i << 3) | 1, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, int i2) {
        ((zzhw) obj).zza((i << 3) | 5, (Object) Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzhw) obj).zza(i << 3, (Object) Long.valueOf(j));
    }
}
