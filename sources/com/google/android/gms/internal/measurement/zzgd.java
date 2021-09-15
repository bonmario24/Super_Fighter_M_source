package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzgd implements zzhe {
    private static final zzgn zzb = new zzgc();
    private final zzgn zza;

    public zzgd() {
        this(new zzgf(zzfc.zza(), zza()));
    }

    private zzgd(zzgn zzgn) {
        this.zza = (zzgn) zzfh.zza(zzgn, "messageInfoFactory");
    }

    public final <T> zzhf<T> zza(Class<T> cls) {
        zzhh.zza((Class<?>) cls);
        zzgk zzb2 = this.zza.zzb(cls);
        if (zzb2.zzb()) {
            if (zzfe.class.isAssignableFrom(cls)) {
                return zzgs.zza(zzhh.zzc(), zzev.zza(), zzb2.zzc());
            }
            return zzgs.zza(zzhh.zza(), zzev.zzb(), zzb2.zzc());
        } else if (zzfe.class.isAssignableFrom(cls)) {
            if (zza(zzb2)) {
                return zzgq.zza(cls, zzb2, zzgw.zzb(), zzfw.zzb(), zzhh.zzc(), zzev.zza(), zzgl.zzb());
            }
            return zzgq.zza(cls, zzb2, zzgw.zzb(), zzfw.zzb(), zzhh.zzc(), (zzet<?>) null, zzgl.zzb());
        } else if (zza(zzb2)) {
            return zzgq.zza(cls, zzb2, zzgw.zza(), zzfw.zza(), zzhh.zza(), zzev.zzb(), zzgl.zza());
        } else {
            return zzgq.zza(cls, zzb2, zzgw.zza(), zzfw.zza(), zzhh.zzb(), (zzet<?>) null, zzgl.zza());
        }
    }

    private static boolean zza(zzgk zzgk) {
        return zzgk.zza() == zzfe.zzf.zzh;
    }

    private static zzgn zza() {
        try {
            return (zzgn) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            return zzb;
        }
    }
}
