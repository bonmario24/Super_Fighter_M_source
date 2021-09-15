package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzgb;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzfw implements zzgb<zzp.zzn> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private zzga zzf = new zzga();
    private zzga zzg = new zzga();
    private boolean zzh = true;
    private String zzi;
    @Nullable
    private String zzj;

    public final boolean zza(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzg.zza().contains(str);
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final String zzc() {
        return this.zzc;
    }

    @Nullable
    public final String zzd() {
        return this.zzd;
    }

    @Nullable
    public final String zze() {
        return this.zze;
    }

    @NonNull
    public final zzfw zzb(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
        return this;
    }

    @NonNull
    public final zzfw zzc(@Nullable String str) {
        if (str == null) {
            this.zzg.zza().add("EMAIL");
        } else {
            this.zzb = str;
        }
        return this;
    }

    @NonNull
    public final zzfw zzd(@Nullable String str) {
        if (str == null) {
            this.zzg.zza().add("PASSWORD");
        } else {
            this.zzc = str;
        }
        return this;
    }

    @NonNull
    public final zzfw zze(@Nullable String str) {
        if (str == null) {
            this.zzg.zza().add("DISPLAY_NAME");
        } else {
            this.zzd = str;
        }
        return this;
    }

    @NonNull
    public final zzfw zzf(@Nullable String str) {
        if (str == null) {
            this.zzg.zza().add("PHOTO_URL");
        } else {
            this.zze = str;
        }
        return this;
    }

    @NonNull
    public final zzfw zzg(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzf.zza().add(str);
        return this;
    }

    @NonNull
    public final zzfw zzh(String str) {
        this.zzi = Preconditions.checkNotEmpty(str);
        return this;
    }

    @NonNull
    public final zzfw zzi(@Nullable String str) {
        this.zzj = str;
        return this;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzjr zza() {
        /*
            r8 = this;
            r2 = 0
            com.google.android.gms.internal.firebase_auth.zzp$zzn$zzb r0 = com.google.android.gms.internal.firebase_auth.zzp.zzn.zza()
            boolean r1 = r8.zzh
            com.google.android.gms.internal.firebase_auth.zzp$zzn$zzb r0 = r0.zza((boolean) r1)
            com.google.android.gms.internal.firebase_auth.zzga r1 = r8.zzf
            java.util.List r1 = r1.zza()
            com.google.android.gms.internal.firebase_auth.zzp$zzn$zzb r4 = r0.zzb((java.lang.Iterable<java.lang.String>) r1)
            com.google.android.gms.internal.firebase_auth.zzga r0 = r8.zzg
            java.util.List r5 = r0.zza()
            int r0 = r5.size()
            com.google.android.gms.internal.firebase_auth.zzv[] r6 = new com.google.android.gms.internal.firebase_auth.zzv[r0]
            r1 = r2
        L_0x0022:
            int r0 = r5.size()
            if (r1 >= r0) goto L_0x0076
            java.lang.Object r0 = r5.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            r3 = -1
            int r7 = r0.hashCode()
            switch(r7) {
                case -333046776: goto L_0x004c;
                case 66081660: goto L_0x0042;
                case 1939891618: goto L_0x0060;
                case 1999612571: goto L_0x0056;
                default: goto L_0x0036;
            }
        L_0x0036:
            r0 = r3
        L_0x0037:
            switch(r0) {
                case 0: goto L_0x006a;
                case 1: goto L_0x006d;
                case 2: goto L_0x0070;
                case 3: goto L_0x0073;
                default: goto L_0x003a;
            }
        L_0x003a:
            com.google.android.gms.internal.firebase_auth.zzv r0 = com.google.android.gms.internal.firebase_auth.zzv.USER_ATTRIBUTE_NAME_UNSPECIFIED
        L_0x003c:
            r6[r1] = r0
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0022
        L_0x0042:
            java.lang.String r7 = "EMAIL"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0036
            r0 = r2
            goto L_0x0037
        L_0x004c:
            java.lang.String r7 = "DISPLAY_NAME"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x0056:
            java.lang.String r7 = "PASSWORD"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0036
            r0 = 2
            goto L_0x0037
        L_0x0060:
            java.lang.String r7 = "PHOTO_URL"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0036
            r0 = 3
            goto L_0x0037
        L_0x006a:
            com.google.android.gms.internal.firebase_auth.zzv r0 = com.google.android.gms.internal.firebase_auth.zzv.EMAIL
            goto L_0x003c
        L_0x006d:
            com.google.android.gms.internal.firebase_auth.zzv r0 = com.google.android.gms.internal.firebase_auth.zzv.DISPLAY_NAME
            goto L_0x003c
        L_0x0070:
            com.google.android.gms.internal.firebase_auth.zzv r0 = com.google.android.gms.internal.firebase_auth.zzv.PASSWORD
            goto L_0x003c
        L_0x0073:
            com.google.android.gms.internal.firebase_auth.zzv r0 = com.google.android.gms.internal.firebase_auth.zzv.PHOTO_URL
            goto L_0x003c
        L_0x0076:
            java.util.List r0 = java.util.Arrays.asList(r6)
            com.google.android.gms.internal.firebase_auth.zzp$zzn$zzb r0 = r4.zza((java.lang.Iterable<? extends com.google.android.gms.internal.firebase_auth.zzv>) r0)
            java.lang.String r1 = r8.zza
            if (r1 == 0) goto L_0x0087
            java.lang.String r1 = r8.zza
            r0.zza((java.lang.String) r1)
        L_0x0087:
            java.lang.String r1 = r8.zzb
            if (r1 == 0) goto L_0x0090
            java.lang.String r1 = r8.zzb
            r0.zzc(r1)
        L_0x0090:
            java.lang.String r1 = r8.zzc
            if (r1 == 0) goto L_0x0099
            java.lang.String r1 = r8.zzc
            r0.zzd(r1)
        L_0x0099:
            java.lang.String r1 = r8.zzd
            if (r1 == 0) goto L_0x00a2
            java.lang.String r1 = r8.zzd
            r0.zzb((java.lang.String) r1)
        L_0x00a2:
            java.lang.String r1 = r8.zze
            if (r1 == 0) goto L_0x00ab
            java.lang.String r1 = r8.zze
            r0.zzf(r1)
        L_0x00ab:
            java.lang.String r1 = r8.zzi
            if (r1 == 0) goto L_0x00b4
            java.lang.String r1 = r8.zzi
            r0.zze(r1)
        L_0x00b4:
            java.lang.String r1 = r8.zzj
            if (r1 == 0) goto L_0x00bd
            java.lang.String r1 = r8.zzj
            r0.zzg(r1)
        L_0x00bd:
            com.google.android.gms.internal.firebase_auth.zzjr r0 = r0.zzf()
            com.google.android.gms.internal.firebase_auth.zzig r0 = (com.google.android.gms.internal.firebase_auth.zzig) r0
            com.google.android.gms.internal.firebase_auth.zzp$zzn r0 = (com.google.android.gms.internal.firebase_auth.zzp.zzn) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzfw.zza():com.google.android.gms.internal.firebase_auth.zzjr");
    }
}
