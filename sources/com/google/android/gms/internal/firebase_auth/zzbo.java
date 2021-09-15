package com.google.android.gms.internal.firebase_auth;

import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzbo<K, V> extends zzbk<K, V> {
    static final zzbk<Object, Object> zza = new zzbo((Object) null, new Object[0], 0);
    private final transient Object zzb;
    private final transient Object[] zzc;
    private final transient int zzd;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: short[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: short[]} */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0094, code lost:
        r1[r0] = (byte) r4;
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d1, code lost:
        r1[r0] = (short) r4;
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0109, code lost:
        r1[r0] = r4;
        r2 = r2 + 1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <K, V> com.google.android.gms.internal.firebase_auth.zzbo<K, V> zza(int r12, java.lang.Object[] r13) {
        /*
            r3 = 1073741824(0x40000000, float:2.0)
            r11 = 65535(0xffff, float:9.1834E-41)
            r10 = -1
            r1 = 1
            r2 = 0
            if (r12 != 0) goto L_0x000f
            com.google.android.gms.internal.firebase_auth.zzbk<java.lang.Object, java.lang.Object> r0 = zza
            com.google.android.gms.internal.firebase_auth.zzbo r0 = (com.google.android.gms.internal.firebase_auth.zzbo) r0
        L_0x000e:
            return r0
        L_0x000f:
            if (r12 != r1) goto L_0x001f
            r0 = r13[r2]
            r2 = r13[r1]
            com.google.android.gms.internal.firebase_auth.zzbe.zza(r0, r2)
            com.google.android.gms.internal.firebase_auth.zzbo r0 = new com.google.android.gms.internal.firebase_auth.zzbo
            r2 = 0
            r0.<init>(r2, r13, r1)
            goto L_0x000e
        L_0x001f:
            int r0 = r13.length
            int r0 = r0 >> 1
            com.google.android.gms.internal.firebase_auth.zzav.zzb(r12, r0)
            r0 = 2
            int r4 = java.lang.Math.max(r12, r0)
            r0 = 751619276(0x2ccccccc, float:5.8207657E-12)
            if (r4 >= r0) goto L_0x0046
            int r0 = r4 + -1
            int r0 = java.lang.Integer.highestOneBit(r0)
            int r0 = r0 << 1
        L_0x0037:
            double r6 = (double) r0
            r8 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            double r6 = r6 * r8
            double r8 = (double) r4
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x005a
            int r0 = r0 << 1
            goto L_0x0037
        L_0x0046:
            if (r4 >= r3) goto L_0x0057
            r0 = r1
        L_0x0049:
            java.lang.String r4 = "collection too large"
            if (r0 != 0) goto L_0x0059
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = java.lang.String.valueOf(r4)
            r0.<init>(r1)
            throw r0
        L_0x0057:
            r0 = r2
            goto L_0x0049
        L_0x0059:
            r0 = r3
        L_0x005a:
            if (r12 != r1) goto L_0x006b
            r0 = r13[r2]
            r1 = r13[r1]
            com.google.android.gms.internal.firebase_auth.zzbe.zza(r0, r1)
            r0 = 0
        L_0x0064:
            com.google.android.gms.internal.firebase_auth.zzbo r1 = new com.google.android.gms.internal.firebase_auth.zzbo
            r1.<init>(r0, r13, r12)
            r0 = r1
            goto L_0x000e
        L_0x006b:
            int r3 = r0 + -1
            r1 = 128(0x80, float:1.794E-43)
            if (r0 > r1) goto L_0x00ac
            byte[] r1 = new byte[r0]
            java.util.Arrays.fill(r1, r10)
        L_0x0076:
            if (r2 >= r12) goto L_0x00aa
            int r4 = r2 * 2
            r5 = r13[r4]
            r0 = r4 ^ 1
            r6 = r13[r0]
            com.google.android.gms.internal.firebase_auth.zzbe.zza(r5, r6)
            int r0 = r5.hashCode()
            int r0 = com.google.android.gms.internal.firebase_auth.zzbh.zza(r0)
        L_0x008b:
            r0 = r0 & r3
            byte r7 = r1[r0]
            r7 = r7 & 255(0xff, float:3.57E-43)
            r8 = 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L_0x009a
            byte r4 = (byte) r4
            r1[r0] = r4
            int r2 = r2 + 1
            goto L_0x0076
        L_0x009a:
            r8 = r13[r7]
            boolean r8 = r8.equals(r5)
            if (r8 == 0) goto L_0x00a7
            java.lang.IllegalArgumentException r0 = zza(r5, r6, r13, r7)
            throw r0
        L_0x00a7:
            int r0 = r0 + 1
            goto L_0x008b
        L_0x00aa:
            r0 = r1
            goto L_0x0064
        L_0x00ac:
            r1 = 32768(0x8000, float:4.5918E-41)
            if (r0 > r1) goto L_0x00ea
            short[] r1 = new short[r0]
            java.util.Arrays.fill(r1, r10)
        L_0x00b6:
            if (r2 >= r12) goto L_0x00e7
            int r4 = r2 * 2
            r5 = r13[r4]
            r0 = r4 ^ 1
            r6 = r13[r0]
            com.google.android.gms.internal.firebase_auth.zzbe.zza(r5, r6)
            int r0 = r5.hashCode()
            int r0 = com.google.android.gms.internal.firebase_auth.zzbh.zza(r0)
        L_0x00cb:
            r0 = r0 & r3
            short r7 = r1[r0]
            r7 = r7 & r11
            if (r7 != r11) goto L_0x00d7
            short r4 = (short) r4
            r1[r0] = r4
            int r2 = r2 + 1
            goto L_0x00b6
        L_0x00d7:
            r8 = r13[r7]
            boolean r8 = r8.equals(r5)
            if (r8 == 0) goto L_0x00e4
            java.lang.IllegalArgumentException r0 = zza(r5, r6, r13, r7)
            throw r0
        L_0x00e4:
            int r0 = r0 + 1
            goto L_0x00cb
        L_0x00e7:
            r0 = r1
            goto L_0x0064
        L_0x00ea:
            int[] r1 = new int[r0]
            java.util.Arrays.fill(r1, r10)
        L_0x00ef:
            if (r2 >= r12) goto L_0x011e
            int r4 = r2 * 2
            r5 = r13[r4]
            r0 = r4 ^ 1
            r6 = r13[r0]
            com.google.android.gms.internal.firebase_auth.zzbe.zza(r5, r6)
            int r0 = r5.hashCode()
            int r0 = com.google.android.gms.internal.firebase_auth.zzbh.zza(r0)
        L_0x0104:
            r0 = r0 & r3
            r7 = r1[r0]
            if (r7 != r10) goto L_0x010e
            r1[r0] = r4
            int r2 = r2 + 1
            goto L_0x00ef
        L_0x010e:
            r8 = r13[r7]
            boolean r8 = r8.equals(r5)
            if (r8 == 0) goto L_0x011b
            java.lang.IllegalArgumentException r0 = zza(r5, r6, r13, r7)
            throw r0
        L_0x011b:
            int r0 = r0 + 1
            goto L_0x0104
        L_0x011e:
            r0 = r1
            goto L_0x0064
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzbo.zza(int, java.lang.Object[]):com.google.android.gms.internal.firebase_auth.zzbo");
    }

    private static IllegalArgumentException zza(Object obj, Object obj2, Object[] objArr, int i) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[i]);
        String valueOf4 = String.valueOf(objArr[i ^ 1]);
        return new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 39 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length()).append("Multiple entries with same key: ").append(valueOf).append("=").append(valueOf2).append(" and ").append(valueOf3).append("=").append(valueOf4).toString());
    }

    private zzbo(Object obj, Object[] objArr, int i) {
        this.zzb = obj;
        this.zzc = objArr;
        this.zzd = i;
    }

    public final int size() {
        return this.zzd;
    }

    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzb;
        V[] vArr = this.zzc;
        int i = this.zzd;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (vArr[0].equals(obj)) {
                return vArr[1];
            }
            return null;
        } else if (obj2 == null) {
            return null;
        } else {
            if (obj2 instanceof byte[]) {
                byte[] bArr = (byte[]) obj2;
                int length = bArr.length - 1;
                int zza2 = zzbh.zza(obj.hashCode());
                while (true) {
                    int i2 = zza2 & length;
                    byte b = bArr[i2] & 255;
                    if (b == 255) {
                        return null;
                    }
                    if (vArr[b].equals(obj)) {
                        return vArr[b ^ 1];
                    }
                    zza2 = i2 + 1;
                }
            } else if (obj2 instanceof short[]) {
                short[] sArr = (short[]) obj2;
                int length2 = sArr.length - 1;
                int zza3 = zzbh.zza(obj.hashCode());
                while (true) {
                    int i3 = zza3 & length2;
                    short s = sArr[i3] & 65535;
                    if (s == 65535) {
                        return null;
                    }
                    if (vArr[s].equals(obj)) {
                        return vArr[s ^ 1];
                    }
                    zza3 = i3 + 1;
                }
            } else {
                int[] iArr = (int[]) obj2;
                int length3 = iArr.length - 1;
                int zza4 = zzbh.zza(obj.hashCode());
                while (true) {
                    int i4 = zza4 & length3;
                    int i5 = iArr[i4];
                    if (i5 == -1) {
                        return null;
                    }
                    if (vArr[i5].equals(obj)) {
                        return vArr[i5 ^ 1];
                    }
                    zza4 = i4 + 1;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final zzbm<Map.Entry<K, V>> zzb() {
        return new zzbr(this, this.zzc, 0, this.zzd);
    }

    /* access modifiers changed from: package-private */
    public final zzbm<K> zzc() {
        return new zzbt(this, new zzbs(this.zzc, 0, this.zzd));
    }

    /* access modifiers changed from: package-private */
    public final zzbg<V> zzd() {
        return new zzbs(this.zzc, 1, this.zzd);
    }
}
