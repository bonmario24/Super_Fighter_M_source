package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzp;
import com.lzy.okgo.OkGo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzil extends zzg {
    /* access modifiers changed from: private */
    public final zzjd zza;
    /* access modifiers changed from: private */
    public zzek zzb;
    private volatile Boolean zzc;
    private final zzag zzd;
    private final zzjx zze;
    private final List<Runnable> zzf = new ArrayList();
    private final zzag zzg;

    protected zzil(zzfw zzfw) {
        super(zzfw);
        this.zze = new zzjx(zzfw.zzm());
        this.zza = new zzjd(this);
        this.zzd = new zzik(this, zzfw);
        this.zzg = new zziv(this, zzfw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    @WorkerThread
    public final boolean zzab() {
        zzd();
        zzw();
        return this.zzb != null;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzac() {
        zzd();
        zzw();
        zza((Runnable) new zziu(this, zza(true)));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzek zzek, AbstractSafeParcelable abstractSafeParcelable, zzn zzn) {
        List<AbstractSafeParcelable> zza2;
        zzd();
        zzb();
        zzw();
        boolean zzaj = zzaj();
        int i = 100;
        for (int i2 = 0; i2 < 1001 && i == 100; i2++) {
            ArrayList arrayList = new ArrayList();
            if (!zzaj || (zza2 = zzj().zza(100)) == null) {
                i = 0;
            } else {
                arrayList.addAll(zza2);
                i = zza2.size();
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList2.get(i3);
                i3++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzao) {
                    try {
                        zzek.zza((zzao) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e) {
                        zzr().zzf().zza("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzkh) {
                    try {
                        zzek.zza((zzkh) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e2) {
                        zzr().zzf().zza("Failed to send user property to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzw) {
                    try {
                        zzek.zza((zzw) abstractSafeParcelable2, zzn);
                    } catch (RemoteException e3) {
                        zzr().zzf().zza("Failed to send conditional user property to the service", e3);
                    }
                } else {
                    zzr().zzf().zza("Discarding data. Unrecognized parcel type.");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzao zzao, String str) {
        Preconditions.checkNotNull(zzao);
        zzd();
        zzw();
        boolean zzaj = zzaj();
        zza((Runnable) new zzix(this, zzaj, zzaj && zzj().zza(zzao), zzao, zza(true), str));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzw zzw) {
        Preconditions.checkNotNull(zzw);
        zzd();
        zzw();
        zzu();
        zza((Runnable) new zziw(this, true, zzj().zza(zzw), new zzw(zzw), zza(true), zzw));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzw>> atomicReference, String str, String str2, String str3) {
        zzd();
        zzw();
        zza((Runnable) new zziz(this, atomicReference, str, str2, str3, zza(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzp zzp, String str, String str2) {
        zzd();
        zzw();
        zza((Runnable) new zziy(this, str, str2, zza(false), zzp));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzkh>> atomicReference, String str, String str2, String str3, boolean z) {
        zzd();
        zzw();
        zza((Runnable) new zzjb(this, atomicReference, str, str2, str3, z, zza(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzp zzp, String str, String str2, boolean z) {
        zzd();
        zzw();
        zza((Runnable) new zzja(this, str, str2, z, zza(false), zzp));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzkh zzkh) {
        zzd();
        zzw();
        zza((Runnable) new zzin(this, zzaj() && zzj().zza(zzkh), zzkh, zza(true)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzkh>> atomicReference, boolean z) {
        zzd();
        zzw();
        zza((Runnable) new zzim(this, atomicReference, zza(false), z));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzad() {
        zzd();
        zzb();
        zzw();
        zzn zza2 = zza(false);
        if (zzaj()) {
            zzj().zzab();
        }
        zza((Runnable) new zzip(this, zza2));
    }

    private final boolean zzaj() {
        zzu();
        return true;
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzd();
        zzw();
        zza((Runnable) new zzio(this, atomicReference, zza(false)));
    }

    @WorkerThread
    public final void zza(zzp zzp) {
        zzd();
        zzw();
        zza((Runnable) new zzir(this, zza(false), zzp));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzae() {
        zzd();
        zzw();
        zzn zza2 = zza(true);
        zzj().zzac();
        zza((Runnable) new zziq(this, zza2));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(zzih zzih) {
        zzd();
        zzw();
        zza((Runnable) new zzit(this, zzih));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzak() {
        zzd();
        this.zze.zza();
        this.zzd.zza(zzaq.zzai.zza(null).longValue());
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzaf() {
        zzd();
        zzw();
        if (!zzab()) {
            if (zzal()) {
                this.zza.zzb();
            } else if (!zzt().zzy()) {
                zzu();
                List<ResolveInfo> queryIntentServices = zzn().getPackageManager().queryIntentServices(new Intent().setClassName(zzn(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (queryIntentServices != null && queryIntentServices.size() > 0) {
                    Intent intent = new Intent("com.google.android.gms.measurement.START");
                    Context zzn = zzn();
                    zzu();
                    intent.setComponent(new ComponentName(zzn, "com.google.android.gms.measurement.AppMeasurementService"));
                    this.zza.zza(intent);
                    return;
                }
                zzr().zzf().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzag() {
        return this.zzc;
    }

    @WorkerThread
    private final boolean zzal() {
        boolean z;
        boolean z2;
        boolean z3 = true;
        boolean z4 = false;
        zzd();
        zzw();
        if (this.zzc == null) {
            zzd();
            zzw();
            Boolean zzj = zzs().zzj();
            if (zzj == null || !zzj.booleanValue()) {
                zzu();
                if (zzg().zzag() != 1) {
                    zzr().zzx().zza("Checking service availability");
                    int zza2 = zzp().zza(12451000);
                    switch (zza2) {
                        case 0:
                            zzr().zzx().zza("Service available");
                            z = true;
                            z2 = true;
                            break;
                        case 1:
                            zzr().zzx().zza("Service missing");
                            z = true;
                            z2 = false;
                            break;
                        case 2:
                            zzr().zzw().zza("Service container out of date");
                            if (zzp().zzj() >= 17443) {
                                if (zzj != null) {
                                    z3 = false;
                                }
                                z = false;
                                z2 = z3;
                                break;
                            } else {
                                z = true;
                                z2 = false;
                                break;
                            }
                        case 3:
                            zzr().zzi().zza("Service disabled");
                            z = false;
                            z2 = false;
                            break;
                        case 9:
                            zzr().zzi().zza("Service invalid");
                            z = false;
                            z2 = false;
                            break;
                        case 18:
                            zzr().zzi().zza("Service updating");
                            z = true;
                            z2 = true;
                            break;
                        default:
                            zzr().zzi().zza("Unexpected service status", Integer.valueOf(zza2));
                            z = false;
                            z2 = false;
                            break;
                    }
                } else {
                    z = true;
                    z2 = true;
                }
                if (z2 || !zzt().zzy()) {
                    z4 = z;
                } else {
                    zzr().zzf().zza("No way to upload. Consider using the full version of Analytics");
                }
                if (z4) {
                    zzs().zza(z2);
                }
                z3 = z2;
            }
            this.zzc = Boolean.valueOf(z3);
        }
        return this.zzc.booleanValue();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzek zzek) {
        zzd();
        Preconditions.checkNotNull(zzek);
        this.zzb = zzek;
        zzak();
        zzan();
    }

    @WorkerThread
    public final void zzah() {
        zzd();
        zzw();
        this.zza.zza();
        try {
            ConnectionTracker.getInstance().unbindService(zzn(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException e) {
        }
        this.zzb = null;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(ComponentName componentName) {
        zzd();
        if (this.zzb != null) {
            this.zzb = null;
            zzr().zzx().zza("Disconnected from device MeasurementService", componentName);
            zzd();
            zzaf();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzam() {
        zzd();
        if (zzab()) {
            zzr().zzx().zza("Inactivity, disconnecting from the service");
            zzah();
        }
    }

    @WorkerThread
    private final void zza(Runnable runnable) throws IllegalStateException {
        zzd();
        if (zzab()) {
            runnable.run();
        } else if (((long) this.zzf.size()) >= 1000) {
            zzr().zzf().zza("Discarding data. Max runnable queue size reached");
        } else {
            this.zzf.add(runnable);
            this.zzg.zza(OkGo.DEFAULT_MILLISECONDS);
            zzaf();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzan() {
        zzd();
        zzr().zzx().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable run : this.zzf) {
            try {
                run.run();
            } catch (Exception e) {
                zzr().zzf().zza("Task exception while flushing queue", e);
            }
        }
        this.zzf.clear();
        this.zzg.zzc();
    }

    @WorkerThread
    @Nullable
    private final zzn zza(boolean z) {
        zzu();
        return zzg().zza(z ? zzr().zzy() : null);
    }

    @WorkerThread
    public final void zza(zzp zzp, zzao zzao, String str) {
        zzd();
        zzw();
        if (zzp().zza(12451000) != 0) {
            zzr().zzi().zza("Not bundling data. Service unavailable or out of date");
            zzp().zza(zzp, new byte[0]);
            return;
        }
        zza((Runnable) new zzis(this, zzao, str, zzp));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzai() {
        zzd();
        zzw();
        if (zzal() && zzp().zzj() < 200900) {
            return false;
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    public final /* bridge */ /* synthetic */ zza zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzhb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzep zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzil zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzig zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzeo zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzjm zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzeq zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzkm zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzft zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzes zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzfe zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
