package com.google.firebase.auth;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzw;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public final class PhoneAuthOptions {
    private final FirebaseAuth zza;
    private Long zzb;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks zzc;
    private Executor zzd;
    @Nullable
    private String zze;
    @Nullable
    private Activity zzf;
    @Nullable
    private PhoneAuthProvider.ForceResendingToken zzg;
    @Nullable
    private MultiFactorSession zzh;
    @Nullable
    private PhoneMultiFactorInfo zzi;
    @Nullable
    private String zzj;
    private boolean zzk;

    private PhoneAuthOptions(FirebaseAuth firebaseAuth, Long l, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, @Nullable String str, @Nullable Activity activity, @Nullable PhoneAuthProvider.ForceResendingToken forceResendingToken, @Nullable MultiFactorSession multiFactorSession, @Nullable PhoneMultiFactorInfo phoneMultiFactorInfo, @Nullable String str2, boolean z) {
        this.zza = firebaseAuth;
        this.zze = str;
        this.zzb = l;
        this.zzc = onVerificationStateChangedCallbacks;
        this.zzf = activity;
        this.zzd = executor;
        this.zzg = forceResendingToken;
        this.zzh = multiFactorSession;
        this.zzi = phoneMultiFactorInfo;
        this.zzj = str2;
        this.zzk = z;
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
    public static final class Builder {
        private final FirebaseAuth zza;
        private String zzb;
        private Long zzc;
        private PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd;
        private Executor zze;
        private Activity zzf;
        private PhoneAuthProvider.ForceResendingToken zzg;
        private MultiFactorSession zzh;
        private PhoneMultiFactorInfo zzi;
        private boolean zzj;

        public Builder(@NonNull FirebaseAuth firebaseAuth) {
            this.zza = (FirebaseAuth) Preconditions.checkNotNull(firebaseAuth);
        }

        @NonNull
        public final Builder setPhoneNumber(@NonNull String str) {
            this.zzb = str;
            return this;
        }

        @NonNull
        public final Builder setMultiFactorHint(@NonNull PhoneMultiFactorInfo phoneMultiFactorInfo) {
            this.zzi = phoneMultiFactorInfo;
            return this;
        }

        @NonNull
        public final Builder setTimeout(@NonNull Long l, @NonNull TimeUnit timeUnit) {
            this.zzc = Long.valueOf(TimeUnit.SECONDS.convert(l.longValue(), timeUnit));
            return this;
        }

        @NonNull
        public final Builder setCallbacks(@NonNull PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
            this.zzd = onVerificationStateChangedCallbacks;
            return this;
        }

        @NonNull
        public final Builder setActivity(@NonNull Activity activity) {
            this.zzf = activity;
            return this;
        }

        @NonNull
        public final Builder setExecutor(@NonNull Executor executor) {
            this.zze = executor;
            return this;
        }

        @NonNull
        public final Builder setForceResendingToken(@NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            this.zzg = forceResendingToken;
            return this;
        }

        @NonNull
        public final Builder setMultiFactorSession(@NonNull MultiFactorSession multiFactorSession) {
            this.zzh = multiFactorSession;
            return this;
        }

        @NonNull
        public final Builder requireSmsValidation(boolean z) {
            this.zzj = z;
            return this;
        }

        @NonNull
        public final PhoneAuthOptions build() {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4 = true;
            Preconditions.checkNotNull(this.zza);
            Preconditions.checkNotNull(this.zzc);
            Preconditions.checkNotNull(this.zzd);
            if (this.zze == null) {
                this.zze = TaskExecutors.MAIN_THREAD;
            }
            if (this.zze != TaskExecutors.MAIN_THREAD && this.zzf != null) {
                throw new IllegalArgumentException("You cannot specify both an executor and an activity.");
            } else if (this.zzc.longValue() < 0 || this.zzc.longValue() > 120) {
                throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
            } else {
                if (this.zzh == null) {
                    Preconditions.checkNotEmpty(this.zzb);
                    if (!this.zzj) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    Preconditions.checkArgument(z3, "You cannot require sms validation without setting a multi-factor session.");
                    if (this.zzi != null) {
                        z4 = false;
                    }
                    Preconditions.checkArgument(z4, "A phoneMultiFactorInfo must be set for second factor sign-in.");
                } else {
                    if (this.zzh == null || !((zzw) this.zzh).zzc()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (z) {
                        Preconditions.checkNotEmpty(this.zzb);
                        if (this.zzi != null) {
                            z4 = false;
                        }
                        Preconditions.checkArgument(z4, "Invalid MultiFactorSession - use the getSession method in MultiFactorResolver to get a valid sign-in session.");
                    } else {
                        if (this.zzi != null) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        Preconditions.checkArgument(z2, "A phoneMultiFactorInfo must be set for second factor sign-in.");
                        if (this.zzb != null) {
                            z4 = false;
                        }
                        Preconditions.checkArgument(z4, "A phone number must not be set for MFA sign-in. A PhoneMultiFactorInfo should be set instead.");
                    }
                }
                return new PhoneAuthOptions(this.zza, this.zzc, this.zzd, this.zze, this.zzb, this.zzf, this.zzg, this.zzh, this.zzi, (String) null, this.zzj, (zzx) null);
            }
        }
    }

    @NonNull
    public final FirebaseAuth zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zze;
    }

    public final Long zzc() {
        return this.zzb;
    }

    public final PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd() {
        return this.zzc;
    }

    public final Executor zze() {
        return this.zzd;
    }

    @Nullable
    public final PhoneAuthProvider.ForceResendingToken zzf() {
        return this.zzg;
    }

    @Nullable
    public final MultiFactorSession zzg() {
        return this.zzh;
    }

    @Nullable
    public final String zzh() {
        return this.zzj;
    }

    public final boolean zzi() {
        return this.zzk;
    }

    @Nullable
    public final Activity zzj() {
        return this.zzf;
    }

    @Nullable
    public final PhoneMultiFactorInfo zzk() {
        return this.zzi;
    }

    public final boolean zzl() {
        return this.zzh != null;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(FirebaseAuth.getInstance());
    }

    @NonNull
    public static Builder newBuilder(@NonNull FirebaseAuth firebaseAuth) {
        return new Builder(firebaseAuth);
    }

    /* synthetic */ PhoneAuthOptions(FirebaseAuth firebaseAuth, Long l, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, String str, Activity activity, PhoneAuthProvider.ForceResendingToken forceResendingToken, MultiFactorSession multiFactorSession, PhoneMultiFactorInfo phoneMultiFactorInfo, String str2, boolean z, zzx zzx) {
        this(firebaseAuth, l, onVerificationStateChangedCallbacks, executor, str, activity, forceResendingToken, multiFactorSession, phoneMultiFactorInfo, (String) null, z);
    }
}
