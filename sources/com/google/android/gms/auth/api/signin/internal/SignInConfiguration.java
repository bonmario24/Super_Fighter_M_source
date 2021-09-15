package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInConfigurationCreator")
@SafeParcelable.Reserved({1})
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInConfiguration> CREATOR = new zzx();
    @SafeParcelable.Field(getter = "getConsumerPkgName", mo21170id = 2)
    private final String zzbr;
    @SafeParcelable.Field(getter = "getGoogleConfig", mo21170id = 5)
    private GoogleSignInOptions zzbs;

    @SafeParcelable.Constructor
    public SignInConfiguration(@SafeParcelable.Param(mo21173id = 2) String str, @SafeParcelable.Param(mo21173id = 5) GoogleSignInOptions googleSignInOptions) {
        this.zzbr = Preconditions.checkNotEmpty(str);
        this.zzbs = googleSignInOptions;
    }

    public final GoogleSignInOptions zzm() {
        return this.zzbs;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbr, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzbs, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x001a A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            boolean r1 = r4 instanceof com.google.android.gms.auth.api.signin.internal.SignInConfiguration
            if (r1 != 0) goto L_0x0006
        L_0x0005:
            return r0
        L_0x0006:
            com.google.android.gms.auth.api.signin.internal.SignInConfiguration r4 = (com.google.android.gms.auth.api.signin.internal.SignInConfiguration) r4
            java.lang.String r1 = r3.zzbr
            java.lang.String r2 = r4.zzbr
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0005
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r1 = r3.zzbs
            if (r1 != 0) goto L_0x001c
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r1 = r4.zzbs
            if (r1 != 0) goto L_0x0005
        L_0x001a:
            r0 = 1
            goto L_0x0005
        L_0x001c:
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r1 = r3.zzbs
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r2 = r4.zzbs
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0005
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.internal.SignInConfiguration.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return new HashAccumulator().addObject(this.zzbr).addObject(this.zzbs).hash();
    }
}
