package com.google.android.gms.internal.firebase_auth;

import java.lang.reflect.Type;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public enum zzia {
    DOUBLE(0, zzic.SCALAR, zzit.DOUBLE),
    FLOAT(1, zzic.SCALAR, zzit.FLOAT),
    INT64(2, zzic.SCALAR, zzit.LONG),
    UINT64(3, zzic.SCALAR, zzit.LONG),
    INT32(4, zzic.SCALAR, zzit.INT),
    FIXED64(5, zzic.SCALAR, zzit.LONG),
    FIXED32(6, zzic.SCALAR, zzit.INT),
    BOOL(7, zzic.SCALAR, zzit.BOOLEAN),
    STRING(8, zzic.SCALAR, zzit.STRING),
    MESSAGE(9, zzic.SCALAR, zzit.MESSAGE),
    BYTES(10, zzic.SCALAR, zzit.BYTE_STRING),
    UINT32(11, zzic.SCALAR, zzit.INT),
    ENUM(12, zzic.SCALAR, zzit.ENUM),
    SFIXED32(13, zzic.SCALAR, zzit.INT),
    SFIXED64(14, zzic.SCALAR, zzit.LONG),
    SINT32(15, zzic.SCALAR, zzit.INT),
    SINT64(16, zzic.SCALAR, zzit.LONG),
    GROUP(17, zzic.SCALAR, zzit.MESSAGE),
    DOUBLE_LIST(18, zzic.VECTOR, zzit.DOUBLE),
    FLOAT_LIST(19, zzic.VECTOR, zzit.FLOAT),
    INT64_LIST(20, zzic.VECTOR, zzit.LONG),
    UINT64_LIST(21, zzic.VECTOR, zzit.LONG),
    INT32_LIST(22, zzic.VECTOR, zzit.INT),
    FIXED64_LIST(23, zzic.VECTOR, zzit.LONG),
    FIXED32_LIST(24, zzic.VECTOR, zzit.INT),
    BOOL_LIST(25, zzic.VECTOR, zzit.BOOLEAN),
    STRING_LIST(26, zzic.VECTOR, zzit.STRING),
    MESSAGE_LIST(27, zzic.VECTOR, zzit.MESSAGE),
    BYTES_LIST(28, zzic.VECTOR, zzit.BYTE_STRING),
    UINT32_LIST(29, zzic.VECTOR, zzit.INT),
    ENUM_LIST(30, zzic.VECTOR, zzit.ENUM),
    SFIXED32_LIST(31, zzic.VECTOR, zzit.INT),
    SFIXED64_LIST(32, zzic.VECTOR, zzit.LONG),
    SINT32_LIST(33, zzic.VECTOR, zzit.INT),
    SINT64_LIST(34, zzic.VECTOR, zzit.LONG),
    DOUBLE_LIST_PACKED(35, zzic.PACKED_VECTOR, zzit.DOUBLE),
    FLOAT_LIST_PACKED(36, zzic.PACKED_VECTOR, zzit.FLOAT),
    INT64_LIST_PACKED(37, zzic.PACKED_VECTOR, zzit.LONG),
    UINT64_LIST_PACKED(38, zzic.PACKED_VECTOR, zzit.LONG),
    INT32_LIST_PACKED(39, zzic.PACKED_VECTOR, zzit.INT),
    FIXED64_LIST_PACKED(40, zzic.PACKED_VECTOR, zzit.LONG),
    FIXED32_LIST_PACKED(41, zzic.PACKED_VECTOR, zzit.INT),
    BOOL_LIST_PACKED(42, zzic.PACKED_VECTOR, zzit.BOOLEAN),
    UINT32_LIST_PACKED(43, zzic.PACKED_VECTOR, zzit.INT),
    ENUM_LIST_PACKED(44, zzic.PACKED_VECTOR, zzit.ENUM),
    SFIXED32_LIST_PACKED(45, zzic.PACKED_VECTOR, zzit.INT),
    SFIXED64_LIST_PACKED(46, zzic.PACKED_VECTOR, zzit.LONG),
    SINT32_LIST_PACKED(47, zzic.PACKED_VECTOR, zzit.INT),
    SINT64_LIST_PACKED(48, zzic.PACKED_VECTOR, zzit.LONG),
    GROUP_LIST(49, zzic.VECTOR, zzit.MESSAGE),
    MAP(50, zzic.MAP, zzit.VOID);
    
    private static final zzia[] zzbe = null;
    private static final Type[] zzbf = null;
    private final zzit zzaz;
    private final int zzba;
    private final zzic zzbb;
    private final Class<?> zzbc;
    private final boolean zzbd;

    private zzia(int i, zzic zzic, zzit zzit) {
        this.zzba = i;
        this.zzbb = zzic;
        this.zzaz = zzit;
        switch (zzid.zza[zzic.ordinal()]) {
            case 1:
                this.zzbc = zzit.zza();
                break;
            case 2:
                this.zzbc = zzit.zza();
                break;
            default:
                this.zzbc = null;
                break;
        }
        boolean z = false;
        if (zzic == zzic.SCALAR) {
            switch (zzid.zzb[zzit.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzbd = z;
    }

    public final int zza() {
        return this.zzba;
    }

    static {
        int i;
        zzbf = new Type[0];
        zzia[] values = values();
        zzbe = new zzia[values.length];
        for (zzia zzia : values) {
            zzbe[zzia.zzba] = zzia;
        }
    }
}
