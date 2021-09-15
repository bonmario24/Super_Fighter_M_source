package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.text.TextUtils;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.0 */
public final class zzgb extends zzen {
    /* access modifiers changed from: private */
    public final zzka zza;
    private Boolean zzb;
    @Nullable
    private String zzc;

    public zzgb(zzka zzka) {
        this(zzka, (String) null);
    }

    private zzgb(zzka zzka, @Nullable String str) {
        Preconditions.checkNotNull(zzka);
        this.zza = zzka;
        this.zzc = null;
    }

    @BinderThread
    public final void zzb(zzn zzn) {
        zzb(zzn, false);
        zza((Runnable) new zzga(this, zzn));
    }

    @BinderThread
    public final void zza(zzao zzao, zzn zzn) {
        Preconditions.checkNotNull(zzao);
        zzb(zzn, false);
        zza((Runnable) new zzgj(this, zzao, zzn));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final zzao zzb(zzao zzao, zzn zzn) {
        boolean z = false;
        if (!(!"_cmp".equals(zzao.zza) || zzao.zzb == null || zzao.zzb.zza() == 0)) {
            String zzd = zzao.zzb.zzd("_cis");
            if (!TextUtils.isEmpty(zzd) && (("referrer broadcast".equals(zzd) || "referrer API".equals(zzd)) && this.zza.zzb().zze(zzn.zza, zzaq.zzar))) {
                z = true;
            }
        }
        if (!z) {
            return zzao;
        }
        this.zza.zzr().zzv().zza("Event has been filtered ", zzao.toString());
        return new zzao("_cmpx", zzao.zzb, zzao.zzc, zzao.zzd);
    }

    @BinderThread
    public final void zza(zzao zzao, String str, String str2) {
        Preconditions.checkNotNull(zzao);
        Preconditions.checkNotEmpty(str);
        zza(str, true);
        zza((Runnable) new zzgi(this, zzao, str));
    }

    @BinderThread
    public final byte[] zza(zzao zzao, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzao);
        zza(str, true);
        this.zza.zzr().zzw().zza("Log and bundle. event", this.zza.zzi().zza(zzao.zza));
        long nanoTime = this.zza.zzm().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzq().zzb(new zzgl(this, zzao, str)).get();
            if (bArr == null) {
                this.zza.zzr().zzf().zza("Log and bundle returned null. appId", zzes.zza(str));
                bArr = new byte[0];
            }
            this.zza.zzr().zzw().zza("Log and bundle processed. event, size, time_ms", this.zza.zzi().zza(zzao.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzm().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzr().zzf().zza("Failed to log and bundle. appId, event, error", zzes.zza(str), this.zza.zzi().zza(zzao.zza), e);
            return null;
        }
    }

    @BinderThread
    public final void zza(zzkh zzkh, zzn zzn) {
        Preconditions.checkNotNull(zzkh);
        zzb(zzn, false);
        zza((Runnable) new zzgk(this, zzkh, zzn));
    }

    @BinderThread
    public final List<zzkh> zza(zzn zzn, boolean z) {
        zzb(zzn, false);
        try {
            List<zzkj> list = (List) this.zza.zzq().zza(new zzgn(this, zzn)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkj zzkj : list) {
                if (z || !zzkm.zze(zzkj.zzc)) {
                    arrayList.add(new zzkh(zzkj));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzr().zzf().zza("Failed to get user properties. appId", zzes.zza(zzn.zza), e);
            return null;
        }
    }

    @BinderThread
    public final void zza(zzn zzn) {
        zzb(zzn, false);
        zza((Runnable) new zzgm(this, zzn));
    }

    @BinderThread
    private final void zzb(zzn zzn, boolean z) {
        Preconditions.checkNotNull(zzn);
        zza(zzn.zza, false);
        this.zza.zzj().zza(zzn.zzb, zzn.zzr, zzn.zzv);
    }

    @BinderThread
    private final void zza(String str, boolean z) {
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            this.zza.zzr().zzf().zza("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zzb == null) {
                    if ("com.google.android.gms".equals(this.zzc) || UidVerifier.isGooglePlayServicesUid(this.zza.zzn(), Binder.getCallingUid()) || GoogleSignatureVerifier.getInstance(this.zza.zzn()).isUidGoogleSigned(Binder.getCallingUid())) {
                        z2 = true;
                    }
                    this.zzb = Boolean.valueOf(z2);
                }
                if (this.zzb.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.zza.zzr().zzf().zza("Measurement Service called with invalid calling package. appId", zzes.zza(str));
                throw e;
            }
        }
        if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzn(), Binder.getCallingUid(), str)) {
            this.zzc = str;
        }
        if (!str.equals(this.zzc)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
        }
    }

    @BinderThread
    public final void zza(long j, String str, String str2, String str3) {
        zza((Runnable) new zzgp(this, str2, str3, str, j));
    }

    @BinderThread
    public final String zzc(zzn zzn) {
        zzb(zzn, false);
        return this.zza.zzd(zzn);
    }

    @BinderThread
    public final void zza(zzw zzw, zzn zzn) {
        Preconditions.checkNotNull(zzw);
        Preconditions.checkNotNull(zzw.zzc);
        zzb(zzn, false);
        zzw zzw2 = new zzw(zzw);
        zzw2.zza = zzn.zza;
        zza((Runnable) new zzgo(this, zzw2, zzn));
    }

    @BinderThread
    public final void zza(zzw zzw) {
        Preconditions.checkNotNull(zzw);
        Preconditions.checkNotNull(zzw.zzc);
        zza(zzw.zza, true);
        zza((Runnable) new zzgd(this, new zzw(zzw)));
    }

    @BinderThread
    public final List<zzkh> zza(String str, String str2, boolean z, zzn zzn) {
        zzb(zzn, false);
        try {
            List<zzkj> list = (List) this.zza.zzq().zza(new zzgc(this, zzn, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkj zzkj : list) {
                if (z || !zzkm.zze(zzkj.zzc)) {
                    arrayList.add(new zzkh(zzkj));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzr().zzf().zza("Failed to query user properties. appId", zzes.zza(zzn.zza), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzkh> zza(String str, String str2, String str3, boolean z) {
        zza(str, true);
        try {
            List<zzkj> list = (List) this.zza.zzq().zza(new zzgf(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkj zzkj : list) {
                if (z || !zzkm.zze(zzkj.zzc)) {
                    arrayList.add(new zzkh(zzkj));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzr().zzf().zza("Failed to get user properties as. appId", zzes.zza(str), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzw> zza(String str, String str2, zzn zzn) {
        zzb(zzn, false);
        try {
            return (List) this.zza.zzq().zza(new zzge(this, zzn, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzr().zzf().zza("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzw> zza(String str, String str2, String str3) {
        zza(str, true);
        try {
            return (List) this.zza.zzq().zza(new zzgh(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzr().zzf().zza("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final void zzd(zzn zzn) {
        zza(zzn.zza, false);
        zza((Runnable) new zzgg(this, zzn));
    }

    @VisibleForTesting
    private final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzq().zzg()) {
            runnable.run();
        } else {
            this.zza.zzq().zza(runnable);
        }
    }
}
