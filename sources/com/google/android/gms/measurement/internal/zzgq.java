package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
abstract class zzgq extends zzgr {
    private boolean zza;

    zzgq(zzfw zzfw) {
        super(zzfw);
        this.zzz.zza(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zze();

    /* access modifiers changed from: package-private */
    public final boolean zzz() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzaa() {
        if (!zzz()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzab() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zze()) {
            this.zzz.zzaf();
            this.zza = true;
        }
    }

    public final void zzac() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        mo24471f_();
        this.zzz.zzaf();
        this.zza = true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f_ */
    public void mo24471f_() {
    }
}
