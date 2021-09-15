package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzbj;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SafeParcelable.Class(creator = "DefaultFirebaseUserCreator")
/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class zzp extends FirebaseUser {
    public static final Parcelable.Creator<zzp> CREATOR = new zzo();
    @SafeParcelable.Field(getter = "getCachedTokenState", mo21170id = 1)
    private zzff zza;
    @SafeParcelable.Field(getter = "getDefaultAuthUserInfo", mo21170id = 2)
    private zzl zzb;
    @SafeParcelable.Field(getter = "getFirebaseAppName", mo21170id = 3)
    private String zzc;
    @SafeParcelable.Field(getter = "getUserType", mo21170id = 4)
    private String zzd;
    @SafeParcelable.Field(getter = "getUserInfos", mo21170id = 5)
    private List<zzl> zze;
    @SafeParcelable.Field(getter = "getProviders", mo21170id = 6)
    private List<String> zzf;
    @SafeParcelable.Field(getter = "getCurrentVersion", mo21170id = 7)
    private String zzg;
    @SafeParcelable.Field(getter = "isAnonymous", mo21170id = 8)
    private Boolean zzh;
    @SafeParcelable.Field(getter = "getMetadata", mo21170id = 9)
    private zzr zzi;
    @SafeParcelable.Field(getter = "isNewUser", mo21170id = 10)
    private boolean zzj;
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", mo21170id = 11)
    private zze zzk;
    @SafeParcelable.Field(getter = "getMultiFactorInfoList", mo21170id = 12)
    private zzau zzl;

    @SafeParcelable.Constructor
    zzp(@SafeParcelable.Param(mo21173id = 1) zzff zzff, @SafeParcelable.Param(mo21173id = 2) zzl zzl2, @SafeParcelable.Param(mo21173id = 3) String str, @SafeParcelable.Param(mo21173id = 4) String str2, @SafeParcelable.Param(mo21173id = 5) List<zzl> list, @SafeParcelable.Param(mo21173id = 6) List<String> list2, @SafeParcelable.Param(mo21173id = 7) String str3, @SafeParcelable.Param(mo21173id = 8) Boolean bool, @SafeParcelable.Param(mo21173id = 9) zzr zzr, @SafeParcelable.Param(mo21173id = 10) boolean z, @SafeParcelable.Param(mo21173id = 11) zze zze2, @SafeParcelable.Param(mo21173id = 12) zzau zzau) {
        this.zza = zzff;
        this.zzb = zzl2;
        this.zzc = str;
        this.zzd = str2;
        this.zze = list;
        this.zzf = list2;
        this.zzg = str3;
        this.zzh = bool;
        this.zzi = zzr;
        this.zzj = z;
        this.zzk = zze2;
        this.zzl = zzau;
    }

    public zzp(FirebaseApp firebaseApp, List<? extends UserInfo> list) {
        Preconditions.checkNotNull(firebaseApp);
        this.zzc = firebaseApp.getName();
        this.zzd = "com.google.firebase.auth.internal.DefaultFirebaseUser";
        this.zzg = "2";
        zza(list);
    }

    @Nullable
    public String getDisplayName() {
        return this.zzb.getDisplayName();
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zzb.getPhotoUrl();
    }

    @Nullable
    public String getEmail() {
        return this.zzb.getEmail();
    }

    @Nullable
    public String getPhoneNumber() {
        return this.zzb.getPhoneNumber();
    }

    @Nullable
    public final String zzd() {
        String str;
        if (this.zza == null || this.zza.zzd() == null) {
            return null;
        }
        Map map = (Map) zzap.zza(this.zza.zzd()).getClaims().get(FirebaseAuthProvider.PROVIDER_ID);
        if (map != null) {
            str = (String) map.get("tenant");
        } else {
            str = null;
        }
        return str;
    }

    public final zzp zza(String str) {
        this.zzg = str;
        return this;
    }

    @NonNull
    public String getProviderId() {
        return this.zzb.getProviderId();
    }

    @NonNull
    public final FirebaseApp zzc() {
        return FirebaseApp.getInstance(this.zzc);
    }

    public final List<zzl> zzh() {
        return this.zze;
    }

    @NonNull
    public String getUid() {
        return this.zzb.getUid();
    }

    public boolean isAnonymous() {
        if (this.zzh == null || this.zzh.booleanValue()) {
            String str = "";
            if (this.zza != null) {
                GetTokenResult zza2 = zzap.zza(this.zza.zzd());
                str = zza2 != null ? zza2.getSignInProvider() : "";
            }
            this.zzh = Boolean.valueOf(getProviderData().size() <= 1 && (str == null || !str.equals("custom")));
        }
        return this.zzh.booleanValue();
    }

    @Nullable
    public final List<String> zza() {
        return this.zzf;
    }

    @NonNull
    public final FirebaseUser zza(List<? extends UserInfo> list) {
        Preconditions.checkNotNull(list);
        this.zze = new ArrayList(list.size());
        this.zzf = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = (UserInfo) list.get(i);
            if (userInfo.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                this.zzb = (zzl) userInfo;
            } else {
                this.zzf.add(userInfo.getProviderId());
            }
            this.zze.add((zzl) userInfo);
        }
        if (this.zzb == null) {
            this.zzb = this.zze.get(0);
        }
        return this;
    }

    @NonNull
    public List<? extends UserInfo> getProviderData() {
        return this.zze;
    }

    @NonNull
    public final zzff zze() {
        return this.zza;
    }

    @NonNull
    public final String zzg() {
        return zze().zzd();
    }

    @NonNull
    public final String zzf() {
        return this.zza.zzh();
    }

    public final void zza(zzff zzff) {
        this.zza = (zzff) Preconditions.checkNotNull(zzff);
    }

    public boolean isEmailVerified() {
        return this.zzb.isEmailVerified();
    }

    public final void zza(zzr zzr) {
        this.zzi = zzr;
    }

    public FirebaseUserMetadata getMetadata() {
        return this.zzi;
    }

    public final void zza(boolean z) {
        this.zzj = z;
    }

    public final boolean zzi() {
        return this.zzj;
    }

    public final void zza(zze zze2) {
        this.zzk = zze2;
    }

    @Nullable
    public final zze zzj() {
        return this.zzk;
    }

    public final void zzb(List<MultiFactorInfo> list) {
        this.zzl = zzau.zza(list);
    }

    @Nullable
    public final List<MultiFactorInfo> zzk() {
        if (this.zzl != null) {
            return this.zzl.zza();
        }
        return zzbj.zzf();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zze(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zze, false);
        SafeParcelWriter.writeStringList(parcel, 6, zza(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeBooleanObject(parcel, 8, Boolean.valueOf(isAnonymous()), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getMetadata(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static FirebaseUser zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser) {
        zzp zzp = new zzp(firebaseApp, firebaseUser.getProviderData());
        if (firebaseUser instanceof zzp) {
            zzp zzp2 = (zzp) firebaseUser;
            zzp.zzg = zzp2.zzg;
            zzp.zzd = zzp2.zzd;
            zzp.zzi = (zzr) zzp2.getMetadata();
        } else {
            zzp.zzi = null;
        }
        if (firebaseUser.zze() != null) {
            zzp.zza(firebaseUser.zze());
        }
        if (!firebaseUser.isAnonymous()) {
            zzp.zzb();
        }
        return zzp;
    }

    public /* synthetic */ MultiFactor getMultiFactor() {
        return new zzt(this);
    }

    public final /* synthetic */ FirebaseUser zzb() {
        this.zzh = false;
        return this;
    }
}
