package com.google.firebase.auth;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.internal.firebase_auth.zzgm;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.api.internal.zzas;
import com.google.firebase.auth.api.internal.zzec;
import com.google.firebase.auth.api.internal.zzej;
import com.google.firebase.auth.api.internal.zzeu;
import com.google.firebase.auth.api.internal.zzev;
import com.google.firebase.auth.api.internal.zzfe;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.auth.internal.zzae;
import com.google.firebase.auth.internal.zzaf;
import com.google.firebase.auth.internal.zzap;
import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.internal.zzaw;
import com.google.firebase.auth.internal.zzax;
import com.google.firebase.auth.internal.zzay;
import com.google.firebase.auth.internal.zzaz;
import com.google.firebase.auth.internal.zzbc;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzm;
import com.google.firebase.auth.internal.zzp;
import com.google.firebase.auth.internal.zzw;
import com.google.firebase.internal.InternalTokenResult;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public class FirebaseAuth implements InternalAuthProvider {
    private FirebaseApp zza;
    /* access modifiers changed from: private */
    public final List<IdTokenListener> zzb;
    /* access modifiers changed from: private */
    public final List<com.google.firebase.auth.internal.IdTokenListener> zzc;
    /* access modifiers changed from: private */
    public List<AuthStateListener> zzd;
    private zzas zze;
    /* access modifiers changed from: private */
    public FirebaseUser zzf;
    /* access modifiers changed from: private */
    public zzm zzg;
    private final Object zzh;
    private String zzi;
    private final Object zzj;
    private String zzk;
    private final zzay zzl;
    private final zzaq zzm;
    private zzax zzn;
    private zzaz zzo;

    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    public interface AuthStateListener {
        void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    public interface IdTokenListener {
        void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    @VisibleForTesting
    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    class zza implements com.google.firebase.auth.internal.zzb {
        zza() {
        }

        public final void zza(@NonNull zzff zzff, @NonNull FirebaseUser firebaseUser) {
            Preconditions.checkNotNull(zzff);
            Preconditions.checkNotNull(firebaseUser);
            firebaseUser.zza(zzff);
            FirebaseAuth.this.zza(firebaseUser, zzff, true);
        }
    }

    @VisibleForTesting
    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    class zzb implements zzae, com.google.firebase.auth.internal.zzb {
        zzb() {
        }

        public final void zza(@NonNull zzff zzff, @NonNull FirebaseUser firebaseUser) {
            Preconditions.checkNotNull(zzff);
            Preconditions.checkNotNull(firebaseUser);
            firebaseUser.zza(zzff);
            FirebaseAuth.this.zza(firebaseUser, zzff, true, true);
        }

        public final void zza(Status status) {
            if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005 || status.getStatusCode() == 17091) {
                FirebaseAuth.this.signOut();
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    class zzc extends zza implements zzae, com.google.firebase.auth.internal.zzb {
        zzc(FirebaseAuth firebaseAuth) {
            super();
        }

        public final void zza(Status status) {
        }
    }

    @NonNull
    @Keep
    public static FirebaseAuth getInstance() {
        return (FirebaseAuth) FirebaseApp.getInstance().get(FirebaseAuth.class);
    }

    @NonNull
    @Keep
    public static FirebaseAuth getInstance(@NonNull FirebaseApp firebaseApp) {
        return (FirebaseAuth) firebaseApp.get(FirebaseAuth.class);
    }

    public FirebaseAuth(FirebaseApp firebaseApp) {
        this(firebaseApp, zzeu.zza(firebaseApp.getApplicationContext(), new zzev(firebaseApp.getOptions().getApiKey()).zza()), new zzay(firebaseApp.getApplicationContext(), firebaseApp.getPersistenceKey()), zzaq.zza());
    }

    @VisibleForTesting
    private FirebaseAuth(FirebaseApp firebaseApp, zzas zzas, zzay zzay, zzaq zzaq) {
        zzff zzb2;
        this.zzh = new Object();
        this.zzj = new Object();
        this.zza = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zze = (zzas) Preconditions.checkNotNull(zzas);
        this.zzl = (zzay) Preconditions.checkNotNull(zzay);
        this.zzg = new zzm();
        this.zzm = (zzaq) Preconditions.checkNotNull(zzaq);
        this.zzb = new CopyOnWriteArrayList();
        this.zzc = new CopyOnWriteArrayList();
        this.zzd = new CopyOnWriteArrayList();
        this.zzo = zzaz.zza();
        this.zzf = this.zzl.zza();
        if (!(this.zzf == null || (zzb2 = this.zzl.zzb(this.zzf)) == null)) {
            zza(this.zzf, zzb2, false);
        }
        this.zzm.zza(this);
    }

    @Nullable
    public FirebaseUser getCurrentUser() {
        return this.zzf;
    }

    @Nullable
    public String getUid() {
        if (this.zzf == null) {
            return null;
        }
        return this.zzf.getUid();
    }

    public final void zza(@NonNull FirebaseUser firebaseUser, @NonNull zzff zzff, boolean z) {
        zza(firebaseUser, zzff, z, false);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final void zza(FirebaseUser firebaseUser, zzff zzff, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        boolean z5 = false;
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzff);
        boolean z6 = this.zzf != null && firebaseUser.getUid().equals(this.zzf.getUid());
        if (z6 || !z2) {
            if (this.zzf == null) {
                z5 = true;
                z4 = true;
            } else {
                if (!this.zzf.zze().zzd().equals(zzff.zzd())) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z6 || z3) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (!z6) {
                    z5 = true;
                }
            }
            Preconditions.checkNotNull(firebaseUser);
            if (this.zzf == null) {
                this.zzf = firebaseUser;
            } else {
                this.zzf.zza(firebaseUser.getProviderData());
                if (!firebaseUser.isAnonymous()) {
                    this.zzf.zzb();
                }
                this.zzf.zzb(firebaseUser.getMultiFactor().getEnrolledFactors());
            }
            if (z) {
                this.zzl.zza(this.zzf);
            }
            if (z4) {
                if (this.zzf != null) {
                    this.zzf.zza(zzff);
                }
                zzc(this.zzf);
            }
            if (z5) {
                zzd(this.zzf);
            }
            if (z) {
                this.zzl.zza(firebaseUser, zzff);
            }
            zzd().zza(this.zzf.zze());
        }
    }

    public final void zza() {
        if (this.zzf != null) {
            zzay zzay = this.zzl;
            FirebaseUser firebaseUser = this.zzf;
            Preconditions.checkNotNull(firebaseUser);
            zzay.zza(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}));
            this.zzf = null;
        }
        this.zzl.zza("com.google.firebase.auth.FIREBASE_USER");
        zzc((FirebaseUser) null);
        zzd((FirebaseUser) null);
    }

    @VisibleForTesting
    private final synchronized void zza(zzax zzax) {
        this.zzn = zzax;
    }

    @VisibleForTesting
    private final synchronized zzax zzd() {
        if (this.zzn == null) {
            zza(new zzax(this.zza));
        }
        return this.zzn;
    }

    @NonNull
    public FirebaseApp getApp() {
        return this.zza;
    }

    public final FirebaseApp zzb() {
        return this.zza;
    }

    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzb.add(idTokenListener);
        this.zzo.execute(new zzi(this, idTokenListener));
    }

    @KeepForSdk
    public void addIdTokenListener(@NonNull com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzc.add(idTokenListener);
        zzd().zza(this.zzc.size());
    }

    public void removeIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzb.remove(idTokenListener);
    }

    @KeepForSdk
    public void removeIdTokenListener(@NonNull com.google.firebase.auth.internal.IdTokenListener idTokenListener) {
        Preconditions.checkNotNull(idTokenListener);
        this.zzc.remove(idTokenListener);
        zzd().zza(this.zzc.size());
    }

    public void addAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzd.add(authStateListener);
        this.zzo.execute(new zzh(this, authStateListener));
    }

    public void removeAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzd.remove(authStateListener);
    }

    private final void zzc(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            Log.d("FirebaseAuth", new StringBuilder(String.valueOf(uid).length() + 45).append("Notifying id token listeners about user ( ").append(uid).append(" ).").toString());
        } else {
            Log.d("FirebaseAuth", "Notifying id token listeners about a sign-out event.");
        }
        this.zzo.execute(new zzk(this, new InternalTokenResult(firebaseUser != null ? firebaseUser.zzg() : null)));
    }

    private final void zzd(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            Log.d("FirebaseAuth", new StringBuilder(String.valueOf(uid).length() + 47).append("Notifying auth state listeners about user ( ").append(uid).append(" ).").toString());
        } else {
            Log.d("FirebaseAuth", "Notifying auth state listeners about a sign-out event.");
        }
        this.zzo.execute(new zzj(this));
    }

    @NonNull
    public Task<GetTokenResult> getAccessToken(boolean z) {
        return zza(this.zzf, z);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.zzm] */
    @NonNull
    public final Task<GetTokenResult> zza(@Nullable FirebaseUser firebaseUser, boolean z) {
        if (firebaseUser == null) {
            return Tasks.forException(zzej.zza(new Status(FirebaseError.ERROR_NO_SIGNED_IN_USER)));
        }
        zzff zze2 = firebaseUser.zze();
        if (!zze2.zzb() || z) {
            return this.zze.zza(this.zza, firebaseUser, zze2.zzc(), (zzbc) new zzm(this));
        }
        return Tasks.forResult(zzap.zza(zze2.zzd()));
    }

    @NonNull
    public Task<AuthResult> signInWithCredential(@NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if (!emailAuthCredential.zzg()) {
                return this.zze.zzb(this.zza, emailAuthCredential.zzb(), emailAuthCredential.zzc(), this.zzk, (com.google.firebase.auth.internal.zzb) new zza());
            }
            if (zzb(emailAuthCredential.zzd())) {
                return Tasks.forException(zzej.zza(new Status(17072)));
            }
            return this.zze.zza(this.zza, emailAuthCredential, (com.google.firebase.auth.internal.zzb) new zza());
        } else if (!(zza2 instanceof PhoneAuthCredential)) {
            return this.zze.zza(this.zza, zza2, this.zzk, (com.google.firebase.auth.internal.zzb) new zza());
        } else {
            return this.zze.zza(this.zza, (PhoneAuthCredential) zza2, this.zzk, (com.google.firebase.auth.internal.zzb) new zza());
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r5v1, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r3v2, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if ("password".equals(emailAuthCredential.getSignInMethod())) {
                return this.zze.zza(this.zza, firebaseUser, emailAuthCredential.zzb(), emailAuthCredential.zzc(), firebaseUser.zzd(), new zzb());
            } else if (zzb(emailAuthCredential.zzd())) {
                return Tasks.forException(zzej.zza(new Status(17072)));
            } else {
                return this.zze.zza(this.zza, firebaseUser, emailAuthCredential, (zzbc) new zzb());
            }
        } else if (zza2 instanceof PhoneAuthCredential) {
            return this.zze.zza(this.zza, firebaseUser, (PhoneAuthCredential) zza2, this.zzk, (zzbc) new zzb());
        } else {
            return this.zze.zza(this.zza, firebaseUser, zza2, firebaseUser.zzd(), (zzbc) new zzb());
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r5v1, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r3v2, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<AuthResult> zzb(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if ("password".equals(emailAuthCredential.getSignInMethod())) {
                return this.zze.zzb(this.zza, firebaseUser, emailAuthCredential.zzb(), emailAuthCredential.zzc(), firebaseUser.zzd(), new zzb());
            } else if (zzb(emailAuthCredential.zzd())) {
                return Tasks.forException(zzej.zza(new Status(17072)));
            } else {
                return this.zze.zzb(this.zza, firebaseUser, emailAuthCredential, (zzbc) new zzb());
            }
        } else if (zza2 instanceof PhoneAuthCredential) {
            return this.zze.zzb(this.zza, firebaseUser, (PhoneAuthCredential) zza2, this.zzk, (zzbc) new zzb());
        } else {
            return this.zze.zzb(this.zza, firebaseUser, zza2, firebaseUser.zzd(), (zzbc) new zzb());
        }
    }

    @NonNull
    public Task<AuthResult> signInWithCustomToken(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zza(this.zza, str, this.zzk, (com.google.firebase.auth.internal.zzb) new zza());
    }

    @NonNull
    public Task<AuthResult> signInWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zzb(this.zza, str, str2, this.zzk, (com.google.firebase.auth.internal.zzb) new zza());
    }

    @NonNull
    public Task<AuthResult> signInWithEmailLink(@NonNull String str, @NonNull String str2) {
        return signInWithCredential(EmailAuthProvider.getCredentialWithLink(str, str2));
    }

    @NonNull
    public Task<AuthResult> signInAnonymously() {
        if (this.zzf == null || !this.zzf.isAnonymous()) {
            return this.zze.zza(this.zza, (com.google.firebase.auth.internal.zzb) new zza(), this.zzk);
        }
        zzp zzp = (zzp) this.zzf;
        zzp.zza(false);
        return Tasks.forResult(new zzj(zzp));
    }

    public final void zza(@NonNull String str, long j, TimeUnit timeUnit, @NonNull PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, @NonNull Executor executor, boolean z, @Nullable String str2) {
        long convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert < 0 || convert > 120) {
            throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        }
        this.zze.zza(this.zza, new zzfr(str, convert, z, this.zzi, this.zzk, str2), zza(str, onVerificationStateChangedCallbacks), activity, executor);
    }

    public static void zza(@NonNull PhoneAuthOptions phoneAuthOptions) {
        boolean z;
        boolean z2;
        if (phoneAuthOptions.zzl()) {
            FirebaseAuth zza2 = phoneAuthOptions.zza();
            long longValue = phoneAuthOptions.zzc().longValue();
            PhoneAuthProvider.OnVerificationStateChangedCallbacks zza3 = zza2.zza(phoneAuthOptions.zzb(), phoneAuthOptions.zzd());
            zzw zzw = (zzw) phoneAuthOptions.zzg();
            if (zzw.zzc()) {
                zza2.zze.zza(zzw, phoneAuthOptions.zzb(), zza2.zzi, longValue, phoneAuthOptions.zzf() != null, phoneAuthOptions.zzi(), zza3, phoneAuthOptions.zze(), phoneAuthOptions.zzj());
                return;
            }
            zzas zzas = zza2.zze;
            PhoneMultiFactorInfo zzk2 = phoneAuthOptions.zzk();
            String str = zza2.zzi;
            if (phoneAuthOptions.zzf() != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            zzas.zza(zzw, zzk2, str, longValue, z2, phoneAuthOptions.zzi(), zza3, phoneAuthOptions.zze(), phoneAuthOptions.zzj());
            return;
        }
        FirebaseAuth zza4 = phoneAuthOptions.zza();
        String zzb2 = phoneAuthOptions.zzb();
        long longValue2 = phoneAuthOptions.zzc().longValue();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd2 = phoneAuthOptions.zzd();
        Activity zzj2 = phoneAuthOptions.zzj();
        Executor zze2 = phoneAuthOptions.zze();
        if (phoneAuthOptions.zzf() != null) {
            z = true;
        } else {
            z = false;
        }
        zza4.zza(zzb2, longValue2, timeUnit, zzd2, zzj2, zze2, z, phoneAuthOptions.zzh());
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        if (!this.zzg.zzc() || !str.equals(this.zzg.zza())) {
            return onVerificationStateChangedCallbacks;
        }
        return new zzl(this, onVerificationStateChangedCallbacks);
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzc] */
    @NonNull
    public Task<Void> updateCurrentUser(@NonNull FirebaseUser firebaseUser) {
        if (firebaseUser == null) {
            throw new IllegalArgumentException("Cannot update current user with null user!");
        } else if ((firebaseUser.zzd() != null && !firebaseUser.zzd().equals(this.zzk)) || (this.zzk != null && !this.zzk.equals(firebaseUser.zzd()))) {
            return Tasks.forException(zzej.zza(new Status(17072)));
        } else {
            String apiKey = firebaseUser.zzc().getOptions().getApiKey();
            String apiKey2 = this.zza.getOptions().getApiKey();
            if (!firebaseUser.zze().zzb() || !apiKey2.equals(apiKey)) {
                return zza(firebaseUser, (zzbc) new zzc(this));
            }
            zza(zzp.zza(this.zza, firebaseUser), firebaseUser.zze(), true);
            return Tasks.forResult(null);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser) {
        return zza(firebaseUser, (zzbc) new zzb());
    }

    @NonNull
    private final Task<Void> zza(@NonNull FirebaseUser firebaseUser, zzbc zzbc) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zza(this.zza, firebaseUser, zzbc);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    @NonNull
    public final Task<AuthResult> zzc(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zza(this.zza, firebaseUser, authCredential.zza(), (zzbc) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    @NonNull
    public final Task<AuthResult> zza(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzd(this.zza, firebaseUser, str, new zzb());
    }

    @NonNull
    public Task<AuthResult> createUserWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zza(this.zza, str, str2, this.zzk, (com.google.firebase.auth.internal.zzb) new zza());
    }

    @NonNull
    public Task<SignInMethodQueryResult> fetchSignInMethodsForEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zza(this.zza, str, this.zzk);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull UserProfileChangeRequest userProfileChangeRequest) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(userProfileChangeRequest);
        return this.zze.zza(this.zza, firebaseUser, userProfileChangeRequest, (zzbc) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    @NonNull
    public final Task<Void> zzb(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzb(this.zza, firebaseUser, str, (zzbc) new zzb());
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull PhoneAuthCredential phoneAuthCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(phoneAuthCredential);
        return this.zze.zza(this.zza, firebaseUser, (PhoneAuthCredential) phoneAuthCredential.zza(), (zzbc) new zzb());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    @NonNull
    public final Task<Void> zzc(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzc(this.zza, firebaseUser, str, new zzb());
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return sendPasswordResetEmail(str, (ActionCodeSettings) null);
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str, @Nullable ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zza();
        }
        if (this.zzi != null) {
            actionCodeSettings.zza(this.zzi);
        }
        actionCodeSettings.zza(zzgm.PASSWORD_RESET);
        return this.zze.zza(this.zza, str, actionCodeSettings, this.zzk);
    }

    @NonNull
    public Task<Void> sendSignInLinkToEmail(@NonNull String str, @NonNull ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(actionCodeSettings);
        if (!actionCodeSettings.canHandleCodeInApp()) {
            throw new IllegalArgumentException("You must set canHandleCodeInApp in your ActionCodeSettings to true for Email-Link Sign-in.");
        }
        if (this.zzi != null) {
            actionCodeSettings.zza(this.zzi);
        }
        return this.zze.zzb(this.zza, str, actionCodeSettings, this.zzk);
    }

    public boolean isSignInWithEmailLink(@NonNull String str) {
        return EmailAuthCredential.zza(str);
    }

    @NonNull
    public final Task<Void> zza(@Nullable ActionCodeSettings actionCodeSettings, @NonNull String str) {
        Preconditions.checkNotEmpty(str);
        if (this.zzi != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.zza();
            }
            actionCodeSettings.zza(this.zzi);
        }
        return this.zze.zza(this.zza, actionCodeSettings, str);
    }

    @NonNull
    public Task<ActionCodeResult> checkActionCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzb(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<Void> applyActionCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzc(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<String> verifyPasswordResetCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        return this.zze.zzd(this.zza, str, this.zzk);
    }

    @NonNull
    public Task<Void> confirmPasswordReset(@NonNull String str, @NonNull String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return this.zze.zza(this.zza, str, str2, this.zzk);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.firebase.auth.internal.zzbc, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<Void> zzd(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zze(this.zza, firebaseUser, str, new zzb()).continueWithTask(new zzo(this));
    }

    public final Task<AuthResult> zza(MultiFactorAssertion multiFactorAssertion, zzw zzw, @Nullable FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzw);
        return this.zze.zza(this.zza, firebaseUser, (PhoneMultiFactorAssertion) multiFactorAssertion, zzw.zzb(), (com.google.firebase.auth.internal.zzb) new zza());
    }

    @NonNull
    public Task<AuthResult> startActivityForSignInWithProvider(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(activity);
        if (!zzec.zza()) {
            return Tasks.forException(zzej.zza(new Status(17063)));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zza(activity, taskCompletionSource, this)) {
            return Tasks.forException(zzej.zza(new Status(17057)));
        }
        zzaw.zza(activity.getApplicationContext(), this);
        federatedAuthProvider.zza(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<AuthResult> zza(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider, @NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        if (!zzec.zza()) {
            return Tasks.forException(zzej.zza(new Status(17063)));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zza(activity, (TaskCompletionSource<AuthResult>) taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzej.zza(new Status(17057)));
        }
        zzaw.zza(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzb(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<AuthResult> zzb(@NonNull Activity activity, @NonNull FederatedAuthProvider federatedAuthProvider, @NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        if (!zzec.zza()) {
            return Tasks.forException(zzej.zza(new Status(17063)));
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zza(activity, (TaskCompletionSource<AuthResult>) taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzej.zza(new Status(17057)));
        }
        zzaw.zza(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzc(activity);
        return taskCompletionSource.getTask();
    }

    @Nullable
    public Task<AuthResult> getPendingAuthResult() {
        return this.zzm.zzb();
    }

    @NonNull
    public final Task<Void> zzb(@NonNull FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zza(firebaseUser, (zzaf) new zzn(this, firebaseUser));
    }

    public final Task<Void> zza(String str, String str2, @Nullable ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zza();
        }
        if (this.zzi != null) {
            actionCodeSettings.zza(this.zzi);
        }
        return this.zze.zza(str, str2, actionCodeSettings);
    }

    public final Task<Void> zza(FirebaseUser firebaseUser, MultiFactorAssertion multiFactorAssertion, @Nullable String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(multiFactorAssertion);
        if (!(multiFactorAssertion instanceof PhoneMultiFactorAssertion)) {
            return Tasks.forException(zzej.zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR)));
        }
        return this.zze.zza(this.zza, (PhoneMultiFactorAssertion) multiFactorAssertion, firebaseUser, str, (com.google.firebase.auth.internal.zzb) new zza());
    }

    public void signOut() {
        zza();
        if (this.zzn != null) {
            this.zzn.zza();
        }
    }

    public void setLanguageCode(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzh) {
            this.zzi = str;
        }
    }

    @Nullable
    public String getLanguageCode() {
        String str;
        synchronized (this.zzh) {
            str = this.zzi;
        }
        return str;
    }

    public final void zza(@NonNull String str) {
        Preconditions.checkNotEmpty(str);
        synchronized (this.zzj) {
            this.zzk = str;
        }
    }

    @Nullable
    public final String zzc() {
        String str;
        synchronized (this.zzj) {
            str = this.zzk;
        }
        return str;
    }

    public void useAppLanguage() {
        synchronized (this.zzh) {
            this.zzi = zzfe.zza();
        }
    }

    @NonNull
    public FirebaseAuthSettings getFirebaseAuthSettings() {
        return this.zzg;
    }

    @NonNull
    public Task<Void> setFirebaseUIVersion(@Nullable String str) {
        return this.zze.zza(str);
    }

    private final boolean zzb(String str) {
        ActionCodeUrl parseLink = ActionCodeUrl.parseLink(str);
        return parseLink != null && !TextUtils.equals(this.zzk, parseLink.zza());
    }
}
