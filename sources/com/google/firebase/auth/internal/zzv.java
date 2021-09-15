package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultMultiFactorResolverCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class zzv extends MultiFactorResolver {
    public static final Parcelable.Creator<zzv> CREATOR = new zzx();
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", mo21170id = 1)
    private final List<PhoneMultiFactorInfo> zza = new ArrayList();
    @SafeParcelable.Field(getter = "getSession", mo21170id = 2)
    private final zzw zzb;
    @SafeParcelable.Field(getter = "getFirebaseAppName", mo21170id = 3)
    private final String zzc;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", mo21170id = 4)
    public final zze zzd;
    @SafeParcelable.Field(getter = "getReauthUser", mo21170id = 5)
    private final zzp zze;

    @SafeParcelable.Constructor
    public zzv(@SafeParcelable.Param(mo21173id = 1) List<PhoneMultiFactorInfo> list, @SafeParcelable.Param(mo21173id = 2) zzw zzw, @SafeParcelable.Param(mo21173id = 3) String str, @SafeParcelable.Param(mo21173id = 4) @Nullable zze zze2, @SafeParcelable.Param(mo21173id = 5) @Nullable zzp zzp) {
        for (MultiFactorInfo next : list) {
            if (next instanceof PhoneMultiFactorInfo) {
                this.zza.add((PhoneMultiFactorInfo) next);
            }
        }
        this.zzb = (zzw) Preconditions.checkNotNull(zzw);
        this.zzc = Preconditions.checkNotEmpty(str);
        this.zzd = zze2;
        this.zze = zzp;
    }

    public static zzv zza(zzem zzem, FirebaseAuth firebaseAuth, @Nullable FirebaseUser firebaseUser) {
        List<MultiFactorInfo> zzc2 = zzem.zzc();
        ArrayList arrayList = new ArrayList();
        for (MultiFactorInfo next : zzc2) {
            if (next instanceof PhoneMultiFactorInfo) {
                arrayList.add((PhoneMultiFactorInfo) next);
            }
        }
        return new zzv(arrayList, zzw.zza(zzem.zzc(), zzem.zza()), firebaseAuth.zzb().getName(), zzem.zzb(), (zzp) firebaseUser);
    }

    public final MultiFactorSession getSession() {
        return this.zzb;
    }

    public final List<MultiFactorInfo> getHints() {
        ArrayList arrayList = new ArrayList();
        for (PhoneMultiFactorInfo add : this.zza) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public final Task<AuthResult> resolveSignIn(MultiFactorAssertion multiFactorAssertion) {
        return getFirebaseAuth().zza(multiFactorAssertion, this.zzb, (FirebaseUser) this.zze).continueWithTask(new zzu(this));
    }

    public final FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance(FirebaseApp.getInstance(this.zzc));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getSession(), i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
