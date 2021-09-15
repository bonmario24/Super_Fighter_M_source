package com.google.firebase.auth;

import android.app.Activity;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class FirebaseUser extends AbstractSafeParcelable implements UserInfo {
    @Nullable
    public abstract String getDisplayName();

    @Nullable
    public abstract String getEmail();

    @Nullable
    public abstract FirebaseUserMetadata getMetadata();

    @NonNull
    public abstract MultiFactor getMultiFactor();

    @Nullable
    public abstract String getPhoneNumber();

    @Nullable
    public abstract Uri getPhotoUrl();

    @NonNull
    public abstract List<? extends UserInfo> getProviderData();

    @NonNull
    public abstract String getProviderId();

    @NonNull
    public abstract String getUid();

    public abstract boolean isAnonymous();

    @NonNull
    public abstract FirebaseUser zza(@NonNull List<? extends UserInfo> list);

    @Nullable
    public abstract List<String> zza();

    public abstract void zza(@NonNull zzff zzff);

    public abstract FirebaseUser zzb();

    public abstract void zzb(List<MultiFactorInfo> list);

    @NonNull
    public abstract FirebaseApp zzc();

    @Nullable
    public abstract String zzd();

    @NonNull
    public abstract zzff zze();

    @NonNull
    public abstract String zzf();

    @NonNull
    public abstract String zzg();

    @NonNull
    public Task<GetTokenResult> getIdToken(boolean z) {
        return FirebaseAuth.getInstance(zzc()).zza(this, z);
    }

    @NonNull
    public Task<Void> reload() {
        return FirebaseAuth.getInstance(zzc()).zza(this);
    }

    @NonNull
    public Task<Void> reauthenticate(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zzc()).zza(this, authCredential);
    }

    @NonNull
    public Task<AuthResult> reauthenticateAndRetrieveData(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zzc()).zzb(this, authCredential);
    }

    @NonNull
    public Task<AuthResult> startActivityForReauthenticateWithProvider(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        return FirebaseAuth.getInstance(zzc()).zzb(activity, federatedAuthProvider, this);
    }

    @NonNull
    public Task<AuthResult> linkWithCredential(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zzc()).zzc(this, authCredential);
    }

    @NonNull
    public Task<AuthResult> startActivityForLinkWithProvider(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        return FirebaseAuth.getInstance(zzc()).zza(activity, federatedAuthProvider, this);
    }

    @NonNull
    public Task<AuthResult> unlink(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zzc()).zza(this, str);
    }

    @NonNull
    public Task<Void> updateProfile(@NonNull UserProfileChangeRequest userProfileChangeRequest) {
        Preconditions.checkNotNull(userProfileChangeRequest);
        return FirebaseAuth.getInstance(zzc()).zza(this, userProfileChangeRequest);
    }

    @NonNull
    public Task<Void> updateEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zzc()).zzb(this, str);
    }

    @NonNull
    public Task<Void> updatePhoneNumber(@NonNull PhoneAuthCredential phoneAuthCredential) {
        return FirebaseAuth.getInstance(zzc()).zza(this, phoneAuthCredential);
    }

    @NonNull
    public Task<Void> updatePassword(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zzc()).zzc(this, str);
    }

    @NonNull
    public Task<Void> delete() {
        return FirebaseAuth.getInstance(zzc()).zzb(this);
    }

    @NonNull
    public Task<Void> sendEmailVerification() {
        return FirebaseAuth.getInstance(zzc()).zza(this, false).continueWithTask(new zzq(this));
    }

    @NonNull
    public Task<Void> sendEmailVerification(@NonNull ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zzc()).zza(this, false).continueWithTask(new zzs(this, actionCodeSettings));
    }

    @NonNull
    public Task<Void> verifyBeforeUpdateEmail(@NonNull String str) {
        return verifyBeforeUpdateEmail(str, (ActionCodeSettings) null);
    }

    @NonNull
    public Task<Void> verifyBeforeUpdateEmail(@NonNull String str, @Nullable ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zzc()).zza(this, false).continueWithTask(new zzr(this, str, actionCodeSettings));
    }
}
