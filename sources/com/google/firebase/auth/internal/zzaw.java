package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.firebase_auth.zzbj;
import com.google.android.gms.internal.firebase_auth.zzgc;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzaw {
    @VisibleForTesting
    private static long zza = 3600000;
    private static final zzbj<String> zzb = zzbj.zza("firebaseAppName", "firebaseUserUid", "operation", "tenantId", "verifyAssertionRequest", "statusCode", "statusMessage", "timestamp");
    private static final zzaw zzc = new zzaw();
    private Task<AuthResult> zzd;
    private long zze = 0;

    private zzaw() {
    }

    public static zzaw zza() {
        return zzc;
    }

    public static void zza(Context context, FirebaseAuth firebaseAuth) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(firebaseAuth);
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putString("firebaseAppName", firebaseAuth.zzb().getName());
        edit.commit();
    }

    public static void zza(Context context, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(firebaseAuth);
        Preconditions.checkNotNull(firebaseUser);
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putString("firebaseAppName", firebaseAuth.zzb().getName());
        edit.putString("firebaseUserUid", firebaseUser.getUid());
        edit.commit();
    }

    public static void zza(Context context, zzgc zzgc, String str, @Nullable String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putString("verifyAssertionRequest", SafeParcelableSerializer.serializeToString(zzgc));
        edit.putString("operation", str);
        edit.putString("tenantId", str2);
        edit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
        edit.commit();
    }

    public static void zza(Context context, Status status) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        edit.putInt("statusCode", status.getStatusCode());
        edit.putString("statusMessage", status.getStatusMessage());
        edit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
        edit.commit();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0082, code lost:
        if (r4.equals("com.google.firebase.auth.internal.SIGN_IN") != false) goto L_0x0073;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.firebase.auth.FirebaseAuth r13) {
        /*
            r12 = this;
            r10 = 0
            r1 = 0
            r8 = 0
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            com.google.firebase.FirebaseApp r0 = r13.zzb()
            android.content.Context r0 = r0.getApplicationContext()
            java.lang.String r2 = "com.google.firebase.auth.internal.ProcessDeathHelper"
            android.content.SharedPreferences r3 = r0.getSharedPreferences(r2, r1)
            java.lang.String r0 = "firebaseAppName"
            java.lang.String r2 = ""
            java.lang.String r0 = r3.getString(r0, r2)
            com.google.firebase.FirebaseApp r2 = r13.zzb()
            java.lang.String r2 = r2.getName()
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x002c
        L_0x002b:
            return
        L_0x002c:
            java.lang.String r0 = "verifyAssertionRequest"
            boolean r0 = r3.contains(r0)
            if (r0 == 0) goto L_0x00e4
            java.lang.String r0 = "verifyAssertionRequest"
            java.lang.String r2 = ""
            java.lang.String r0 = r3.getString(r0, r2)
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzgc> r2 = com.google.android.gms.internal.firebase_auth.zzgc.CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromString(r0, r2)
            com.google.android.gms.internal.firebase_auth.zzgc r0 = (com.google.android.gms.internal.firebase_auth.zzgc) r0
            java.lang.String r2 = "operation"
            java.lang.String r4 = ""
            java.lang.String r4 = r3.getString(r2, r4)
            java.lang.String r2 = "tenantId"
            java.lang.String r2 = r3.getString(r2, r8)
            java.lang.String r5 = "firebaseUserUid"
            java.lang.String r6 = ""
            java.lang.String r5 = r3.getString(r5, r6)
            java.lang.String r6 = "timestamp"
            long r6 = r3.getLong(r6, r10)
            r12.zze = r6
            if (r2 == 0) goto L_0x006a
            r13.zza((java.lang.String) r2)
            r0.zzb((java.lang.String) r2)
        L_0x006a:
            r2 = -1
            int r6 = r4.hashCode()
            switch(r6) {
                case -1843829902: goto L_0x008f;
                case -286760092: goto L_0x0085;
                case 1731327805: goto L_0x007c;
                default: goto L_0x0072;
            }
        L_0x0072:
            r1 = r2
        L_0x0073:
            switch(r1) {
                case 0: goto L_0x0099;
                case 1: goto L_0x00a4;
                case 2: goto L_0x00c4;
                default: goto L_0x0076;
            }
        L_0x0076:
            r12.zzd = r8
        L_0x0078:
            zza((android.content.SharedPreferences) r3)
            goto L_0x002b
        L_0x007c:
            java.lang.String r6 = "com.google.firebase.auth.internal.SIGN_IN"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0072
            goto L_0x0073
        L_0x0085:
            java.lang.String r1 = "com.google.firebase.auth.internal.LINK"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0072
            r1 = 1
            goto L_0x0073
        L_0x008f:
            java.lang.String r1 = "com.google.firebase.auth.internal.REAUTHENTICATE"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0072
            r1 = 2
            goto L_0x0073
        L_0x0099:
            com.google.firebase.auth.zze r0 = com.google.firebase.auth.zze.zza(r0)
            com.google.android.gms.tasks.Task r0 = r13.signInWithCredential(r0)
            r12.zzd = r0
            goto L_0x0078
        L_0x00a4:
            com.google.firebase.auth.FirebaseUser r1 = r13.getCurrentUser()
            java.lang.String r1 = r1.getUid()
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x00c1
            com.google.firebase.auth.FirebaseUser r1 = r13.getCurrentUser()
            com.google.firebase.auth.zze r0 = com.google.firebase.auth.zze.zza(r0)
            com.google.android.gms.tasks.Task r0 = r13.zzc((com.google.firebase.auth.FirebaseUser) r1, (com.google.firebase.auth.AuthCredential) r0)
            r12.zzd = r0
            goto L_0x0078
        L_0x00c1:
            r12.zzd = r8
            goto L_0x0078
        L_0x00c4:
            com.google.firebase.auth.FirebaseUser r1 = r13.getCurrentUser()
            java.lang.String r1 = r1.getUid()
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x00e1
            com.google.firebase.auth.FirebaseUser r1 = r13.getCurrentUser()
            com.google.firebase.auth.zze r0 = com.google.firebase.auth.zze.zza(r0)
            com.google.android.gms.tasks.Task r0 = r13.zzb((com.google.firebase.auth.FirebaseUser) r1, (com.google.firebase.auth.AuthCredential) r0)
            r12.zzd = r0
            goto L_0x0078
        L_0x00e1:
            r12.zzd = r8
            goto L_0x0078
        L_0x00e4:
            java.lang.String r0 = "statusCode"
            boolean r0 = r3.contains(r0)
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = "statusCode"
            r1 = 17062(0x42a6, float:2.3909E-41)
            int r0 = r3.getInt(r0, r1)
            java.lang.String r1 = "statusMessage"
            java.lang.String r2 = ""
            java.lang.String r1 = r3.getString(r1, r2)
            com.google.android.gms.common.api.Status r2 = new com.google.android.gms.common.api.Status
            r2.<init>(r0, r1)
            java.lang.String r0 = "timestamp"
            long r0 = r3.getLong(r0, r10)
            r12.zze = r0
            zza((android.content.SharedPreferences) r3)
            com.google.firebase.FirebaseException r0 = com.google.firebase.auth.api.internal.zzej.zza((com.google.android.gms.common.api.Status) r2)
            com.google.android.gms.tasks.Task r0 = com.google.android.gms.tasks.Tasks.forException(r0)
            r12.zzd = r0
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzaw.zza(com.google.firebase.auth.FirebaseAuth):void");
    }

    public final Task<AuthResult> zzb() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zze < zza) {
            return this.zzd;
        }
        return null;
    }

    public final void zza(Context context) {
        Preconditions.checkNotNull(context);
        zza(context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0));
        this.zzd = null;
        this.zze = 0;
    }

    private static void zza(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        zzbj zzbj = zzb;
        int size = zzbj.size();
        int i = 0;
        while (i < size) {
            Object obj = zzbj.get(i);
            i++;
            edit.remove((String) obj);
        }
        edit.commit();
    }
}
