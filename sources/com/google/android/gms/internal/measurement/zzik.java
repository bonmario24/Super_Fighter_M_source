package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public enum zzik {
    DOUBLE(zzir.DOUBLE, 1),
    FLOAT(zzir.FLOAT, 5),
    INT64(zzir.LONG, 0),
    UINT64(zzir.LONG, 0),
    INT32(zzir.INT, 0),
    FIXED64(zzir.LONG, 1),
    FIXED32(zzir.INT, 5),
    BOOL(zzir.BOOLEAN, 0),
    STRING(zzir.STRING, 2),
    GROUP(zzir.MESSAGE, 3),
    MESSAGE(zzir.MESSAGE, 2),
    BYTES(zzir.BYTE_STRING, 2),
    UINT32(zzir.INT, 0),
    ENUM(zzir.ENUM, 0),
    SFIXED32(zzir.INT, 5),
    SFIXED64(zzir.LONG, 1),
    SINT32(zzir.INT, 0),
    SINT64(zzir.LONG, 0);
    
    private final zzir zzs;
    private final int zzt;

    private zzik(zzir zzir, int i) {
        this.zzs = zzir;
        this.zzt = i;
    }

    public final zzir zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }
}
