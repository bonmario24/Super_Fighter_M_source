package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
abstract class zzg extends zzd {
    private boolean zza;

    zzg(zzfw zzfw) {
        super(zzfw);
        this.zzz.zza(this);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzz();

    /* access modifiers changed from: package-private */
    public final boolean zzv() {
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzw() {
        if (!zzv()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzx() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzz()) {
            this.zzz.zzaf();
            this.zza = true;
        }
    }

    public final void zzy() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzaa();
        this.zzz.zzaf();
        this.zza = true;
    }

    /* access modifiers changed from: protected */
    public void zzaa() {
    }
}
