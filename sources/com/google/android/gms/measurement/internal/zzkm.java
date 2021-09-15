package com.google.android.gms.measurement.internal;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.android.vending.expansion.zipfile.APEZProvider;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzjm;
import com.google.android.gms.internal.measurement.zzlb;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.okgo.OkGo;
import com.teamtop3.Defenders.HttpResponse;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzkm extends zzgq {
    private static final String[] zza = {"firebase_", "google_", "ga_"};
    private static final String[] zzb = {"_err"};
    private SecureRandom zzc;
    private final AtomicLong zzd = new AtomicLong(0);
    private int zze;
    private Integer zzf = null;

    zzkm(zzfw zzfw) {
        super(zzfw);
    }

    /* access modifiers changed from: protected */
    public final boolean zze() {
        return true;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    /* renamed from: f_ */
    public final void mo24471f_() {
        zzd();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzr().zzi().zza("Utils falling back to Random for random id");
            }
        }
        this.zzd.set(nextLong);
    }

    public final long zzg() {
        long andIncrement;
        if (this.zzd.get() == 0) {
            synchronized (this.zzd) {
                long nextLong = new Random(System.nanoTime() ^ zzm().currentTimeMillis()).nextLong();
                int i = this.zze + 1;
                this.zze = i;
                andIncrement = nextLong + ((long) i);
            }
        } else {
            synchronized (this.zzd) {
                this.zzd.compareAndSet(-1, 1);
                andIncrement = this.zzd.getAndIncrement();
            }
        }
        return andIncrement;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final SecureRandom zzh() {
        zzd();
        if (this.zzc == null) {
            this.zzc = new SecureRandom();
        }
        return this.zzc;
    }

    static boolean zza(String str) {
        Preconditions.checkNotEmpty(str);
        if (str.charAt(0) != '_' || str.equals("_ep")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zza(@NonNull Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        Bundle bundle = null;
        if (uri != null) {
            try {
                if (uri.isHierarchical()) {
                    str4 = uri.getQueryParameter("utm_campaign");
                    str3 = uri.getQueryParameter("utm_source");
                    str2 = uri.getQueryParameter("utm_medium");
                    str = uri.getQueryParameter("gclid");
                } else {
                    str = null;
                    str2 = null;
                    str3 = null;
                    str4 = null;
                }
                if (!TextUtils.isEmpty(str4) || !TextUtils.isEmpty(str3) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
                    bundle = new Bundle();
                    if (!TextUtils.isEmpty(str4)) {
                        bundle.putString(FirebaseAnalytics.Param.CAMPAIGN, str4);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        bundle.putString("source", str3);
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        bundle.putString(FirebaseAnalytics.Param.MEDIUM, str2);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        bundle.putString("gclid", str);
                    }
                    String queryParameter = uri.getQueryParameter("utm_term");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter);
                    }
                    String queryParameter2 = uri.getQueryParameter("utm_content");
                    if (!TextUtils.isEmpty(queryParameter2)) {
                        bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter2);
                    }
                    String queryParameter3 = uri.getQueryParameter(FirebaseAnalytics.Param.ACLID);
                    if (!TextUtils.isEmpty(queryParameter3)) {
                        bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter3);
                    }
                    String queryParameter4 = uri.getQueryParameter(FirebaseAnalytics.Param.CP1);
                    if (!TextUtils.isEmpty(queryParameter4)) {
                        bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter4);
                    }
                    String queryParameter5 = uri.getQueryParameter("anid");
                    if (!TextUtils.isEmpty(queryParameter5)) {
                        bundle.putString("anid", queryParameter5);
                    }
                }
            } catch (UnsupportedOperationException e) {
                zzr().zzi().zza("Install referrer url isn't a hierarchical URI", e);
            }
        }
        return bundle;
    }

    static boolean zza(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(String str, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzr().zzh().zza("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzr().zzh().zza("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzr().zzh().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(String str, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzr().zzh().zza("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzr().zzh().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzr().zzh().zza("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(String str, String[] strArr, String str2) {
        boolean z;
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr2 = zza;
        int length = strArr2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (str2.startsWith(strArr2[i])) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            zzr().zzh().zza("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        } else if (strArr == null || !zza(str2, strArr)) {
            return true;
        } else {
            zzr().zzh().zza("Name is reserved. Type, name", str, str2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(String str, int i, String str2) {
        if (str2 == null) {
            zzr().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            zzr().zzh().zza("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzb(String str) {
        if (!zzb("event", str)) {
            return 2;
        }
        if (!zza("event", zzgv.zza, str)) {
            return 13;
        }
        if (zza("event", 40, str)) {
            return 0;
        }
        return 2;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(String str) {
        if (!zzb("user property", str)) {
            return 6;
        }
        if (!zza("user property", zzgx.zza, str)) {
            return 15;
        }
        if (zza("user property", 24, str)) {
            return 0;
        }
        return 6;
    }

    private final int zzh(String str) {
        if (!zza("event param", str)) {
            return 3;
        }
        if (!zza("event param", (String[]) null, str)) {
            return 14;
        }
        if (zza("event param", 40, str)) {
            return 0;
        }
        return 3;
    }

    private final int zzi(String str) {
        if (!zzb("event param", str)) {
            return 3;
        }
        if (!zza("event param", (String[]) null, str)) {
            return 14;
        }
        if (zza("event param", 40, str)) {
            return 0;
        }
        return 3;
    }

    static boolean zza(Object obj) {
        return (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle);
    }

    private final boolean zzb(String str, String str2, int i, Object obj) {
        int size;
        if (obj instanceof Parcelable[]) {
            size = ((Parcelable[]) obj).length;
        } else if (!(obj instanceof ArrayList)) {
            return true;
        } else {
            size = ((ArrayList) obj).size();
        }
        if (size <= i) {
            return true;
        }
        zzr().zzk().zza("Parameter array is too long; discarded. Value kind, name, array length", str, str2, Integer.valueOf(size));
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(String str, String str2, int i, Object obj) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
            return false;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.codePointCount(0, valueOf.length()) <= i) {
            return true;
        }
        zzr().zzk().zza("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
        return false;
    }

    static boolean zza(Bundle bundle, int i) {
        if (bundle.size() <= i) {
            return false;
        }
        int i2 = 0;
        for (String str : new TreeSet(bundle.keySet())) {
            i2++;
            if (i2 > i) {
                bundle.remove(str);
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.lang.String r16, java.lang.String r17, java.lang.String r18, android.os.Bundle r19, @androidx.annotation.Nullable java.util.List<java.lang.String> r20, boolean r21) {
        /*
            r15 = this;
            if (r19 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            com.google.android.gms.measurement.internal.zzy r2 = r15.zzt()
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzaq.zzci
            boolean r13 = r2.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r3)
            r3 = 0
            if (r13 == 0) goto L_0x0055
            r2 = 0
            r11 = r2
        L_0x0012:
            java.util.TreeSet r2 = new java.util.TreeSet
            java.util.Set r4 = r19.keySet()
            r2.<init>(r4)
            java.util.Iterator r14 = r2.iterator()
            r12 = r3
        L_0x0020:
            boolean r2 = r14.hasNext()
            if (r2 == 0) goto L_0x0002
            java.lang.Object r5 = r14.next()
            java.lang.String r5 = (java.lang.String) r5
            r2 = 0
            if (r20 == 0) goto L_0x0037
            r0 = r20
            boolean r3 = r0.contains(r5)
            if (r3 != 0) goto L_0x011c
        L_0x0037:
            if (r21 == 0) goto L_0x003d
            int r2 = r15.zzh(r5)
        L_0x003d:
            if (r2 != 0) goto L_0x011c
            int r2 = r15.zzi(r5)
            r3 = r2
        L_0x0044:
            if (r3 == 0) goto L_0x0061
            r2 = 3
            if (r3 != r2) goto L_0x005f
            r2 = r5
        L_0x004a:
            r0 = r19
            zza((android.os.Bundle) r0, (int) r3, (java.lang.String) r5, (java.lang.String) r5, (java.lang.Object) r2)
            r0 = r19
            r0.remove(r5)
            goto L_0x0020
        L_0x0055:
            com.google.android.gms.measurement.internal.zzy r2 = r15.zzt()
            int r2 = r2.zze()
            r11 = r2
            goto L_0x0012
        L_0x005f:
            r2 = 0
            goto L_0x004a
        L_0x0061:
            r0 = r19
            java.lang.Object r2 = r0.get(r5)
            boolean r2 = zza((java.lang.Object) r2)
            if (r2 == 0) goto L_0x009b
            com.google.android.gms.measurement.internal.zzes r2 = r15.zzr()
            com.google.android.gms.measurement.internal.zzeu r2 = r2.zzk()
            java.lang.String r3 = "Nested Bundle parameters are not allowed; discarded. event name, param name, child param name"
            r0 = r17
            r1 = r18
            r2.zza(r3, r0, r1, r5)
            r2 = 22
        L_0x0080:
            if (r2 == 0) goto L_0x00b2
            java.lang.String r3 = "_ev"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L_0x00b2
            r0 = r19
            java.lang.Object r3 = r0.get(r5)
            r0 = r19
            zza((android.os.Bundle) r0, (int) r2, (java.lang.String) r5, (java.lang.String) r5, (java.lang.Object) r3)
            r0 = r19
            r0.remove(r5)
            goto L_0x0020
        L_0x009b:
            r0 = r19
            java.lang.Object r6 = r0.get(r5)
            r10 = 0
            r2 = r15
            r3 = r16
            r4 = r17
            r7 = r19
            r8 = r20
            r9 = r21
            int r2 = r2.zza(r3, r4, r5, r6, r7, r8, r9, r10)
            goto L_0x0080
        L_0x00b2:
            boolean r2 = zza((java.lang.String) r5)
            if (r2 == 0) goto L_0x0118
            if (r13 == 0) goto L_0x00c2
            java.lang.String[] r2 = com.google.android.gms.measurement.internal.zzgu.zzd
            boolean r2 = zza((java.lang.String) r5, (java.lang.String[]) r2)
            if (r2 != 0) goto L_0x0118
        L_0x00c2:
            int r2 = r12 + 1
            if (r2 <= r11) goto L_0x0119
            if (r13 == 0) goto L_0x00fa
            java.lang.String r3 = "Item cannot contain custom parameters"
        L_0x00ca:
            com.google.android.gms.measurement.internal.zzes r4 = r15.zzr()
            com.google.android.gms.measurement.internal.zzeu r4 = r4.zzh()
            com.google.android.gms.measurement.internal.zzeq r6 = r15.zzo()
            r0 = r17
            java.lang.String r6 = r6.zza((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzeq r7 = r15.zzo()
            r0 = r19
            java.lang.String r7 = r7.zza((android.os.Bundle) r0)
            r4.zza(r3, r6, r7)
            if (r13 == 0) goto L_0x0116
            r3 = 23
        L_0x00ed:
            r0 = r19
            zzb((android.os.Bundle) r0, (int) r3)
            r0 = r19
            r0.remove(r5)
            r12 = r2
            goto L_0x0020
        L_0x00fa:
            r3 = 63
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Child bundles can't contain more than "
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.StringBuilder r3 = r3.append(r11)
            java.lang.String r4 = " custom params"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            goto L_0x00ca
        L_0x0116:
            r3 = 5
            goto L_0x00ed
        L_0x0118:
            r2 = r12
        L_0x0119:
            r12 = r2
            goto L_0x0020
        L_0x011c:
            r3 = r2
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkm.zza(java.lang.String, java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            if (!zzj(str)) {
                if (!this.zzz.zzl()) {
                    return false;
                }
                zzr().zzh().zza("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzes.zza(str));
                return false;
            }
        } else if (!zzlb.zzb() || !zzt().zza(zzaq.zzbo) || TextUtils.isEmpty(str3)) {
            if (!TextUtils.isEmpty(str2)) {
                if (!zzj(str2)) {
                    zzr().zzh().zza("Invalid admob_app_id. Analytics disabled.", zzes.zza(str2));
                    return false;
                }
            } else if (!this.zzz.zzl()) {
                return false;
            } else {
                zzr().zzh().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
                return false;
            }
        }
        return true;
    }

    static boolean zza(String str, String str2, String str3, String str4) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (isEmpty || isEmpty2) {
            if (!isEmpty || !isEmpty2) {
                if (isEmpty || !isEmpty2) {
                    if (TextUtils.isEmpty(str3) || !str3.equals(str4)) {
                        return true;
                    }
                    return false;
                } else if (TextUtils.isEmpty(str4)) {
                    return false;
                } else {
                    if (TextUtils.isEmpty(str3) || !str3.equals(str4)) {
                        return true;
                    }
                    return false;
                }
            } else if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                if (TextUtils.isEmpty(str4)) {
                    return false;
                }
                return true;
            } else if (str3.equals(str4)) {
                return false;
            } else {
                return true;
            }
        } else if (!str.equals(str2)) {
            return true;
        } else {
            return false;
        }
    }

    @VisibleForTesting
    private static boolean zzj(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    private final Object zza(int i, Object obj, boolean z, boolean z2) {
        Bundle zza2;
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i, z);
            }
            if (!zzjm.zzb() || !zzt().zza(zzaq.zzch) || !zzt().zza(zzaq.zzcg) || !z2 || (!(obj instanceof Bundle[]) && !(obj instanceof Parcelable[]))) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : (Parcelable[]) obj) {
                if ((parcelable instanceof Bundle) && (zza2 = zza((Bundle) parcelable)) != null && !zza2.isEmpty()) {
                    arrayList.add(zza2);
                }
            }
            return arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    public static String zza(String str, int i, boolean z) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
        }
        return null;
    }

    @WorkerThread
    private final int zza(String str, String str2, String str3, Object obj, Bundle bundle, @Nullable List<String> list, boolean z, boolean z2) {
        int i;
        int i2;
        boolean z3;
        zzd();
        if (!zzjm.zzb() || !zzt().zza(zzaq.zzci)) {
            if (z2 && !zzb("param", str3, 1000, obj)) {
                return 17;
            }
            i = 0;
        } else {
            if (zza(obj)) {
                if (!z2) {
                    return 21;
                }
                if (!zza(str3, zzgu.zzc)) {
                    return 20;
                }
                if (!this.zzz.zzw().zzai()) {
                    return 25;
                }
                if (!zzb("param", str3, HttpResponse.f88OK, obj)) {
                    if (obj instanceof Parcelable[]) {
                        if (((Parcelable[]) obj).length > 200) {
                            bundle.putParcelableArray(str3, (Parcelable[]) Arrays.copyOf((Parcelable[]) obj, HttpResponse.f88OK));
                            i = 17;
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) obj;
                        if (arrayList.size() > 200) {
                            bundle.putParcelableArrayList(str3, new ArrayList(arrayList.subList(0, HttpResponse.f88OK)));
                        }
                    }
                    i = 17;
                }
            }
            i = 0;
        }
        if ((!zzt().zze(str, zzaq.zzaq) || !zze(str2)) && !zze(str3)) {
            i2 = 100;
        } else {
            i2 = 256;
        }
        if (zza("param", str3, i2, obj)) {
            return i;
        }
        if (z2) {
            boolean z4 = zzjm.zzb() && zzt().zza(zzaq.zzch);
            if (!(obj instanceof Bundle)) {
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    int length = parcelableArr.length;
                    int i3 = 0;
                    while (i3 < length) {
                        Parcelable parcelable = parcelableArr[i3];
                        if (!(parcelable instanceof Bundle)) {
                            zzr().zzk().zza("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str3);
                        } else {
                            if (z4) {
                                zza(str, str2, str3, (Bundle) parcelable, list, z);
                            }
                            i3++;
                        }
                    }
                    z3 = true;
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList2 = (ArrayList) obj;
                    int size = arrayList2.size();
                    int i4 = 0;
                    while (i4 < size) {
                        Object obj2 = arrayList2.get(i4);
                        int i5 = i4 + 1;
                        if (!(obj2 instanceof Bundle)) {
                            zzr().zzk().zza("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str3);
                        } else {
                            if (z4) {
                                zza(str, str2, str3, (Bundle) obj2, list, z);
                            }
                            i4 = i5;
                        }
                    }
                    z3 = true;
                }
                z3 = false;
                break;
            } else {
                if (z4) {
                    zza(str, str2, str3, (Bundle) obj, list, z);
                }
                z3 = true;
            }
            if (!z3) {
                return 4;
            }
            return i;
        }
        return 4;
    }

    /* access modifiers changed from: package-private */
    public final Object zza(String str, Object obj) {
        int i = 256;
        if ("_ev".equals(str)) {
            return zza(256, obj, true, true);
        }
        if (!zze(str)) {
            i = 100;
        }
        return zza(i, obj, false, true);
    }

    static Bundle[] zzb(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        } else if (obj instanceof Parcelable[]) {
            return (Bundle[]) Arrays.copyOf((Parcelable[]) obj, ((Parcelable[]) obj).length, Bundle[].class);
        } else {
            if (!(obj instanceof ArrayList)) {
                return null;
            }
            ArrayList arrayList = (ArrayList) obj;
            return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zza(java.lang.String r15, java.lang.String r16, android.os.Bundle r17, @androidx.annotation.Nullable java.util.List<java.lang.String> r18, boolean r19, boolean r20) {
        /*
            r14 = this;
            boolean r1 = com.google.android.gms.internal.measurement.zzjm.zzb()
            if (r1 == 0) goto L_0x007c
            com.google.android.gms.measurement.internal.zzy r1 = r14.zzt()
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzaq.zzci
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzel<java.lang.Boolean>) r2)
            if (r1 == 0) goto L_0x007c
            r1 = 1
            r11 = r1
        L_0x0014:
            if (r11 == 0) goto L_0x007f
            java.lang.String[] r1 = com.google.android.gms.measurement.internal.zzgv.zzc
            r0 = r16
            boolean r9 = zza((java.lang.String) r0, (java.lang.String[]) r1)
        L_0x001e:
            r6 = 0
            if (r17 == 0) goto L_0x011b
            android.os.Bundle r6 = new android.os.Bundle
            r0 = r17
            r6.<init>(r0)
            r2 = 0
            com.google.android.gms.measurement.internal.zzy r1 = r14.zzt()
            int r12 = r1.zze()
            com.google.android.gms.measurement.internal.zzy r1 = r14.zzt()
            com.google.android.gms.measurement.internal.zzel<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzaq.zzbb
            boolean r1 = r1.zze(r15, r3)
            if (r1 == 0) goto L_0x0082
            java.util.TreeSet r1 = new java.util.TreeSet
            java.util.Set r3 = r17.keySet()
            r1.<init>(r3)
        L_0x0046:
            java.util.Iterator r13 = r1.iterator()
            r10 = r2
        L_0x004b:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto L_0x011b
            java.lang.Object r4 = r13.next()
            java.lang.String r4 = (java.lang.String) r4
            r1 = 0
            if (r18 == 0) goto L_0x0062
            r0 = r18
            boolean r2 = r0.contains(r4)
            if (r2 != 0) goto L_0x011c
        L_0x0062:
            if (r19 == 0) goto L_0x0068
            int r1 = r14.zzh(r4)
        L_0x0068:
            if (r1 != 0) goto L_0x011c
            int r1 = r14.zzi(r4)
            r2 = r1
        L_0x006f:
            if (r2 == 0) goto L_0x0089
            r1 = 3
            if (r2 != r1) goto L_0x0087
            r1 = r4
        L_0x0075:
            zza((android.os.Bundle) r6, (int) r2, (java.lang.String) r4, (java.lang.String) r4, (java.lang.Object) r1)
            r6.remove(r4)
            goto L_0x004b
        L_0x007c:
            r1 = 0
            r11 = r1
            goto L_0x0014
        L_0x007f:
            r9 = r20
            goto L_0x001e
        L_0x0082:
            java.util.Set r1 = r17.keySet()
            goto L_0x0046
        L_0x0087:
            r1 = 0
            goto L_0x0075
        L_0x0089:
            r0 = r17
            java.lang.Object r5 = r0.get(r4)
            r1 = r14
            r2 = r15
            r3 = r16
            r7 = r18
            r8 = r19
            int r2 = r1.zza(r2, r3, r4, r5, r6, r7, r8, r9)
            if (r11 == 0) goto L_0x00f7
            r1 = 17
            if (r2 != r1) goto L_0x00f7
            r1 = 0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            zza((android.os.Bundle) r6, (int) r2, (java.lang.String) r4, (java.lang.String) r4, (java.lang.Object) r1)
        L_0x00a9:
            boolean r1 = zza((java.lang.String) r4)
            if (r1 == 0) goto L_0x0117
            int r1 = r10 + 1
            if (r1 <= r12) goto L_0x0118
            r2 = 48
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Event can't contain more than "
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.StringBuilder r2 = r2.append(r12)
            java.lang.String r3 = " params"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.google.android.gms.measurement.internal.zzes r3 = r14.zzr()
            com.google.android.gms.measurement.internal.zzeu r3 = r3.zzh()
            com.google.android.gms.measurement.internal.zzeq r5 = r14.zzo()
            r0 = r16
            java.lang.String r5 = r5.zza((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzeq r7 = r14.zzo()
            r0 = r17
            java.lang.String r7 = r7.zza((android.os.Bundle) r0)
            r3.zza(r2, r5, r7)
            r2 = 5
            zzb((android.os.Bundle) r6, (int) r2)
            r6.remove(r4)
            r10 = r1
            goto L_0x004b
        L_0x00f7:
            if (r2 == 0) goto L_0x00a9
            java.lang.String r1 = "_ev"
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x00a9
            r1 = 21
            if (r2 != r1) goto L_0x0115
            r1 = r16
        L_0x0107:
            r0 = r17
            java.lang.Object r3 = r0.get(r4)
            zza((android.os.Bundle) r6, (int) r2, (java.lang.String) r1, (java.lang.String) r4, (java.lang.Object) r3)
            r6.remove(r4)
            goto L_0x004b
        L_0x0115:
            r1 = r4
            goto L_0x0107
        L_0x0117:
            r1 = r10
        L_0x0118:
            r10 = r1
            goto L_0x004b
        L_0x011b:
            return r6
        L_0x011c:
            r2 = r1
            goto L_0x006f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkm.zza(java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean, boolean):android.os.Bundle");
    }

    private static void zza(Bundle bundle, int i, String str, String str2, Object obj) {
        if (zzb(bundle, i)) {
            bundle.putString("_ev", zza(str, 40, true));
            if (obj != null) {
                Preconditions.checkNotNull(bundle);
                if (obj == null) {
                    return;
                }
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    bundle.putLong("_el", (long) String.valueOf(obj).length());
                }
            }
        }
    }

    static boolean zzb(Bundle bundle, int i) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    private final int zzk(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        if (APEZProvider.FILEID.equals(str)) {
            return 256;
        }
        if (!zzt().zza(zzaq.zzbl) || !"_lgclid".equals(str)) {
            return 36;
        }
        return 100;
    }

    /* access modifiers changed from: package-private */
    public final int zzb(String str, Object obj) {
        boolean zza2;
        if ("_ldl".equals(str)) {
            zza2 = zza("user property referrer", str, zzk(str), obj);
        } else {
            zza2 = zza("user property", str, zzk(str), obj);
        }
        return zza2 ? 0 : 7;
    }

    /* access modifiers changed from: package-private */
    public final Object zzc(String str, Object obj) {
        if ("_ldl".equals(str)) {
            return zza(zzk(str), obj, true, false);
        }
        return zza(zzk(str), obj, false, false);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (zzjm.zzb() && zzt().zza(zzaq.zzch) && zzt().zza(zzaq.zzcg) && (obj instanceof Bundle[])) {
                bundle.putParcelableArray(str, (Bundle[]) obj);
            } else if (str != null) {
                zzr().zzk().zza("Not putting event parameter. Invalid value type. name, type", zzo().zzb(str), obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza((String) null, i, str, str2, i2);
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzb(bundle, i);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzz.zzu();
        this.zzz.zzh().zza("auto", "_err", bundle);
    }

    static MessageDigest zzi() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 2) {
                return null;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (instance != null) {
                    return instance;
                }
                i = i2 + 1;
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    @VisibleForTesting
    static long zza(byte[] bArr) {
        int i = 0;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkState(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j += (((long) bArr[length]) & 255) << i;
            i += 8;
            length--;
        }
        return j;
    }

    static boolean zza(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        if (Build.VERSION.SDK_INT >= 24) {
            return zzb(context, "com.google.android.gms.measurement.AppMeasurementJobService");
        }
        return zzb(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    private static boolean zzb(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0)) == null || !serviceInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zzd(String str) {
        zzd();
        if (Wrappers.packageManager(zzn()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzr().zzw().zza("Permission not granted", str);
        return false;
    }

    static boolean zze(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    static boolean zzc(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    static boolean zza(Boolean bool, Boolean bool2) {
        if (bool == null && bool2 == null) {
            return true;
        }
        if (bool == null) {
            return false;
        }
        return bool.equals(bool2);
    }

    static boolean zza(@Nullable List<String> list, @Nullable List<String> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        return list.equals(list2);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzw = zzt().zzw();
        zzu();
        return zzw.equals(str);
    }

    /* access modifiers changed from: package-private */
    public final Bundle zza(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zza2 = zza(str, bundle.get(str));
                if (zza2 == null) {
                    zzr().zzk().zza("Param value can't be null", zzo().zzb(str));
                } else {
                    zza(bundle2, str, zza2);
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: package-private */
    public final zzao zza(String str, String str2, Bundle bundle, String str3, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zzb(str2) != 0) {
            zzr().zzf().zza("Invalid conditional property event name", zzo().zzc(str2));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str3);
        return new zzao(str2, new zzan(zza(zza(str, str2, bundle2, (List<String>) CollectionUtils.listOf("_o"), false, false))), str3, j);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final long zza(Context context, String str) {
        zzd();
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzi = zzi();
        if (zzi == null) {
            zzr().zzf().zza("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzc(context, str)) {
                    PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(zzn().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return zza(zzi.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzr().zzi().zza("Could not get signatures");
                    return -1;
                }
            } catch (PackageManager.NameNotFoundException e) {
                zzr().zzf().zza("Package name not found", e);
            }
        }
        return 0;
    }

    @VisibleForTesting
    private final boolean zzc(Context context, String str) {
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e) {
            zzr().zzf().zza("Error obtaining certificate", e);
        } catch (PackageManager.NameNotFoundException e2) {
            zzr().zzf().zza("Package name not found", e2);
        }
        return true;
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public static Bundle zzb(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str, new Bundle((Bundle) obj));
            } else if (obj instanceof Parcelable[]) {
                Parcelable[] parcelableArr = (Parcelable[]) obj;
                for (int i = 0; i < parcelableArr.length; i++) {
                    if (parcelableArr[i] instanceof Bundle) {
                        parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                    }
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    Object obj2 = list.get(i2);
                    if (obj2 instanceof Bundle) {
                        list.set(i2, new Bundle((Bundle) obj2));
                    }
                }
            }
        }
        return bundle2;
    }

    private static boolean zza(String str, String[] strArr) {
        Preconditions.checkNotNull(strArr);
        for (String zzc2 : strArr) {
            if (zzc(str, zzc2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean zzg(String str) {
        for (String equals : zzb) {
            if (equals.equals(str)) {
                return false;
            }
        }
        return true;
    }

    public final int zzj() {
        if (this.zzf == null) {
            this.zzf = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(zzn()) / 1000);
        }
        return this.zzf.intValue();
    }

    public final int zza(int i) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(zzn(), 12451000);
    }

    public static long zza(long j, long j2) {
        return ((OkGo.DEFAULT_MILLISECONDS * j2) + j) / 86400000;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzk() {
        byte[] bArr = new byte[16];
        zzh().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(Bundle bundle, long j) {
        long j2 = bundle.getLong("_et");
        if (j2 != 0) {
            zzr().zzi().zza("Params already contained engagement", Long.valueOf(j2));
        }
        bundle.putLong("_et", j2 + j);
    }

    public final void zza(zzp zzp, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzp.zza(bundle);
        } catch (RemoteException e) {
            this.zzz.zzr().zzi().zza("Error returning string value to wrapper", e);
        }
    }

    public final void zza(zzp zzp, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j);
        try {
            zzp.zza(bundle);
        } catch (RemoteException e) {
            this.zzz.zzr().zzi().zza("Error returning long value to wrapper", e);
        }
    }

    public final void zza(zzp zzp, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i);
        try {
            zzp.zza(bundle);
        } catch (RemoteException e) {
            this.zzz.zzr().zzi().zza("Error returning int value to wrapper", e);
        }
    }

    public final void zza(zzp zzp, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzp.zza(bundle);
        } catch (RemoteException e) {
            this.zzz.zzr().zzi().zza("Error returning byte array to wrapper", e);
        }
    }

    public final void zza(zzp zzp, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z);
        try {
            zzp.zza(bundle);
        } catch (RemoteException e) {
            this.zzz.zzr().zzi().zza("Error returning boolean value to wrapper", e);
        }
    }

    public final void zza(zzp zzp, Bundle bundle) {
        try {
            zzp.zza(bundle);
        } catch (RemoteException e) {
            this.zzz.zzr().zzi().zza("Error returning bundle value to wrapper", e);
        }
    }

    public static Bundle zza(List<zzkh> list) {
        Bundle bundle = new Bundle();
        if (list == null) {
            return bundle;
        }
        for (zzkh next : list) {
            if (next.zzd != null) {
                bundle.putString(next.zza, next.zzd);
            } else if (next.zzc != null) {
                bundle.putLong(next.zza, next.zzc.longValue());
            } else if (next.zzf != null) {
                bundle.putDouble(next.zza, next.zzf.doubleValue());
            }
        }
        return bundle;
    }

    public final void zza(zzp zzp, ArrayList<Bundle> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zzp.zza(bundle);
        } catch (RemoteException e) {
            this.zzz.zzr().zzi().zza("Error returning bundle list to wrapper", e);
        }
    }

    public static ArrayList<Bundle> zzb(List<zzw> list) {
        if (list == null) {
            return new ArrayList<>(0);
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (zzw next : list) {
            Bundle bundle = new Bundle();
            bundle.putString("app_id", next.zza);
            bundle.putString("origin", next.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, next.zzd);
            bundle.putString("name", next.zzc.zza);
            zzgs.zza(bundle, next.zzc.zza());
            bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, next.zze);
            if (next.zzf != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, next.zzf);
            }
            if (next.zzg != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, next.zzg.zza);
                if (next.zzg.zzb != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, next.zzg.zzb.zzb());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, next.zzh);
            if (next.zzi != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, next.zzi.zza);
                if (next.zzi.zzb != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, next.zzi.zzb.zzb());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, next.zzc.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, next.zzj);
            if (next.zzk != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, next.zzk.zza);
                if (next.zzk.zzb != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, next.zzk.zzb.zzb());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    public final URL zza(long j, @NonNull String str, @NonNull String str2, long j2) {
        String str3;
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            String format = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", new Object[]{String.format("v%s.%s", new Object[]{Long.valueOf(j), Integer.valueOf(zzj())}), str2, str, Long.valueOf(j2)});
            if (str.equals(zzt().zzx())) {
                str3 = format.concat("&ddl_test=1");
            } else {
                str3 = format;
            }
            return new URL(str3);
        } catch (IllegalArgumentException | MalformedURLException e) {
            zzr().zzf().zza("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"ApplySharedPref"})
    public final boolean zza(String str, double d) {
        try {
            SharedPreferences.Editor edit = zzn().getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
            edit.putString("deeplink", str);
            edit.putLong("timestamp", Double.doubleToRawLongBits(d));
            return edit.commit();
        } catch (Exception e) {
            zzr().zzf().zza("Failed to persist Deferred Deep Link. exception", e);
            return false;
        }
    }

    public final boolean zzv() {
        try {
            zzn().getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static long zza(zzan zzan) {
        long j = 0;
        if (zzan == null) {
            return 0;
        }
        Iterator<String> it = zzan.iterator();
        while (true) {
            long j2 = j;
            if (!it.hasNext()) {
                return j2;
            }
            Object zza2 = zzan.zza(it.next());
            if (zza2 instanceof Parcelable[]) {
                j = ((long) ((Parcelable[]) zza2).length) + j2;
            } else {
                j = j2;
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzeq zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzkm zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzft zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzes zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzfe zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
