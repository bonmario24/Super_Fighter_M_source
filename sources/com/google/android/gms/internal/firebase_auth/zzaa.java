package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public enum zzaa implements zzil {
    VERIFY_OP_UNSPECIFIED(0),
    SIGN_UP_OR_IN(1),
    REAUTH(2),
    UPDATE(3),
    zze(4);
    
    private static final zzik<zzaa> zzf = null;
    private final int zzg;

    public final int zza() {
        return this.zzg;
    }

    public static zzaa zza(int i) {
        switch (i) {
            case 0:
                return VERIFY_OP_UNSPECIFIED;
            case 1:
                return SIGN_UP_OR_IN;
            case 2:
                return REAUTH;
            case 3:
                return UPDATE;
            case 4:
                return zze;
            default:
                return null;
        }
    }

    public static zzin zzb() {
        return zzab.zza;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" number=").append(this.zzg);
        return sb.append(" name=").append(name()).append('>').toString();
    }

    private zzaa(int i) {
        this.zzg = i;
    }

    static {
        zzf = new zzac();
    }
}
