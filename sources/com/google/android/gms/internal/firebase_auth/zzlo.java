package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzlo extends zzlj {
    zzlo() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b4, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ea, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(int r9, byte[] r10, int r11, int r12) {
        /*
            r8 = this;
            r0 = r11 | r12
            int r1 = r10.length
            int r1 = r1 - r12
            r0 = r0 | r1
            if (r0 >= 0) goto L_0x002c
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.String r1 = "Array length=%d, index=%d, limit=%d"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            int r4 = r10.length
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            r3 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r11)
            r2[r3] = r4
            r3 = 2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)
            r2[r3] = r4
            java.lang.String r1 = java.lang.String.format(r1, r2)
            r0.<init>(r1)
            throw r0
        L_0x002c:
            long r4 = (long) r11
            long r0 = (long) r12
            long r0 = r0 - r4
            int r1 = (int) r0
            r0 = 16
            if (r1 >= r0) goto L_0x004a
            r0 = 0
        L_0x0035:
            int r1 = r1 - r0
            long r2 = (long) r0
            long r2 = r2 + r4
            r0 = r1
        L_0x0039:
            r1 = 0
            r4 = r2
        L_0x003b:
            if (r0 <= 0) goto L_0x005e
            r2 = 1
            long r2 = r2 + r4
            byte r1 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r10, (long) r4)
            if (r1 < 0) goto L_0x005d
            int r0 = r0 + -1
            r4 = r2
            goto L_0x003b
        L_0x004a:
            r0 = 0
            r2 = r4
        L_0x004c:
            if (r0 >= r1) goto L_0x005b
            r6 = 1
            long r6 = r6 + r2
            byte r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r10, (long) r2)
            if (r2 < 0) goto L_0x0035
            int r0 = r0 + 1
            r2 = r6
            goto L_0x004c
        L_0x005b:
            r0 = r1
            goto L_0x0035
        L_0x005d:
            r4 = r2
        L_0x005e:
            if (r0 != 0) goto L_0x0062
            r0 = 0
        L_0x0061:
            return r0
        L_0x0062:
            int r0 = r0 + -1
            r2 = -32
            if (r1 >= r2) goto L_0x007f
            if (r0 != 0) goto L_0x006c
            r0 = r1
            goto L_0x0061
        L_0x006c:
            int r0 = r0 + -1
            r2 = -62
            if (r1 < r2) goto L_0x007d
            r2 = 1
            long r2 = r2 + r4
            byte r1 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r10, (long) r4)
            r4 = -65
            if (r1 <= r4) goto L_0x0039
        L_0x007d:
            r0 = -1
            goto L_0x0061
        L_0x007f:
            r2 = -16
            if (r1 >= r2) goto L_0x00b6
            r2 = 2
            if (r0 >= r2) goto L_0x008b
            int r0 = zza((byte[]) r10, (int) r1, (long) r4, (int) r0)
            goto L_0x0061
        L_0x008b:
            int r0 = r0 + -2
            r2 = 1
            long r6 = r4 + r2
            byte r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r10, (long) r4)
            r3 = -65
            if (r2 > r3) goto L_0x00b4
            r3 = -32
            if (r1 != r3) goto L_0x00a1
            r3 = -96
            if (r2 < r3) goto L_0x00b4
        L_0x00a1:
            r3 = -19
            if (r1 != r3) goto L_0x00a9
            r1 = -96
            if (r2 >= r1) goto L_0x00b4
        L_0x00a9:
            r2 = 1
            long r2 = r2 + r6
            byte r1 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r10, (long) r6)
            r4 = -65
            if (r1 <= r4) goto L_0x0039
        L_0x00b4:
            r0 = -1
            goto L_0x0061
        L_0x00b6:
            r2 = 3
            if (r0 >= r2) goto L_0x00be
            int r0 = zza((byte[]) r10, (int) r1, (long) r4, (int) r0)
            goto L_0x0061
        L_0x00be:
            int r0 = r0 + -3
            r2 = 1
            long r2 = r2 + r4
            byte r4 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r10, (long) r4)
            r5 = -65
            if (r4 > r5) goto L_0x00ea
            int r1 = r1 << 28
            int r4 = r4 + 112
            int r1 = r1 + r4
            int r1 = r1 >> 30
            if (r1 != 0) goto L_0x00ea
            r4 = 1
            long r4 = r4 + r2
            byte r1 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r10, (long) r2)
            r2 = -65
            if (r1 > r2) goto L_0x00ea
            r2 = 1
            long r2 = r2 + r4
            byte r1 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r10, (long) r4)
            r4 = -65
            if (r1 <= r4) goto L_0x0039
        L_0x00ea:
            r0 = -1
            goto L_0x0061
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzlo.zza(int, byte[], int, int):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: CFG modification limit reached, blocks count: 145 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzb(byte[] r13, int r14, int r15) throws com.google.android.gms.internal.firebase_auth.zzir {
        /*
            r12 = this;
            r7 = 0
            r0 = r14 | r15
            int r1 = r13.length
            int r1 = r1 - r14
            int r1 = r1 - r15
            r0 = r0 | r1
            if (r0 >= 0) goto L_0x002d
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.String r1 = "buffer length=%d, index=%d, size=%d"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            int r3 = r13.length
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r7] = r3
            r3 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)
            r2[r3] = r4
            r3 = 2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r15)
            r2[r3] = r4
            java.lang.String r1 = java.lang.String.format(r1, r2)
            r0.<init>(r1)
            throw r0
        L_0x002d:
            int r8 = r14 + r15
            char[] r4 = new char[r15]
            r5 = r7
            r1 = r14
        L_0x0033:
            if (r1 >= r8) goto L_0x00e2
            long r2 = (long) r1
            byte r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r2)
            boolean r0 = com.google.android.gms.internal.firebase_auth.zzlk.zzd(r2)
            if (r0 == 0) goto L_0x00e2
            int r1 = r1 + 1
            int r0 = r5 + 1
            com.google.android.gms.internal.firebase_auth.zzlk.zzb(r2, r4, r5)
            r5 = r0
            goto L_0x0033
        L_0x0049:
            int r2 = r1 + 1
            long r10 = (long) r1
            byte r3 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r10)
            int r1 = r5 + 1
            com.google.android.gms.internal.firebase_auth.zzlk.zzb(r0, r3, r4, r5)
            r5 = r1
        L_0x0056:
            if (r2 >= r8) goto L_0x00dc
            int r1 = r2 + 1
            long r2 = (long) r2
            byte r0 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r2)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzlk.zzd(r0)
            if (r2 == 0) goto L_0x0081
            int r2 = r5 + 1
            com.google.android.gms.internal.firebase_auth.zzlk.zzb(r0, r4, r5)
            r0 = r2
        L_0x006b:
            if (r1 >= r8) goto L_0x00d8
            long r2 = (long) r1
            byte r3 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r2)
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzlk.zzd(r3)
            if (r2 == 0) goto L_0x00d8
            int r1 = r1 + 1
            int r2 = r0 + 1
            com.google.android.gms.internal.firebase_auth.zzlk.zzb(r3, r4, r0)
            r0 = r2
            goto L_0x006b
        L_0x0081:
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzlk.zze(r0)
            if (r2 == 0) goto L_0x008e
            if (r1 < r8) goto L_0x0049
            com.google.android.gms.internal.firebase_auth.zzir r0 = com.google.android.gms.internal.firebase_auth.zzir.zzi()
            throw r0
        L_0x008e:
            boolean r2 = com.google.android.gms.internal.firebase_auth.zzlk.zzf(r0)
            if (r2 == 0) goto L_0x00b2
            int r2 = r8 + -1
            if (r1 < r2) goto L_0x009d
            com.google.android.gms.internal.firebase_auth.zzir r0 = com.google.android.gms.internal.firebase_auth.zzir.zzi()
            throw r0
        L_0x009d:
            int r3 = r1 + 1
            long r10 = (long) r1
            byte r6 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r10)
            int r2 = r3 + 1
            long r10 = (long) r3
            byte r3 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r10)
            int r1 = r5 + 1
            com.google.android.gms.internal.firebase_auth.zzlk.zzb(r0, r6, r3, r4, r5)
            r5 = r1
            goto L_0x0056
        L_0x00b2:
            int r2 = r8 + -2
            if (r1 < r2) goto L_0x00bb
            com.google.android.gms.internal.firebase_auth.zzir r0 = com.google.android.gms.internal.firebase_auth.zzir.zzi()
            throw r0
        L_0x00bb:
            int r2 = r1 + 1
            long r10 = (long) r1
            byte r1 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r10)
            int r3 = r2 + 1
            long r10 = (long) r2
            byte r2 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r10)
            int r6 = r3 + 1
            long r10 = (long) r3
            byte r3 = com.google.android.gms.internal.firebase_auth.zzlf.zza((byte[]) r13, (long) r10)
            int r9 = r5 + 1
            com.google.android.gms.internal.firebase_auth.zzlk.zzb(r0, r1, r2, r3, r4, r5)
            int r0 = r9 + 1
            r1 = r6
        L_0x00d8:
            r5 = r0
            r2 = r1
            goto L_0x0056
        L_0x00dc:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4, r7, r5)
            return r0
        L_0x00e2:
            r2 = r1
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzlo.zzb(byte[], int, int):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public final int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j;
        long j2 = (long) i;
        long j3 = j2 + ((long) i2);
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(length - 1)).append(" at index ").append(i + i2).toString());
        }
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt >= 128) {
                break;
            }
            zzlf.zza(bArr, j2, (byte) charAt);
            i3++;
            j2 = 1 + j2;
        }
        if (i3 == length) {
            return (int) j2;
        }
        long j4 = j2;
        while (i3 < length) {
            char charAt2 = charSequence.charAt(i3);
            if (charAt2 < 128 && j4 < j3) {
                j = 1 + j4;
                zzlf.zza(bArr, j4, (byte) charAt2);
            } else if (charAt2 < 2048 && j4 <= j3 - 2) {
                long j5 = j4 + 1;
                zzlf.zza(bArr, j4, (byte) ((charAt2 >>> 6) | 960));
                j = 1 + j5;
                zzlf.zza(bArr, j5, (byte) ((charAt2 & '?') | 128));
            } else if ((charAt2 < 55296 || 57343 < charAt2) && j4 <= j3 - 3) {
                long j6 = 1 + j4;
                zzlf.zza(bArr, j4, (byte) ((charAt2 >>> 12) | 480));
                long j7 = 1 + j6;
                zzlf.zza(bArr, j6, (byte) (((charAt2 >>> 6) & 63) | 128));
                j = 1 + j7;
                zzlf.zza(bArr, j7, (byte) ((charAt2 & '?') | 128));
            } else if (j4 <= j3 - 4) {
                if (i3 + 1 != length) {
                    i3++;
                    char charAt3 = charSequence.charAt(i3);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        long j8 = 1 + j4;
                        zzlf.zza(bArr, j4, (byte) ((codePoint >>> 18) | 240));
                        long j9 = 1 + j8;
                        zzlf.zza(bArr, j8, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j10 = j9 + 1;
                        zzlf.zza(bArr, j9, (byte) (((codePoint >>> 6) & 63) | 128));
                        j = 1 + j10;
                        zzlf.zza(bArr, j10, (byte) ((codePoint & 63) | 128));
                    }
                }
                throw new zzll(i3 - 1, length);
            } else if (55296 > charAt2 || charAt2 > 57343 || (i3 + 1 != length && Character.isSurrogatePair(charAt2, charSequence.charAt(i3 + 1)))) {
                throw new ArrayIndexOutOfBoundsException(new StringBuilder(46).append("Failed writing ").append(charAt2).append(" at index ").append(j4).toString());
            } else {
                throw new zzll(i3, length);
            }
            i3++;
            j4 = j;
        }
        return (int) j4;
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzli.zzb(i);
            case 1:
                return zzli.zzb(i, zzlf.zza(bArr, j));
            case 2:
                return zzli.zzb(i, (int) zzlf.zza(bArr, j), (int) zzlf.zza(bArr, 1 + j));
            default:
                throw new AssertionError();
        }
    }
}
