package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class zzdu<T> implements zzef<T> {
    private final zzdo zzmn;
    private final boolean zzmo;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;

    private zzdu(zzex<?, ?> zzex, zzbu<?> zzbu, zzdo zzdo) {
        this.zzmx = zzex;
        this.zzmo = zzbu.zze(zzdo);
        this.zzmy = zzbu;
        this.zzmn = zzdo;
    }

    static <T> zzdu<T> zza(zzex<?, ?> zzex, zzbu<?> zzbu, zzdo zzdo) {
        return new zzdu<>(zzex, zzbu, zzdo);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzmx.zzq(t).equals(this.zzmx.zzq(t2))) {
            return false;
        }
        if (this.zzmo) {
            return this.zzmy.zza((Object) t).equals(this.zzmy.zza((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzmx.zzq(t).hashCode();
        return this.zzmo ? (hashCode * 53) + this.zzmy.zza((Object) t).hashCode() : hashCode;
    }

    public final T newInstance() {
        return this.zzmn.zzbd().zzbi();
    }

    public final void zza(T t, zzfr zzfr) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzmy.zza((Object) t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzca zzca = (zzca) next.getKey();
            if (zzca.zzav() != zzfq.MESSAGE || zzca.zzaw() || zzca.zzax()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzct) {
                zzfr.zza(zzca.zzc(), (Object) ((zzct) next).zzbs().zzr());
            } else {
                zzfr.zza(zzca.zzc(), next.getValue());
            }
        }
        zzex<?, ?> zzex = this.zzmx;
        zzex.zzc(zzex.zzq(t), zzfr);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r9, byte[] r10, int r11, int r12, com.google.android.gms.internal.clearcut.zzay r13) throws java.io.IOException {
        /*
            r8 = this;
            r7 = 2
            r0 = r9
            com.google.android.gms.internal.clearcut.zzcg r0 = (com.google.android.gms.internal.clearcut.zzcg) r0
            com.google.android.gms.internal.clearcut.zzey r4 = r0.zzjp
            com.google.android.gms.internal.clearcut.zzey r0 = com.google.android.gms.internal.clearcut.zzey.zzea()
            if (r4 != r0) goto L_0x0014
            com.google.android.gms.internal.clearcut.zzey r4 = com.google.android.gms.internal.clearcut.zzey.zzeb()
            com.google.android.gms.internal.clearcut.zzcg r9 = (com.google.android.gms.internal.clearcut.zzcg) r9
            r9.zzjp = r4
        L_0x0014:
            if (r11 >= r12) goto L_0x006f
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r10, r11, r13)
            int r0 = r13.zzfd
            r1 = 11
            if (r0 == r1) goto L_0x0031
            r1 = r0 & 7
            if (r1 != r7) goto L_0x002c
            r1 = r10
            r3 = r12
            r5 = r13
            int r11 = com.google.android.gms.internal.clearcut.zzax.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.clearcut.zzey) r4, (com.google.android.gms.internal.clearcut.zzay) r5)
            goto L_0x0014
        L_0x002c:
            int r11 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r10, r2, r12, r13)
            goto L_0x0014
        L_0x0031:
            r5 = 0
            r0 = 0
            r3 = r0
            r1 = r2
        L_0x0035:
            if (r1 >= r12) goto L_0x0063
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r10, r1, r13)
            int r1 = r13.zzfd
            int r2 = r1 >>> 3
            r6 = r1 & 7
            switch(r2) {
                case 2: goto L_0x004d;
                case 3: goto L_0x0057;
                default: goto L_0x0044;
            }
        L_0x0044:
            r2 = 12
            if (r1 == r2) goto L_0x0064
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r1, r10, r0, r12, r13)
            goto L_0x0035
        L_0x004d:
            if (r6 != 0) goto L_0x0044
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r10, r0, r13)
            int r0 = r13.zzfd
            r5 = r0
            goto L_0x0035
        L_0x0057:
            if (r6 != r7) goto L_0x0044
            int r1 = com.google.android.gms.internal.clearcut.zzax.zze(r10, r0, r13)
            java.lang.Object r0 = r13.zzff
            com.google.android.gms.internal.clearcut.zzbb r0 = (com.google.android.gms.internal.clearcut.zzbb) r0
            r3 = r0
            goto L_0x0035
        L_0x0063:
            r0 = r1
        L_0x0064:
            if (r3 == 0) goto L_0x006d
            int r1 = r5 << 3
            r1 = r1 | 2
            r4.zzb(r1, r3)
        L_0x006d:
            r11 = r0
            goto L_0x0014
        L_0x006f:
            if (r11 == r12) goto L_0x0076
            com.google.android.gms.internal.clearcut.zzco r0 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r0
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzdu.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):void");
    }

    public final void zzc(T t) {
        this.zzmx.zzc(t);
        this.zzmy.zzc(t);
    }

    public final void zzc(T t, T t2) {
        zzeh.zza(this.zzmx, t, t2);
        if (this.zzmo) {
            zzeh.zza(this.zzmy, t, t2);
        }
    }

    public final int zzm(T t) {
        zzex<?, ?> zzex = this.zzmx;
        int zzr = zzex.zzr(zzex.zzq(t)) + 0;
        return this.zzmo ? zzr + this.zzmy.zza((Object) t).zzat() : zzr;
    }

    public final boolean zzo(T t) {
        return this.zzmy.zza((Object) t).isInitialized();
    }
}
