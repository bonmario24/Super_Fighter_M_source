package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class zzk {
    private static int zza(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    private static long zza(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    public static long zza(byte[] bArr) {
        int length = bArr.length;
        if (length < 0 || length > bArr.length) {
            throw new IndexOutOfBoundsException(new StringBuilder(67).append("Out of bound index with offput: 0 and length: ").append(length).toString());
        } else if (length <= 32) {
            if (length > 16) {
                long j = -7286425919675154353L + ((long) (length << 1));
                long zzb = -5435081209227447693L * zzb(bArr, 0);
                long zzb2 = zzb(bArr, 8);
                long zzb3 = zzb(bArr, (length + 0) - 8) * j;
                return zza((zzb(bArr, (length + 0) - 16) * -7286425919675154353L) + Long.rotateRight(zzb + zzb2, 43) + Long.rotateRight(zzb3, 30), zzb + Long.rotateRight(-7286425919675154353L + zzb2, 18) + zzb3, j);
            } else if (length >= 8) {
                long j2 = -7286425919675154353L + ((long) (length << 1));
                long zzb4 = -7286425919675154353L + zzb(bArr, 0);
                long zzb5 = zzb(bArr, (length + 0) - 8);
                return zza((Long.rotateRight(zzb5, 37) * j2) + zzb4, (Long.rotateRight(zzb4, 25) + zzb5) * j2, j2);
            } else if (length >= 4) {
                return zza(((((long) zza(bArr, 0)) & 4294967295L) << 3) + ((long) length), ((long) zza(bArr, (length + 0) - 4)) & 4294967295L, -7286425919675154353L + ((long) (length << 1)));
            } else if (length <= 0) {
                return -7286425919675154353L;
            } else {
                long j3 = (((long) (((bArr[(length - 1) + 0] & 255) << 2) + length)) * -4348849565147123417L) ^ (((long) ((bArr[0] & 255) + ((bArr[(length >> 1) + 0] & 255) << 8))) * -7286425919675154353L);
                return (j3 ^ (j3 >>> 47)) * -7286425919675154353L;
            }
        } else if (length <= 64) {
            long j4 = -7286425919675154353L + ((long) (length << 1));
            long zzb6 = zzb(bArr, 0) * -7286425919675154353L;
            long zzb7 = zzb(bArr, 8);
            long zzb8 = zzb(bArr, (length + 0) - 8) * j4;
            long zzb9 = (zzb(bArr, (length + 0) - 16) * -7286425919675154353L) + Long.rotateRight(zzb6 + zzb7, 43) + Long.rotateRight(zzb8, 30);
            long zza = zza(zzb9, Long.rotateRight(zzb7 - 7286425919675154353L, 18) + zzb6 + zzb8, j4);
            long zzb10 = zzb(bArr, 16) * j4;
            long zzb11 = zzb(bArr, 24);
            long zzb12 = (zzb9 + zzb(bArr, (length + 0) - 32)) * j4;
            return zza(((zzb(bArr, (length + 0) - 24) + zza) * j4) + Long.rotateRight(zzb10 + zzb11, 43) + Long.rotateRight(zzb12, 30), Long.rotateRight(zzb11 + zzb6, 18) + zzb10 + zzb12, j4);
        } else {
            int i = 0;
            long j5 = 2480279821605975764L;
            long j6 = 1390051526045402406L;
            long[] jArr = new long[2];
            long[] jArr2 = new long[2];
            long zzb13 = 95310865018149119L + zzb(bArr, 0);
            int i2 = (((length - 1) / 64) << 6) + 0;
            int i3 = (((length - 1) & 63) + i2) - 63;
            while (true) {
                long rotateRight = (Long.rotateRight(((zzb13 + j5) + jArr[0]) + zzb(bArr, i + 8), 37) * -5435081209227447693L) ^ jArr2[1];
                long rotateRight2 = (Long.rotateRight(j5 + jArr[1] + zzb(bArr, i + 48), 42) * -5435081209227447693L) + jArr[0] + zzb(bArr, i + 40);
                long rotateRight3 = Long.rotateRight(j6 + jArr2[0], 33) * -5435081209227447693L;
                zza(bArr, i, jArr[1] * -5435081209227447693L, jArr2[0] + rotateRight, jArr);
                zza(bArr, i + 32, rotateRight3 + jArr2[1], rotateRight2 + zzb(bArr, i + 16), jArr2);
                i += 64;
                if (i == i2) {
                    long j7 = -5435081209227447693L + ((255 & rotateRight) << 1);
                    jArr2[0] = jArr2[0] + ((long) ((length - 1) & 63));
                    jArr[0] = jArr[0] + jArr2[0];
                    jArr2[0] = jArr2[0] + jArr[0];
                    long rotateRight4 = (Long.rotateRight(((rotateRight3 + rotateRight2) + jArr[0]) + zzb(bArr, i3 + 8), 37) * j7) ^ (jArr2[1] * 9);
                    long rotateRight5 = (Long.rotateRight(jArr[1] + rotateRight2 + zzb(bArr, i3 + 48), 42) * j7) + (jArr[0] * 9) + zzb(bArr, i3 + 40);
                    long rotateRight6 = Long.rotateRight(jArr2[0] + rotateRight, 33) * j7;
                    zza(bArr, i3, jArr[1] * j7, rotateRight4 + jArr2[0], jArr);
                    zza(bArr, i3 + 32, rotateRight6 + jArr2[1], rotateRight5 + zzb(bArr, i3 + 16), jArr2);
                    return zza(zza(jArr[0], jArr2[0], j7) + (((rotateRight5 >>> 47) ^ rotateRight5) * -4348849565147123417L) + rotateRight4, zza(jArr[1], jArr2[1], j7) + rotateRight6, j7);
                }
                j6 = rotateRight;
                j5 = rotateRight2;
                zzb13 = rotateRight3;
            }
        }
    }

    private static void zza(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long zzb = zzb(bArr, i);
        long zzb2 = zzb(bArr, i + 8);
        long zzb3 = zzb(bArr, i + 16);
        long zzb4 = zzb(bArr, i + 24);
        long j3 = zzb + j;
        long rotateRight = Long.rotateRight(j2 + j3 + zzb4, 21);
        long j4 = zzb2 + j3 + zzb3;
        jArr[0] = j4 + zzb4;
        jArr[1] = j3 + Long.rotateRight(j4, 44) + rotateRight;
    }

    private static long zzb(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }
}
